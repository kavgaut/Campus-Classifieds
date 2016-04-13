package com.my.friends;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.SQLException;

import javax.swing.JTextArea;

import java.awt.event.MouseMotionAdapter;


public class SellForm extends JFrame {

	private JPanel SellPanel;
	private JTextField sProdTitleTxtBox;
	private JButton btnSellFormSubmit;
	private JComboBox sCategoryCombo;
	private JComboBox sConditionCombo;
	private JLabel lblCategory;
	private JLabel lblCost;
	private JLabel lblDescription;
	private JLabel lblPrice;
	private JTextField sPriceTxtBox;
	private JLabel lblCondition;
	private JTextField sMakeTxtBox;
	private JTextField sModelTxtBox;
	private JLabel lblSizedimensions;
	private JTextField sSizeTxtBox;
	private JLabel lblContactNo;
	private JTextField sContactTxtBox;
	private JLabel lblNewLabel;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel lblValidation;
	
	private String sCategoryVal, sProdTitleVal, sConditionVal, sMakeVal, sModelVal, sSizeVal, sDescVal;
	private String sContactNoVal;
	private double sPriceVal;
	
	public static boolean sCreateSubChk;
	public static boolean sPriceTxtChk;
	public static boolean sContactTxtChk;
	private JTextPane sDescTxtBox;

	/**
	 * Launch the application.
	 * @author kavyagautam
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SellForm frame = new SellForm();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SellForm() {
		setResizable(false);
		setTitle("Sell Form");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 550);
		SellPanel = new JPanel();
		SellPanel.setBackground(Color.LIGHT_GRAY);
		SellPanel.setBorder(new EmptyBorder(7, 7, 7, 7));
		setContentPane(SellPanel);
		
		sProdTitleTxtBox = new JTextField(40);
		sCreateSubChk = false;
		sPriceTxtChk = false;
		
		btnSellFormSubmit = new JButton("Submit");
		btnSellFormSubmit.requestFocus();
		btnSellFormSubmit.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		btnSellFormSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TextFieldsValidator textValidate = new TextFieldsValidator();
				sCategoryVal = sCategoryCombo.getSelectedItem().toString();
				sConditionVal = sConditionCombo.getSelectedItem().toString();
				sProdTitleVal = sProdTitleTxtBox.getText().toString();
				sMakeVal = sMakeTxtBox.getText().toString();
				sModelVal = sModelTxtBox.getText().toString();
				sSizeVal = sSizeTxtBox.getText().toString();
				sDescVal = sDescTxtBox.getText().toString();
				
				try{
					sPriceTxtChk = true;
					sPriceVal = Double.parseDouble(sPriceTxtBox.getText());
				}
				catch(NumberFormatException ex){
					sPriceTxtChk = false;
					lblValidation.setForeground(Color.RED);
					lblValidation.setHorizontalAlignment(SwingConstants.CENTER);
					lblValidation.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblValidation.setText("Please enter a valid price");
				}
				
				sContactNoVal = sContactTxtBox.getText().toString();
				if(textValidate.validatePhoneNumber(sContactNoVal) || (sContactNoVal.isEmpty())){
					sContactTxtChk = true;
				}
				else{
					sContactTxtChk = false;
				}
				
				//If all mandatory fields are filled, create a file and generate postId and call data processing class
				if(!(sCategoryVal.isEmpty()) && !(sProdTitleVal.isEmpty()) && sPriceTxtChk && !(sMakeVal.isEmpty()) && !(sConditionVal.isEmpty()) && sContactTxtChk)
				{
					int sellId;
					String userId;
					sCreateSubChk = true;
					lblValidation.setText("");
					PostIdGenerator pg = new PostIdGenerator();
					GetUserID gusrid = new GetUserID(); 
					//calling the PostIdGenerator class to generate a unique rental id
					sellId = pg.writeRentalId(new File("/Users/kavyagautam/JAVA/CampusClassifieds/src/com/my/friends/sellIdGenerator.txt"));
					//calling the GetUserID class to get the user id of the user who is logged in
					userId = gusrid.getUserID();
					
					DataProcessing dp = new DataProcessing(sellId, userId, sCategoryVal, sProdTitleVal, sPriceVal, sConditionVal, sMakeVal, 
							sModelVal, sSizeVal, sContactNoVal, sDescVal);
					dp.processData(false, false, false, false, sCreateSubChk, false, true, false, DisplayData.someRowSelectedChk, false);
					dispose();
					
				}
				else if(sCategoryVal.isEmpty()){
					lblValidation.setForeground(Color.RED);
					lblValidation.setHorizontalAlignment(SwingConstants.CENTER);
					lblValidation.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblValidation.setText("Select a valid category");
				}
				else if(sProdTitleVal.isEmpty()){
					lblValidation.setForeground(Color.RED);
					lblValidation.setHorizontalAlignment(SwingConstants.CENTER);
					lblValidation.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblValidation.setText("Select a valid Product Title");
				}
				else if(sPriceTxtChk == false){
					lblValidation.setForeground(Color.RED);
					lblValidation.setHorizontalAlignment(SwingConstants.CENTER);
					lblValidation.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblValidation.setText("Enter a valid price");
				}
				
				else if(sConditionVal.isEmpty()){
					lblValidation.setForeground(Color.RED);
					lblValidation.setHorizontalAlignment(SwingConstants.CENTER);
					lblValidation.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblValidation.setText("Enter a valid condition of the product");
				}
				else if(sMakeVal.isEmpty()){
					lblValidation.setForeground(Color.RED);
					lblValidation.setHorizontalAlignment(SwingConstants.CENTER);
					lblValidation.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblValidation.setText("Enter a valid Make/Manufacturer");
				}
				else if(!sContactTxtChk){
					lblValidation.setForeground(Color.RED);
					lblValidation.setHorizontalAlignment(SwingConstants.CENTER);
					lblValidation.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblValidation.setText("Enter a valid Phone number");
				}
				else{
					lblValidation.setForeground(Color.RED);
					lblValidation.setHorizontalAlignment(SwingConstants.CENTER);
					lblValidation.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblValidation.setText("Enter all the mandatory fields");
				}
		
			}
		});
		
		String[] category = new String[] {"Books","Clothes","Electronics", "Furtniture", "Games", "Health & Beauty", "Jewelry", "Musical Instruments", "Sporting Goods", "Tickets", "Other"};
		sCategoryCombo = new JComboBox(category);
		
		lblCategory = new JLabel("Category");
		
		lblCost = new JLabel("Product Title");
		
		lblDescription = new JLabel("Description");
		
		lblPrice = new JLabel("Price");
		
		sPriceTxtBox = new JTextField(10);
		
		lblCondition = new JLabel("Condition");
		String[] condition = new String[] {"Excellent", "Like New", "Very Good", "Fair", "some wear and tear"};
		sConditionCombo = new JComboBox(condition);
		
		JLabel lblMakemanufacturer = new JLabel("Make/Manufacturer");
		
		sMakeTxtBox = new JTextField(50);
		
		JLabel lblModelNamenumber = new JLabel("Model Name/Number");
		lblModelNamenumber.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		
		sModelTxtBox = new JTextField(40);
		
		lblSizedimensions = new JLabel("Size/Dimensions");
		
		sSizeTxtBox = new JTextField(40);
			
		lblContactNo = new JLabel("Contact No");
		
		sContactTxtBox = new JTextField(10);
		
		lblNewLabel = new JLabel("*");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblNewLabel.setForeground(Color.RED);
		
		label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		label_2 = new JLabel("*");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		label_3 = new JLabel("*");
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		lblValidation = new JLabel("");
		
		sDescTxtBox = new JTextPane();
		
		
		GroupLayout gl_SellPanel = new GroupLayout(SellPanel);
		gl_SellPanel.setHorizontalGroup(
			gl_SellPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_SellPanel.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_SellPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_SellPanel.createSequentialGroup()
							.addGroup(gl_SellPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_SellPanel.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_SellPanel.createSequentialGroup()
										.addComponent(lblPrice)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_SellPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(label, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_SellPanel.createSequentialGroup()
											.addComponent(lblCategory)
											.addGap(75)
											.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_SellPanel.createSequentialGroup()
									.addComponent(lblMakemanufacturer, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
									.addGap(1)
									.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)))
							.addGap(6)
							.addGroup(gl_SellPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(sMakeTxtBox, 410, 410, 410)
								.addGroup(gl_SellPanel.createParallelGroup(Alignment.TRAILING, false)
									.addGroup(gl_SellPanel.createSequentialGroup()
										.addComponent(sPriceTxtBox, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(lblCondition)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)
										.addGap(1)
										.addComponent(sConditionCombo, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addComponent(sCategoryCombo, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(sProdTitleTxtBox, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE))))
						.addGroup(gl_SellPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblModelNamenumber)
							.addGap(25)
							.addComponent(sModelTxtBox, 410, 410, 410))
						.addGroup(gl_SellPanel.createSequentialGroup()
							.addComponent(lblSizedimensions, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
							.addGap(36)
							.addComponent(sSizeTxtBox, 410, 410, 410))
						.addComponent(lblCost)
						.addGroup(gl_SellPanel.createSequentialGroup()
							.addGroup(gl_SellPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblContactNo, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDescription))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_SellPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(sDescTxtBox)
								.addComponent(sContactTxtBox, GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE))))
					.addGap(5))
				.addGroup(gl_SellPanel.createSequentialGroup()
					.addComponent(lblValidation, GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_SellPanel.createSequentialGroup()
					.addGap(247)
					.addComponent(btnSellFormSubmit, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(261, Short.MAX_VALUE))
		);
		gl_SellPanel.setVerticalGroup(
			gl_SellPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_SellPanel.createSequentialGroup()
					.addComponent(lblValidation, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_SellPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCategory)
						.addComponent(sCategoryCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(18)
					.addGroup(gl_SellPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_SellPanel.createSequentialGroup()
							.addGroup(gl_SellPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCost)
								.addComponent(sProdTitleTxtBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_SellPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPrice)
								.addComponent(lblCondition)
								.addComponent(sConditionCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(sPriceTxtBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_SellPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMakemanufacturer, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(sMakeTxtBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_SellPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblModelNamenumber)
						.addComponent(sModelTxtBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_SellPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSizedimensions)
						.addComponent(sSizeTxtBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_SellPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblContactNo)
						.addComponent(sContactTxtBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(gl_SellPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDescription)
						.addComponent(sDescTxtBox, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSellFormSubmit)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		SellPanel.setLayout(gl_SellPanel);
	}
}
