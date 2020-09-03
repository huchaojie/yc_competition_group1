package bean_sort;

public class Test1 {
	public static void main(String[] args) {
		int temp=0;
		//冒泡排序算法   遍历所有元素 对比相邻之间大小进行交换
		int sort[]=new int [] {13,2,456,13456,12,111,24};
		for(int i=0;i<sort.length;i++) {
			for(int j=0;j<sort.length-i-1;j++) {
				if(sort[j]<sort[j+1]) {
					temp=sort[j+1];
					sort[j+1]=sort[j];
					sort[j]=temp;
				}
			}
		}
		
		System.out.println("排序后的结果:");
		for(int x=0;x<sort.length;x++) {
			System.out.println(sort[x]);
		}
	}
}
