package com.my.friends;

/** class to get the userID of the user who is logged in. this is very
 * important when the user id needs to accessed from multiple pages
 * @author Ramya Bangaluru Gopalakrishna
 *
 */
public class GetUserID{
	public static String userId;
	public static String OwnerId;
	public static String OwnerEmailid;
	public static String OwnerFirstName;
	public static String OwnerLastName;
	public static String currentUserEmailID;
	
	
	public void setUsrID(String UID){
		GetUserID.userId = UID;
	}
	
	public String getUserID(){
		return userId;
	}

	public void setAdOwnerId(String adOwnerId) {
		GetUserID.OwnerId = adOwnerId;
	}
	
	/*public String getAdOwnerId(){
		return OwnerId;
	}*/
	
	public void setAdOwnerEmailId(String adOwnerEmailId) {
		this.OwnerEmailid = adOwnerEmailId;
	}
	
	/*public String getAdOwnerEmailId(){
		return OwnerEmailid;
	}*/
	
	public void setAdOwnerFirstName(String adOwnerFirstName){
		GetUserID.OwnerFirstName = adOwnerFirstName;
	}
	
	public void setAdOwnerLastname(String adOwnerLastName){
		GetUserID.OwnerLastName = adOwnerLastName;
	}
	
	public void setCurrentUserEmailId (String LoggedInUserEmailId) {
		GetUserID.currentUserEmailID = LoggedInUserEmailId;
	}
	
}