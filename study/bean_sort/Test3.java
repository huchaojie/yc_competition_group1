package bean_sort;

public class Test3 {
	//插入排序
	private static int []a;
	
	public static void main(String[] args) {
		a= new int []{2,34,5,1234,453,13345};
		sort(a);
		for(int i=0;i<a.length;i++) {
			System.out.println(a[i]+" ");
		}
	}
	
	
	public static void sort(int []x) {
		//假定第一个元素是已经排好序的  从第二个元素循环整个数组
		for(int i=1;i<x.length;i++) {
			//取出当前的值
			int next=x[i];
			//记录当前元素的索引
			int j=i;
			//循环当前的值与前面的值进行比较 若小于 则将前面的值向后移  再将索引向前移  直到移到开头索引为0位置
			while(j>0 && x[j-1]>next) {
				x[j]=x[j-1];
				j--;
			}
			//将当前值放到合适的位置
			x[j]=next;
		}
	}
}
