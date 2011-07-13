package ege.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class SignedIn_CommMethodRequest implements SignedIn_CommMethodReq {

	private UserSes userSession;
	private String smsNumber;
	@Override
	public UserSes getUserSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUserSession(UserSes userSession) {
		this.userSession = userSession;
	}

	
	public void setSmsNumber(String sms)
	{
		
		this.smsNumber = sms;
	}
	public String getSmsNumber()
	{
		return smsNumber;
	}
	
	public String next() {
		if (smsNumber.length()<5)
		{
			FacesContext facesContext;
			facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null , new FacesMessage("Your number cannot contain less than 5 digits. Please enter again", 
					"Please choose another one."));
			return "choosecommmethod";
		}
		for (int i=0; i< this.smsNumber.length(); i++)
		{
			if (!Character.isDigit(smsNumber.charAt(i)))
			{
				FacesContext facesContext;
				facesContext = FacesContext.getCurrentInstance();
    			facesContext.addMessage(null , new FacesMessage("Your number cannot contain letters. Please enter again", 
    					"Please choose another one."));
				return "choosecommmethod";
			}
		}
		
		
		
		return "choosePaymentMethod";
	}

	
	public String back() {	
		
		
		
		return "register";
	}
	
	public String addNumber() {
		if (smsNumber.length()<5)
		{
			FacesContext facesContext;
			facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null , new FacesMessage("Your number cannot contain less than 5 digits. Please enter again", 
					"Please choose another one."));
			return "choosecommmethod";
		} 
		for (int i=0; i< this.smsNumber.length(); i++)
		{
			if (!Character.isDigit(smsNumber.charAt(i)))
			{
				FacesContext facesContext;
				facesContext = FacesContext.getCurrentInstance();
    			facesContext.addMessage(null , new FacesMessage("Your number cannot contain letters. Please enter again", 
    					"Please choose another one."));
				return "choosecommmethod";
			}
		}
		this.smsNumber = smsNumber;
		
		return "editcommmethodinfo";
	}
	
	public String update() throws Exception{
		
		FacesContext facesContext= FacesContext.getCurrentInstance();;
		SignInRequest yourBean1 =
			facesContext.getApplication().evaluateExpressionGet(facesContext,
			"#{signInRequest}", SignInRequest.class);
		
		String username = yourBean1.getUserSession().getUserId();
		Connection connection = null;
		  //Loading the JDBC driver for MySql
	  Class.forName("com.mysql.jdbc.Driver");
	  
	  int i=0;
	  //Getting a connection to the database. Change the URL parameters
	  connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "testuser", "test");
	
	  //Creating a statement object
	  Statement stmt = connection.createStatement();
	  
	  String t2 = "INSERT INTO user_communication_relation (comm_id, user_name) VALUES ('1','" +
  	username + "');";
  	int count2 = stmt.executeUpdate(t2);
  	 stmt.executeQuery ("SELECT idUSER_COMMUNICATION_RELATION FROM test.user_communication_relation WHERE comm_id ='1' AND  user_name='" +
  			 username + "';");
  	
  	ResultSet rs = stmt.getResultSet ();
  	
  	rs.last();
  	int vl = rs.getInt(1);
  	
  	String t3 = "INSERT INTO sms (phone_number, user_comm_relationid) VALUES ('"
  		+ this.smsNumber+ "','" + String.valueOf(vl) + "');";
  	int count3 = stmt.executeUpdate(t3);   
		return "signedin";
	}
	
	
}
