package bean_study1;

/**
 * @author fangxiang
 *
 *         判断回文 1234 5 4321
 */
public class Test3 {

	public static void main(String[] args) {
		System.out.println("判断是否是回文数据:" + isPalindrome("abcdedcbad"));
	}

	private static Boolean isPalindrome(String s) {
		// TODO Auto-generated method stub
		if (s.length() <= 1) {
			return true;
		} else if(s.charAt(0)!=s.charAt(s.length()-1)) {
			return false;
		} else {
			return isPalindrome(s.substring(1, s.length() - 1));
		}
	}

}
