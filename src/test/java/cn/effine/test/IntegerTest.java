package cn.effine.test;

public class IntegerTest {
	
	public static void main(String[] args) {
		
		Integer i1 = 2;
		Integer i2 = 2;
		System.err.println("i1 == i2 : " + (i1 == i2));
		
		Integer i3 = new Integer(2);
		Integer i4 = new Integer(2);
		System.err.println("i3 == i4: " + (i3 == i4));
		
		Integer i5 = 128;
		Integer i6 = 128;
		System.err.println("i5 == i6 : " + (i5 == i6));
		System.err.println("i5、i6的inValue()方法等值比较： " + (i5.intValue() == i6.intValue()));
		System.err.println("i5、i6的equals的比较： " + i5.equals(i6));

		
	}
}
