package Test_3;

/**
 * @author fangxiang
 * 判断下列语句中的 a 和 b 的值是否相等：      
 *  (1)   int  a=0;                    int  b=‘0’;       '0'代表的是48  所以不相等
 *  (2)   int  a=0;                   char  b=‘\u0000’;   '\u0000'代表的是0      所以是相等的
 *  (3)   int  a=123;               char  b=‘\u007B’;       '\u007B'代表的是123   所以是相等的
 *  (4)   int  a=0x123;           char  b=123;       		char  b=123; 	代表的是123   所以不相等
 *  (5)   int  a=3+‘5’;             char  b=’8’;       		也是不相等
 *  (6)   int  a=‘3’+’5’;           char  b=‘8’;        
 *  (7)   char  a=‘\u0000’;     char  b=‘0’;       			\u0000代表的是0    '0'代表的是48  所以不相等
 *
 */
public class Test4 {
	public static void main(String[] args) {
		int  a='\u0000';
		int  b='0'; 
		System.out.println(a+","+b);
	}

}
