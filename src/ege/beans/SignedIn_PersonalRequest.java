package ege.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.faces.context.FacesContext;

public class SignedIn_PersonalRequest implements SignedIn_PersonalReq{
	
	
	private String userId ;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String Address;	
	private String Email;
	
	@Override
	public void setUserSession(UserSes userSession) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserSes getUserSession() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userId) {
		this.userName = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getEmail() {
		return Email;
	}
	
	public void setEmail(String email) {
		Email = email;
	}
	
	public String update() throws Exception
	{
		Connection connection = null;
        
        //Loading the JDBC driver for MySql
        Class.forName("com.mysql.jdbc.Driver");
        
        int i=0;
        //Getting a connection to the database. Change the URL parameters
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "testuser", "test");

        //Creating a statement object
        Statement stmt = connection.createStatement();
		
        
        
        String t5= "UPDATE test.user SET first_name='" + this.firstName + "', last_name='"
        + this.lastName +"', email='" + this.Email + "', address='" + this.Address + "' " +
        "where user_name='" + this.userName +"';"; 
	       
        
        int count5 = stmt.executeUpdate(t5);
        
        
		return "signedin";
	}
	
	
	
}
