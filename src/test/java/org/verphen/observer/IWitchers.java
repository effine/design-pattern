/**
 * @author Verphen
 * @date 2013年10月4日  下午11:06:57
 */

package org.verphen.observer;

/*抽象观察者角色*/
public interface IWitchers {

	/* 当被观察者发生变化，接到通知做出反应，更新操作 */
	public void update(Object o);
}
