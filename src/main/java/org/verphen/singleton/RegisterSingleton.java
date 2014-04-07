/**
 * @author VerpHen
 * @date 2013年9月29日  下午1:43:31
 */

package org.verphen.singleton;

import java.util.HashMap;
import java.util.Map;

/*单例模式：登记式 --- 类似Spring里面的方法，将类名注册，下次从里面直接获取*/
public class RegisterSingleton {
	private static Map<String, RegisterSingleton> map = new HashMap<String, RegisterSingleton>();
	static {
		RegisterSingleton register = new RegisterSingleton();
		map.put(register.getClass().getName(), register);
	}

	/* 构造方法私有，防止外部实例化 */
	private RegisterSingleton() {
	}

	/* 静态方法返回类的实例 ,参数：map集合的索引名 */
	public static RegisterSingleton getInstance(String register) {
		if (register == null) {
			register = RegisterSingleton.class.getName();
		}
		if (map.get(register) == null) {
			try {
				map.put(register, (RegisterSingleton) Class.forName(register)
						.newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return map.get(register);
	}
}