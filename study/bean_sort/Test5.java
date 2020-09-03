package bean_sort;

public class Test5 {

	public static void main(String[] args) {
		int [] x=new int [] {1,12,3,4,2,10,22};
		 quicksort(x,0,x.length-1);
		for(int i=0;i<x.length;i++) {
			System.out.println(x[i]+" ");
		}
	}
	
	public static void quicksort(int []x,int left,int right) {
		if(left>right) {
			return;
		}
		
		int i=left;
		int j=right;
		
		int base=x[left];
		
		while(i!=j) {
			while(x[j]>=base && j>i) {
				j--;
			}
			
			while(x[i]<=base &&j>i) {
				i++;
			}
			
			int temp=x[j];
			x[j]=x[i];
			x[i]=temp;
		}
		
		x[left]=x[i];
		x[j]=base;
		
		
		quicksort(x,0,i-1);
		quicksort(x,i+1,right);
	}
	
}
