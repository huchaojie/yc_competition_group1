package bean_study2;

import java.util.Scanner;

public class Test2_BubbleSortTimer {


	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入数组大小:");
		int n=sc.nextInt();
		
		int[] a=ArrayUtil.randomIntArray(n, 100);
		BubbleSorter sorter=new BubbleSorter(a);    //初始化算法类,  
		//初始化秒表
		StopWatch timer=new StopWatch();
		timer.start();   //启动秒表
		
		sorter.sort();    //开始排序
		
		timer.stop();    //停止秒表
		
		System.out.println( "总计费时:"+ timer.getElapsedTime()+"  milliseconds");    //   390
		
		//   打印出排序后的结果.
		//ArrayUtil.print(a);
	}

}
