import java.util.Arrays;

public class Array {
	//复制数组

	public static void main(String[] args) {
		int arr [] =new int[] {13,22,31,4};
		System.out.println("原数组为：");
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+"\t");
		}
		
		System.out.println();
		System.out.println("复制后数组为：");
		int arr2 [] =Arrays.copyOf(arr, 10);
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+"\t");
		}
		
		//复制指定数组位置
		System.out.println();
		int arr3 [] =new int[] {14,42,51,24};
		System.out.println("原数组为：");
		for(int i=0;i<arr.length;i++){
			System.out.print(arr3[i]+"\t");
		}
		
		System.out.println();
		System.out.println("复制后数组为：");
		int arr4 [] =Arrays.copyOfRange(arr3, 0, 3);
		for(int i=0;i<arr4.length;i++){
			System.out.print(arr4[i]+"\t");
	}
		
		int arr5[] =new int []{13,24,24,2332};
		Arrays.sort(arr5);
		int x=Arrays.binarySearch(arr5, 1, 2, 24);
		System.out.println();
		System.out.println("查找返回值的索引为为:"+x);
		
		
		//直接插入排序
		int tmp;
		int j;
		for(int x1=1;x1<arr.length;x1++){
			tmp=arr[x1];//先保留值
			for(j=x1-1;j>=0&&arr[j]>tmp;j--){
				arr[j+1]=arr[j];
			}
			arr[j+1]=tmp;		
		}
		System.out.println("直接插入排序后的：");
		for(int c=0;c<arr.length;c++){
			System.out.print(arr[c]+"\t");
		}
		String a="欢迎学习java";
		System.out.println();
		System.out.println("查询13的索引"+a.indexOf("学"));
	}
}
