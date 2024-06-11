package cn.effine.strategy.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * 结束时间类型枚举
 */
@AllArgsConstructor
@Getter
public enum EndTimeTypeEnum {
    /**
     * 结束时间类型（0：无结束时间 1：重复次数 2：指定时间）
     */
    NO_END_TIME(0, "无结束时间"),
    REPEAT_TIMES(1, "重复次数"),
    SPECIFY_END_TIME(2, "指定时间");

    private Integer type;
    private String name;

    public static EndTimeTypeEnum get(Integer type) {
        if (Objects.isNull(type)) {
            return null;
        }
        return Arrays.stream(EndTimeTypeEnum.values())
                .filter(item -> Objects.equals(item.getType(), type))
                .findFirst().orElse(null);
    }

}
