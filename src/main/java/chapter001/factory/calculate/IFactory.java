package main.java.chapter001.factory.calculate;
/**
 *@author wangjl
 *@date 2017��7��22��
 */
public interface IFactory<T> {
	
	/**
	 * ���ݲ�������ȡ��Ҫ�Ĳ���
	 * @param opName
	 * @return
	 */
	 T create(String opName);
}
