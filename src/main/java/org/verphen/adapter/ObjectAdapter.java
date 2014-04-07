/**
 * @author verphen
 * @date 2014年4月8日   上午12:20:12
 */

package org.verphen.adapter;

public class ObjectAdapter implements Target {

	private Adaptee adaptee;

	public ObjectAdapter(Adaptee adaptee) {
		super();
		this.adaptee = adaptee;
	}

	/**
	 * 源类（客户端类）的方法method1(),适配器直接委派即可
	 */
	public void method1() {
		adaptee.method1();
	}

	/**
	 * 由于源类（客户端类）没有方法method2(),因此适配器补上该方法
	 */
	public void method2() {
		/* method body */
	}

}
