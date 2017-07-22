package main.java.chapter001.factory.calculate;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import main.java.chapter001.factory.calculate.enums.CalculateStatus;
import main.java.utils.StringUtils;

/**
 * ������
 *@author wangjl
 *@date 2017��7��22��
 */
public class CalculatorView extends JFrame {

	private static final long serialVersionUID = 6675631336371856760L;
	
	/**
	 * ������ť���
	 */
	private static final int bWidth = 30;
	
	/**
	 * ������ť�߶�
	 */
	private static final int bHeight = 20;
	
	/**
	 * �������1
	 */
	private double param1;
	
	/**
	 * �������2
	 */
	private double param2;
	
	/**
	 * ������
	 */
	private double result;
	
	/**
	 * ����ѡ��Ĳ���
	 */
	private AbstractOperation currentOp;
	
	/**
	 * ���㹫ʽ���
	 */
	private JTextField formula = new JTextField(25);
	
	/**
	 * ���������
	 */
	private JTextField screen = new JTextField(25);
	
	/**
	 * ֧�ֵĲ�������
	 */
	private List<String> opNames = new ArrayList<String>();
	
	/**
	 * ������ť
	 */
	private List<JButton> operButtons = new ArrayList<JButton>();
	
	/**
	 * ���ݲ�������ȡ����ʵ���Ĺ���
	 */
	private OperationFactory factory = new OperationFactory();
	
	/**
	 * ������״̬
	 */
	private CalculateStatus status;
	
	/**
	 * ���췽��
	 * @param opNames
	 */
	public CalculatorView(List<String> opNames) {
		this.opNames = opNames;
		initComponents();
		resetStatus();
		
		setResizable(false);
		setLocationRelativeTo(getOwner());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * ��ʼ���������
	 */
	private void initComponents() {
		initUI();
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(2, 1));
		textPanel.add(formula);
		textPanel.add(screen);
		add(textPanel, BorderLayout.NORTH);
		
		JPanel bPanel = new JPanel();
		JPanel numAndRes = createNumAndResButtons();
		bPanel.add(numAndRes);
		JPanel opp = createOperationButtons();
		bPanel.add(opp);
		add(bPanel, BorderLayout.CENTER);
		
		screen.setEditable(false);
		formula.setEditable(false);
	}

	/**
	 * ���Ի�ȡ��ǰϵͳ����۷��
	 */
	private void initUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			new RuntimeException(e);
		}
	}

	/**
	 * �����������Ͱ�ť���
	 */
	private JPanel createOperationButtons() {
		JButton opButton;
		for (String opName : opNames) {
			AbstractOperation op = factory.create(opName);
			opButton = new JButton();
			opButton.setText(op.getDesp());
			opButton.setSize(bWidth, bHeight);
			opButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					boolean isValidResult = showCalculateResult();
					if(!isValidResult) {
						return;
					}
					
					try {
						setParamValue(op);
					} catch (Exception ex) {
						if ("���������ʽ�����������������͵Ĳ���".equals(ex.getMessage())) {
							showErrorMessage("���������ʽ�����������������͵Ĳ���");
						} else {
							throw ex;
						}
						return;
					}
					
					currentOp = op;
					screen.setText("");
					formula.setText(result + op.getDesp());
				}
			});
			operButtons.add(opButton);
		}
		
		JButton backspace = createBackSpaceButton();
		operButtons.add(backspace);
		
		JButton reset = createResetButton();
		operButtons.add(reset);
		
		List<JPanel> opps = getOperPanels(); 
		JPanel totalOpp = new JPanel(new FlowLayout());
		totalOpp.setLayout(new FlowLayout());
		for (JPanel p : opps) {
			totalOpp.add(p);
		}

		return totalOpp;
	}

	/**
	 * �������ð�ť
	 * @return
	 */
	private JButton createResetButton() {
		JButton reset = new JButton("reset");
		reset.setSize(bWidth, bHeight);
		reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				resetStatus();
			}
		});
		return reset;
	}

	/**
	 * �����˸�ť
	 * @return
	 */
	private JButton createBackSpaceButton() {
		JButton backspace = new JButton("backspace");
		backspace.setSize(bWidth, bHeight);
		backspace.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!StringUtils.isEmpty(screen.getText().trim())) {
					screen.setText(screen.getText().substring(0, screen.getText().length() - 1));
					formula.setText(formula.getText().substring(0, formula.getText().length() - 1));
				}
			}
		});
		return backspace;
	}

	/**
	 * ������ť��ֱ�Ų���4��һ��
	 * @return
	 */
	private List<JPanel> getOperPanels() {
		List<JPanel> opps = new ArrayList<JPanel>();
		int count = 0;
		JPanel p = null;
		for (JButton b : operButtons) {
			if (null == b) {
				continue;
			}
			if (count % 4 == 0) {
				p = new JPanel();
				p.setLayout(new GridLayout(4, 1));
				opps.add(p);
			}
			p.add(b);
			count ++;
		}
		return opps;
	}

	/**
	 * ��Ⲣ���ü������1������ֵ
	 * @param op
	 */
	protected void setParamValue(AbstractOperation op) {
		boolean isValidParam = false;
		if (CalculateStatus.RES.equals(status)) {
			status = CalculateStatus.CAL;
			isValidParam = op.checkParam(String.valueOf(result));
		} else {
			isValidParam = !StringUtils.isEmpty(String.valueOf(result));
		}
		
		if (!isValidParam) {
			param1 = 0;
			showErrorMessage("���������ʽ�����������������͵Ĳ���");
		}
		param1 = result;
	}
	
	/**
	 * ��ʾ�������������Ϣ�������ü�����״̬
	 */
	private void showErrorMessage(String errMsg) {
		JOptionPane.showMessageDialog(getParent(), errMsg);
		resetStatus();
	}

	/**
	 * �������ְ�ť�������ʾ��������ť���
	 * @return
	 */
	private JPanel createNumAndResButtons() {
		JButton[] numbers = new JButton[10];
		for (int i = 0; i < 10; i ++) {
			numbers[i] = new JButton(String.valueOf(i));
			final int number = i;
			numbers[i].setSize(bWidth, bHeight);
			numbers[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if (CalculateStatus.CAL.equals(status)) {
						screen.setText(screen.getText() + String.valueOf(number));
					} else {
						resetStatus();
						screen.setText(String.valueOf(number));
					}
					
					formula.setText(formula.getText() + String.valueOf(number));
				}
			});
		}
		
		JButton pointButton = new JButton(".");
		pointButton.setSize(bWidth, bHeight);
		pointButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (CalculateStatus.CAL.equals(status)) {
					screen.setText(screen.getText() + ".");
				} else {
					resetStatus();
					screen.setText(".");
				}
				formula.setText(formula.getText() + ".");
			}
		});
		
		JButton resButton = new JButton("=");
		resButton.setSize(bWidth, bHeight);
		resButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showCalculateResult();
			}
		});
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4, 3));
		p.add(numbers[7]);
		p.add(numbers[8]);
		p.add(numbers[9]);
		p.add(numbers[4]);
		p.add(numbers[5]);
		p.add(numbers[6]);
		p.add(numbers[1]);
		p.add(numbers[2]);
		p.add(numbers[3]);
		p.add(numbers[0]);
		p.add(pointButton);
		p.add(resButton);
		return p;
	}
	
	/**
	 * ����õ����
	 */
	protected boolean showCalculateResult() {
		if (null == currentOp) {
			if (StringUtils.isEmpty(screen.getText())) {
				showErrorMessage("��������������");
				return false;
			} else {
				result = Double.parseDouble(screen.getText().trim());
				screen.setText("");
				formula.setText(String.valueOf(result));
			}
			return true;
		}
		
		try {
			param2 = Double.parseDouble(screen.getText().trim());
			result = currentOp.getResult(param1, param2);
			currentOp = null;
			screen.setText("");
			formula.setText(String.valueOf(result));
			status = CalculateStatus.RES;
			return true;
		} catch (Exception ex) {
			if ("��������Ϊ0".equals(ex.getMessage())) {
				showErrorMessage("��������Ϊ0");
			} else {
				showErrorMessage("���������ʽ�����������������͵Ĳ���");
			}
			resetStatus();
			return false;
		}
	}

	/**
	 * ���ü�����״̬
	 */
	private void resetStatus() {
		status = CalculateStatus.CAL;
		currentOp = null;
		screen.setText("");
		formula.setText("");
	}

}
