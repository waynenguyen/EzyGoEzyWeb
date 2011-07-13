package ege.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.enterprise.inject.spi.Bean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import ege.CreditCard;


public class PaymentMethodRequest implements PaymentMethodReq{
	private UserSes userSession;
	private String smsNumber;
	private List creditCardList = new ArrayList();
	private ArrayList realCcList = new ArrayList();
	private int currentCreditCard;
	private String ccCardHolder;
	private String ccCardNumber ;
	private String ccCardExpiry;
	private boolean hasCreditCard = false;
	@Override
	public UserSes getUserSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUserSession(UserSes userSession) {
		this.userSession = userSession;
	}

	public List getCreditCardList()
	{
		if (this.creditCardList.isEmpty())
		{
			this.creditCardList.add(new SelectItem("-1" , "No credit card yet"));
		}
		return creditCardList;
	}
	
	public List getRealCcList()
	{
		return this.realCcList;
	}
	
	
	public void addNewCreditCard(CreditCard cre) throws Exception
	{
		this.hasCreditCard = true;
		realCcList.add(cre);
		this.creditCardList.add(new SelectItem(realCcList.size()-1, "Credit card number " + cre.getCardNumber()));
		
		SelectItem tt = new SelectItem("-1" , "No credit card yet");
		SelectItem tt1 = (SelectItem)this.creditCardList.get(0); 
		if ((tt1.getValue()==tt.getValue()) && (tt1.getLabel()==tt.getLabel()))
		{
			this.creditCardList.remove(0);
		}
		
		

		FacesContext facesContext = FacesContext.getCurrentInstance();
		RegisterRequest yourBean =
		facesContext.getApplication().evaluateExpressionGet(facesContext,
		"#{registerRequest}", RegisterRequest.class); 
		
		Connection connection = null;
		 //Loading the JDBC driver for MySql
        Class.forName("com.mysql.jdbc.Driver");
        
        int i=0;
        //Getting a connection to the database. Change the URL parameters
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "testuser", "test");

        //Creating a statement object
        Statement stmt = connection.createStatement();

        String username = yourBean.getUserName();
        
        // create credit - user relation
        String t7 = "INSERT INTO user_payment_relation (payment_id, user_name) VALUES ('2','" +
        	username + "');";
        
        int count7 = stmt.executeUpdate(t7);
        
        
        
        stmt.executeQuery ("SELECT idUSER_PAYMENT_RELATION FROM test.user_payment_relation WHERE payment_id ='2' AND  user_name='" +
      			 username + "';");
      	
      	ResultSet rs = stmt.getResultSet ();
      	
      		rs.last();
      		int vl = rs.getInt(1);
      		
      		
      		
        String cardnumber = cre.getCardNumber();
        String cardholdername = cre.getCardHolderName();
        String expirydate = cre.getCardExpiryDate();
        
        if ((cardnumber!=null) && (cardholdername!=null) && (expirydate!=null))
        {
        	String t6= "INSERT INTO creditcard (card_number, card_holder_name, paymentid, expiry_date) VALUES ('"
        		+ cardnumber + "','" + cardholdername + "','" + String.valueOf(vl) + "','" + expirydate + "');";
        	int count6 = stmt.executeUpdate(t6);
        	
        }
	}
	
	public boolean getHasCreditCard()
	{
		return hasCreditCard;
	}

	public int getCurrentCreditCard()
	{
		
		return currentCreditCard;
	}
	
	public void setCurrentCreditCard(int cc)
	{
		
		this.currentCreditCard = cc;
		if (cc==-1) return;
		Object[] newarr = this.realCcList.toArray();
		CreditCard cc1 = (CreditCard) newarr[this.currentCreditCard];
		this.ccCardExpiry = cc1.getCardExpiryDate();
		this.ccCardHolder = cc1.getCardHolderName();
		this.ccCardNumber = cc1.getCardNumber();
	}
	
	public String getCcCardHolder()
	{
		Object[] newarr = this.realCcList.toArray();
		CreditCard cc = (CreditCard) newarr[this.currentCreditCard];
		setCcCardHolder(cc.getCardHolderName());
		return ccCardHolder;
	}
	public void setCcCardHolder(String cc)
	{
		this.ccCardHolder = cc;
	}
	public String getCcCardNumber()
	{
		Object[] newarr = this.realCcList.toArray();
		CreditCard cc = (CreditCard) newarr[this.currentCreditCard];
		setCcCardNumber(cc.getCardNumber());
		return ccCardNumber;
	}
	public void setCcCardNumber(String cc)
	{
		this.ccCardNumber = cc;
	}
	public String getCcCardExpiry()
	{
		return ccCardExpiry;
	}
	public void setCcCardExpiry(String cc)
	{
		this.ccCardExpiry = cc;
	}
	
	public String goEditCard()
	{
		
		return "goeditcard";
	}
	
	public String finish() throws Exception{
		// save everything to db
		
		
		
		
	
		
		
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		RegisterRequest yourBean =
		facesContext.getApplication().evaluateExpressionGet(facesContext,
		"#{registerRequest}", RegisterRequest.class); 
		
		
		Connection connection = null;
        //Loading the JDBC driver for MySql
        Class.forName("com.mysql.jdbc.Driver");
        
        int i=0;
        //Getting a connection to the database. Change the URL parameters
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "testuser", "test");

        //Creating a statement object
        Statement stmt = connection.createStatement();

        //Executing the query and getting the result set
        UserSession hi = (UserSession) this.userSession;
       
        
        String firstname = yourBean.getFirstName();
        String lastname = yourBean.getLastName();
        String username = yourBean.getUserName();
        String password = yourBean.getPassword();
        String email = yourBean.getEmail();
        String address = yourBean.getAddress();
        /*
        String t1 = "INSERT INTO user (first_name,last_name, user_name, password, email, address) VALUES ( '" 
    		+ firstname + "','" + lastname + "','" + username + "','" + password + "','" + email +  "','" + address + "');";
        int count = stmt.executeUpdate(t1);
        */
        
        
        
        CommMethodRequest commmethod =
    		facesContext.getApplication().evaluateExpressionGet(facesContext,
    		"#{commMethodRequest}", CommMethodRequest.class); 
        String smsnumber = commmethod.getSmsNumber();
        if (smsnumber!=null)
        {
        	// use sms communication method
        	String t2 = "INSERT INTO user_communication_relation (comm_id, user_name) VALUES ('1','" +
        	username + "');";
        	int count2 = stmt.executeUpdate(t2);
        	 stmt.executeQuery ("SELECT idUSER_COMMUNICATION_RELATION FROM test.user_communication_relation WHERE comm_id ='1' AND  user_name='" +
        			 username + "';");
        	
        	ResultSet rs = stmt.getResultSet ();
        	
        	rs.last();
        	int vl = rs.getInt(1);
        	
        	String t3 = "INSERT INTO sms (phone_number, user_comm_relationid) VALUES ('"
        		+ smsnumber + "','" + String.valueOf(vl) + "');";
        	int count3 = stmt.executeUpdate(t3);    	
        	
        	
        }
        
        
        
        PaymentMethodRequest paymentmethod =
        	facesContext.getApplication().evaluateExpressionGet(facesContext,
            		"#{paymentMethodRequest}", PaymentMethodRequest.class); 
        
        // create prepaid - user relation
        String t4 = "INSERT INTO user_payment_relation (payment_id, user_name) VALUES ('1','" +
        	username + "');";
        
        int count4 = stmt.executeUpdate(t4);
        stmt.executeQuery ("SELECT idUSER_PAYMENT_RELATION FROM test.user_payment_relation WHERE payment_id ='1' AND  user_name='" +
   			 username + "';");
   	
   	ResultSet rs = stmt.getResultSet ();
   	
   		rs.last();
   		int vl = rs.getInt(1);
        
        
        PrepaidRequest prepaidrequest =
        	facesContext.getApplication().evaluateExpressionGet(facesContext,
            		"#{prepaidRequest}", PrepaidRequest.class); 
        
        String balance = String.valueOf(prepaidrequest.getBalance());
        String t5= "INSERT INTO prepaid (balance, paymentid) VALUES ('" + balance + "','" 
        + String.valueOf(vl) + "');";
        
        int count5 = stmt.executeUpdate(t5);
        
        
        
        /*
        
        CreditRequest creditrequest =
        	facesContext.getApplication().evaluateExpressionGet(facesContext,
            		"#{creditRequest}", CreditRequest.class); 
        String cardnumber = creditrequest.getCardNumber();
        String cardholdername = creditrequest.getCardHolderName();
        String expirydate = creditrequest.getExpiryDate();
        
        
     // create credit - user relation
        String t7 = "INSERT INTO user_payment_relation (payment_id, user_name) VALUES ('2','" +
        	username + "');";
        
        int count7 = stmt.executeUpdate(t7);
        
        
        
        stmt.executeQuery ("SELECT idUSER_PAYMENT_RELATION FROM test.user_payment_relation WHERE payment_id ='2' AND  user_name='" +
      			 username + "';");
      	
      	 rs = stmt.getResultSet ();
      	
      		rs.last();
      		vl = rs.getInt(1);
        
        
        if ((cardnumber!=null) && (cardholdername!=null) && (expirydate!=null))
        {
        	String t6= "INSERT INTO test.creditcard (card_number, card_holder_name, paymentid, expiry_date) VALUES ('"
        		+ cardnumber + "','" + cardholdername + "','" + String.valueOf(vl) + "','" + expirydate + "');";
        	int count6 = stmt.executeUpdate(t6);
        	
        }
        
        	
        	*/
        
        stmt.close();
        connection.close();
		
		
		return "index";
	}

	
	public String goPrepaid() {	
		
		return "prepaid";
	}
	
	public String goCreditCard() {

		return "index1";
		
	}
	
	public String saveAndGoBackCredit() throws Exception
	{
		CreditCard newcc = new CreditCard();
		newcc.setCardExpiryDate(ccCardExpiry);
		newcc.setCardHolderName(ccCardHolder);
		newcc.setCardNumber(ccCardNumber);
		
		realCcList.add(newcc);
		this.creditCardList.add(new SelectItem(realCcList.size(), "Credit card number " + newcc.getCardNumber()));
		
		if ((!creditCardList.isEmpty() && (currentCreditCard!=-1)))
			this.creditCardList.remove(currentCreditCard);
			if (!realCcList.isEmpty() && (currentCreditCard!=-1))
				this.realCcList.remove(currentCreditCard);
			
			Connection connection = null;
	        //Loading the JDBC driver for MySql
	        Class.forName("com.mysql.jdbc.Driver");
	        
	        int i=0;
	        //Getting a connection to the database. Change the URL parameters
	        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "testuser", "test");

	        //Creating a statement object
	        Statement stmt = connection.createStatement();
	        	
	        String t0 = "SELECT paymentid FROM test.creditcard WHERE card_number='" +
 			 this.ccCardNumber + "';";
	        stmt.executeQuery(t0);
	        ResultSet rs = stmt.getResultSet();
	        rs.last();
	        int vl = rs.getInt(1);
	        String t1 = "DELETE FROM test.creditcard WHERE card_number= '" 
	    		+ this.ccCardNumber + "';";
	        int count = stmt.executeUpdate(t1);
	        
	        String t2 = "DELETE FROM test.user_payment_relation WHERE payment_id= '" 
	    		+ String.valueOf(vl) + "';";
	        int count2 = stmt.executeUpdate(t1);
	        
			
			
			
			
			
			
			
			
			
			
		
		return "paymentMethodRequest";
	}
	
	public String deleteCard() throws Exception
	{
		if ((!creditCardList.isEmpty() && (currentCreditCard!=-1)))
			this.creditCardList.remove(currentCreditCard);
			if (!realCcList.isEmpty() && (currentCreditCard!=-1))
				this.realCcList.remove(currentCreditCard);
			Connection connection = null;
	        //Loading the JDBC driver for MySql
	        Class.forName("com.mysql.jdbc.Driver");
	        
	        int i=0;
	        //Getting a connection to the database. Change the URL parameters
	        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "testuser", "test");

	        //Creating a statement object
	        Statement stmt = connection.createStatement();
	        	
	        String t0 = "SELECT paymentid FROM test.creditcard WHERE card_number='" +
 			 this.ccCardNumber + "';";
	        stmt.executeQuery(t0);
	        ResultSet rs = stmt.getResultSet();
	        rs.last();
	        int vl = rs.getInt(1);
	        String t1 = "DELETE FROM test.creditcard WHERE card_number= '" 
	    		+ this.ccCardNumber + "';";
	        int count = stmt.executeUpdate(t1);
	        
	        String t2 = "DELETE FROM test.user_payment_relation WHERE payment_id= '" 
	    		+ String.valueOf(vl) + "';";
	        int count2 = stmt.executeUpdate(t1);
	        
	        
			
		return "paymentMethodRequest";
	}
	
}

