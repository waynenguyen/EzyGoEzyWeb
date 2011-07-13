package ege.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

public class RegisterRequest implements RegisterReq {

	private String userId;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String Address; 
	private String Email;
	private List<String> CommunicationMethod;
	private boolean renderRegister;
	
	private UserSes userSession;
	
	@Override
	public void validateUserId(FacesContext context,
			UIComponent componentToValidate, Object value) {
		// TODO Auto-generated method stub
	}

	@Override
	public void validatePassword(FacesContext context,
			UIComponent componentToValidate, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateFirstName(FacesContext context,
			UIComponent componentToValidate, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateLastName(FacesContext context,
			UIComponent componentToValidate, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateAddress(FacesContext context,
			UIComponent componentToValidate, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateEmail(FacesContext context,
			UIComponent componentToValidate, Object value) {
		// TODO Auto-generated method stub
		
	}
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
	
	public List<String> getCommunicationMethod(){
		return CommunicationMethod;
	}
	
	public void setCommunicationMethod(List<String> communicationmethod){
		CommunicationMethod = communicationmethod;
	}
	
	
	public boolean isRenderRegister() {
		return renderRegister;
	}

	public void setRenderRegister(boolean renderRegister) {
		this.renderRegister = renderRegister;
	}
	
	@Override
	public UserSes getUserSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUserSession(UserSes userSession) {
		this.userSession = userSession;
	}
	
	public String register() {
		
		
		
		
		
		
		
		
		
		this.userSession.setUserId(this.userId);
		this.userSession.setPassword(this.password);
		this.userSession.setRegistering(true);
		this.userSession.setSignedIn(true);
		Connection connection = null;
        //Loading the JDBC driver for MySql
		
        try {
			Class.forName("com.mysql.jdbc.Driver");		
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		 int i=0;
	        //Getting a connection to the database. Change the URL parameters
	        try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "testuser", "test");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FacesContext facesContext;
	        //Creating a statement object
	        try {
	        	String username = this.userName;
	        	if (username.isEmpty() || (username==null))
				{
					facesContext = FacesContext.getCurrentInstance();
        			facesContext.addMessage(null , new FacesMessage("Please enter your user name", 
        					"Please choose another one."));
        			
        			return "register";
				}
	        	
	        	String password = this.password;
	        	
	        	if (password.isEmpty() || (password==null))
				{
					facesContext = FacesContext.getCurrentInstance();
        			facesContext.addMessage(null , new FacesMessage("Please enter your password", 
        					"Please choose another one."));
        			
        			return "register";
				}
	        	
	        	
	        	
	        	
				Statement stmt = connection.createStatement();
				String firstname = this.firstName;
				if (firstname.isEmpty() || (firstname==null))
				{
					facesContext = FacesContext.getCurrentInstance();
        			facesContext.addMessage(null , new FacesMessage("Please enter your first name", 
        					"Please choose another one."));
        			
        			return "register";
				}
				for (int j=0; j< firstname.length(); j++)
				{
					if (Character.isDigit(firstname.charAt(j)))
					{
						facesContext = FacesContext.getCurrentInstance();
	        			facesContext.addMessage(null , new FacesMessage("First name should not include letters only. Please try again", 
	        					"Please choose another one."));
	        			
	        			return "register";
					}
				}
				
		        String lastname = this.lastName;
		        if (lastname.isEmpty() || (lastname==null))
				{
					facesContext = FacesContext.getCurrentInstance();
        			facesContext.addMessage(null , new FacesMessage("Please enter your last name", 
        					"Please choose another one."));
        			
        			return "register";
				}
		        for (int j=0; j< lastname.length(); j++)
				{
					if (Character.isDigit(lastname.charAt(j)))
					{
						facesContext = FacesContext.getCurrentInstance();
	        			facesContext.addMessage(null , new FacesMessage("Last name should not include letters only. Please try again", 
	        					"Please choose another one."));
	        			
	        			return "register";
					}
				}
		        
		        
		        String email = this.Email;
		        if (email.isEmpty() || (email==null))
				{
					facesContext = FacesContext.getCurrentInstance();
        			facesContext.addMessage(null , new FacesMessage("Please enter your email", 
        					"Please choose another one."));
        			
        			return "register";
				}
		        
		        
		        
		        String address = this.Address;
		        if (address.isEmpty() || (address==null))
				{
					facesContext = FacesContext.getCurrentInstance();
        			facesContext.addMessage(null , new FacesMessage("Please enter your address", 
        					"Please choose another one."));
        			
        			return "register";
				}
		        try {
		        String t1 = "INSERT INTO user (first_name,last_name, user_name, password, email, address) VALUES ( '" 
		    		+ firstname + "','" + lastname + "','" + username + "','" + password + "','" + email +  "','" + address + "');";
		        int count = stmt.executeUpdate(t1);
		        }
		        catch(SQLException e) {
		        	
		        	facesContext = FacesContext.getCurrentInstance();
        			facesContext.addMessage(null , new FacesMessage("User name already existed", 
        					"Please choose another one."));
        			
        			return "register";
		        	
		        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		
        
        
		return "chooseCommMethod";
		
	}

	public RegisterRequest() {
		this.renderRegister = true;
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public void setUserName(String username) {
		// TODO Auto-generated method stub
		this.userName = username;
	}
}
