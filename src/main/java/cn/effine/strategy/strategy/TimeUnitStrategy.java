package cn.effine.strategy.strategy;


import cn.effine.strategy.enums.PatrolCycleUnitEnum;

import java.util.Date;

/**
 * 巡检任务策略定义
 * @author admin
 */
public interface TimeUnitStrategy<R, P> {

    /**
     * 获取策略编码类型
     *
     * @return 策略编码类型
     * @see PatrolCycleUnitEnum 返回类型参考该枚举值
     */
    Integer getStrategyType();

    /**
     * 执行任务生产
     *
     * @param p 参数信息
     * @return
     */
    R exec(P p) throws Exception;

    /**
     * 获取时间间隔
     *
     * @param source 原始时间
     * @param amount 间隔数
     * @return 追加后的时间
     */
    Date buildTime4Interval(Date source, final int amount);
}
