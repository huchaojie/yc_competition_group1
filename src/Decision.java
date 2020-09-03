import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Decision {
	
	public static void main(String[] args) throws IOException {
	//需要进行判断的字符串
	String s1="Fang123";
	String s2="xian123";
	//判断字符串变量的值是否合法
//	boolean b1=s1.matches("\\p{Upper}\\p{Lower}\\p{Lower}\\p{Lower}\\d\\d\\d");
//	可以写成简便的
	boolean b1=s1.matches("\\p{Upper}\\p{Lower}{3}\\d{3}");
	boolean b2=s2.matches("\\p{Upper}\\p{Lower}\\p{Lower}\\p{Lower}\\d\\d\\d");
	System.out.println("字符串s1:"+b1);
	System.out.println("字符串s2:"+b2);
	
	//判断是否为邮箱格式
	String s3="1336995479@qq.com";
	String s4="#@qq.com";
	String b3="\\w{0,}\\@\\w{0,}\\.{1}\\w{0,}";
	if(s3.matches(b3)){
		System.out.println("s3是邮箱的格式");
	}else if(s4.matches(b3)){
		System.out.println("s4是邮箱的格式");
	}else{
		System.out.println("都不是邮箱的格式");
	}
	
	//计算文章中文字的个数 
	InputStream in =Study.class.getClassLoader().getResourceAsStream("Test.txt");
	int length=0;
	int count=0;
	Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");//汉字的正则表达式范围
	byte[] bt =new byte[1024*10];
	while((length=in.read(bt))!=-1){
		String str=new String(bt,0,length);
		System.out.println(str);
		
	}
	for(int x=0;x<bt.length;x++){
		Matcher matcher = pattern.matcher(String.valueOf(length));
		if(bt[x] < 0 ){
			count++;
		}
	}
	System.out.println("文章有"+count+"个汉字");
	
	String str = "北京海淀 hello world!";
	int count1=0;
	Pattern pattern1 = Pattern.compile("[\u4e00-\u9fa5]");//汉字的正则表达式范围
	char c[] = str.toCharArray();
	for(int i=0;i<c.length;i++){
		Matcher matcher = pattern1.matcher(String.valueOf(c[i]));
		if(matcher.matches()){
			count1++;
		}
	}
	System.out.println("句子有"+count1+"个汉字");
	
	//StirngBuilder类的常用方法
	String s="good";
	StringBuilder builder =new StringBuilder(); //构造字符串生成器
	builder.append(s);//字符串的追加
	builder.append("\t"+str);
	System.out.println("追加后的bulider的值为:"+builder);
	
	//向指定索引处追加
	builder.insert(3, s);
	System.out.println("再次追加后的bulider的值为:"+builder);
	//向指定索引处进行删除操作
	builder.delete(3, 5);
	System.out.println("删除后的bulider的值为:"+builder);
	
	
	//将汉字和字母转化成Unicode码
	char  [] p =str.toCharArray();
	StringBuilder builder1= new StringBuilder();//构造字符串生成器
	for(char o:p){
		builder1.append((int)o+"");
	}
	System.out.println("str的Unicode码是：");
	System.out.println(builder1);
	
	
	//去掉字符串中重复的字符
	System.out.println();
	String sss="ssssssaaaaaaadjsaiodjoasdpsadasssssaxxxx";
	String result=removeRepeatChar(sss);
	System.out.println("去重之前："+sss);
	System.out.println("去重之后："+result);
}

	private static String removeRepeatChar(String sss) {
		if(sss==null){
			return "";
		}
		//去重的小算法
		StringBuilder builder2 =new StringBuilder();
		int i=0;
		int len=sss.length();
		while(i<len){
			char v=sss.charAt(i);
			builder2.append(v);
			i++;
			while(i<len&&v==sss.charAt(i)){
				i++;
			}
		}
		return builder2.toString();
	}
}
