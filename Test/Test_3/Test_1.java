package Test_3;

//写一个程序，要求定义出 boolean,int,char,double,float,String       
//类型的变量并赋初值，然后将这些值输出出来。 

/*
 * java是属于强类型语言  所以变量必须显示声明类型  因此 变量要先声明后使用
 * java八大基本类型
 * 整型：byte，short，int,long

	浮点型：float，double

	字符型：char

	Boolean型：boolean

 * 
 * */

public class Test_1 {

	public static void main(String[] args) {
		boolean flag = true;// boolean取值只能是true 或者false
		int i = 1; // 整形 取值范围约-21亿~21亿
		char c = 97;  //任意单个字符，加单引号    因为char类型在Unicode字符编码中，有对应的数值，可直接做运算，输出字符表中对应的字符  因为字符a在Unicode字符集中的排序位置97 
		double d = 1.1; // 当不声明的时候，默认小数都用double来表示，所以如果要用float的话，则应该在其后加上f
		float f = 1.2f;// double 和 float 的区别是double精度高，但double消耗内存是float的两倍，且double的运算速度较float稍慢。
		String s="fang"; //注意:String不是八大基本类型    因为String是一个对象   字符串String属于引用数据类型      只是编译器对其做了特殊处理（使其和基本数据类型一样）
		
		System.out.println("boolean类型:" + flag + "\t" + "i整形:" + i + "\t" + "char字符型:" + c + "\t" + "double浮点型:" + d
				+ "\t" + "float浮点型:" + f+ "\t" +"引用数据类型String:"+s);
	}

}
