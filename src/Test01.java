public class Test01{
	 public static void main(String[] args) {
	        int []array = {1,2,3,4,5,6,7,8,9,10,11,12,13};
	        reOrderArray(array);
	        for(int i=0;i<array.length;i++)
	        {
	            System.out.print(array[i]+" ");
	        }
	        for(int j=0;j<array.length;j++)
	        {
	        	if(array[j]%2==0) {
	        		System.out.println();
	        		 System.out.print("第一个为偶数的元素在数组中的位置为:"+j);
	        		 return;
	        	}          
	        }
	        
	    }
	    public static void reOrderArray(int []array)
	    {
	        int i=0;
	        int j=array.length-1;
	        while(i<j)
	        {
	            if(array[i]%2==1)//奇数
	            {
	                if(array[j]%2==1)//奇数
	                {
	                    i++;
	                }else//偶数
	                {
	                    j--;
	                }
	            }else//偶数
	            {
	                if(array[j]%2==1)//奇数
	                {
	                    //交换array[i]和array[j]
	                    int temp = array[i];
	                    array[i] = array[j];
	                    array[j] = temp;
	                }else//偶数
	                {
	                    j--;
	                }
	            }
	        }
	    }
}
