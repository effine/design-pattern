/**
 * @author VerpHen
 * @date 2013年9月10日  上午9:38:08
 */

package cn.effine.factoryMethod;

import cn.effine.factoryMethod.ICar;

/*具体产品角色类：汽车-奔驰Benz*/
public class Benz implements ICar {

	public void run() {
		System.out.println("奔驰启动");
	}

	public void stop() {
		System.out.println("奔驰停止");
	}
}
