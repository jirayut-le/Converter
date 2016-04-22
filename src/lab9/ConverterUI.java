package lab9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConverterUI extends JFrame {

	private JButton convertButton;
	private UnitConverter unitconverter;
	private JPanel contentPane;
	private JTextField firstTextField;
	private JTextField secondTextField;
	private JComboBox chooseUnitFirstBox;
	private JComboBox chooseUnitSecondBox;
	private JButton clearButton;
	private JRadioButton leftToRight;
	private JRadioButton rightToLeft;
	private JRadioButton autoDetect;

	/**
	 * Constructor of ConverterUI
	 * @param is Object from UnitConverter
	 */
	public ConverterUI ( UnitConverter uc){
		this.unitconverter = uc;
		this.setTitle("Length Converter");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents();
	}
	/**
	 * initialize components in the window
	 */
	private void initComponents(){
		setBounds(100, 100, 670, 100);

		contentPane = new JPanel(new BorderLayout(0,0));
		setContentPane(contentPane);

		JPanel converterPanel = new JPanel(new FlowLayout( FlowLayout.CENTER ,5,5));
		contentPane.add(converterPanel, BorderLayout.NORTH);

		firstTextField = new JTextField();
		firstTextField.setPreferredSize( new Dimension (100,30));
		//		firstTextField.addActionListener(new ConvertButtonListener());
		firstTextField.addKeyListener(new ConvertButtonListener());
		converterPanel.add(firstTextField );

		chooseUnitFirstBox = new JComboBox<Unit>( unitconverter.getUnits() );
		chooseUnitFirstBox.addActionListener( new ConvertButtonListener() );
		converterPanel.add(chooseUnitFirstBox);

		JLabel equalsLabel = new JLabel("=");
		converterPanel.add(equalsLabel);

		secondTextField = new JTextField();
		secondTextField.setPreferredSize( new Dimension (100,30) );
		secondTextField.addKeyListener(new ConvertButtonListener());
		converterPanel.add( secondTextField );
		secondTextField.setEnabled(false);

		chooseUnitSecondBox = new JComboBox<Unit>( unitconverter.getUnits() );
		chooseUnitSecondBox.addActionListener( new ConvertButtonListener() );
		converterPanel.add(chooseUnitSecondBox);

		convertButton = new JButton("Convert!");
		convertButton.addActionListener( new ConvertButtonListener() );
		converterPanel.add(convertButton);

		clearButton = new JButton("Clear");
		clearButton.addActionListener(new Clear());
		converterPanel.add(clearButton);

		JPanel directionToConverter = new JPanel(new FlowLayout(FlowLayout.CENTER , 5 ,5));
		contentPane.add(directionToConverter, BorderLayout.CENTER);

		leftToRight = new JRadioButton("Left -> Right");
		leftToRight.setSelected(true);
		leftToRight.addActionListener( new clickLeftToRight());
		directionToConverter.add(leftToRight);

		rightToLeft = new JRadioButton("Right -> Left");
		rightToLeft.addActionListener( new clickRightToLeft());
		directionToConverter.add(rightToLeft);

		autoDetect = new JRadioButton("Auto-Detect");
		autoDetect.addActionListener(new autoDetectButton());
		directionToConverter.add(autoDetect);
	}
	/**
	 * clickRightToLeft is an ActionListener that performs an action when pressed rightToLeft Radio button.
	 * It set enabled to false the Left JTextField , set enabled to true the Right JTextField , 
	 * set enabled to true the convertButton , switch selected JRadioButton 
	 */
	class clickRightToLeft implements ActionListener {
		public void actionPerformed ( ActionEvent evt){
			leftToRight.setSelected(false);
			rightToLeft.setSelected(true);
			autoDetect.setSelected(false);
			firstTextField.setEnabled(false);
			secondTextField.setEnabled(true);
			convertButton.setEnabled(true);
		}
	}
	/**
	 * clickLeftToRight is an ActionListener that performs an action when pressed leftToRight Radio button.
	 * It set enabled to true the Left JTextField , set enabled to false the Right JTextField , 
	 * set enabled to true the convertButton , switch selected JRadioButton 
	 */
	class clickLeftToRight implements ActionListener {
		public void actionPerformed ( ActionEvent evt){
			leftToRight.setSelected(true);
			rightToLeft.setSelected(false);
			autoDetect.setSelected(false);
			firstTextField.setEnabled(true);
			secondTextField.setEnabled(false);
			convertButton.setEnabled(true);
		}
	}
	/**
	 * autoDetectButton is an ActionListener that performs an action when key any number in both firstTextField and secondTextField
	 * It set enabled to true the Left JTextField and Right JTextField 
	 * set enabled to false the convertButton , switch selected JRadioButton 
	 */
	class autoDetectButton implements ActionListener {
		public void actionPerformed ( ActionEvent evt){
			autoDetect.setSelected(true);
			leftToRight.setSelected(false);
			rightToLeft.setSelected(false);
			firstTextField.setEnabled(true);
			secondTextField.setEnabled(true);
			convertButton.setEnabled(false);
		}
	}
	/**
	 * Clear is an ActionListener that performs an action when pressed "Clear" button.
	 * It reset null in both JTextField , and reset selected to first list in JComboBox
	 */
	class Clear implements ActionListener{
		public void actionPerformed ( ActionEvent evt){
			chooseUnitFirstBox.setSelectedIndex(0);
			chooseUnitSecondBox.setSelectedIndex(0);
			firstTextField.setText(null);
			secondTextField.setText(null);
		}
	}
	
	/**
	 * ConvertButtonListener is an ActionListener that performs an action when the button is pressed.
	 * It read the text form JTextField , Convert the value ,  call the unitconvecter to convert ,
	 * and set Text in other text field
	 */
	class ConvertButtonListener implements ActionListener , KeyListener {
		
		/**
		 * The method convert is action to convert any direction you want.
		 */
		public void convert(){
			JTextField[] textFieldArray = { firstTextField , secondTextField };
			int numberArrayToConverter = ( leftToRight.isSelected() || (autoDetect.isSelected() &&  (firstTextField.isFocusOwner() ||chooseUnitSecondBox.isFocusOwner() ))) ? 0 : 1 ;
			String s = textFieldArray[numberArrayToConverter].getText().trim();
			if ( s.length() > 0 ){
				try {
					double value = Double.valueOf(s);
					Unit unitFirstSelected = (Unit) chooseUnitFirstBox.getSelectedItem();
					Unit unitSecondSelected = (Unit) chooseUnitSecondBox.getSelectedItem();
					Unit[] unitArray = {unitFirstSelected , unitSecondSelected};
					int numberSwitch = (numberArrayToConverter == 0) ? 1 : 0 ;
					textFieldArray[numberSwitch].setText( String.format("%.3f", unitconverter.convert(value, unitArray[numberArrayToConverter], unitArray[numberSwitch])));
				} catch (NumberFormatException e){
					e.printStackTrace();
				}
			}
		}
		
		/**
		 * The action to perform action when pressed the convert button and change Unit vector
		 */
		public void actionPerformed( ActionEvent evt){
			this.convert();
		}
		
		/**
		 * The action to keyPressed action when pressed Key "ENTER" in each JTextField
		 */
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER )
				this.convert();
		}

		@Override
		public void keyTyped(KeyEvent e) {}
		
		/**
		 * The action to keyReleased action when released the key in each JTextField
		 */
		@Override
		public void keyReleased(KeyEvent e) {
			if ( autoDetect.isSelected() ){
				this.convert();
				if ( firstTextField.getText().equals("") )
					secondTextField.setText("");
				else if ( secondTextField.getText().equals(""))
					firstTextField.setText("");
			}

		}
	}

	public static void main (String[] args){
		UnitConverter uc = new UnitConverter();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConverterUI converter = new ConverterUI(uc); 
					converter.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}