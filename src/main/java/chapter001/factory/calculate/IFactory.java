package main.java.chapter001.factory.calculate;
/**
 *@author wangjl
 *@date 2017年7月22日
 */
public interface IFactory<T> {
	
	/**
	 * 根据操作名获取需要的操作
	 * @param opName
	 * @return
	 */
	 T create(String opName);
}
