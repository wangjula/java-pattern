package main.java.chapter001.factory.calculate;

import main.java.utils.StringUtils;

/**
 *@author wangjl
 *@date 2017Äê7ÔÂ22ÈÕ
 */

public class OperationFactory implements IFactory<AbstractOperation> {
	
	private static final String packageName = "main.java.chapter001.factory.calculate.operations";

	public static OperationFactory getInstance() {
		return new OperationFactory();
	}

	@Override
	public AbstractOperation create(String opName) {
		Class<?> clazz;
		try {
			clazz = Class.forName(packageName + "." + StringUtils.toUpperCaseFirstChar(opName) + "Operation");
			return (AbstractOperation) clazz.getConstructor(String.class).newInstance(opName);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
