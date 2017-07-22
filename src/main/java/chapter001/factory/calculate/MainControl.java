package main.java.chapter001.factory.calculate;

import java.util.Arrays;

import javax.swing.SwingUtilities;

/**
 *@author wangjl
 *@date 2017��7��22��
 */
public class MainControl {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				String[] opNames = new String[] {"add", "sub", "mul", "div"};
				CalculatorView view = new CalculatorView(Arrays.asList(opNames));
				view.setSize(300, 180);
				view.setVisible(true);
			}
		});

	}

}
