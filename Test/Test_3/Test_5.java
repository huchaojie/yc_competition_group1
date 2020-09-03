package Test_3;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fangxiang
 * 给定一个随机数字，要求：判断该数字是否是回文数据
 * 回文数据：从左往右 或者从左  123454321  1234321
 */
public class Test_5 {
	public static void main(String[] args) {
		int n=123454321;
		String string=n+"";
		int x=string.length();
		if(x%2==0) {
			System.out.println("数字不为回文数据");
		}else{
			int z=x/2;
			String q=string.substring(0,z);
			System.out.println(q);
			String[] split = q.split("");
			for(int i=0;i<split.length;i++) {
				System.out.println(split[i]);
			}
//			list.addAll();
			
			String p=string.substring(z+1);
			String[] split2 = p.split("");
			for(int i=split2.length-1;i>=0;i--) {
				System.out.println(split2[i]);
			}
			System.out.println(p);
		}
		
	}
	
	public String Sort(String x) {
		int z=Integer.parseInt(x);
		
		
		return x;
		
	}
	
	public boolean Test(int n) {
		String string=n+"";
		int x=string.length();
		
		return false;
		
	} 

}
