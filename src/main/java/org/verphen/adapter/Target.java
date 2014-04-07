/**
 * @author verphen
 * @date 2014年4月7日   下午11:44:58
 */

package org.verphen.adapter;

/* 目标角色 */
public interface Target {
	
	/**
	 * 这是源类（客户端类）也有的方法method1()
	 */
	void method1();

	
	/**
	 * 这是源类（客户端类）没有的方法method2()
	 */
	void method2();
}
