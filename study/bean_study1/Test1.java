package bean_study1;

/**
 * @author fangxiang
 *
 *计算n的阶乘
 */
public class Test1 {
	
	public static void main(String[] args) {
		long a=123L;
		System.out.println(factorial(4));
	}
	
	public static int factorial(int n) {
		if (n==1) {
			return 1;
		}else {
			return n*factorial(n-1);
		}
	}

}
