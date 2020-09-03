package Test_3;



/**
 * @author fangxiang
 * 
 * 下列哪些赋值语句是错误的：      
 *  (1)   byte  b= 0x175;      byte 取值范围为何是-128~127       
 *  (2)   int  i=0x27f;        等于639
 *  (3)   char  c=123;         123在char类型中Unicode码中对应的是{     
 *  (4)   int  a=‘真’;         当char类型赋值给int时 会有一个隐式转换
 *  (5)   float  f=12.345E2;              
 *  (6)   boolean  f=0;        取值范围是 true 或者false
 *
 */
public class Test_3 {
	

	public static void main(String[] args) {
		String test="This is a Test";
		String [] tokens=test.split(" ");
		System.out.println(tokens.length);
	}
}
