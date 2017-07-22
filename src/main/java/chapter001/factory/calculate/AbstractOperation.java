package main.java.chapter001.factory.calculate;

import main.java.utils.StringUtils;

/**
 *@author wangjl
 *@date 2017Äê7ÔÂ22ÈÕ
 */
public abstract class AbstractOperation {

	private String operationName;
	
	public AbstractOperation(String opName) {
		this.setOperationName(opName);
	}
	
	public boolean checkParam(String param) {
		return !StringUtils.isEmpty(param);
	}
	
	public abstract double getResult(double number1, double number2);
	
	public abstract String getDesp();

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
}
