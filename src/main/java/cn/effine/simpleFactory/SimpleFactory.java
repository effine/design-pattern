/**
 * @author VerpHen
 * @date 2013年9月10日  上午9:29:02
 */

package cn.effine.simpleFactory;

import cn.effine.simpleFactory.Benz;
import cn.effine.simpleFactory.Ferrari;
import cn.effine.simpleFactory.ICar;
import cn.effine.simpleFactory.Jaguar;

/*工厂角色类*/
public class SimpleFactory {

	/* 工厂方法，返回类型为抽象的产品角色 */
	public static ICar driveCar(String carType) {

		/* equalsIgnoreCase() 忽略carType大小写 */
		if ("Benz".equalsIgnoreCase(carType)) {
			return new Benz();
		} else if ("Ferrari".equalsIgnoreCase(carType)) {
			return new Ferrari();
		} else if ("Jaguar".equalsIgnoreCase(carType)) {
			return new Jaguar();
		} else {
			throw new RuntimeException("没有该汽车类型");
		}
	}
}
