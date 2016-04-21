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

	public ConverterUI ( UnitConverter uc){
		this.unitconverter = uc;
		
		this.setTitle("Length Converter");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents();
	}
	
	private void initComponents(){
		setBounds(100, 100, 670, 60);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		firstTextField = new JTextField();
		firstTextField.setPreferredSize( new Dimension (100,30));
		contentPane.add(firstTextField );
		
		chooseUnitFirstBox = new JComboBox<Unit>();
		contentPane.add(chooseUnitFirstBox);
		
		Unit[] lengths = unitconverter.getUnits();
		for( Unit u : lengths ) chooseUnitFirstBox.addItem( u );
			
		JLabel equalsLabel = new JLabel("=");
		contentPane.add(equalsLabel);
		
		secondTextField = new JTextField();
		secondTextField.setPreferredSize( new Dimension (100,30));
		contentPane.add( secondTextField );
		secondTextField.setEditable(false);
		
		chooseUnitSecondBox = new JComboBox<Unit>();
		contentPane.add(chooseUnitSecondBox);
		
		for( Unit u : lengths ) chooseUnitSecondBox.addItem( u );
		
		convertButton = new JButton("Convert!");
		contentPane.add(convertButton);
		
		clearButton = new JButton("Clear");
		contentPane.add(clearButton);
		
		ActionListener clearListener = new Clear();
		clearButton.addActionListener(clearListener);
		
		ActionListener listener = new ConvertButtonListener();
		convertButton.addActionListener( listener );
	}
	
	class Clear implements ActionListener{
		public void actionPerformed ( ActionEvent evt){
			chooseUnitFirstBox.setSelectedIndex(0);
			chooseUnitSecondBox.setSelectedIndex(0);
			firstTextField.setText(null);
			secondTextField.setText(null);
		}
	}
	class ConvertButtonListener implements ActionListener {
		public void actionPerformed( ActionEvent evt){
			String s = firstTextField.getText().trim();
			if ( s.length() > 0 ){
				try {
					double value = Double.valueOf(s);
					Unit unitFirstSelected = (Unit) chooseUnitFirstBox.getSelectedItem();
					Unit unitSecondSelected = (Unit) chooseUnitSecondBox.getSelectedItem();
					secondTextField.setText( Double.toString(unitconverter.convert(value, unitFirstSelected, unitSecondSelected)));
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		}
		
		public void round(){
			
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
