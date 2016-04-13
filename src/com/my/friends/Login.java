package com.my.friends;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.border.LineBorder;

import java.awt.FlowLayout;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.SystemColor;

import javax.swing.UIManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import java.awt.event.MouseMotionAdapter;


/** Class to allow a user to login into the SCU helper
 * application to buy or sell items and check rentals
 * @author Ramya Bangaluru Gopalakrishna
 */
public class Login {

	public JFrame frame;
	private JTextField usrtxtData;
	public String usrDataVal, usrPwdVal;
	public static JLabel lblTxtFieldsChklogin;
	public static boolean submitLoginChk = false;
	private JPasswordField loginPwdField;
	public CampusClassifieds mainPage;
	public static String updatedUserText;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
					window.frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	public static void updateUserText(String text){
		updatedUserText = "Welcome " + text + "!!!";
	}

	/** Initialize the contents of the frame.
	 * method to add all the GUI controls
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(730, 550);
		//frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("/Users/kavyagautam/JAVA/CampusClassifieds/src/com/my/friends/CampusClassifieds.jpg"));
		
		JPanel panelControls = new JPanel();
		panelControls.setBackground(Color.WHITE);
		panelControls.setPreferredSize(new Dimension(788,454));
		
		JPanel panelUIControls = new JPanel();
		panelUIControls.setBackground(new Color(192, 192, 192));
		panelUIControls.setForeground(Color.BLACK);
		panelUIControls.setPreferredSize(new Dimension(370, 305));
		
		JLabel labelUsrName = new JLabel("User ID:");
		labelUsrName.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblLoginPage = new JLabel("Login Page");
		lblLoginPage.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		usrtxtData = new JTextField();
		usrtxtData.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Password:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		loginPwdField = new JPasswordField();
		loginPwdField.setEchoChar('*');
		JButton btnLoginSubmit = new JButton("Submit");
		frame.getRootPane().setDefaultButton(btnLoginSubmit);
		btnLoginSubmit.requestFocus();
		btnLoginSubmit.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		btnLoginSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				submitLoginChk = true;
				usrDataVal = usrtxtData.getText().trim().toLowerCase();
				usrPwdVal = new String(loginPwdField.getPassword());
				if(!usrDataVal.isEmpty() && !usrPwdVal.isEmpty()){
					//creates an instance of the data processing class which connects to the database 
					//and executes queries based on the user request
					DataProcessing dpLogin = new DataProcessing(usrDataVal, usrPwdVal);
					dpLogin.processData(false, submitLoginChk, false, false, false, false, false, false, DisplayData.someRowSelectedChk, false);
					if(lblTxtFieldsChklogin.getText() == ""){
						frame.dispose();
						mainPage = new CampusClassifieds();
						mainPage.setVisible(true);
						mainPage.setResizable(false);
						mainPage.userWelcome.setText(updatedUserText);
					}
					
				}
			}
		});
		btnLoginSubmit.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_2 = new JLabel("Register New User");
		lblNewLabel_2.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NewUser nuser = new NewUser();
			}
		});
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		lblTxtFieldsChklogin = new JLabel("");
		lblTxtFieldsChklogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblTxtFieldsChklogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		
	
		GroupLayout gl_panelUIControls = new GroupLayout(panelUIControls);
		gl_panelUIControls.setHorizontalGroup(
			gl_panelUIControls.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelUIControls.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panelUIControls.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelUIControls.createSequentialGroup()
							.addGroup(gl_panelUIControls.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panelUIControls.createSequentialGroup()
									.addGap(152)
									.addComponent(lblLoginPage, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelUIControls.createSequentialGroup()
									.addGap(52)
									.addComponent(labelUsrName, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(usrtxtData, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelUIControls.createSequentialGroup()
									.addGap(52)
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(loginPwdField))
								.addGroup(gl_panelUIControls.createSequentialGroup()
									.addGap(174)
									.addGroup(gl_panelUIControls.createParallelGroup(Alignment.LEADING)
										.addComponent(btnLoginSubmit, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))))
							.addGap(47))
						.addGroup(Alignment.TRAILING, gl_panelUIControls.createSequentialGroup()
							.addComponent(lblTxtFieldsChklogin, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE)
							.addGap(14))))
		);
		gl_panelUIControls.setVerticalGroup(
			gl_panelUIControls.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelUIControls.createSequentialGroup()
					.addGap(32)
					.addComponent(lblLoginPage, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addGroup(gl_panelUIControls.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelUsrName, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(usrtxtData, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(25)
					.addGroup(gl_panelUIControls.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(loginPwdField, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnLoginSubmit)
					.addGap(18)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTxtFieldsChklogin, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
					.addGap(11))
		);
		panelUIControls.setLayout(gl_panelUIControls);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panelControls, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 719, Short.MAX_VALUE))
					.addContainerGap(5, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(lblNewLabel_1)
					.addGap(5)
					.addComponent(panelControls, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE))
		);
		GroupLayout gl_panelControls = new GroupLayout(panelControls);
		gl_panelControls.setHorizontalGroup(
			gl_panelControls.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelControls.createSequentialGroup()
					.addGap(169)
					.addComponent(panelUIControls, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(162, Short.MAX_VALUE))
		);
		gl_panelControls.setVerticalGroup(
			gl_panelControls.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelControls.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelUIControls, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(57, Short.MAX_VALUE))
		);
		panelControls.setLayout(gl_panelControls);
		panel.setLayout(gl_panel);
	}
}

