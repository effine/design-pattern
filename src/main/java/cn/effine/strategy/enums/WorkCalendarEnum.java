package cn.effine.strategy.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * 工作日历枚举
 */
@AllArgsConstructor
@Getter
public enum WorkCalendarEnum {

    EVERY_DAY(0, "每天"),
    WORK_DAY(1, "工作日"),
    NO_WORK_DAY(2, "非工作日");

    private Integer code;
    private String name;

    public static WorkCalendarEnum get(Integer type) {
        if (Objects.isNull(type)) {
            return null;
        }
        return Arrays.stream(WorkCalendarEnum.values())
                .filter(item -> Objects.equals(item.getCode(), type))
                .findFirst().orElse(null);
    }

}
