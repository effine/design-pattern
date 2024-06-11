package cn.effine.strategy.enums;

import cn.effine.common.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * 附加（提前、延后）时间单位枚举
 */
@AllArgsConstructor
@Getter
public enum AppendTimeUnitEnum {

    MINUTE(1, "分钟"),
    HOUR(2, "小时");

    private Integer type;
    private String name;

    public static AppendTimeUnitEnum get(Integer type) {
        if (Objects.isNull(type)) {
            return null;
        }
        return Arrays.stream(AppendTimeUnitEnum.values())
                .filter(item -> Objects.equals(item.getType(), type))
                .findFirst().orElse(null);
    }

    public static AppendTimeUnitEnum get4Name(String name) {
        if (StringUtil.isBlank(name)) {
            return null;
        }
        return Arrays.stream(AppendTimeUnitEnum.values())
                .filter(item -> Objects.equals(item.getName(), name))
                .findFirst().orElse(null);
    }
}
