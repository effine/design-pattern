package cn.effine.strategy.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * 巡检周期单位枚举
 */
@AllArgsConstructor
@Getter
public enum PatrolCycleUnitEnum {

    SINGLE_TIMES(0, "单次不重复"),
    HOUR(1, "小时"),
    DAY(2, "天"),
    MONTH(3, "月");

    private Integer type;
    private String name;

    public static PatrolCycleUnitEnum get(Integer type) {
        if (Objects.isNull(type)) {
            return null;
        }
        return Arrays.stream(PatrolCycleUnitEnum.values())
                .filter(item -> Objects.equals(item.getType(), type))
                .findFirst().orElse(null);
    }

    public static PatrolCycleUnitEnum get4Name(String name) {
        if (Objects.isNull(name)) {
            return null;
        }
        return Arrays.stream(PatrolCycleUnitEnum.values())
                .filter(item -> Objects.equals(item.getName(), name))
                .findFirst().orElse(null);
    }

}
