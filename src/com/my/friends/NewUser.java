package com.my.friends;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.regex.*;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.MouseMotionAdapter;


/** Class to register a new userwho doesnt have an account
 * details of user include userid, user first name and last name, emailid.
 * phone number and password
 * @author Ramya Bangaluru Gopalakrishna
 */
public class NewUser extends JFrame {

	private JTextField usrIDTxt;
	private JTextField firstNameTxt;
	private JTextField lastNameTxt;
	private JTextField emailIdTxt;
	private JTextField phoneTxt;
	public JLabel lblTxtFieldsChk;
	final JButton submitBtn;
	public static boolean generateUsrID = false ;
	public static boolean submitBtnChk = false;
	public static boolean emailIdTxtChk = false;
	public static boolean firstNameTxtChk = false;
	public static boolean lastNameTxtChk = false;
	public static boolean phoneValChk = false;
	public static boolean confirmPwdChk = false;
	public static boolean newPwdChk = false;
	public JFrame newUserFrame;
	public String userIDVal, firstNameVal, lastNameVal, emailIdVal, newPwdVal, confirmPwdVal;
	static Long phoneVal;
	static int count = 0;
	private JPasswordField pwdField;
	private JPasswordField pwdFieldConfirm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewUser frame = new NewUser();
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/** Create the frame with all the GUI fields necessary for
	 * creating a new user
	 */
	public NewUser() {
		count++;
		newUserFrame = new JFrame();
		newUserFrame.setVisible(true);
		newUserFrame.setSize(400, 412);
		
		JPanel newUserPanel = new JPanel();
		JLabel lblRegNewUser = new JLabel("Register New User");
		lblRegNewUser.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRegNewUser.setBounds(152, 32, 112, 40);
		JPanel newUserControls = new JPanel();
		newUserControls.setPreferredSize(new Dimension(300,320));
		
		JLabel lblUserID = new JLabel("User ID:");
		lblUserID.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		usrIDTxt = new JTextField();
		usrIDTxt.setEditable(false);
		usrIDTxt.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		firstNameTxt  = new JTextField();
		firstNameTxt.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lastNameTxt = new JTextField();
		lastNameTxt.setColumns(10);
		
		JLabel lblEmailId = new JLabel("Email ID:");
		lblEmailId.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		emailIdTxt = new JTextField();
		emailIdTxt.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		phoneTxt = new JTextField();
		phoneTxt.setColumns(10);
		
		pwdField = new JPasswordField();
		pwdField.setEchoChar('*');
		
		JLabel lblNewPwd = new JLabel("New Password:");
		lblNewPwd.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		pwdFieldConfirm = new JPasswordField();
		pwdFieldConfirm.setEchoChar('*');
		
		JLabel lblConfirmPwd = new JLabel("Confirm Password:");
		lblConfirmPwd.setFont(new Font("Tahoma", Font.BOLD, 11));
		

		//Mouse click event to generate a unique user id. The unique user id is generated
		//based on the first letter of the user's first name and last name and a count
		JButton userIdBtn = new JButton("Generate User ID");
		userIdBtn.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		userIdBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String firstNameChar, lastNameChar; 
				long phoneNumVal;
				generateUsrID = true;
				firstNameVal = firstNameTxt.getText();
				lastNameVal = lastNameTxt.getText();
				
				try{
					phoneVal = Long.parseLong(phoneTxt.getText().trim());
				
					
					if(!firstNameVal.isEmpty() && !lastNameVal.isEmpty()){
						firstNameChar = firstNameVal.substring(0, 1);
						lastNameChar = lastNameVal.substring(0, 1);
						phoneNumVal = phoneVal % 100;
						usrIDTxt.setText((firstNameChar + lastNameChar + count + phoneNumVal).toLowerCase());
						userIDVal = usrIDTxt.getText();
						if(!userIDVal.isEmpty()){
							submitBtn.addActionListener(new ActionListener(){
								@Override
								public void actionPerformed(ActionEvent e) {
									if(e.getActionCommand().equalsIgnoreCase("Enable")){
										submitBtn.setEnabled (true);
									}
									
								}
							});
						}
						
					}
					else{
						lblTxtFieldsChk.setForeground(Color.RED);
						lblTxtFieldsChk.setHorizontalAlignment(SwingConstants.CENTER);
						lblTxtFieldsChk.setFont(new Font("Tahoma", Font.BOLD, 11));
						lblTxtFieldsChk.setText("first name and last name fields are empty");
					}
				}
				catch(NumberFormatException ex){
					phoneValChk = false;
					lblTxtFieldsChk.setForeground(Color.RED);
					lblTxtFieldsChk.setHorizontalAlignment(SwingConstants.CENTER);
					lblTxtFieldsChk.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblTxtFieldsChk.setText("Invalid phone number");
				}
				 
			}
		});
		userIdBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		submitBtn = new JButton("Submit");
		submitBtn.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		submitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TextFieldsValidator textValidate = new TextFieldsValidator();
				
				firstNameVal = firstNameTxt.getText().trim();
				if(textValidate.validateUserName(firstNameVal) && !(firstNameVal.isEmpty())){
					firstNameTxtChk = true;
				}
				else{
					firstNameTxtChk = false;
				}
				
				lastNameVal = lastNameTxt.getText().trim();
				if(textValidate.validateUserName(lastNameVal) && !(lastNameVal.isEmpty())){
					lastNameTxtChk = true;
				}
				else{
					lastNameTxtChk = false;
				}
				
				emailIdVal = emailIdTxt.getText().trim();
				
				if(textValidate.validateEmail(emailIdVal) && !(emailIdVal.isEmpty())){
					emailIdTxtChk = true;
				}
				else{
					emailIdTxtChk = false;
				}
				
				try{
					phoneVal = Long.parseLong(phoneTxt.getText().trim());
					phoneValChk = true;
				}
				catch(NumberFormatException e){
					phoneValChk = false;
					lblTxtFieldsChk.setForeground(Color.RED);
					lblTxtFieldsChk.setHorizontalAlignment(SwingConstants.CENTER);
					lblTxtFieldsChk.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblTxtFieldsChk.setText("Invalid phone number");
				}
				
				
				newPwdVal = new String(pwdField.getPassword());
				if(!(newPwdVal.isEmpty())){
					newPwdChk = true;
				}
				else{
					newPwdChk = false;
				}
				
				confirmPwdVal = new String(pwdFieldConfirm.getPassword());
				if(!(confirmPwdVal.isEmpty())){
					confirmPwdChk = true;
				}
				else{
					confirmPwdChk = false;
				}
				
				submitBtnChk = true;
				if(NewUser.generateUsrID == true && emailIdTxtChk == true && firstNameTxtChk == true && lastNameTxtChk == true 
						&& phoneValChk == true && newPwdChk == true && confirmPwdChk== true){
					if(newPwdVal.equals(confirmPwdVal)){
						//creates an instance of the data processing class which connects to the database 
						//and executes queries based on the user request
						DataProcessing dp = new DataProcessing(userIDVal, firstNameVal, lastNameVal, emailIdVal, phoneVal, newPwdVal);
						dp.processData(submitBtnChk, false, false, false, false, false, false, false, DisplayData.someRowSelectedChk, false);
						newUserFrame.dispose();
						DialogInfo dialog = new DialogInfo(userIDVal);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					}
					else{
						lblTxtFieldsChk.setForeground(Color.RED);
						lblTxtFieldsChk.setHorizontalAlignment(SwingConstants.CENTER);
						lblTxtFieldsChk.setFont(new Font("Tahoma", Font.BOLD, 11));
						lblTxtFieldsChk.setText("Passwords doesnt match");
					}
				}
				else if(emailIdTxtChk == false){
					lblTxtFieldsChk.setForeground(Color.RED);
					lblTxtFieldsChk.setHorizontalAlignment(SwingConstants.CENTER);
					lblTxtFieldsChk.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblTxtFieldsChk.setText("Invalid email id");
				}
				else if(firstNameTxtChk == false){
					lblTxtFieldsChk.setForeground(Color.RED);
					lblTxtFieldsChk.setHorizontalAlignment(SwingConstants.CENTER);
					lblTxtFieldsChk.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblTxtFieldsChk.setText("Invalid first name");
				}
				else if(lastNameTxtChk == false){
					lblTxtFieldsChk.setForeground(Color.RED);
					lblTxtFieldsChk.setHorizontalAlignment(SwingConstants.CENTER);
					lblTxtFieldsChk.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblTxtFieldsChk.setText("Invalid last name");
				}
				else if(phoneValChk == false){
					lblTxtFieldsChk.setForeground(Color.RED);
					lblTxtFieldsChk.setHorizontalAlignment(SwingConstants.CENTER);
					lblTxtFieldsChk.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblTxtFieldsChk.setText("phone No is empty");
				}
				else if(NewUser.generateUsrID == false){
					lblTxtFieldsChk.setForeground(Color.RED);
					lblTxtFieldsChk.setHorizontalAlignment(SwingConstants.CENTER);
					lblTxtFieldsChk.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblTxtFieldsChk.setText("user id field is empty");
				}
				else if(newPwdChk == false){
					lblTxtFieldsChk.setForeground(Color.RED);
					lblTxtFieldsChk.setHorizontalAlignment(SwingConstants.CENTER);
					lblTxtFieldsChk.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblTxtFieldsChk.setText("password is empty");
				}
				else if(confirmPwdChk == false){
					lblTxtFieldsChk.setForeground(Color.RED);
					lblTxtFieldsChk.setHorizontalAlignment(SwingConstants.CENTER);
					lblTxtFieldsChk.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblTxtFieldsChk.setText("password is empty");
				}
				else{
					lblTxtFieldsChk.setForeground(Color.RED);
					lblTxtFieldsChk.setHorizontalAlignment(SwingConstants.CENTER);
					lblTxtFieldsChk.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblTxtFieldsChk.setText("cannot process");
				}
				
			}
		});
		
		
		submitBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		cancelBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				newUserFrame.dispose();
			}
		});
		cancelBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		lblTxtFieldsChk = new JLabel("");
		
		JLabel label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel label_2 = new JLabel("*");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel label_3 = new JLabel("*");
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel label_4 = new JLabel("*");
		label_4.setForeground(Color.RED);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel label_5 = new JLabel("*");
		label_5.setForeground(Color.RED);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel label_6 = new JLabel("*");
		label_6.setForeground(Color.RED);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		
		GroupLayout gl_newUserControls = new GroupLayout(newUserControls);
		gl_newUserControls.setHorizontalGroup(
			gl_newUserControls.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_newUserControls.createSequentialGroup()
					.addGroup(gl_newUserControls.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_newUserControls.createSequentialGroup()
							.addGap(83)
							.addComponent(userIdBtn, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_newUserControls.createSequentialGroup()
							.addGap(60)
							.addComponent(submitBtn, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_newUserControls.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_newUserControls.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_newUserControls.createSequentialGroup()
									.addComponent(lblEmailId, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
									.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 9, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED))
								.addGroup(gl_newUserControls.createSequentialGroup()
									.addComponent(lblPhone, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
									.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 9, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED))
								.addComponent(lblNewPwd, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_newUserControls.createSequentialGroup()
									.addComponent(lblConfirmPwd, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 9, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED))
								.addGroup(gl_newUserControls.createSequentialGroup()
									.addComponent(lblLastName, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
									.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 9, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED))
								.addGroup(gl_newUserControls.createSequentialGroup()
									.addComponent(lblFirstName, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
									.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 9, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED))
								.addGroup(gl_newUserControls.createSequentialGroup()
									.addComponent(lblUserID, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
									.addGroup(gl_newUserControls.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_newUserControls.createSequentialGroup()
											.addComponent(label, GroupLayout.PREFERRED_SIZE, 9, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED))
										.addGroup(gl_newUserControls.createSequentialGroup()
											.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 9, GroupLayout.PREFERRED_SIZE)
											.addGap(1)))))
							.addGap(9)
							.addGroup(gl_newUserControls.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(emailIdTxt, Alignment.LEADING)
								.addComponent(phoneTxt, Alignment.LEADING)
								.addComponent(pwdField, Alignment.LEADING)
								.addComponent(pwdFieldConfirm, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
								.addComponent(usrIDTxt, Alignment.LEADING)
								.addComponent(firstNameTxt, Alignment.LEADING)
								.addComponent(lastNameTxt, Alignment.LEADING))))
					.addGap(24))
				.addGroup(gl_newUserControls.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTxtFieldsChk, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_newUserControls.setVerticalGroup(
			gl_newUserControls.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_newUserControls.createSequentialGroup()
					.addGap(2)
					.addComponent(lblTxtFieldsChk, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addGroup(gl_newUserControls.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_newUserControls.createSequentialGroup()
							.addGroup(gl_newUserControls.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUserID, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(usrIDTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(13))
						.addGroup(gl_newUserControls.createSequentialGroup()
							.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGroup(gl_newUserControls.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFirstName, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(firstNameTxt, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addGroup(gl_newUserControls.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_newUserControls.createSequentialGroup()
							.addGroup(gl_newUserControls.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblLastName, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(lastNameTxt, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
							.addGap(11))
						.addGroup(gl_newUserControls.createSequentialGroup()
							.addComponent(label_3)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGroup(gl_newUserControls.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_newUserControls.createSequentialGroup()
							.addGroup(gl_newUserControls.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEmailId, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_newUserControls.createParallelGroup(Alignment.BASELINE)
									.addComponent(emailIdTxt, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
									.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_newUserControls.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_newUserControls.createSequentialGroup()
									.addGap(10)
									.addComponent(lblPhone, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_newUserControls.createSequentialGroup()
									.addGap(11)
									.addGroup(gl_newUserControls.createParallelGroup(Alignment.BASELINE)
										.addComponent(phoneTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_newUserControls.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewPwd, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_newUserControls.createSequentialGroup()
									.addGap(1)
									.addComponent(pwdField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(11))
						.addGroup(gl_newUserControls.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGroup(gl_newUserControls.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_newUserControls.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblConfirmPwd, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addComponent(pwdFieldConfirm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_newUserControls.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
					.addGap(11)
					.addComponent(userIdBtn)
					.addGap(11)
					.addGroup(gl_newUserControls.createParallelGroup(Alignment.LEADING)
						.addComponent(submitBtn)
						.addComponent(cancelBtn)))
		);
		newUserControls.setLayout(gl_newUserControls);
		newUserFrame.setContentPane(newUserPanel);
		GroupLayout gl_newUserPanel = new GroupLayout(newUserPanel);
		gl_newUserPanel.setHorizontalGroup(
			gl_newUserPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_newUserPanel.createSequentialGroup()
					.addGroup(gl_newUserPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_newUserPanel.createSequentialGroup()
							.addGap(108)
							.addComponent(lblRegNewUser))
						.addGroup(gl_newUserPanel.createSequentialGroup()
							.addGap(42)
							.addComponent(newUserControls, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(33, Short.MAX_VALUE))
		);
		gl_newUserPanel.setVerticalGroup(
			gl_newUserPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_newUserPanel.createSequentialGroup()
					.addGap(5)
					.addComponent(lblRegNewUser)
					.addGap(5)
					.addComponent(newUserControls, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
					.addContainerGap())
		);
		newUserPanel.setLayout(gl_newUserPanel);
		
	}
	public void registerNewUser(){
		
	}
}
	


