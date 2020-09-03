import java.util.Date;

public class Study {

	public static void main(String[] args) {
		String a="欢迎学习java";
		System.out.println("原来的字符串:"+a);
		String b=a.replace("java", "大数据");
		System.out.println("替换后的字符串:"+b);
		
		//字符串的分割
		String c =new String("曾经的曾经，我们都是孩子，最后的最后");
		System.out.println("分割前的字符串："+c);
		System.out.println();
		
		String[] d=c.split("，");
		for(String str:d){
		System.out.println("分割后的字符串："+str.toString());
		}
		
		System.out.println();
		//对今天日期进行格式化处理
		Date date =new Date();
		String e =String.format("%tY", date);
		String f =String.format("%tm", date);
		String g =String.format("%td", date);
		String h =String.format("%tA", date);
		
		String i=String.format("%tk", date);
		String j=String.format("%tM", date);
		String k=String.format("%tS", date);
		System.out.println("现在的日期是:"+e+"年"+f+"月"+g+"号"+i+"时"+j+"分"+k+"秒"+"\t"+h);
		
		System.out.println("转换为八进制"+String.format("%o", 130));
	}
}
