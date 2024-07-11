package cn.effine.test;

import cn.effine.singleton.EnumSingleTon;
import org.junit.Test;

/**
 * 单例测试
 *
 * @author effine
 * @email zhang.yafei#gzdzswy.cn
 * @date 2024-07-11 09:56
 */
public class SingletonTest {

    @Test
    public void enumTest() {
        EnumSingleTon instant = EnumSingleTon.INSTANT;
        instant.setValue("1");
        System.out.println(instant.getValue());

        instant.doSomething();
    }

}
