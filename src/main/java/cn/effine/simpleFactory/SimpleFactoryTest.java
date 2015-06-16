/**
 * @author VerpHen
 * @date 2013年9月10日  上午9:48:32
 */

package cn.effine.simpleFactory;

import cn.effine.simpleFactory.ICar;
import cn.effine.simpleFactory.SimpleFactory;

/*测试简单工厂方法类*/
public class SimpleFactoryTest {
	public static void main(String[] args) {

		// ICar car = SimpleFactory.driveCar("Benz");
		// ICar car = SimpleFactory.driveCar("Ferrari");
		ICar car = SimpleFactory.driveCar("Jaguar");

		car.run();
		car.stop();
	}
}
