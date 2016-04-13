package com.my.friends;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;

import java.awt.event.MouseMotionAdapter;

public class SignOut extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;
	private JPanel buttonPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SignOut dialog = new SignOut();
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
	public SignOut() {
		setTitle("Sign Out");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 150);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblNewLabel = new JLabel("Are you sure you want to SignOut? \n");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		JLabel lblThisWillClose = new JLabel("\nThis will close the Application");
		lblThisWillClose.setHorizontalAlignment(SwingConstants.CENTER);
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseMotionListener(new MouseMotionAdapter() {
					@Override
					public void mouseMoved(MouseEvent e) {
						e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
					}
				});
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						System.exit(0);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseMotionListener(new MouseMotionAdapter() {
					@Override
					public void mouseMoved(MouseEvent e) {
						e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
					}
				});
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(85, Short.MAX_VALUE)
					.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(42, Short.MAX_VALUE)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
					.addGap(35))
				.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
					.addGap(67)
					.addComponent(lblThisWillClose)
					.addContainerGap(79, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblThisWillClose)
					.addGap(18)
					.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, 32, Short.MAX_VALUE)
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
	}

}
