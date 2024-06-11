package cn.effine.strategy.strategy.impl;

import cn.effine.common.util.CollectionUtil;
import cn.effine.strategy.dto.PatrolPlanDTO;
import cn.effine.strategy.dto.PatrolTask;
import cn.effine.strategy.enums.PatrolCycleUnitEnum;
import cn.effine.strategy.enums.TaskStatusEnum;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 单次计划策略实现
 */
@Slf4j
@Component
public class SingleTimesStrategyImpl extends AbstractStrategy {


    @Override
    public List<PatrolTask> exec(PatrolPlanDTO patrolPlanDTO) {
        log.info("生成单次任务计划参数：{}", JSON.toJSONString(patrolPlanDTO));
        if (Objects.isNull(patrolPlanDTO)) {
            return null;
        }
        PatrolTask task = PatrolTask.builder()
                .name("任务1")
                .startTime(patrolPlanDTO.getStartTime())
                .taskStatus(TaskStatusEnum.UN_START.getStatus())
                .build();
        log.info("{}", JSON.toJSONString(task));
        return CollectionUtil.newArrayList(task);
    }

    @Override
    public Date buildTime4Interval(Date source, int amount) {
        return null;
    }

    @Override
    public Integer getStrategyType() {
        return PatrolCycleUnitEnum.SINGLE_TIMES.getType();
    }
}



