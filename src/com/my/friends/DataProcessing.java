package com.my.friends;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingConstants;

/**
 * Data processing class that connects to the database and executes all kind of
 * queries based on the user request which is manipulated by using flag
 * variables
 */
public class DataProcessing {
	private String usrID, usrPassword, usrFName, usrLName, usrEmailId,
			rCategory, rPropType, rDescription, rAddress, rCity, sCategory,
			sProdTitle, sCondition, sMake, sModel, sSize, sDescription,
			sContactNo;
	private int postId, rNumBeds, rZipCode;
	private double rPrice, rNumBath, sPrice;
	private long usrPhoneNo;
	private boolean resultFlag;
	private char rLaundry, rGym, rPool, rPets, rParking, rFurnished;
	CampusClassifieds mainPage = new CampusClassifieds();

	public String AdOwnerId;
	public String AdOwnerFirstName, AdOwnerLastName, AdOwnerEmailId;

	private String category;
	private String needBy;
	private double priceRange;
	private String prodTitle;
	private String description;
	private String phoneNumber;

	public static String rentalCategory, rentalPropType, rentalAddress,
			rentalCity, rentalDescription, rentalLaundry, rentalGym,
			rentalPool, rentalParking, rentalFurnished, rentalPets;
	public static int rBeds, rentalZipCode;
	public static double rbath, rentalPrice;
	public static long rentalPhNo;

	// view button variable declaration for buy & sell
	public static String buyCategory, buyDescription, buyNeedBy,
			buyProductName, buyPhoneNo;
	public static double buyPrice;
	public static String sellCategory, sellProductName, sellCondition,
			sellMake, sellModel, sellSize, sellPhoneNo, sellDescription;
	public static double sellPrice;

	/**
	 * constructor to set the values of userid and password from the Login form
	 * 
	 * @param uId
	 * @param uPassword
	 */
	DataProcessing(String uId, String uPassword) {
		resultFlag = false;
		this.usrID = uId;
		this.usrPassword = uPassword;
	}

	// change starts
	DataProcessing() {

	}

	// change ends

	/**
	 * constructor to set the values of userid, user firstname and lastname,
	 * emailid, phone number and password from the new user registration form
	 * 
	 * @param uId
	 * @param uFirstName
	 * @param uLastName
	 * @param uEmailId
	 * @param uPhone
	 * @param uPassword
	 */
	DataProcessing(String uId, String uFirstName, String uLastName,
			String uEmailId, long uPhone, String uPassword) {
		this.usrID = uId;
		this.usrFName = uFirstName;
		this.usrLName = uLastName;
		this.usrEmailId = uEmailId;
		this.usrPhoneNo = uPhone;
		this.usrPassword = uPassword;
	}

	DataProcessing(int buyId, String userId, String catEntered,
			String needByEntered, double priceEntered, String prodTitleEntered,
			String phoneNumber, String descEntered) {
		this.postId = buyId;
		this.usrID = userId;
		this.category = catEntered;
		this.needBy = needByEntered;
		this.priceRange = priceEntered;
		this.prodTitle = prodTitleEntered;
		this.description = descEntered;
		this.phoneNumber = phoneNumber;
	}

	DataProcessing(int reID, String uID, String category, String propertyType,
			double price, int beds, double bath, char laundry, char gym,
			char pool, char pets, char parking, char furnished, long contact,
			String description, String address, String city, int zipCode) {
		this.postId = reID;
		this.usrID = uID;
		this.rCategory = category;
		this.rPropType = propertyType;
		this.rPrice = price;
		this.rNumBeds = beds;
		this.rNumBath = bath;
		this.rLaundry = laundry;
		this.rGym = gym;
		this.rPool = pool;
		this.rPets = pets;
		this.rParking = parking;
		this.rFurnished = furnished;
		this.usrPhoneNo = contact;
		this.rDescription = description;
		this.rAddress = address;
		this.rCity = city;
		this.rZipCode = zipCode;
	}

	// change starts
	DataProcessing(int sid, String uid, String category, String prodTitle,
			double price, String condition, String make, String model,
			String size, String contactNo, String description) {
		this.postId = sid;
		this.usrID = uid;
		this.sCategory = category;
		this.sProdTitle = prodTitle;
		this.sCondition = condition;
		this.sMake = make;
		this.sModel = model;
		this.sSize = size;
		this.sDescription = description;
		this.sPrice = price;
		this.sContactNo = contactNo;
	}

	// change ends

	/**
	 * method to execute queries
	 * 
	 * @param submitChk
	 * @param subLoginChk
	 */
	public void processData(boolean submitChk, boolean subLoginChk,
			boolean buyCreateChk, boolean rentalCreateChk,
			boolean sellCreateChk, boolean buy_flag, boolean sell_flag,
			boolean rental_flag, boolean someRowSelectedChk,
			boolean delete_button_flag) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException ex) {
			System.out.println("Where is your Oracle JDBC Driver?");
			ex.printStackTrace();
			return;
		}

		System.out.println("Oracle JDBC Driver Registered!");
		Connection connection1 = null;
		try {
			connection1 = DriverManager.getConnection(
					"jdbc:oracle:thin:@//localhost:1521/orcl", "hr", "hr");
			if (connection1 != null) {
				System.out
						.println("You made it, take control your database now!");
			} else {
				System.out.println("Failed to make connection!");
			}

			// To insert the user data after the user registers
			if (submitChk) {
				Statement newUsrStatement = null;
				newUsrStatement = connection1.createStatement();
				String query;
				query = "INSERT INTO NewUser VALUES('" + usrID + "','"
						+ usrFName + "','" + usrLName + "','" + usrEmailId
						+ "','" + usrPhoneNo + "','" + usrPassword + "')";
				newUsrStatement.executeUpdate(query);
			}

			// To Login by checking if the user data exists in the database or
			// not
			// Login submit button checking
			if (subLoginChk) {
				PreparedStatement LoginStatement = null;
				String query;
				query = "select UserId, FirstName, UserPassword, EMAILID from NewUser where UserId = ?";
				connection1.setAutoCommit(false);
				LoginStatement = connection1.prepareStatement(query);
				LoginStatement.setString(1, usrID);
				ResultSet rs = LoginStatement.executeQuery();

				while (rs.next()) {
					resultFlag = true;
					String usrId = rs.getString(1);
					String firstName = rs.getString(2);
					String pwd = rs.getString(3);
					String emailId = rs.getString(4);
					if (usrId.equals(usrID)) {
						if (pwd.equals(usrPassword)) {
							// create the main page instance here
							GetUserID guid = new GetUserID();
							Login.lblTxtFieldsChklogin.setText("");
							Login.updateUserText(firstName);
							guid.setUsrID(usrId);
							guid.setCurrentUserEmailId(emailId);
						}

						else {
							Login.lblTxtFieldsChklogin.setForeground(Color.RED);
							Login.lblTxtFieldsChklogin
									.setHorizontalAlignment(SwingConstants.CENTER);
							Login.lblTxtFieldsChklogin.setFont(new Font(
									"Tahoma", Font.BOLD, 12));
							Login.lblTxtFieldsChklogin
									.setText("Invalid Password, Try Again");
						}
					}

				}

				rs.close();

				if (!resultFlag) {
					Login.lblTxtFieldsChklogin.setForeground(Color.RED);
					Login.lblTxtFieldsChklogin
							.setHorizontalAlignment(SwingConstants.CENTER);
					Login.lblTxtFieldsChklogin.setFont(new Font("Tahoma",
							Font.BOLD, 12));
					Login.lblTxtFieldsChklogin
							.setText("User ID and Password do not match");
				}

			}

			if (buyCreateChk) {
				Statement buyStatement = null;
				buyStatement = connection1.createStatement();
				String query;
				query = "insert into Buy values('" + postId + "','" + usrID
						+ "','" + category + "','" + needBy + "','"
						+ priceRange + "','" + prodTitle + "','" + phoneNumber
						+ "','" + description + "')";
				buyStatement.executeUpdate(query);

			}

			// main Buy label click logic - to retrieve the buy table as
			// jtable
			if (buy_flag) {
				Statement buyStatement = null;
				buyStatement = connection1.createStatement();
				String query;
				query = "select postId, bCategory,productName,priceExpected,needBy from buy";
				ResultSet rs = buyStatement.executeQuery(query);
				DisplayData buydisplay = new DisplayData(buy_flag, false, false);
				buydisplay.processTableData(rs);

				// if a row from table is selected, the corresponding userid and
				// email-id is retreived from database
				if (someRowSelectedChk) {
					PreparedStatement postSelectedStatement = null;
					String query1;
					query1 = "select UserId from Buy where PostId = ?";
					connection1.setAutoCommit(false);
					postSelectedStatement = connection1
							.prepareStatement(query1);
					postSelectedStatement.setInt(1,
							DisplayData.CurrentRowPostId);
					ResultSet rs1 = postSelectedStatement.executeQuery();

					while (rs1.next()) {
						AdOwnerId = rs1.getString(1);
						GetUserID.OwnerId = AdOwnerId;
					}
					rs1.close();

					postSelectedStatement = null;
					query1 = "select FirstName, LastName, EMAILID from NewUser where USERID = ? ";
					connection1.setAutoCommit(false);
					postSelectedStatement = connection1
							.prepareStatement(query1);
					postSelectedStatement.setString(1, AdOwnerId);
					rs1 = postSelectedStatement.executeQuery();

					while (rs1.next()) {
						AdOwnerFirstName = rs1.getString(1);
						AdOwnerLastName = rs1.getString(2);
						AdOwnerEmailId = rs1.getString(3);
						GetUserID.OwnerFirstName = AdOwnerFirstName;
						GetUserID.OwnerLastName = AdOwnerLastName;
						GetUserID.OwnerEmailid = AdOwnerEmailId;
					}
					rs1.close();

					postSelectedStatement = null;
					query1 = "select Bcategory, NeedBy,PriceExpected, ProductName, PhoneNo, Description from Buy where postId = ? ";
					connection1.setAutoCommit(false);
					postSelectedStatement = connection1
							.prepareStatement(query1);
					postSelectedStatement.setInt(1,
							DisplayData.CurrentRowPostId);
					rs1 = postSelectedStatement.executeQuery();

					while (rs1.next()) {
						buyCategory = rs1.getString("Bcategory");
						buyNeedBy = rs1.getString("NeedBy");
						buyPrice = rs1.getDouble("PriceExpected");
						buyProductName = rs1.getString("ProductName");
						buyPhoneNo = rs1.getString("PhoneNo");
						buyDescription = rs1.getString("Description");
					}
					rs1.close();

					if (delete_button_flag) {
						if (GetUserID.OwnerId.equals(GetUserID.userId)) {
							try {
								PreparedStatement deleteSelectedStatement = null;
								query1 = "Delete from Buy where PostId = ? ";
								connection1.setAutoCommit(false);
								deleteSelectedStatement = connection1
										.prepareStatement(query1);
								deleteSelectedStatement.setInt(1,
										DisplayData.CurrentRowPostId);
								deleteSelectedStatement.executeUpdate();
							} catch (SQLException ex) {
									ex.printStackTrace();
							}
						}
						else
							CampusClassifieds.delete_alert_flag = true;
					}
				}
			}

			// To insert the rental post data after the user creates a rental
			// post
			// Rental post Create button click logic w.r.t database
			if (rentalCreateChk) {
				Statement rentalStatement = null;
				rentalStatement = connection1.createStatement();
				String query;
				query = "insert into Rentals values('" + postId + "','" + usrID
						+ "','" + rCategory + "','" + rPropType + "','"
						+ rPrice + "','" + rNumBeds + "','" + rNumBath + "','"
						+ rLaundry + "','" + rGym + "','" + rPool + "','"
						+ rPets + "','" + rParking + "','" + rFurnished + "','"
						+ usrPhoneNo + "','" + rDescription + "','" + rAddress
						+ "','" + rCity + "','" + rZipCode + "')";
				rentalStatement.executeUpdate(query);
			}

			// To insert the entries given by user from Sell create form
			if (sellCreateChk) {
				Statement sellStatement = null;
				sellStatement = connection1.createStatement();
				String query;
				query = "insert into SELLER values('" + postId + "','" + usrID
						+ "','" + sCategory + "','" + sProdTitle + "','"
						+ sPrice + "','" + sCondition + "','" + sMake + "','"
						+ sModel + "','" + sSize + "','" + sContactNo + "','"
						+ sDescription + "')";
				sellStatement.executeUpdate(query);
			}

			// the main sell_flag click - table retrieval logic
			if (sell_flag) {
				Statement sellTableStatment = null;
				sellTableStatment = connection1.createStatement();
				String query;
				query = "select SELLID, sCategory, sProdTitle, sPrice, sCondition, sMake, sModel, sSize from SELLER";
				ResultSet rs = sellTableStatment.executeQuery(query);
				DisplayData selldisplay = new DisplayData(false, sell_flag,
						false);
				selldisplay.processTableData(rs);
				// After loading the table if a row from table is selected, the
				// corresponding userid and emailid retreived from database
				if (someRowSelectedChk) {
					PreparedStatement postSelectedStatement = null;
					String query1;
					query1 = "select UserId from SELLER where SELLID = ?";
					connection1.setAutoCommit(false);
					postSelectedStatement = connection1
							.prepareStatement(query1);
					postSelectedStatement.setInt(1,
							DisplayData.CurrentRowPostId);
					ResultSet rs1 = postSelectedStatement.executeQuery();

					while (rs1.next()) {
						AdOwnerId = rs1.getString(1);
						GetUserID.OwnerId = AdOwnerId;
					}
					rs1.close();

					postSelectedStatement = null;
					query1 = "select FirstName, LastName, EMAILID from NewUser where USERID = ? ";
					connection1.setAutoCommit(false);
					postSelectedStatement = connection1
							.prepareStatement(query1);
					postSelectedStatement.setString(1, AdOwnerId);
					rs1 = postSelectedStatement.executeQuery();

					while (rs1.next()) {
						AdOwnerFirstName = rs1.getString(1);
						AdOwnerLastName = rs1.getString(2);
						AdOwnerEmailId = rs1.getString(3);
						GetUserID.OwnerFirstName = AdOwnerFirstName;
						GetUserID.OwnerLastName = AdOwnerLastName;
						GetUserID.OwnerEmailid = AdOwnerEmailId;
					}
					rs1.close();
					postSelectedStatement = null;
					query1 = "Select SCATEGORY,SPRODTITLE,SPRICE,SCONDITION,SMAKE,SMODEL,SSIZE,CONTACTNO,DESCRIPTION from SELLER where SELLID = ?";
					connection1.setAutoCommit(false);
					postSelectedStatement = connection1
							.prepareStatement(query1);
					postSelectedStatement.setInt(1,
							DisplayData.CurrentRowPostId);
					rs1 = postSelectedStatement.executeQuery();

					while (rs1.next()) {
						sellCategory = rs1.getString("SCATEGORY");
						sellPrice = rs1.getDouble("SPRICE");
						sellProductName = rs1.getString("SPRODTITLE");
						sellCondition = rs1.getString("SCONDITION");
						sellMake = rs1.getString("SMAKE");
						sellModel = rs1.getString("SMODEL");
						sellSize = rs1.getString("SSIZE");
						sellPhoneNo = rs1.getString("CONTACTNO");
						sellDescription = rs1.getString("DESCRIPTION");

					}
					rs1.close();

					if (delete_button_flag) {
						if (GetUserID.OwnerId.equals(GetUserID.userId)) {
							try {
								PreparedStatement deleteSelectedStatement = null;
								query1 = "Delete from SELLER where SELLID = ? ";
								connection1.setAutoCommit(false);
								deleteSelectedStatement = connection1
										.prepareStatement(query1);
								deleteSelectedStatement.setInt(1,
										DisplayData.CurrentRowPostId);
								deleteSelectedStatement.executeUpdate();
							} catch (SQLException ex) {
								ex.printStackTrace();
							}

						} else
							CampusClassifieds.delete_alert_flag = true;
					}
				}
			}

			// main rental label click logic - to retrieve the rental table as
			// jtable
			if (rental_flag) {
				Statement rntlMnStatment = null;
				rntlMnStatment = connection1.createStatement();
				String query;
				query = "select RId, RCategory, PropertyType, RPrice, NoOfBeds, NoOfBath, CITY, ZipCode from Rentals";
				ResultSet rs = rntlMnStatment.executeQuery(query);
				DisplayData rentaldisplay = new DisplayData(false, false,
						rental_flag);
				rentaldisplay.processTableData(rs);
				// retrieves the corresponding row's userid and email id
				if (someRowSelectedChk) {
					PreparedStatement postSelectedStatement = null;
					String query1;
					query1 = "select UserId from Rentals where RID = ?";
					connection1.setAutoCommit(false);
					postSelectedStatement = connection1
							.prepareStatement(query1);
					postSelectedStatement.setInt(1,
							DisplayData.CurrentRowPostId);
					ResultSet rs1 = postSelectedStatement.executeQuery();

					while (rs1.next()) {
						AdOwnerId = rs1.getString(1);
						GetUserID.OwnerId = AdOwnerId;
					}
					rs1.close();

					postSelectedStatement = null;
					query1 = "select FirstName, LastName, EMAILID from NewUser where USERID = ? ";
					connection1.setAutoCommit(false);
					postSelectedStatement = connection1
							.prepareStatement(query1);
					postSelectedStatement.setString(1, AdOwnerId);
					rs1 = postSelectedStatement.executeQuery();

					while (rs1.next()) {
						AdOwnerFirstName = rs1.getString(1);
						AdOwnerLastName = rs1.getString(2);
						AdOwnerEmailId = rs1.getString(3);
						GetUserID.OwnerFirstName = AdOwnerFirstName;
						GetUserID.OwnerLastName = AdOwnerLastName;
						GetUserID.OwnerEmailid = AdOwnerEmailId;
					}
					rs1.close();

					postSelectedStatement = null;
					query1 = "Select RCategory, propertytype, rprice, Noofbeds, noofbath, alaundry, agym, apool, apetallowed, aparking, "
							+ "afurnished, phoneno, description, streetaddress, city, zipcode from Rentals where RID = ? ";
					connection1.setAutoCommit(false);
					postSelectedStatement = connection1
							.prepareStatement(query1);
					postSelectedStatement.setInt(1,
							DisplayData.CurrentRowPostId);
					rs1 = postSelectedStatement.executeQuery();

					while (rs1.next()) {
						rentalCategory = rs1.getString("RCategory");
						rentalPropType = rs1.getString("PropertyType");
						rentalPrice = rs1.getDouble("rprice");
						rBeds = rs1.getInt("NoOfBeds");
						rbath = rs1.getDouble("NoOfBath");
						rentalLaundry = rs1.getString("ALaundry");
						rentalGym = rs1.getString("AGym");
						rentalPool = rs1.getString("APool");
						rentalPets = rs1.getString("APetAllowed");
						rentalParking = rs1.getString("AParking");
						rentalFurnished = rs1.getString("AFurnished");
						rentalPhNo = rs1.getLong("PhoneNo");
						rentalDescription = rs1.getString("DESCRIPTION");
						rentalAddress = rs1.getString("StreetAddress");
						rentalCity = rs1.getString("City");
						rentalZipCode = rs1.getInt("ZipCode");

						// mainPage.displayPanel.repaint();
						// mainPage.displayPanel.revalidate();

						// DisplayData dsd = new DisplayData(rentalCategory);

					}
					rs1.close();

					if (delete_button_flag) {
						if (GetUserID.OwnerId.equals(GetUserID.userId)) {
							try {
								PreparedStatement deleteSelectedStatement = null;
								query1 = "Delete from Rentals where RID = ? ";
								connection1.setAutoCommit(false);
								deleteSelectedStatement = connection1
										.prepareStatement(query1);
								deleteSelectedStatement.setInt(1,
										DisplayData.CurrentRowPostId);
								deleteSelectedStatement.executeUpdate();
							} catch (SQLException ex) {
								ex.printStackTrace();
							}

						} else
							CampusClassifieds.delete_alert_flag = true;
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection1.close();
			} catch (SQLException e) {
			
			}
		}
	}
}
