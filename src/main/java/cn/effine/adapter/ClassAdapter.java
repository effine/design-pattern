/**
 * @author verphen
 * @date 2014年4月7日   下午11:48:30
 */

package cn.effine.adapter;

public class ClassAdapter extends Adaptee implements Target {

	/**
	 * 由于源类（客户端类）没有方法method2(),因此适配器补上该方法
	 */
	public void method2() {
		/* method body */
	}
}
