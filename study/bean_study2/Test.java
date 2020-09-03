package bean_study2;

public class Test {
	public static void main(String args[]) {
		int i, min, max;
		int A[] = { 74, 48, 30, 17, 62 }; // 声明整数数组A,并赋初值

		min = max = A[0];
		System.out.print("数组A的元素包括：");
		for (i = 0; i < A.length; i++) {
			System.out.print(A[i] + " ");
			if (A[i] > max) { // 判断最大值
				max = A[i];
			}
			if (A[i] < min) {// 判断最小值
				min = A[i];
			}
		}
		System.out.println("\n数组的最大值是:" + max); // 输出最大值
		System.out.println("数组的最小值是:" + min); // 输出最小值
		System.out.println("元素差值的替代为:"+(max-min));
	}
}