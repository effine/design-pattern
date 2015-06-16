/**
 * @author Verphen
 * @date 2013年10月4日  下午11:27:18
 */

package cn.effine.observer;

import cn.effine.observer.ConcreteWitched;
import cn.effine.observer.ConcreteWitchers;
import cn.effine.observer.IWitched;
import cn.effine.observer.IWitchers;

/* observer pattern test class*/
public class TestWitch {
	public static void main(String[] args) {

		/* witched : girl */
		IWitched girl = new ConcreteWitched();

		/* witchers */
		IWitchers witcher1 = new ConcreteWitchers();
		IWitchers witcher2 = new ConcreteWitchers();
		IWitchers witcher3 = new ConcreteWitchers();

		/* witched add witchers */
		girl.addWitcher(witcher1);
		girl.addWitcher(witcher2);
		girl.addWitcher(witcher3);

		girl.notifyWitcher(args);
	}
}
