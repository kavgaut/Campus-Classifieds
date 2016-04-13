package com.my.friends;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DateFormatter;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextPane;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.Font;
import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.swing.SpringLayout;

import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

/** A page to create buy posts when the user want to BuyForm anything.
 * He can post an ad for various categories like books, electronics etc. 
 * @author Sambhavi Raghuraman
 *
 */
public class BuyForm extends JFrame {

	private JPanel contentPane;
	private JList list;
	private JLabel lblCategory;
	private JLabel lblNeedBy;
	private JLabel label;
	private JLabel lblDescription;
	private JTextPane textPane;
	private JTextField textField;
	private JLabel lblPriceRange;
	private JLabel lblProductTitle;
	private JTextField textField_1;
	private String catEntered, descEntered, prodTitleEntered, needByEntered,phoneNumber;;
	private double priceEntered;
 	private JLabel lblChkAll;
	Boolean price_flag;
	private JTextField txtNeedBy;
	private JTextField txtlabel;
	private JTextField txtDescription;
	private JTextField txtProdDetails;
	JComboBox comboBox;
	JDatePickerImpl datePicker;
	private JLabel label_2;
	private SpringLayout springLayout;
	Boolean ValidDate;
	private JTextField textField_2;
	private JLabel label_1;
	private JLabel label_3;
	private JLabel label_4;
	Boolean sContactTxtChk;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuyForm frame = new BuyForm();
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
	public BuyForm() {
		setResizable(false);
		setTitle("Buy Form");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 430);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(7, 7, 7, 7));
		setContentPane(contentPane);

		UtilDateModel model = new UtilDateModel();
		
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		ValidDate = true; // true when empty or when you select a valid date
		
		//action listener for Submit Button
	
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TextFieldsValidator textValidate = new TextFieldsValidator();
			
				catEntered = comboBox.getSelectedItem().toString();
				descEntered = textPane.getText();
				phoneNumber = textField_2.getText();
				prodTitleEntered = textField.getText();
				phoneNumber = textField_2.getText().toString();
				if(textValidate.validatePhoneNumber(phoneNumber) || (phoneNumber.isEmpty())){
					sContactTxtChk = true;
				}
				else{
					sContactTxtChk = false;
				}
				
				try {
					price_flag = true;
					priceEntered = Double.parseDouble(textField_1.getText().toString());
				}
				catch(NumberFormatException e) {
					price_flag = false;
					lblChkAll.setForeground(Color.RED);
					lblChkAll.setHorizontalAlignment(SwingConstants.CENTER);
					lblChkAll.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblChkAll.setText("Please enter a valid price");
				}
				
				//Checking if any field is empty
				if(!(catEntered.isEmpty()) &&  !(descEntered.isEmpty()) && !(prodTitleEntered.isEmpty()) && !(price_flag == false) && ValidDate == true) {
				int buyId;
				String userId;
				boolean bCreateSubChk = true;
				lblChkAll.setText("");
				PostIdGenerator pg = new PostIdGenerator();
				GetUserID gusrid = new GetUserID(); 
				//calling the PostIdGenerator class to generate a unique BuyPost id
				buyId = pg.writeRentalId(new File("/Users/kavyagautam/JAVA/CampusClassifieds/src/com/my/friends/BuyIdGenerator.txt"));
				
				//calling the GetUserID class to get the user id of the user who is logged in
				userId = gusrid.getUserID();
								
				//calling the data processing class to insert data into the Buy table
				DataProcessing dp = new DataProcessing(buyId, userId, catEntered, needByEntered, priceEntered, prodTitleEntered,phoneNumber, descEntered);
				dp.processData(false, false, true, false, false,true,
					false,false, DisplayData.someRowSelectedChk,false);
				dispose();
		}
				else if(catEntered.isEmpty()){
					lblChkAll.setForeground(Color.RED);
					lblChkAll.setHorizontalAlignment(SwingConstants.CENTER);
					lblChkAll.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblChkAll.setText("Select a valid category");
				}
				
				else if(prodTitleEntered.isEmpty()){
					lblChkAll.setForeground(Color.RED);
					lblChkAll.setHorizontalAlignment(SwingConstants.CENTER);
					lblChkAll.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblChkAll.setText("Select a valid product Title");
				}
				else if(ValidDate == false){
					lblChkAll.setForeground(Color.RED);
					lblChkAll.setHorizontalAlignment(SwingConstants.CENTER);
					lblChkAll.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblChkAll.setText("Select a valid Date");
				}
				else if(descEntered.isEmpty()){
						lblChkAll.setForeground(Color.RED);
						lblChkAll.setHorizontalAlignment(SwingConstants.CENTER);
						lblChkAll.setFont(new Font("Tahoma", Font.BOLD, 11));
						lblChkAll.setText("Select a valid Description");
								
				}
		
		 }
		});
		
				list = new JList();
				String[] category_buy = { "Electronics", "Clothes", "Furniture", "Clothing, Shoes & Accessories", "Crafts" ,
						"Health & Beauty","Jewelry & Watches", "Sporting Goods", "Others"};
				 comboBox = new JComboBox(category_buy);
				
				lblCategory = new JLabel("Category");
				lblCategory.setFont(new Font("Tahoma", Font.BOLD, 11));
				
				lblNeedBy = new JLabel("Need By");
				lblNeedBy.setFont(new Font("Tahoma", Font.BOLD, 11));
				
				label_2 = new JLabel("");
				
				lblDescription = new JLabel("Description");
				lblDescription.setFont(new Font("Tahoma", Font.BOLD, 11));
				
				textPane = new JTextPane();
				
				textField = new JTextField();
				textField.setColumns(10);
				
				lblProductTitle = new JLabel("Product Title");
				lblProductTitle.setFont(new Font("Tahoma", Font.BOLD, 11));
				
				lblPriceRange = new JLabel("Price Range");
				lblPriceRange.setFont(new Font("Tahoma", Font.BOLD, 11));
				
				textField_1 = new JTextField();
				textField_1.setColumns(10);
				
				lblChkAll = new JLabel("            ");
				lblChkAll.setForeground(Color.RED);
				
				JLabel lblNewLabel = new JLabel("*");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
				lblNewLabel.setForeground(Color.RED);
				
				JLabel lblNewLabel_1 = new JLabel("Ph. Number");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
				
				textField_2 = new JTextField();
				textField_2.setColumns(10);
				
				label_1 = new JLabel("*");
				label_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
				label_1.setForeground(Color.RED);
				
				label_3 = new JLabel("*");
				label_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
				label_3.setForeground(Color.RED);
				
				label_4 = new JLabel("*");
				label_4.setForeground(Color.RED);
				label_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
				
						datePicker = new JDatePickerImpl(datePanel,
								new DateComponentFormatter());
						datePicker.setBackground(Color.LIGHT_GRAY);
						//		springLayout.putConstraint(SpringLayout.NORTH, datePicker.getJFormattedTextField(), 0, SpringLayout.NORTH, datePicker);
						//		springLayout = (SpringLayout) datePicker.getLayout();
						//		springLayout.putConstraint(SpringLayout.SOUTH, datePicker.getJFormattedTextField(), 0, SpringLayout.SOUTH, datePicker);
								datePicker.getJFormattedTextField().setHorizontalAlignment(
										SwingConstants.CENTER);
								datePicker.getJFormattedTextField().setToolTipText("Pick a date!");
								datePicker.setBounds(220, 350, 120, 30);
								datePicker.setVisible(true);
								datePicker.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent arg0) {
									Date selectedDate = null;
									selectedDate = (Date) datePicker.getModel().getValue();

									String date = selectedDate + "";

									if (selectedDate != null) {
									     java.util.Date date2 = new java.util.Date();
										date2.getDate();
										boolean isAfter  = selectedDate.after(date2);
										
											if (isAfter) {
												ValidDate = true;
										needByEntered = (selectedDate.toString()).substring(4,(selectedDate.toString().length())-18) + "," +
												(selectedDate.toString()).substring((selectedDate.toString().length()) - 5,(selectedDate.toString().length()));  
										}
										
										else {
											ValidDate = false;
										}
									}
									}
								});
				GroupLayout gl_contentPane = new GroupLayout(contentPane);
				gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(6)
									.addComponent(lblChkAll, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(6)
									.addComponent(lblCategory)
									.addGap(28)
									.addComponent(lblNewLabel)
									.addGap(6)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE)
									.addGap(21)
									.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(412)
									.addComponent(label_2))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(6)
									.addComponent(lblProductTitle)
									.addGap(7)
									.addComponent(label_3)
									.addGap(6)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(6)
									.addComponent(lblNewLabel_1)
									.addGap(30)
									.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(6)
									.addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(label_4)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(195)
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(6)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblPriceRange)
											.addGap(12)
											.addComponent(label_1))
										.addComponent(lblNeedBy))
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(6)
											.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(14)
											.addComponent(datePicker, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)))))
							.addGap(11))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addComponent(lblChkAll)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(7)
									.addComponent(lblCategory))
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(1)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(26)
									.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(16)
									.addComponent(lblNeedBy))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addComponent(datePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(12)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(12)
									.addComponent(lblPriceRange))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(6)
									.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(13)
							.addComponent(label_2)
							.addGap(6)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(8)
									.addComponent(lblProductTitle))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(2)
									.addComponent(label_3))
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(8)
									.addComponent(lblNewLabel_1))
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(19)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(11)
											.addComponent(lblDescription))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(5)
											.addComponent(label_4)))
									.addGap(54))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)))
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
				);
				
				//action listener for NeedBy
				
				
				JPanel panel = new JPanel();
				datePicker.add(panel);
				
						panel.setVisible(true);
				contentPane.setLayout(gl_contentPane);
			}
		}
