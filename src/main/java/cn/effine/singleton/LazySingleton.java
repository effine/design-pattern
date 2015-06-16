/**
 * @author VerpHen
 * @date 2013年9月26日  下午1:50:47
 */

package cn.effine.singleton;


/*单例模式：懒汉式*/
public class LazySingleton {

	/* 声明的时候不初始化对象 */
	public static LazySingleton lazy = null;

	/* 将构造器私有化，不对外提供构造器 */
	private LazySingleton() {
	}

	/* 提供对外访问该类对象的方法，多线程下为了资源共享将代码块设置信号量，保证线程安全 */
	public static LazySingleton getInstance() {
		synchronized (LazySingleton.class) {
			if (lazy == null) {
				/* sychronized用于代码块时需要一个锁对象，针对实例方法使用this对象； */
				/* 当针对类方法时可以使用该类对应的字节码文件对象，this这个时候不存在 */
				lazy = new LazySingleton();
			}
		}
		return lazy;
	}
}
