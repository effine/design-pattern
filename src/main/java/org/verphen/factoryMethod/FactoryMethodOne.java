/**
 * @author VerpHen
 * @date 2013年9月10日  上午9:29:02
 */

package org.verphen.factoryMethod;

/*具体工厂角色类*/
public class FactoryMethodOne extends Factory {

	@Override
	public <T extends ICar> T createCar() {
		return null;
	}
}
