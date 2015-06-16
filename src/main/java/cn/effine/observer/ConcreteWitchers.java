/**
 * @author Verphen
 * @date 2013年10月4日  下午11:16:56
 */

package cn.effine.observer;

import cn.effine.observer.IWitchers;

/*具体观察者角色*/
public class ConcreteWitchers implements IWitchers {

	public void update(Object o) {
		System.out.println(o);
	}
}
