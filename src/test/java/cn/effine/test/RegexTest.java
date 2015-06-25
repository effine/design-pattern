package cn.effine.test;

import java.util.regex.Pattern;


/**
 * 正则测试类
 */
public class RegexTest {
	public static void main(String[] args) {
		
		String str = "Evening is full of the linnet`s wings";
		
		System.out.println("str.group:  " + Pattern.compile("\\w+").matcher(str));
		
		
		
	}
}
