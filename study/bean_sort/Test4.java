package bean_sort;

/**
 * 快速排序
 * @author fangxiang
 *
 */
public class Test4 {

	public static void main(String[] args) {
		//定义一个数组
		int []arr= {22,13,1,23,55,73,6,4,2};
		//调用方法 进行快速排序
		quicksort(arr,0,arr.length-1);
		
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
	}
	
	//定义方法 用来定义快速排序
	public static void quicksort(int []arr,int left,int right) {
		//进行判断 如果左边索引比右边索引大  不合法 直接结束这个方法
		if(left>right) {
			return;
		}
		//定义变量 保存基准数
		int base=arr[left];
		//定义变量i  指向最左边
		int i=left;
		//定义变量j  指向最右边
		int j=right;
		
		//当i和j不相遇时 在循环中进行检索
		while(i!=j) {
			//System.out.println("遍历了一次");
			//先由j 从右往左检索比基准数小的  就停下
			//如果检索到比基准数大或者相等  就继续检索
			while(arr[j]>=base &&j>i) {
				j--;
			}
			//再由i 从左往右检索比基准数大的  就停下
			//如果检索到比基准数小或者相等  就继续检索
			while(arr[i]<=base &&j>i) {
				i++;
			}
			//代码走到这里 i停下 j也停下 然后交换i和j位置的元素
			int temp=arr[i];
			arr[i]=arr[j];
			arr[j]=temp;
		}
		//如果上面while的条件不成立 就会跳出循环  
		//即 i和j相遇了 交换基准数与这个相遇位置的元素
		arr[left]=arr[i];
		arr[i]=base;
		
		//进行递归调用
		quicksort(arr,left,i-1);
		quicksort(arr,i+1,right);
	}
}
