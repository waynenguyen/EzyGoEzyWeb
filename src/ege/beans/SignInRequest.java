package ege.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class SignInRequest implements SignInReq{
	private String userId;
	private String password;
	
	private String userIdMessage;
	private String passwordMessage;
	
	private UserSes userSession;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUserIdMessage() {
		return userIdMessage;
	}
	public void setUserIdMessage(String userIdMessage) {
		this.userIdMessage = userIdMessage;
	}
	public String getPasswordMessage() {
		return passwordMessage;
	}
	public void setPasswordMessage(String passwordMessage) {
		this.passwordMessage = passwordMessage;
	}
	@Override
	public String register() {
		this.userSession.setSignedIn(false);
		this.userSession.setRegistering(true);
		return "register";
	}
	
	@Override
	public String signIn() {
		FacesContext facesContext;
		
		/** In fact, this should be a database access */
		Connection connection = null;
		 //Loading the JDBC driver for MySql
       
        
       
        //Getting a connection to the database. Change the URL parameters
        try {
        	 try {
     			Class.forName("com.mysql.jdbc.Driver");
     			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "testuser", "test");
    			Statement stmt = connection.createStatement();
    			String t1 = "SELECT password FROM test.user WHERE user_name='" +
    			 this.userId  + "';";
    			stmt.executeQuery(t1);
    			ResultSet rs = stmt.getResultSet ();
            	String vl= null;
            	if (rs.last())
            	{
            		vl = rs.getString(1);
            	}
            	if(!vl.equals(this.password)) {
        			facesContext = FacesContext.getCurrentInstance();
        			facesContext.addMessage(null , new FacesMessage("Wrong User Name or Password", 
        					"Please check carefully; Passwords are case sensitive."));
        			this.userIdMessage = "Try again";
        			this.passwordMessage = "Wrong Password";
        			return "failSignIn";
        		}
     		} catch (ClassNotFoundException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
			
        	
        	
      
		

		
        
		
        
        
        
		
		
		
		
		
		
		this.userSession.setSignedIn(true);
		this.userSession.setRegistering(false);
		this.userSession.setUserId(this.userId);
		return "signedIn";
	  	
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
	}
	
	@Override
	public UserSes getUserSession() {
		return userSession;
	}
	
	@Override
	public void setUserSession(UserSes userSession) {
		this.userSession = userSession;
	}
	
	public SignInRequest() {
	}
}
