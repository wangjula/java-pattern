package main.java.utils;
/**
 *@author wangjl
 *@date 2017��7��22��
 */
public class StringUtils {

	/**
	 * ���ַ���д
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
	 * �ַ����Ƿ�Ϊ��
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
