/**
 * @author VerpHen
 * @date 2013年9月10日  下午1:56:48
 */

package cn.effine.factoryMethod;

/*抽象的工厂类*/
public abstract class Factory {

	/* 抽象方法使用泛型（T为ICar的实现类）， */
	public abstract <T extends ICar> T createCar();
}
