package cn.effine.test;

import java.util.Arrays;
import java.util.Collections;

/**
 *数组测试
 */
public class ArrayTest {
public static void main(String[] args) {
	String[] arr = {"abc","acd","Abs","aBs"};
	Arrays.sort(arr);
	System.out.println(Arrays.toString(arr));
	int i = Arrays.binarySearch(arr, "acd");
	System.out.println("i = " +i);
	
	Arrays.sort(arr, Collections.reverseOrder());
	System.out.println(Arrays.toString(arr));
	
	
	Arrays.sort(arr, String.CASE_INSENSITIVE_ORDER);
	System.out.println(Arrays.toString(arr));
	
	
}
}
