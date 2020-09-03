import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author fangxiang
 */
public class Test1 {
	public static void main(String[] args) {
		String[] arr = { "1", "2", "3", "4", "5" };

		List arrlist = listAll(Arrays.asList(arr), " ");
		String[] s = new String[arrlist.size()];
		arrlist.toArray(s);
		for (String xx : s) {
			if (Ptintall(xx)) {
				System.out.println(xx);
			}

		}
	}

	public static List listAll(List list, String prefix) {
		List arrlist = new ArrayList();
		if (list.isEmpty()) {
			arrlist.add(prefix); // 加入所有的可能
			System.out.println(prefix);
		}
		for (int i = 0; i < list.size(); i++) {
			List temp = new LinkedList(list);
			listAll(temp, prefix + temp.remove(i));
		}
		return arrlist;
	};

	public static boolean Ptintall(String s) {
		if (s.indexOf("4") == 2) { // 判断4不能在第三位
			return false;
		} else if (s.contains("35") || s.contains("53")) { // 判断35不能相连
			return false;
		}
		return true;
	}
}