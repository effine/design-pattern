package cn.effine.strategy.strategy;

import cn.effine.strategy.dto.PatrolPlanDTO;
import cn.effine.strategy.dto.PatrolTask;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 巡检计划上下文入口类
 */
@Component
public class PlanGenerateEntry {

    @Resource
    private StrategyFactory<List<PatrolTask>, PatrolPlanDTO> strategyFactory;

    /**
     * 生成巡检任务
     *
     * @param planDTO 巡检参数
     * @return 巡检任务
     * @throws Exception
     */
    public List<PatrolTask> generateTask(PatrolPlanDTO planDTO) throws Exception {
        if (Objects.isNull(planDTO)) {
            return Lists.newArrayList();
        }
        TimeUnitStrategy<List<PatrolTask>, PatrolPlanDTO> strategy = strategyFactory.getStrategy(planDTO.getPatrolCycleUnit());
        return strategy.exec(planDTO);
    }
}
