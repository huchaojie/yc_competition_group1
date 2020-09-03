package bean_study2;

import java.util.Random;

/**
 * 对数组操作的工具方法
 *
 */
public class ArrayUtil {
	
	private static Random generator=new Random();
	
	/**
	 * 生成一个整形的数组. 
	 * @param length:  数组长度
	 * @param n  :     生成的数据的范围  
	 * @return
	 */
	public static int[] randomIntArray( int length, int n){
		int[] a=new int[length];
		for( int i=0;i<a.length;i++ ){
			a[i]=generator.nextInt( n );
		}
		return a;
	}
	
	/**
	 * 打印输出 
	 * @param a
	 */
	public static void print( int [] a){
		for(int i=0;i<a.length;i++){
			if(   i%50==0){
				System.out.println(  );
			}
			System.out.print(a[i]+" ");
		}
		System.out.println();
	}
}
