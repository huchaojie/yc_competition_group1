package bean_study2;

import java.util.Scanner;

public class Test2_BubbleSortTimer {


	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("�����������С:");
		int n=sc.nextInt();
		
		int[] a=ArrayUtil.randomIntArray(n, 100);
		BubbleSorter sorter=new BubbleSorter(a);    //��ʼ���㷨��,  
		//��ʼ�����
		StopWatch timer=new StopWatch();
		timer.start();   //�������
		
		sorter.sort();    //��ʼ����
		
		timer.stop();    //ֹͣ���
		
		System.out.println( "�ܼƷ�ʱ:"+ timer.getElapsedTime()+"  milliseconds");    //   390
		
		//   ��ӡ�������Ľ��.
		//ArrayUtil.print(a);
	}

}
