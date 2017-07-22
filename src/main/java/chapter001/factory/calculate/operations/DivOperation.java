package main.java.chapter001.factory.calculate.operations;

import main.java.chapter001.factory.calculate.AbstractOperation;

/**
 *@author wangjl
 *@date 2017年7月22日
 */
public class DivOperation extends AbstractOperation {

	public DivOperation(String opName) {
		super(opName);
	}

	@Override
	public double getResult(double number1, double number2) {
		if (number2 == 0) {
			throw new RuntimeException("除数不能为0");
		}
		return number1 / number2;
	}

	@Override
	public String getDesp() {
		return "/";
	}

}
