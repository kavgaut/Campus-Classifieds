package com.my.friends;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseMotionAdapter;


public class DialogInfo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public JLabel infoText;
	private JButton okButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogInfo dialog = new DialogInfo("");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogInfo(){
		setBounds(100, 100, 300, 90);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		infoText = new JLabel("");
		infoText.setVerticalAlignment(SwingConstants.TOP);
		infoText.setText("You cannot delete a post which is not yours");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(infoText, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(infoText, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(68, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton_1 = new JButton("OK");
				okButton_1.addMouseMotionListener(new MouseMotionAdapter() {
					@Override
					public void mouseMoved(MouseEvent e) {
						e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
					}
				});
				okButton_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
				okButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
			
				okButton_1.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton_1);
			}
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(
				gl_buttonPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addContainerGap(204, Short.MAX_VALUE)
						.addComponent(okButton_1)
						.addGap(21))
			);
			gl_buttonPane.setVerticalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addComponent(okButton_1)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
			buttonPane.setLayout(gl_buttonPane);
		}
	}
	public DialogInfo(String userId) {
		setBounds(100, 100, 395, 114);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		infoText = new JLabel("");
		infoText.setVerticalAlignment(SwingConstants.TOP);
		infoText.setText("Please login with the userid: " + userId + " and your password");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(infoText, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(infoText, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(68, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseMotionListener(new MouseMotionAdapter() {
					@Override
					public void mouseMoved(MouseEvent e) {
						e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
					}
				});
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
			
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}

