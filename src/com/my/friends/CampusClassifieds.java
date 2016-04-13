package com.my.friends;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

import java.awt.SystemColor;

import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JTextPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.Font;

import javax.swing.JScrollPane;

import java.awt.Component;
import java.io.File;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JSeparator;

public class CampusClassifieds extends JFrame {

	private JPanel contentPane;
	public boolean create_clicked;
	public boolean buy_flag, sell_flag, rental_flag;
	public static boolean delete_alert_flag = false;
	public boolean delete_flag;

	public JPanel displayPanel;

	private JTable table;
	public JPanel panelMain;
	public JScrollPane jscp;

	public JLabel lblCategory, lblPropType, lblPrice, lblBeds, lblBath,
			lblLaundry, lblGym, lblPool, lblParking, lblPets, lblFurnished,
			lblAddress, lblCity, lblZipCode, lblPhoneNo;

	public JLabel lblNeedBy, lblProdTitle, lblDesc;
	public JLabel lblManifacturer, lblModel, lblDimensions, lblCondition;

	public JTextArea txtDescription;
	public static boolean rdfchk = false;

	JLabel userWelcome = new JLabel("Welcome User");
	JLabel displayTitle = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CampusClassifieds frame = new CampusClassifieds();
					frame.setVisible(true);
					frame.setResizable(false);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void createClicked() {
		BuyForm buy = new BuyForm();
		SellForm sell = new SellForm();
		RentalForm rental = new RentalForm();
		if (create_clicked) {
			if (buy_flag) {
				buy.setVisible(true);
			} else if (sell_flag) {
				sell.setVisible(true);
			} else if (rental_flag) {
				rental.setVisible(true);
			}
		}

	}

	public void loadTable() {
		panelMain.removeAll();
		displayPanel.removeAll();
		displayPanel.repaint();
		displayPanel.revalidate();
		DataProcessing dp = new DataProcessing();
		if (buy_flag) {
			dp.processData(false, false, false, false, false, buy_flag, false,
					false, DisplayData.someRowSelectedChk, delete_flag);

		}
		if (sell_flag) {
			dp.processData(false, false, false, false, false, false, sell_flag,
					false, DisplayData.someRowSelectedChk, delete_flag);

		}
		if (rental_flag) {
			dp.processData(false, false, false, false, false, false, false,
					rental_flag, DisplayData.someRowSelectedChk, delete_flag);
		}
		 DisplayData.someRowSelectedChk = false;

		panelMain.setLayout(new GridLayout(1, 1));
		panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.PAGE_AXIS));
		panelMain.add(DisplayData.scrollpane);
		panelMain.repaint();
		panelMain.revalidate();

		// DisplayData display = new DisplayData();
		// display.getRowSelected();
	}

	/**
	 * Create the frame.
	 */
	public CampusClassifieds() {
		// Initialization of variables
		rental_flag = false;
		buy_flag = false;
		sell_flag = false;
		create_clicked = false;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		panelMain = new JPanel();
		panelMain.setVisible(true);
		//panelMain.setBackground(Color.LIGHT_GRAY);
		JLabel lblWelcome = new JLabel("");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setIcon(new ImageIcon("/Users/kavyagautam/JAVA/CampusClassifieds/src/com/my/friends/WelcomeCC.jpg"));
		
		table = new JTable();
		displayPanel = new JPanel();

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("/Users/kavyagautam/JAVA/CampusClassifieds/src/com/my/friends/CampusClassifieds.jpg"));

		JButton btnCreate = new JButton("Create New Post");
		btnCreate.setToolTipText("Click to create a new Post");
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				create_clicked = true;
				createClicked();
			}
		});
		btnCreate.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});

		JLabel lblBuy = new JLabel("WANTED LIST");
		lblBuy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DisplayData.someRowSelectedChk = false;
				delete_flag = false;
				buy_flag = true;
				displayTitle.setText("View posts for Wanted Items");
				sell_flag = false;
				rental_flag = false;
				loadTable();
				DisplayData dd = new DisplayData(buy_flag, sell_flag,
						rental_flag);
			}
		});
		lblBuy.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		lblBuy.setToolTipText("View the list of Wanted Items\n");
		lblBuy.setBackground(UIManager.getColor("List.selectionBackground"));
		lblBuy.setIcon(new ImageIcon(
				"/Users/kavyagautam/JAVA/CampusClassifieds/src/com/my/friends/buy.jpg"));
		lblBuy.setForeground(UIManager.getColor("Label.foreground"));

		JLabel lblSell = new JLabel("FOR SALE");
		lblSell.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DisplayData.someRowSelectedChk = false;
				delete_flag = false;
				sell_flag = true;
				displayTitle.setText("View posts for Items on Sale");
				buy_flag = false;
				rental_flag = false;
				loadTable();
				DisplayData dd = new DisplayData(buy_flag, sell_flag,
						rental_flag);

			}
		});
		lblSell.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		lblSell.setIcon(new ImageIcon(
				"/Users/kavyagautam/JAVA/CampusClassifieds/src/com/my/friends/sale.jpg"));
		lblSell.setToolTipText("View the list of Wanted Items\n");
		lblSell.setForeground(Color.BLACK);

		JLabel lblRental = new JLabel(" ROOMS AND RENTALS");
		lblRental.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DisplayData.someRowSelectedChk = false;
				delete_flag = false;
				rental_flag = true;
				displayTitle.setText("View Rental posts");
				sell_flag = false;
				buy_flag = false;
				loadTable();
				DisplayData dd = new DisplayData(buy_flag, sell_flag,
						rental_flag);
			}
		});
		lblRental.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		lblRental
				.setIcon(new ImageIcon(
						"/Users/kavyagautam/JAVA/CampusClassifieds/src/com/my/friends/Rental.jpg"));
		lblRental.setToolTipText("View the list of Wanted Items\n");
		lblRental.setForeground(Color.BLACK);

		JLabel Signingout = new JLabel("Sign Out");
		Signingout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SignOut signout = new SignOut();
				signout.setVisible(true);
			}
		});
		Signingout.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		Signingout
				.setForeground(UIManager.getColor("List.selectionBackground"));

		displayTitle.setForeground(new Color(0, 0, 255));
		displayTitle.setBackground(new Color(240, 230, 140));
		displayTitle.setFont(new Font("Lucida Grande", Font.ITALIC, 14));

		JButton btnNewButton = new JButton("View");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (DisplayData.someRowSelectedChk == true) {
					if (rental_flag == true) {
						displayPanel.setBorder(new LineBorder(Color.GRAY));
						displayPanel.removeAll();
						JScrollPane scrollDesc;
						JButton btnSendEmail = new JButton("Email");
						btnSendEmail.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								SwingEmailSender main = new SwingEmailSender();
								main.mainMethod();
							}
						});
						btnSendEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
						btnSendEmail.setBounds(525, 215, 80, 25);

						JLabel lblheadPostDet = new JLabel("Post Details");
						lblheadPostDet.setBounds(200, 5, 100, 15);
						lblheadPostDet
								.setFont(new Font("Tahoma", Font.BOLD, 12));
						lblheadPostDet.setForeground(Color.BLUE);

						JLabel lblheadAmenities = new JLabel("Amenities");
						lblheadAmenities.setBounds(530, 5, 100, 15);
						lblheadAmenities.setFont(new Font("Tahoma", Font.BOLD,
								12));
						lblheadAmenities.setForeground(Color.BLUE);

						JLabel lblheadCategory = new JLabel("Category:");
						lblCategory = new JLabel("");
						lblheadCategory.setBounds(5, 35, 100, 15);
						lblCategory.setText(DataProcessing.rentalCategory);
						lblCategory.setBounds(100, 35, 100, 15);

						JLabel lblheadPropType = new JLabel("Property Type:");
						lblPropType = new JLabel("");
						lblheadPropType.setBounds(250, 35, 100, 15);
						lblPropType.setText(DataProcessing.rentalPropType);
						lblPropType.setBounds(350, 35, 150, 15);

						JLabel lblheadLaundry = new JLabel("Laundry:");
						lblLaundry = new JLabel("");
						lblheadLaundry.setBounds(510, 35, 100, 15);
						if (DataProcessing.rentalLaundry.equals("Y")) {
							lblLaundry.setText("Yes");
						} else {
							lblLaundry.setText("No");
						}
						lblLaundry.setBounds(600, 35, 150, 15);

						JLabel lblheadPrice = new JLabel("Price:");
						lblPrice = new JLabel("");
						lblheadPrice.setBounds(5, 65, 100, 15);
						lblPrice.setText(Double
								.toString(DataProcessing.rentalPrice));
						lblPrice.setBounds(100, 65, 100, 15);

						JLabel lblheadPhoneNo = new JLabel("Phone #:");
						lblPhoneNo = new JLabel("");
						lblheadPhoneNo.setBounds(250, 65, 100, 15);
						if (DataProcessing.rentalPhNo == 0) {
							lblPhoneNo.setText("Not Available");
						} else {
							lblPhoneNo.setText(Long
									.toString(DataProcessing.rentalPhNo));
						}
						lblPhoneNo.setBounds(350, 65, 150, 15);

						JLabel lblheadGym = new JLabel("Gym:");
						lblGym = new JLabel("");
						lblheadGym.setBounds(510, 65, 100, 15);
						if (DataProcessing.rentalGym.equals("Y")) {
							lblGym.setText("Yes");
						} else {
							lblGym.setText("No");
						}
						lblGym.setBounds(600, 65, 150, 15);

						JLabel lblheadBeds = new JLabel("# Beds:");
						lblBeds = new JLabel("");
						lblheadBeds.setBounds(5, 95, 100, 15);
						if (DataProcessing.rBeds == 0) {
							lblBeds.setText("Not Available");
						} else {
							lblBeds.setText(Integer
									.toString(DataProcessing.rBeds));
						}
						lblBeds.setBounds(100, 95, 100, 15);

						JLabel lblheadBath = new JLabel("# Bath:");
						lblBath = new JLabel("");
						lblheadBath.setBounds(250, 95, 100, 15);
						if (DataProcessing.rbath == 0) {
							lblBath.setText("Not Available");
						} else {
							lblBath.setText(Double
									.toString(DataProcessing.rbath));
						}
						lblBath.setBounds(350, 95, 150, 15);

						JLabel lblheadPool = new JLabel("Pool:");
						lblPool = new JLabel("");
						lblheadPool.setBounds(510, 95, 100, 15);
						if (DataProcessing.rentalPool.equals("Y")) {
							lblPool.setText("Yes");
						} else {
							lblPool.setText("No");
						}
						lblPool.setBounds(600, 95, 150, 15);

						JLabel lblheadCity = new JLabel("City:");
						lblCity = new JLabel("");
						lblheadCity.setBounds(5, 125, 100, 15);
						lblCity.setText(DataProcessing.rentalCity);
						lblCity.setBounds(100, 125, 100, 15);

						JLabel lblheadZipCode = new JLabel("Zip Code:");
						lblZipCode = new JLabel("");
						lblheadZipCode.setBounds(250, 125, 100, 15);
						lblZipCode.setText(Integer
								.toString(DataProcessing.rentalZipCode));
						lblZipCode.setBounds(350, 125, 150, 15);

						JLabel lblheadPets = new JLabel("Pets Allowed:");
						lblPets = new JLabel("");
						lblheadPets.setBounds(510, 125, 100, 15);
						if (DataProcessing.rentalPets.equals("Y")) {
							lblPets.setText("Yes");
						} else {
							lblPets.setText("No");
						}
						lblPets.setBounds(600, 125, 150, 15);

						JLabel lblheadAddress = new JLabel("Street Address:");
						lblAddress = new JLabel("");
						lblheadAddress.setBounds(5, 155, 100, 15);
						if (DataProcessing.rentalAddress == null) {
							lblAddress.setText("Not Available");
						} else {
							lblAddress.setText(DataProcessing.rentalAddress);
						}
						lblAddress.setBounds(100, 155, 480, 15);

						JLabel lblheadParking = new JLabel("Parking:");
						lblParking = new JLabel("");
						lblheadParking.setBounds(510, 155, 100, 15);
						if (DataProcessing.rentalParking.equals("Y")) {
							lblParking.setText("Yes");
						} else {
							lblParking.setText("No");
						}
						lblParking.setBounds(600, 155, 150, 15);

						JLabel lblheadDescription = new JLabel("Description:");
						txtDescription = new JTextArea(5, 20);
						lblheadDescription.setBounds(5, 185, 100, 55);
						if (DataProcessing.rentalDescription == null) {
							txtDescription.append("Not Available");
						} else {
							txtDescription
									.append(DataProcessing.rentalDescription);
						}
						// txtDescription.setBounds(100, 185, 480, 70);
						scrollDesc = new JScrollPane(txtDescription,
								JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
								JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
						scrollDesc.setBounds(100, 185, 400, 55);
						scrollDesc.setVisible(true);
						txtDescription.setEditable(false);
						txtDescription.setLineWrap(true);

						JLabel lblheadFurnished = new JLabel("Furnished:");
						lblFurnished = new JLabel("");
						lblheadFurnished.setBounds(510, 185, 100, 15);
						if (DataProcessing.rentalFurnished.equals("Y")) {
							lblFurnished.setText("Yes");
						} else {
							lblFurnished.setText("No");
						}
						lblFurnished.setBounds(600, 185, 150, 15);

						JLabel lblPostID = new JLabel("");
						lblPostID.setText("This Post is created by: "
								+ GetUserID.OwnerFirstName + " "
								+ GetUserID.OwnerLastName);
						lblPostID.setBounds(5, 243, 300, 15);
						lblPostID.setForeground(Color.BLUE);

						displayPanel.add(lblheadCategory);
						displayPanel.add(lblCategory);
						displayPanel.add(lblheadPropType);
						displayPanel.add(lblPropType);
						displayPanel.add(lblheadLaundry);
						displayPanel.add(lblLaundry);

						displayPanel.add(lblheadPrice);
						displayPanel.add(lblPrice);
						displayPanel.add(lblheadPhoneNo);
						displayPanel.add(lblPhoneNo);
						displayPanel.add(lblheadGym);
						displayPanel.add(lblGym);

						displayPanel.add(lblheadBeds);
						displayPanel.add(lblBeds);
						displayPanel.add(lblheadBath);
						displayPanel.add(lblBath);
						displayPanel.add(lblheadPool);
						displayPanel.add(lblPool);

						displayPanel.add(lblheadCity);
						displayPanel.add(lblCity);
						displayPanel.add(lblheadZipCode);
						displayPanel.add(lblZipCode);
						displayPanel.add(lblheadPets);
						displayPanel.add(lblPets);

						displayPanel.add(lblheadAddress);
						displayPanel.add(lblAddress);
						displayPanel.add(lblheadParking);
						displayPanel.add(lblParking);

						displayPanel.add(lblheadDescription);
						displayPanel.add(scrollDesc);
						displayPanel.add(lblheadFurnished);
						displayPanel.add(lblFurnished);

						displayPanel.add(lblheadPostDet);
						displayPanel.add(lblheadAmenities);
						displayPanel.add(btnSendEmail);
						displayPanel.add(lblPostID);

						displayPanel.setLayout(null);
						displayPanel.setVisible(true);
						displayPanel.repaint();
						displayPanel.validate();
					} else if (sell_flag == true) {

						// ---selll
						// ------------------------------------------------------
						// ------------------------------------------------------
						// ------------------------------------------------------

						displayPanel.setBorder(new LineBorder(Color.GRAY));
						displayPanel.removeAll();
						displayPanel.repaint();
						JScrollPane scrollDesc;
						JButton btnSendEmail = new JButton("Email");
						btnSendEmail.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								SwingEmailSender main = new SwingEmailSender();
								main.mainMethod();
							}
						});
						btnSendEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
						btnSendEmail.setBounds(525, 185, 80, 25);

						JLabel lblheadPostDet = new JLabel("Post Details");
						lblheadPostDet.setBounds(310, 5, 100, 15);
						lblheadPostDet
								.setFont(new Font("Tahoma", Font.BOLD, 12));
						lblheadPostDet.setForeground(Color.BLUE);

						JLabel lblheadCategory = new JLabel("Category:");
						lblCategory = new JLabel("");
						lblheadCategory.setBounds(5, 35, 100, 15);
						lblCategory.setText(DataProcessing.sellCategory);
						lblCategory.setBounds(110, 35, 400, 15);

						JLabel lblheadProdTitle = new JLabel("Product Title:");
						lblProdTitle = new JLabel("");
						lblheadProdTitle.setBounds(410, 35, 100, 15);
						lblProdTitle.setText(DataProcessing.sellProductName);
						lblProdTitle.setBounds(510, 35, 150, 15);

						JLabel lblheadPrice = new JLabel("Price:");
						lblPrice = new JLabel("");
						lblheadPrice.setBounds(5, 65, 100, 15);
						lblPrice.setText(Double
								.toString(DataProcessing.sellPrice));
						lblPrice.setBounds(110, 65, 100, 15);

						JLabel lblheadPhoneNo = new JLabel("Phone #:");
						lblPhoneNo = new JLabel("");
						lblheadPhoneNo.setBounds(410, 65, 100, 15);
						if (DataProcessing.sellPhoneNo == null) {
							lblPhoneNo.setText("Not Available");
						} else {
							lblPhoneNo.setText(DataProcessing.sellPhoneNo);
						}
						lblPhoneNo.setBounds(510, 65, 150, 15);

						JLabel lblheadManifacturer = new JLabel("Manufacturer:");
						lblManifacturer = new JLabel("");
						lblheadManifacturer.setBounds(5, 95, 300, 15);
						lblManifacturer.setText(DataProcessing.sellMake);
						lblManifacturer.setBounds(110, 95, 100, 15);

						JLabel lblheadModel = new JLabel("Model No:");
						lblModel = new JLabel("");
						lblheadModel.setBounds(410, 95, 300, 15);
						if (DataProcessing.sellModel == null) {
							lblModel.setText("Not Available");
						} else {
							lblModel.setText(DataProcessing.sellModel);
						}
						lblModel.setBounds(510, 95, 150, 15);

						JLabel lblheadCondition = new JLabel("Condition:");
						lblCondition = new JLabel("");
						lblheadCondition.setBounds(410, 125, 100, 15);
						lblCondition.setText(DataProcessing.sellCondition);
						lblCondition.setBounds(510, 125, 150, 15);

						JLabel lblheadDimensions = new JLabel("Dimensions");
						lblDimensions = new JLabel("");
						lblheadDimensions.setBounds(5, 125, 100, 15);
						if (DataProcessing.sellSize == null) {
							lblDimensions.setText("Not Available");
						} else {
							lblDimensions.setText(DataProcessing.sellSize);
						}
						lblDimensions.setBounds(110, 125, 100, 15);

						JLabel lblheadDescription = new JLabel("Description:");
						txtDescription = new JTextArea(5, 20);
						lblheadDescription.setBounds(5, 165, 100, 55);
						if (DataProcessing.sellDescription == null) {
							txtDescription.append("Not Available");
						} else {
							txtDescription
									.append(DataProcessing.sellDescription);
						}
						// txtDescription.setBounds(100, 185, 480, 70);
						scrollDesc = new JScrollPane(txtDescription,
								JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
								JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
						scrollDesc.setBounds(100, 165, 400, 55);
						scrollDesc.setVisible(true);
						txtDescription.setEditable(false);
						txtDescription.setLineWrap(true);

						JLabel lblPostID = new JLabel("");
						lblPostID.setText("This Post is created by: "
								+ GetUserID.OwnerFirstName + " "
								+ GetUserID.OwnerLastName);
						lblPostID.setBounds(5, 243, 300, 15);
						lblPostID.setForeground(Color.BLUE);

						displayPanel.add(lblheadCategory);
						displayPanel.add(lblCategory);
						displayPanel.add(lblheadProdTitle);
						displayPanel.add(lblProdTitle);

						displayPanel.add(lblheadPrice);
						displayPanel.add(lblPrice);
						displayPanel.add(lblheadPhoneNo);
						displayPanel.add(lblPhoneNo);

						displayPanel.add(lblheadManifacturer);
						displayPanel.add(lblManifacturer);
						displayPanel.add(lblheadModel);
						displayPanel.add(lblModel);

						displayPanel.add(lblheadDimensions);
						displayPanel.add(lblDimensions);

						displayPanel.add(lblheadCondition);
						displayPanel.add(lblCondition);
						displayPanel.add(lblheadDescription);
						displayPanel.add(scrollDesc);

						displayPanel.add(lblheadPostDet);
						displayPanel.add(btnSendEmail);
						displayPanel.add(lblPostID);

						displayPanel.setLayout(null);
						displayPanel.setVisible(true);
						displayPanel.repaint();
						displayPanel.validate();

						// ends----------------------------------------------
						// ------------------------------------------------------
						// ------------------------------------------------------
						// ------------------------------------------------------

					} else if (buy_flag == true) {
						displayPanel.setBorder(new LineBorder(Color.GRAY));
						displayPanel.removeAll();

						JScrollPane scrollDesc;
						JButton btnSendEmail = new JButton("Email");
						btnSendEmail.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								SwingEmailSender main = new SwingEmailSender();
								main.mainMethod();
							}
						});
						btnSendEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
						btnSendEmail.setBounds(525, 195, 80, 25);

						JLabel lblheadPostDet = new JLabel("Post Details");
						lblheadPostDet.setBounds(310, 5, 100, 15);
						lblheadPostDet
								.setFont(new Font("Tahoma", Font.BOLD, 12));
						lblheadPostDet.setForeground(Color.BLUE);

						JLabel lblheadCategory = new JLabel("Category:");
						lblCategory = new JLabel("");
						lblheadCategory.setBounds(5, 45, 100, 15);
						lblCategory.setText(DataProcessing.buyCategory);
						lblCategory.setBounds(110, 45, 400, 15);

						JLabel lblheadNeedBy = new JLabel("Need By:");
						lblNeedBy = new JLabel("");
						lblheadNeedBy.setBounds(410, 45, 100, 15);
						lblNeedBy.setText(DataProcessing.buyNeedBy);
						lblNeedBy.setBounds(490, 45, 150, 15);

						JLabel lblheadPrice = new JLabel("Price Range:");
						lblPrice = new JLabel("");
						lblheadPrice.setBounds(5, 85, 100, 15);
						lblPrice.setText(Double
								.toString(DataProcessing.buyPrice));
						lblPrice.setBounds(110, 85, 100, 15);

						JLabel lblheadPhoneNo = new JLabel("Phone #:");
						lblPhoneNo = new JLabel("");
						lblheadPhoneNo.setBounds(410, 85, 100, 15);
						if (DataProcessing.buyPhoneNo == null) {
							lblPhoneNo.setText("Not Available");
						} else {
							lblPhoneNo.setText(DataProcessing.buyPhoneNo);
						}
						lblPhoneNo.setBounds(490, 85, 150, 15);

						JLabel lblheadProductTitle = new JLabel("Product Title");
						lblProdTitle = new JLabel("");
						lblheadProductTitle.setBounds(5, 125, 100, 15);
						if (DataProcessing.buyProductName == "") {
							lblProdTitle.setText("Not Available");
						} else {
							lblProdTitle.setText(DataProcessing.buyProductName);
						}
						lblProdTitle.setBounds(110, 125, 100, 15);

						JLabel lblheadDescription = new JLabel("Description:");
						txtDescription = new JTextArea(5, 20);
						lblheadDescription.setBounds(5, 175, 100, 55);
						if (DataProcessing.rentalDescription == "") {
							txtDescription.append("Not Available");
						} else {
							txtDescription
									.append(DataProcessing.buyDescription);
						}
						// txtDescription.setBounds(100, 185, 480, 70);
						scrollDesc = new JScrollPane(txtDescription,
								JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
								JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
						scrollDesc.setBounds(100, 175, 400, 55);
						scrollDesc.setVisible(true);
						txtDescription.setEditable(false);
						txtDescription.setLineWrap(true);

						JLabel lblPostID = new JLabel("");
						lblPostID.setText("This Post is created by: "
								+ GetUserID.OwnerFirstName + " "
								+ GetUserID.OwnerLastName);
						lblPostID.setBounds(5, 243, 300, 15);
						lblPostID.setForeground(Color.BLUE);

						displayPanel.add(lblheadCategory);
						displayPanel.add(lblCategory);
						displayPanel.add(lblheadNeedBy);
						displayPanel.add(lblNeedBy);

						displayPanel.add(lblheadPrice);
						displayPanel.add(lblPrice);
						displayPanel.add(lblheadPhoneNo);
						displayPanel.add(lblPhoneNo);

						displayPanel.add(lblheadProductTitle);
						displayPanel.add(lblProdTitle);

						displayPanel.add(lblheadDescription);
						displayPanel.add(scrollDesc);

						displayPanel.add(lblheadPostDet);
						displayPanel.add(btnSendEmail);
						displayPanel.add(lblPostID);

						displayPanel.setLayout(null);
						displayPanel.setVisible(true);
						displayPanel.repaint();
						displayPanel.validate();
					}

				} else {
					TableSelectDialog tsd = new TableSelectDialog();
					tsd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					tsd.setVisible(true);
				}

			}
		});
		btnNewButton.setToolTipText("Select a row to View");
		btnNewButton.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});

		JButton btnDelete = new JButton("Delete");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				delete_flag = true;
				if (delete_flag == true && DisplayData.someRowSelectedChk == true) {

					DisplayData dd = new DisplayData(buy_flag, sell_flag,
							rental_flag);
					loadTable();
					loadTable();	
				}
				else if (DisplayData.someRowSelectedChk == false
						&& delete_flag == true) {
					{
						TableSelectDialog tsd = new TableSelectDialog();
						tsd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						tsd.setVisible(true);
					}
				}

				if (delete_alert_flag) {
					delete_alert_flag = false;
					DialogInfo d = new DialogInfo();
					d.setVisible(true);
				}
			}
		});
		btnDelete.setToolTipText("Select your Post to delete");
		btnDelete.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setToolTipText("Click to refresh the Table");
		btnRefresh.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loadTable();
			}
		});

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(Color.ORANGE);
		separator.setForeground(Color.ORANGE);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.ORANGE);
		separator_1.setBackground(Color.ORANGE);

		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setForeground(Color.ORANGE);
		separator_2.setBackground(Color.ORANGE);

		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setForeground(Color.ORANGE);
		separator_3.setBackground(Color.ORANGE);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 641, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(99, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 720, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(14, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(displayPanel, GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(panelMain, GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(displayTitle, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addGap(122)
							.addComponent(btnCreate))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(lblBuy, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)
							.addGap(35)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(userWelcome, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblSell, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)
									.addGap(35)
									.addComponent(lblRental)
									.addGap(5)
									.addComponent(separator_3, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(Signingout)))))
					.addGap(24))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(userWelcome)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(Signingout)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(14)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblSell, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
								.addComponent(lblBuy, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
								.addComponent(lblRental, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
								.addComponent(separator, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
								.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
								.addComponent(separator_3, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(displayTitle, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnCreate)
							.addComponent(btnNewButton)
							.addComponent(btnDelete)
							.addComponent(btnRefresh)))
					.addGap(18)
					.addComponent(panelMain, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(displayPanel, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JLabel Team = new JLabel("Our Team");
		Team.setForeground(Color.BLACK);
		
		JLabel lblRamyaPic = new JLabel("");
		lblRamyaPic.setHorizontalAlignment(SwingConstants.CENTER);
		lblRamyaPic.setIcon(new ImageIcon("/Users/kavyagautam/JAVA/CampusClassifieds/src/com/my/friends/ramya.png"));
		
		JLabel lblSamPic = new JLabel("");
		lblSamPic.setIcon(new ImageIcon("/Users/kavyagautam/JAVA/CampusClassifieds/src/com/my/friends/sambhavi.png"));
		
		JLabel lblKavyaPic = new JLabel("");
		lblKavyaPic.setIcon(new ImageIcon("/Users/kavyagautam/JAVA/CampusClassifieds/src/com/my/friends/kavya.png"));
		
		JLabel lblRamya = new JLabel("Ramya");
		lblRamya.setForeground(Color.BLUE);
		
		JLabel lblSambhavi = new JLabel("Sambhavi");
		lblSambhavi.setForeground(Color.RED);
		
		JLabel lblKavya = new JLabel("Kavya");
		lblKavya.setForeground(Color.GREEN);
		GroupLayout gl_displayPanel = new GroupLayout(displayPanel);
		gl_displayPanel.setHorizontalGroup(
			gl_displayPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_displayPanel.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_displayPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(Team, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRamya, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSambhavi, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblKavya, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblRamyaPic, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
					.addComponent(lblSamPic, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.addGap(62)
					.addComponent(lblKavyaPic, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.addGap(88))
		);
		gl_displayPanel.setVerticalGroup(
			gl_displayPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_displayPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_displayPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRamyaPic, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSamPic, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblKavyaPic, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_displayPanel.createSequentialGroup()
							.addComponent(Team)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblRamya)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblSambhavi)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblKavya)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		displayPanel.setLayout(gl_displayPanel);
		
		
		GroupLayout gl_panelMain = new GroupLayout(panelMain);
		gl_panelMain.setHorizontalGroup(
			gl_panelMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelMain.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblWelcome, GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelMain.setVerticalGroup(
			gl_panelMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelMain.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblWelcome)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		panelMain.setLayout(gl_panelMain);
		userWelcome.setForeground(UIManager
				.getColor("List.selectionBackground"));
		userWelcome.setHorizontalAlignment(SwingConstants.TRAILING);

		contentPane.setLayout(gl_contentPane);

	}

	CampusClassifieds(int num) {

	}
}
