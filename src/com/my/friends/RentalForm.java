package com.my.friends;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextPane;

import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.DefaultComboBoxModel;

import java.awt.event.MouseMotionAdapter;

/** To create a rental post with all the necessary fields
 * 
 * @author Ramya
 *
 */

public class RentalForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtFieldPrice;
	private JTextField txtFieldAdd;
	private JTextField txtFieldCity;
	private JTextField txtFieldZipCode;
	private JLabel lblDescription;
	private JTextPane txtPaneDesc;
	private JLabel lblContactNumber;
	private JTextField txtFieldCnctNo;
	private JLabel lblBath;
	private JLabel lblNewLabel_3;
	
	private String categoryVal, propTypeVal, addressVal, cityVal, DescVal;
	private Integer noOfBedVal, zipCodeVal;
	private Double noOfBathVal, priceVal;
	private long contactNoVal;
	
	private char laundryVal, gymVal, poolVal, petVal, parkingVal, furnishedVal;
	
	@SuppressWarnings("rawtypes")
	JComboBox cBoxCategory;
	@SuppressWarnings("rawtypes")
	JComboBox cBoxPropType;
	@SuppressWarnings("rawtypes")
	JComboBox cBoxBed;
	@SuppressWarnings("rawtypes")
	JComboBox cBoxBath;
	
	JCheckBox chckbxLaundry;
	JCheckBox chckbxGym;
	JCheckBox chckbxPool;
	JCheckBox chckbxPetsAllowed;
	JCheckBox chckbxCoveredParking;
	JCheckBox chckbxFurnished;
	
	JLabel allFieldsChk;
	
	public static boolean priceTxtChk = false;
	public static boolean zipCodeTxtChk = false;
	public static boolean rCreateSubChk = false;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RentalForm frame = new RentalForm();
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RentalForm() {
		setResizable(false);
		setForeground(Color.BLUE);
		setTitle("Rental Form");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 677, 750);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLUE);
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(7, 7, 7, 7));
		setContentPane(contentPane);
		
		txtFieldPrice = new JTextField();
		txtFieldPrice.setColumns(10);
		
		/** Mouse clicked event to insert data into the database as soon as the  
		 * submit button is clicked with all the data entered in the create rental post page
		 */
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				categoryVal = cBoxCategory.getSelectedItem().toString();
				
				propTypeVal = cBoxPropType.getSelectedItem().toString();
				
				try{
					priceTxtChk = true;
					priceVal = Double.parseDouble(txtFieldPrice.getText());
				}
				catch(NumberFormatException e){
					priceTxtChk = false;
					allFieldsChk.setForeground(Color.RED);
					allFieldsChk.setHorizontalAlignment(SwingConstants.CENTER);
					allFieldsChk.setFont(new Font("Tahoma", Font.BOLD, 11));
					allFieldsChk.setText("Please enter a price");
				}
				
				try{
					noOfBathVal = Double.parseDouble(cBoxBath.getSelectedItem().toString());
				}
				catch(NumberFormatException ex){
					noOfBathVal = 0.0;
				}
				
				try{
					noOfBedVal = Integer.parseInt(cBoxBed.getSelectedItem().toString());
				}
				catch(NumberFormatException ex1){
					noOfBedVal = 0;
				}
				
				
				if(chckbxLaundry.isSelected()){
					laundryVal = 'Y';
				}
				else{
					laundryVal = 'N';
				}
				
				if(chckbxGym.isSelected()){
					gymVal = 'Y';
				}
				else{
					gymVal = 'N';
				}
				
				if(chckbxPool.isSelected()){
					poolVal = 'Y';
				}
				else{
					poolVal = 'N';
				}
				
				if(chckbxPetsAllowed.isSelected()){
					petVal = 'Y';
				}
				else{
					petVal = 'N';
				}
				
				if(chckbxCoveredParking.isSelected()){
					parkingVal = 'Y';
				}
				else{
					parkingVal = 'N';
				}
				
				if(chckbxFurnished.isSelected()){
					furnishedVal = 'Y';
				}
				else{
					furnishedVal = 'N';
				}
				
				try{
					contactNoVal = Long.parseLong(txtFieldCnctNo.getText().toString());
				}
				catch(NumberFormatException ex2){
					contactNoVal = 0;
				}
				
				addressVal = txtFieldAdd.getText();
				
				cityVal = txtFieldCity.getText();
				
				try{
					zipCodeTxtChk = true;
					zipCodeVal = Integer.parseInt(txtFieldZipCode.getText());
				}
				catch(NumberFormatException ex3){
					zipCodeTxtChk = false;
					allFieldsChk.setForeground(Color.RED);
					allFieldsChk.setHorizontalAlignment(SwingConstants.CENTER);
					allFieldsChk.setFont(new Font("Tahoma", Font.BOLD, 11));
					allFieldsChk.setText("Please enter a Zip code");
				}
				
				DescVal = txtPaneDesc.getText();
				
				//loop that checks if all the mandatory fields are entered or not
				if(!(categoryVal.isEmpty()) && !(propTypeVal.isEmpty()) && priceTxtChk == true && zipCodeTxtChk == true && !(cityVal.isEmpty())){
					
					int rentalId;
					String userId;
					rCreateSubChk = true;
					allFieldsChk.setText("");
					PostIdGenerator pg = new PostIdGenerator();
					GetUserID gusrid = new GetUserID(); 
					//calling the PostIdGenerator class to generate a unique rental id
					rentalId = pg.writeRentalId(new File("/Users/kavyagautam/JAVA/CampusClassifieds/src/com/my/friends/rentalIdGenerator.txt"));
					//calling the GetUserID class to get the user id of the user who is logged in
					userId = gusrid.getUserID();
					
					//calling the data processing class to insert data into the rentals table
					DataProcessing dp = new DataProcessing(rentalId, userId, categoryVal, propTypeVal, priceVal, noOfBedVal, noOfBathVal, 
							laundryVal, gymVal, poolVal, petVal, parkingVal, furnishedVal, contactNoVal,DescVal, addressVal, cityVal, zipCodeVal);
					dp.processData(false, false, false, rCreateSubChk, false, false, false, true, DisplayData.someRowSelectedChk, false);
					dispose();
					
					
				}
				else if(categoryVal.isEmpty()){
					allFieldsChk.setForeground(Color.RED);
					allFieldsChk.setHorizontalAlignment(SwingConstants.CENTER);
					allFieldsChk.setFont(new Font("Tahoma", Font.BOLD, 11));
					allFieldsChk.setText("Select a valid category");
				}
				else if(propTypeVal.isEmpty()){
					allFieldsChk.setForeground(Color.RED);
					allFieldsChk.setHorizontalAlignment(SwingConstants.CENTER);
					allFieldsChk.setFont(new Font("Tahoma", Font.BOLD, 11));
					allFieldsChk.setText("Select a valid property type");
				}
				else if(priceTxtChk == false){
					allFieldsChk.setForeground(Color.RED);
					allFieldsChk.setHorizontalAlignment(SwingConstants.CENTER);
					allFieldsChk.setFont(new Font("Tahoma", Font.BOLD, 11));
					allFieldsChk.setText("Enter a valid price");
				}
				else if(zipCodeTxtChk == false){
					allFieldsChk.setForeground(Color.RED);
					allFieldsChk.setHorizontalAlignment(SwingConstants.CENTER);
					allFieldsChk.setFont(new Font("Tahoma", Font.BOLD, 11));
					allFieldsChk.setText("Enter a valid zipcode");
				}
				else if(cityVal.isEmpty()){
					allFieldsChk.setForeground(Color.RED);
					allFieldsChk.setHorizontalAlignment(SwingConstants.CENTER);
					allFieldsChk.setFont(new Font("Tahoma", Font.BOLD, 11));
					allFieldsChk.setText("Enter a valid city");
				}
				else{
					allFieldsChk.setForeground(Color.RED);
					allFieldsChk.setHorizontalAlignment(SwingConstants.CENTER);
					allFieldsChk.setFont(new Font("Tahoma", Font.BOLD, 11));
					allFieldsChk.setText("Enter all the mandatory fields");
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		txtFieldAdd = new JTextField();
		txtFieldAdd.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Category");
		
		JLabel lblNewLabel_1 = new JLabel("Property Type");
		
		JLabel lblPropertyType = new JLabel("Price");
		
		JLabel lblBedBath = new JLabel("Bed");
		
		JLabel lblLocation = new JLabel("Street Address");
		
		JLabel lblCity = new JLabel("City");
		
		txtFieldCity = new JTextField();
		txtFieldCity.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Zip Code");
		
		txtFieldZipCode = new JTextField();
		txtFieldZipCode.setColumns(10);
		
		lblDescription = new JLabel("Description");
		
		txtPaneDesc = new JTextPane();
		
		JLabel label = new JLabel("per month\n");
		
		lblContactNumber = new JLabel("Contact Number");
		
		txtFieldCnctNo = new JTextField();
		txtFieldCnctNo.setColumns(10);
		
		lblBath = new JLabel("Bath");
		
		lblNewLabel_3 = new JLabel("Amenities");
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblNewLabel_3.setForeground(new Color(0, 0, 0));
		
		chckbxLaundry = new JCheckBox("Laundry");
		
		chckbxGym = new JCheckBox("Gym");
		
		chckbxPool = new JCheckBox("Pool");
		
		chckbxPetsAllowed = new JCheckBox("Pets Allowed");
		
		chckbxCoveredParking = new JCheckBox("Covered Parking");
		
		chckbxFurnished = new JCheckBox("Furnished");
		
		String[] category = new String[] {"House Rental", "Room for sharing"};
		cBoxCategory = new JComboBox(category);
		
		String[] PropertyType = new String[] {"Apartment", "Condo", "Studio", "Single-Family Home", "TownHome"};
		cBoxPropType = new JComboBox(PropertyType);
		
		String[] bed = new String[] {" ", "1", "2", "3", "4", "5"};
		cBoxBed = new JComboBox(bed);
		cBoxBed.setModel(new DefaultComboBoxModel(new String[] {" ", "1", "2", "3", "4", "5"}));
		
		String[] bath = new String[] {" ", "1", "1.5", "2", "2.5", "3", "3.5", "4"};
		cBoxBath = new JComboBox(bath);
		cBoxBath.setModel(new DefaultComboBoxModel(new String[] {" ", "1", "1.5", "2", "2.5", "3", "3.5", "4"}));
		
		allFieldsChk = new JLabel("");
		
		label_1 = new JLabel("*");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		label_1.setForeground(Color.RED);
		
		label_2 = new JLabel("*");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		
		label_3 = new JLabel("*");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		
		label_4 = new JLabel("*");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(Color.RED);
		label_4.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		
		label_5 = new JLabel("*");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setForeground(Color.RED);
		label_5.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(254)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(327, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(allFieldsChk, GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
													.addGroup(gl_contentPane.createSequentialGroup()
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
															.addComponent(lblNewLabel_1)
															.addComponent(lblPropertyType)
															.addComponent(lblNewLabel_2)
															.addComponent(lblCity)
															.addGroup(gl_contentPane.createSequentialGroup()
																.addComponent(lblBedBath)
																.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
																.addComponent(cBoxBed, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
															.addComponent(lblContactNumber, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
															.addComponent(lblDescription)
															.addComponent(lblNewLabel_3))
														.addGap(28)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
															.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
															.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
															.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
															.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)))
													.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
														.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)))
												.addGap(6))
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblLocation)
												.addPreferredGap(ComponentPlacement.RELATED)))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(chckbxPool)
											.addPreferredGap(ComponentPlacement.RELATED)))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(chckbxGym, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(chckbxLaundry)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(chckbxPetsAllowed)
								.addComponent(txtPaneDesc, GroupLayout.PREFERRED_SIZE, 408, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtFieldCnctNo, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblBath, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(cBoxBath, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtFieldCity, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtFieldZipCode, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(txtFieldPrice)
										.addComponent(cBoxPropType, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(cBoxCategory, Alignment.LEADING, 0, 139, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
								.addComponent(chckbxCoveredParking)
								.addComponent(chckbxFurnished)
								.addComponent(txtFieldAdd, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(39, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(allFieldsChk, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(cBoxCategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel))
					.addGap(13)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(cBoxPropType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPropertyType)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtFieldPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBedBath)
						.addComponent(cBoxBed, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBath)
						.addComponent(cBoxBath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(11)
							.addComponent(lblNewLabel_3)
							.addGap(15)
							.addComponent(chckbxLaundry)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(chckbxGym)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(chckbxPool))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(35)
							.addComponent(chckbxPetsAllowed)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(chckbxCoveredParking)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(chckbxFurnished)))
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblLocation)
						.addComponent(txtFieldAdd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblCity)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtFieldCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(txtFieldZipCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblContactNumber)
						.addComponent(txtFieldCnctNo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txtPaneDesc, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDescription))
					.addGap(18)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(83))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
}
