package main.java.utils;
/**
 *@author wangjl
 *@date 2017年7月22日
 */
public class StringUtils {

	/**
	 * 首字符大写
	 * @param s
	 * @return
	 */
	public static String toUpperCaseFirstChar(String s) {
		if (isEmpty(s)) {
			throw new RuntimeException("string should not be null");
		}
		
		StringBuilder sb = new StringBuilder(s);
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		return sb.toString();
	}
	
	/**
	 * 字符串是否为空
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		if (null == s || "".equals(s.trim())) {
			return true;
		}
		return false;
	}
}
