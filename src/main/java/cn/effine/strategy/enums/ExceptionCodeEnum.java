package cn.effine.strategy.enums;

import cn.effine.common.exception.Result;
import cn.effine.common.exception.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 异常码枚举
 * <p>
 * 50xx-公共异常
 *
 * @author effine
 * @Date 2021/1/18 16:45
 * @email iballad#163.com
 */
@ToString
@Getter
@AllArgsConstructor
public enum ExceptionCodeEnum {

    /**
     * success
     */
    SUCCESS(0, "success"),
    SERVER_EXCEPTION(5001, "系统开小差了，请稍后再试"),
    PARAM_EXCEPTION(5002, "参数校验不通过"),
    PARAM_IS_NULL(5003, "参数为空"),
    CONCURRENT_EXCEPTION(5004, "操作太频繁，请稍后再试"),
    BUSINESS_EXCEPTION(5020, "业务异常"),

    ;

    private final int code;
    private final String msg;

    public <T> Result<T> fail() {
        return Result.fail(this.getCode(), this.getMsg(), null);
    }

    public void throwException() {
        throw new BusinessException(this.code, this.msg);
    }
}
