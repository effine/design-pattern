package cn.effine.strategy.strategy;

import cn.effine.common.exception.exception.BusinessException;
import cn.effine.common.util.CollectionUtil;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 策略工厂类
 */
@Component
public class StrategyFactory<R, P> {

    /**
     * 策略Map
     */
    private Map<Integer, TimeUnitStrategy<R, P>> STRATEGY_MAP;

    @Resource
    private List<TimeUnitStrategy<R, P>> strategyList;

    @PostConstruct
    private void init() {
        if (CollectionUtil.isEmpty(strategyList)) {
            STRATEGY_MAP = Maps.newHashMap();
            return;
        }
        STRATEGY_MAP = strategyList.stream().collect(Collectors.toMap(TimeUnitStrategy::getStrategyType, Function.identity(), (v1, v2) -> v1));
    }

    /**
     * 获取对应的策略
     *
     * @param type 策略类型值
     * @return 策略实现
     */
    public TimeUnitStrategy<R, P> getStrategy(Integer type) {
        if (Objects.isNull(type)) {
            throw new IllegalArgumentException("巡检策略模式值为空");
        }

        TimeUnitStrategy<R, P> timeUnitStrategy = STRATEGY_MAP.get(type);
        if (Objects.isNull(timeUnitStrategy)) {
            throw new BusinessException(String.format("该巡检策略模式没有对应实现, 模式值=%s", type));
        }
        return timeUnitStrategy;
    }
}
