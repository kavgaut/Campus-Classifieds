package com.my.friends;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.LayoutStyle.ComponentPlacement;

public class DeleteConfirm extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel warning;
	int dummy;
	private JPanel buttonPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DeleteConfirm dialog = new DeleteConfirm();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DeleteConfirm() {
		setBounds(100, 100, 330, 130);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			warning = new JLabel("Are you sure you want to delete your Post forever?");
		}
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						CampusClassifieds fm  = new CampusClassifieds();
						fm.delete_flag = true;
						DisplayData dd = new DisplayData(fm.buy_flag, fm.sell_flag, fm.rental_flag);
						fm.loadTable();
						fm.loadTable();	
						
					}
				});
				okButton.addMouseMotionListener(new MouseMotionAdapter() {
					@Override
					public void mouseMoved(MouseEvent e) {
						e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						dispose();
					}
				});
				cancelButton.addMouseMotionListener(new MouseMotionAdapter() {
					@Override
					public void mouseMoved(MouseEvent e) {
						e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE)
						.addComponent(warning))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(warning, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
	}

}
