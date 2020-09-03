package bean_study2;

import java.util.Random;

/**
 * ����������Ĺ��߷���
 *
 */
public class ArrayUtil {
	
	private static Random generator=new Random();
	
	/**
	 * ����һ�����ε�����. 
	 * @param length:  ���鳤��
	 * @param n  :     ���ɵ����ݵķ�Χ  
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
	 * ��ӡ��� 
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
