/**
 * @author Verphen
 * @date 2013年10月4日  下午11:02:21
 */

package org.verphen.observer;

/*抽象被观察者角色*/
public interface IWitched {

	/* 添加观察者 */
	public void addWitcher(IWitchers w);

	/* 删除观察者 */
	public void deleteWitcher(IWitchers w);

	/* 被观察者 发生变化，通知观察者 */
	public void notifyWitcher(Object o);
}
