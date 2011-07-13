package ege.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import ege.CreditCard;
public class CreditRequest implements CreditReq {

	private String cardNumber;
	private String cardHolderName;
	private String expiryDate;
	private String canCardNumber;
	private String canCardHolderName;
	private String canExpiryDate;
	@Override
	public void setUserSession(UserSes userSession) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserSes getUserSession() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getCanCardNumber()
	{
		return this.canCardNumber;
	}
	public String getCanCardHolderName()
	{
		return this.canCardHolderName;
	}
	public String getCanCardExpiryDate()
	{
		return this.canExpiryDate;
	}
	
	public String getCardNumber()
	{
		return "";
	}
	
	public void setCardNumber(String number)
	{
		this.cardNumber = number;
		this.canCardNumber = number;
	}
	
	public String getCardHolderName()
	{
		return "";
	}
	public void setCardHolderName(String name)
	{
		this.cardHolderName = name;
		this.canCardHolderName = name;
	}
	
	public String getExpiryDate()
	{
		return  "";
	}
	
	public void setExpiryDate(String date)
	{
		this.expiryDate = date;
		this.canExpiryDate = date;
	}
	
	public String saveAndGoBack() throws Exception
	{
		if (canCardNumber.isEmpty() || (canCardNumber==null))
		{
			FacesContext facesContext;
			facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null , new FacesMessage("Please enter your credit card number.", 
					"Please choose another one."));
			
			return "index1";
		}
		if (canCardNumber.length()!=12)
		{
			FacesContext facesContext;
			facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null , new FacesMessage("Please enter your credit card number again.", 
					"Please choose another one."));
			
			return "index1";
		}
		for (int i=0; i< this.canCardNumber.length(); i++)
		{
			if (!Character.isDigit(canCardNumber.charAt(i)))
			{
				FacesContext facesContext;
				facesContext = FacesContext.getCurrentInstance();
    			facesContext.addMessage(null , new FacesMessage("Please enter your credit card number again.", 
    					"Please choose another one."));
    			
    			return "index1";
			}
		}
		
		if (this.canCardHolderName.isEmpty() || (this.canCardHolderName==null))
		{
			FacesContext facesContext;
			facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null , new FacesMessage("Please enter your credit card holder name.", 
					"Please choose another one."));
			
			return "index1";
		}
		
		for (int i=0; i< this.cardHolderName.length(); i++)
		{
			if (Character.isDigit(this.canCardHolderName.charAt(i)))
			{
				FacesContext facesContext;
				facesContext = FacesContext.getCurrentInstance();
    			facesContext.addMessage(null , new FacesMessage("Please enter your credit card holder again.", 
    					"Please choose another one."));
    			
    			return "index1";
			}
		}
		if (this.canExpiryDate.isEmpty() || (this.canExpiryDate==null))
		{
			FacesContext facesContext;
			facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null , new FacesMessage("Please enter your credit card expiry date.", 
					"Please choose another one."));
			
			return "index1";
		}
		
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
       PaymentMethodRequest commmethod =
    		facesContext.getApplication().evaluateExpressionGet(facesContext,
    		"#{paymentMethodRequest}", PaymentMethodRequest.class); 
       
       CreditCard cc = new CreditCard();
       cc.setCardHolderName(this.canCardHolderName);
       cc.setCardNumber(this.canCardNumber);
       cc.setCardExpiryDate(this.canExpiryDate);
       commmethod.addNewCreditCard(cc);
       
       
       
       // String smsnumber = commmethod.getSmsNumber();
		return "choosepaymentmethod";
	}

	public void saveCreditCard() throws Exception
	{
		Connection connection = null;
        
            //Loading the JDBC driver for MySql
            Class.forName("com.mysql.jdbc.Driver");
            
            int i=0;
            //Getting a connection to the database. Change the URL parameters
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "username", "password");

            //Creating a statement object
            Statement stmt = connection.createStatement();

            //Executing the query and getting the result set
            ResultSet rs = stmt.executeQuery("select * from item");

            //Iterating the resultset and printing the 3rd column
            while (rs.next()) {
                System.out.println(rs.getString(3));
            }
            //close the resultset, statement and connection.
            rs.close();
            stmt.close();
            connection.close();
        
	}
}
