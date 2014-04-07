/**
 * @author VerpHen
 * @date 2013年9月10日  上午9:40:18
 */

package org.verphen.simpleFactory;

/*具体产品角色类：汽车-法拉利Ferrari*/
public class Ferrari implements ICar {

	@Override
	public void run() {
		System.out.println("法拉利启动");
	}

	@Override
	public void stop() {
		System.out.println("法拉利停止");
	}
}