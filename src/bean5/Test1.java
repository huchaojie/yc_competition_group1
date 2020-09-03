package bean5;

public class Test1 {

	public static void main(String[] args) {
		double x=0.000001;
		for(int i=0;i<99;i++) {
			System.out.println("x="+x+"");
			x=x+0.000001;
		}
	}
}
