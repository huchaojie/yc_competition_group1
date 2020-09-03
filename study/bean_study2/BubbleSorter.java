package bean_study2;

/**
 * 冒泡算法排序
 * @author fangxiang
 *
 */
public class BubbleSorter {
	private int[] a;
	public BubbleSorter(int[] a) {
		super();
		this.a=a;
	}

	public void sort() {
		int temp=0;
		for(int i=0;i<a.length;i++) {
			for(int j=0;j<a.length-i;j++) {
				if(a[j]<a[j+1]) {
					temp=a[j];
					a[j]=a[j+1];
					a[j+1]=temp;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
	}

}
