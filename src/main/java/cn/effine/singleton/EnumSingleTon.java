package cn.effine.singleton;

import lombok.Getter;

/**
 * 枚举式单例
 *
 * @author effine
 * @email zhang.yafei#gzdzswy.cn
 * @date 2024-07-11 10:00
 */

@Getter
public enum EnumSingleTon {

    INSTANT;

    private String value;

    public void setValue(String value) {
        this.value = value;
    }

    public void doSomething() {
        System.out.println("Singleton instance is doing something...");
    }
}
