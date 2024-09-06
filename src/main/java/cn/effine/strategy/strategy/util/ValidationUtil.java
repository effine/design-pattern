package cn.effine.strategy.strategy.util;

import cn.effine.common.util.BooleanUtil;
import cn.effine.common.util.DateUtil;
import cn.effine.common.util.enums.DateFormatter;
import cn.effine.common.util.enums.DatePatternEnum;
import cn.effine.strategy.constant.Constant;
import cn.effine.strategy.dto.PatrolPlanDTO;
import cn.effine.strategy.enums.EndTimeTypeEnum;
import cn.effine.strategy.enums.WorkCalendarEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Objects;

/**
 * 公共的校验工具类
 */
@Slf4j
public class ValidationUtil {

    /**
     * 零点(00:00:00)时分秒的毫秒数
     */
    private static final long ZERO_CLOCK = DateFormatter.parse("00:00:00", DateFormatter.PATTERN_HMS).getTime();

    /**
     * 每天结束时间（23:59:59）时分秒的毫秒数
     */
    private static final long DAY_FINISH_CLOCK = DateFormatter.parse("23:59:59", DateFormatter.PATTERN_HMS).getTime();

    /**
     * 生成每一次任务时各维度条件校验
     *
     * @param patrolPlanDTO        巡检计划参数
     * @param startTime4SingleTask 单次任务的开始时间
     * @param taskNum              任务数
     * @return 是否满足校验
     * @throws Exception
     */
    public static boolean isSatisfy(PatrolPlanDTO patrolPlanDTO, Date startTime4SingleTask, int taskNum) throws Exception {
        EndTimeTypeEnum endTimeTypeEnum = EndTimeTypeEnum.get(patrolPlanDTO.getEndTimeType());
        if (Objects.isNull(endTimeTypeEnum)) {
            throw new Exception("结束时间类型错误");
        }
        switch (Objects.requireNonNull(endTimeTypeEnum)) {
            case NO_END_TIME:
                // 暂时默认“2024-12-12 00:00:00”，可以切换到查询数据库
                return DateFormatter.parse("2024-01-01 00:00:00").after(startTime4SingleTask);

            case REPEAT_TIMES:
                return Objects.nonNull(patrolPlanDTO.getRepeatTime()) && patrolPlanDTO.getRepeatTime() > taskNum;

            case SPECIFY_END_TIME:
                return Objects.nonNull(patrolPlanDTO.getEndTime())
                        && (patrolPlanDTO.getEndTime().after(startTime4SingleTask));

            default:
                return false;
        }
    }


    /**
     * 验证任务时间是否在工作日历
     *
     * @param workCalendar         工作日历值
     * @param startTime4SingleTask 单次任务开始时间
     * @param endTime4SingleTask   单次任务结束时间
     * @return 验证是否通过
     */
    public static boolean isWorkCalendar(Integer workCalendar, Date startTime4SingleTask, Date endTime4SingleTask) {
        WorkCalendarEnum workCalendarEnum = WorkCalendarEnum.get(workCalendar);
        if (Objects.isNull(workCalendarEnum)) {
            return false;
        }

        // 工作日历：每天
        if (WorkCalendarEnum.EVERY_DAY.equals(workCalendarEnum)) {
            return true;
        }

        // 工作日历：工作日、非工作日
        String startDateStr = DateFormatter.format(startTime4SingleTask, DateFormatter.PATTERN_YY_MM_DD);
        String endDateStr = DateFormatter.format(endTime4SingleTask, DateFormatter.PATTERN_YY_MM_DD);

        switch (workCalendarEnum) {
            case WORK_DAY:
                return BooleanUtil.isFalse(Constant.NO_WORK_DATE_LIST.contains(startDateStr))
                        || BooleanUtil.isFalse(Constant.NO_WORK_DATE_LIST.contains(endDateStr));

            case NO_WORK_DAY:
                return Constant.NO_WORK_DATE_LIST.contains(startDateStr)
                        || Constant.NO_WORK_DATE_LIST.contains(endDateStr);

            default:
                return false;
        }
    }

    /**
     * 参数校验
     *
     * @param planDTO
     * @return
     */
    public static boolean isValid(PatrolPlanDTO planDTO) {
        if (Objects.isNull(planDTO)) {
            return false;
        }
        if (Objects.isNull(planDTO.getStartTime())) {
            return false;
        }
        return true;
    }

    /**
     * 验证是否工作时间(巡检时间)
     *
     * @param startTime4Conf 配置的开始时间
     * @param endTime4Conf   配置的结束时间
     * @param startTime      任务开始时间
     * @param endTime        任务结束时间
     * @return 是否在工作时间范围内
     */
    public static boolean isWorkTime(Long startTime4Conf, Long endTime4Conf, Date startTime, Date endTime) {
        if (Objects.isNull(startTime4Conf) || Objects.isNull(endTime4Conf)) {
            // 配置参数不全默认通过
            return true;
        }

        // 验证：开始时间 = 结束时间（配置的工作时间为全时段）
        if (startTime4Conf.equals(endTime4Conf)) {
            return true;
        }

        // 任务的开始结束时间毫秒数
        long startTime4Hms = DateFormatter.parse(DateUtil.format(startTime, DatePatternEnum.HH_MM_SS), DateFormatter.PATTERN_HMS).getTime();
        long endTime4Hms = DateFormatter.parse(DateUtil.format(endTime, DatePatternEnum.HH_MM_SS), DateFormatter.PATTERN_HMS).getTime();

        // 验证：开始时间 < 结束时间（配置的工作时间在一天）
        if (startTime4Conf < endTime4Conf) {
            return (startTime4Hms >= startTime4Conf && startTime4Hms < endTime4Conf)
                    || (endTime4Hms > startTime4Conf && endTime4Hms < endTime4Conf);
        }

        // 验证：开始时间 > 结束时间（配置的工作时间存在跨天）
        int diffDays = (int) DateUtil.getDiffDays(startTime, endTime);
        switch (diffDays) {
            case 0:
                // 任务开始结束时间在同一天
                return (startTime4Hms <= startTime4Conf && endTime4Hms > startTime4Conf)
                        || (startTime4Hms >= startTime4Conf && endTime4Hms > DAY_FINISH_CLOCK)
                        || (startTime4Hms >= ZERO_CLOCK && endTime4Hms < endTime4Conf)
                        || (startTime4Hms <= endTime4Conf && endTime4Hms > endTime4Conf);

            case 1:
                // 任务开始结束时间存在跨天
                return (startTime4Hms <= startTime4Conf && endTime4Hms < endTime4Conf)
                        || (startTime4Hms >= startTime4Conf && endTime4Hms < endTime4Conf)
                        || (startTime4Hms >= startTime4Conf && endTime4Hms > endTime4Conf);

            default:
                // 任务开始结束时间间隔超过1天，肯定会名中不在工作时间段
                return false;
        }
    }

}
