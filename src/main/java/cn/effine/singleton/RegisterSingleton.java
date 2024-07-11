/**
 * @author VerpHen
 * @date 2013年9月29日  下午1:43:31
 */

package cn.effine.singleton;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 单例模式：登记式 --- 类似Spring里面的方法，将类名注册，下次从里面直接获取
 *
 * @author admin
 */
@Slf4j
public class RegisterSingleton {
    private static final Map<String, RegisterSingleton> MAP = new HashMap<>();

    static {
        RegisterSingleton register = new RegisterSingleton();
        MAP.put(register.getClass().getName(), register);
    }

    private RegisterSingleton() {
    }

    /**
     * 静态方法返回类的实例 ,参数：map集合的索引名
     */
    public static RegisterSingleton getInstance(String register) {
        if (register == null) {
            register = RegisterSingleton.class.getName();
        }
        if (MAP.get(register) == null) {
            try {
                MAP.put(register, (RegisterSingleton) Class.forName(register)
                        .newInstance());
            } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
                log.error("异常信息: ", e);
            }
        }
        return MAP.get(register);
    }
}