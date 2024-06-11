package cn.effine.strategy.strategy.impl;

import cn.effine.common.util.DateUtil;
import cn.effine.strategy.enums.PatrolCycleUnitEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 月维度计划策略实现
 */
@Slf4j
@Component
public class MonthStrategyImpl extends AbstractStrategy {

    @Override
    public Integer getStrategyType() {
        return PatrolCycleUnitEnum.MONTH.getType();
    }

    @Override
    public Date buildTime4Interval(Date source, int amount) {
        return DateUtil.addMonths(source, amount);
    }
}



