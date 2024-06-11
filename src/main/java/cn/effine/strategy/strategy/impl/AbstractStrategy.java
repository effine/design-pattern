package cn.effine.strategy.strategy.impl;

import cn.effine.common.exception.exception.BusinessException;
import cn.effine.common.util.BooleanUtil;
import cn.effine.common.util.CollectionUtil;
import cn.effine.common.util.DateUtil;
import cn.effine.common.util.NumberUtil;
import cn.effine.strategy.dto.PatrolPlanDTO;
import cn.effine.strategy.dto.PatrolTask;
import cn.effine.strategy.enums.AppendTimeUnitEnum;
import cn.effine.strategy.enums.ExceptionCodeEnum;
import cn.effine.strategy.enums.TaskStatusEnum;
import cn.effine.strategy.strategy.TimeUnitStrategy;
import cn.effine.strategy.strategy.util.ValidationUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 策略抽象类
 */
@Slf4j
public abstract class AbstractStrategy implements TimeUnitStrategy<List<PatrolTask>, PatrolPlanDTO> {

    @Override
    public List<PatrolTask> exec(PatrolPlanDTO patrolPlanDTO) {
        log.info("抽象类执行生成任务方法参数：{}", JSON.toJSONString(patrolPlanDTO));
        if (BooleanUtils.isFalse(ValidationUtil.isValid(patrolPlanDTO))) {
            ExceptionCodeEnum.PARAM_EXCEPTION.throwException();
        }
        List<PatrolTask> tasks = Lists.newArrayList();

        // 单次任务的开始结束时间
        Date startTime4SingleTask = patrolPlanDTO.getStartTime();
        Date endTime4SingleTask;
        int taskNum = 0;
        Long patrolStartTime = Objects.isNull(patrolPlanDTO.getPatrolStartTime()) ? null : patrolPlanDTO.getPatrolStartTime().getTime();
        Long patrolEndTime = Objects.isNull(patrolPlanDTO.getPatrolEndTime()) ? null : patrolPlanDTO.getPatrolEndTime().getTime();
        Date currentDate = new Date();

        while (true) {
            try {
                endTime4SingleTask = this.buildTime4Interval(startTime4SingleTask, patrolPlanDTO.getPatrolCycle());

                // 若不生成过期时间的任务需验证结束时间是否大于当前时间
                if (BooleanUtil.isNotTrue(patrolPlanDTO.getIsGenerate4Expire()) && endTime4SingleTask.before(currentDate)) {
                    startTime4SingleTask = endTime4SingleTask;
                    continue;
                }

                // 验证是否满足时间条件
                boolean isSatisfy = ValidationUtil.isSatisfy(patrolPlanDTO, startTime4SingleTask, taskNum);
                if (BooleanUtils.isFalse(isSatisfy)) {
                    break;
                }

                // 验证是否在工作日历
                boolean isWorkCalendar = ValidationUtil.isWorkCalendar(patrolPlanDTO.getWorkCalendar(), startTime4SingleTask, endTime4SingleTask);
                if (BooleanUtil.isFalse(isWorkCalendar)) {
                    startTime4SingleTask = endTime4SingleTask;
                    continue;
                }

                // 验证是否工作时间（小时维度独有校验）
                if (this instanceof HourStrategyImpl) {
                    // 验证是否在工作时间
                    boolean isWorkTime = ValidationUtil.isWorkTime(patrolStartTime, patrolEndTime, startTime4SingleTask, endTime4SingleTask);
                    if (BooleanUtil.isFalse(isWorkTime)) {
                        startTime4SingleTask = endTime4SingleTask;
                        continue;
                    }
                }
                PatrolTask task = this.buildTask(taskNum, startTime4SingleTask, endTime4SingleTask, patrolPlanDTO);
                log.info("{}, 开始时间= {}, 结束时间= {}, 执行开始时间= {}, 执行结束时间= {}",
                        task.getName(), task.getStartTime4Test(), task.getEndTime4Test(), task.getExecStartTime4Test(), task.getExecEndTime4Test());
                tasks.add(task);
                taskNum++;
                startTime4SingleTask = endTime4SingleTask;

            } catch (Exception e) {
                log.error("生成执行任务异常：", e);
                break;
            }
        }
        if (CollectionUtil.isEmpty(tasks)) {
            log.info("巡检配置无法匹配生成巡检任务：{}", JSON.toJSONString(patrolPlanDTO));
        }
        return tasks;
    }

    /**
     * 构建巡检任务信息
     *
     * @param taskNum              任务数
     * @param startTime4SingleTask 任务开始时间
     * @param endTime4SingleTask   任务结束时间
     * @param patrolPlanDTO        巡检参数信息
     * @return 任务信息
     */
    private PatrolTask buildTask(int taskNum, Date startTime4SingleTask, Date endTime4SingleTask, PatrolPlanDTO patrolPlanDTO) {
        // 构建任务信息
        PatrolTask task = PatrolTask.builder()
                .taskStatus(TaskStatusEnum.UN_START.getStatus())
                .name(String.format("任务%s", taskNum + 1))
                .startTime(startTime4SingleTask)
                .execStartTime(this.buildAppendTime(startTime4SingleTask, patrolPlanDTO.getNoAdvanceUnit(), -patrolPlanDTO.getNoAdvance()))
                .endTime(endTime4SingleTask)
                .execEndTime(this.buildAppendTime(endTime4SingleTask, patrolPlanDTO.getNoPostponeUnit(), patrolPlanDTO.getNoPostpone()))

                .startTime4Test(DateUtil.format(startTime4SingleTask))
                .endTime4Test(DateUtil.format(endTime4SingleTask))
                .build();
        task.setExecStartTime4Test(DateUtil.format(task.getExecStartTime()));
        task.setExecEndTime4Test(DateUtil.format(task.getExecEndTime()));
        return task;
    }

    /**
     * 构建追加时间
     *
     * @param source         源时间
     * @param appendTimeUnit 追加时间单位
     * @param appendTime     追加时间
     * @return 处理完成的时间
     */
    private Date buildAppendTime(Date source, Integer appendTimeUnit, Integer appendTime) {
        if (Objects.isNull(source)) {
            throw new BusinessException("构建追加时间时参数为空");
        }
        if (NumberUtil.isEmpty(appendTime)) {
            // 未设置追加时间，无需处理
            return source;
        }
        AppendTimeUnitEnum appendTimeUnitEnum = AppendTimeUnitEnum.get(appendTimeUnit);
        if (Objects.isNull(appendTimeUnitEnum)) {
            log.info("追加时间单位传值错误: 单位值={}", appendTimeUnit);
            throw new BusinessException("追加时间单位传值错误");
        }

        switch (appendTimeUnitEnum) {
            case HOUR:
                return DateUtil.addHour(source, appendTime);
            case MINUTE:
                return DateUtil.addMinutes(source, appendTime);
            default:
                return source;
        }
    }
}
