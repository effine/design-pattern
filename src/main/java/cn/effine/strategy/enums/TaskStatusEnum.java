package cn.effine.strategy.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 任务状态枚举
 */
@AllArgsConstructor
@Getter
public enum TaskStatusEnum {
    /**
     * 计划执行状态（0：未开始 1：执行中：2：完成  3：漏检 9：终止）
     */
    UN_START(0, "未开始"),
    EXECUTING(1, "执行中"),
    FINISH(2, "完成"),
    MISS(3, "漏检"),
    TERMINATION(9, "终止");

    private final Integer status;
    private final String name;

}
