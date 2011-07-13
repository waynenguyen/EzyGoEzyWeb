package ege.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

public class SignedIn_PrepaidRequest implements SignedIn_PrepaidReq{

	private long balance;
	private long topUpAmount;
	private List lit;
	
	
	
	public long getBalance()
	{
		return balance;
	}
	public void setBalance(long setBalance)
	{
		this.balance = setBalance;
	}
	public long getTopUpAmount()
	{
		return this.topUpAmount;
	}
	public void  setTopUpAmount(long topup)
	{
		this.topUpAmount = topup;
	}
	
	public List getLit() 
	{
		List topUpAmountList = new ArrayList();
		
		
			topUpAmountList.add(new SelectItem("10", "10"));
			topUpAmountList.add(new SelectItem("20","20"));
			topUpAmountList.add(new SelectItem("50", "50"));
			topUpAmountList.add(new SelectItem("100","100"));
			
		return topUpAmountList;
	}
	public String topUp()
	{
		setBalance( this.balance+ this.topUpAmount);
		
		return "signedin_prepaid";
	}
	
	public String saveAndGoBack() throws Exception
	{
		// save to the database;
		
		Connection connection = null;
		  //Loading the JDBC driver for MySql
    Class.forName("com.mysql.jdbc.Driver");
    
    int i=0;
    //Getting a connection to the database. Change the URL parameters
    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "testuser", "test");

    //Creating a statement object
    Statement stmt = connection.createStatement();
    
		FacesContext facesContext = FacesContext.getCurrentInstance();
		 PaymentMethodRequest paymentmethod =
	        	facesContext.getApplication().evaluateExpressionGet(facesContext,
	            		"#{paymentMethodRequest}", PaymentMethodRequest.class); 
	       
		 SignedInRequest yourBean =
				facesContext.getApplication().evaluateExpressionGet(facesContext,
				"#{signedInRequest}", SignedInRequest.class); 
				
		 
		 String username = yourBean.getUserSession().getUserId();
	        // create prepaid - user relation
	       
	        
	        stmt.executeQuery ("SELECT idUSER_PAYMENT_RELATION FROM test.user_payment_relation WHERE payment_id ='1' AND  user_name='" +
	   			 username + "';");
	   	
	   	ResultSet rs = stmt.getResultSet ();
	   	
	   		rs.last();
	   		int vl = rs.getInt(1);
	        
	        
	        SignedIn_PrepaidRequest prepaidrequest =
	        	facesContext.getApplication().evaluateExpressionGet(facesContext,
	            		"#{signedIn_PrepaidRequest}", SignedIn_PrepaidRequest.class); 
	        
	        String balance = String.valueOf(prepaidrequest.getBalance());
	        String t5= "UPDATE prepaid SET balance='" + balance +"'where paymentid='" + String.valueOf(vl)+"';"; 
	       
	        
	        int count5 = stmt.executeUpdate(t5);
	        
	        
		
		
		
		
		
		
		
		
		return "editpaymentmethod";
		
	}
	@Override
	public void setUserSession(UserSes userSession) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserSes getUserSession() {
		// TODO Auto-generated method stub
		return null;
	}


}
