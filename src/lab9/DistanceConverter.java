package lab9;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DistanceConverter extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DistanceConverter frame = new DistanceConverter();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DistanceConverter() {
		super("Distance Converter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 60);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JTextField firstTextField = new JTextField();
		firstTextField.setPreferredSize( new Dimension (100,30));
		contentPane.add(firstTextField );
		
		String[] unitConverter = {"Meter","Centimeter","Kilometer","Mile","Foot","Wa"};
		
		JComboBox chooseTypeFirstBox = new JComboBox(unitConverter);
		contentPane.add(chooseTypeFirstBox);
		
		JTextField secondTextField = new JTextField();
		secondTextField.setPreferredSize( new Dimension (100,30));
		contentPane.add( secondTextField );
		
		JComboBox chooseTypeSecondBox = new JComboBox(unitConverter);
		contentPane.add(chooseTypeSecondBox);
		
		JButton convertButton = new JButton("Convert");
		convertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		contentPane.add(convertButton);
		
		JButton clearButton = new JButton("Clear");
		contentPane.add(clearButton);
		
		
	}

}
