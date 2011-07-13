package ege;

public class User {
	private String userName;
	 
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String showChoice() {
		if (userName.equals("Duy")) 
			return ("signedin.xhtml");
		else return ("");
	}
}
