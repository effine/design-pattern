/**
 * @author VerpHen
 * @date 2013年9月10日  上午9:42:21
 */

package cn.effine.factoryMethod;

/*具体产品角色类：汽车-捷豹-Jaguar*/
public class Jaguar implements ICar {

	public void run() {
		System.out.println("捷豹启动");
	}

	public void stop() {
		System.out.println("捷豹停止");
	}
}
