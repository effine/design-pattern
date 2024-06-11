package cn.effine.strategy.strategy.impl;

import cn.effine.common.util.DateUtil;
import cn.effine.strategy.enums.PatrolCycleUnitEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 小时维度计划策略实现
 */
@Slf4j
@Component
public class HourStrategyImpl extends AbstractStrategy {

    @Override
    public Integer getStrategyType() {
        return PatrolCycleUnitEnum.HOUR.getType();
    }

    @Override
    public Date buildTime4Interval(Date source, int amount) {
        return DateUtil.addHours(source, amount);
    }
}



