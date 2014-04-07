/**
 * @author Verphen
 * @date 2013年10月4日  下午11:15:20
 */

package org.verphen.observer;

import java.util.ArrayList;
import java.util.List;

/*具体被观察者角色*/
public class ConcreteWitched implements IWitched {

	/* save witchers list */
	private List<IWitchers> list = new ArrayList<IWitchers>();

	public void addWitcher(IWitchers w) {
		list.add(w);
	}

	public void deleteWitcher(IWitchers w) {
		list.remove(w);
	}

	public void notifyWitcher(Object o) {
		for (IWitchers l : list) {
			l.update(o);
		}
	}
}
