package main.java.chapter001.factory.calculate.exception;
/**
 *@author wangjl
 *@date 2017Äê7ÔÂ22ÈÕ
 */
public class MsrRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1204342505618990285L;
	
	public MsrRuntimeException(String errMsg) {
		super(errMsg);
	}
}
