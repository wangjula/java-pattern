package main.java.chapter001.factory.calculate.operations;

import main.java.chapter001.factory.calculate.AbstractOperation;

/**
 *@author wangjl
 *@date 2017Äê7ÔÂ22ÈÕ
 */
public class AddOperation extends AbstractOperation {

	public AddOperation(String opName) {
		super(opName);
	}

	@Override
	public double getResult(double number1, double number2) {
		return number1 + number2;
	}

	@Override
	public String getDesp() {
		return "+";
	}

}
