package ege.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import ege.CreditCard;

public class SignedIn_PaymentMethodRequest implements SignedIn_PaymentMethodReq{

	
	
	private List creditCardList = new ArrayList();
	private ArrayList realCcList = new ArrayList();
	private int currentCreditCard;
	private String oldpaymentid;
	private String ccCardHolder;
	private String ccCardNumber ;
	private String ccCardExpiry;
	private String oldccid;
	private boolean hasCreditCard = false;
	@Override
	public void setUserSession(UserSes userSession) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserSes getUserSession() {
		// TODO Auto-generated method stub
		return null;
	}

	public List getCreditCardList()
	{
		if (this.creditCardList.isEmpty())
		{
			this.creditCardList.add(new SelectItem("-1" , "No credit card yet"));
		}
		return creditCardList;
	}
	
	public String getoldpaymentid()
	{
		return this.oldpaymentid;
	}
	public void setoldpaymentid(String oid)
	{
		this.oldpaymentid = oid;
	}
	
	public List getRealCcList()
	{
		return this.realCcList;
	}
	
	public void clearall()
	{
		this.currentCreditCard=-1;
		this.realCcList.clear();
		this.creditCardList.clear();
	}
	public void addCCWithoutDB(CreditCard cre)
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
		SignedInRequest yourBean =
		facesContext.getApplication().evaluateExpressionGet(facesContext,
		"#{signedInRequest}", SignedInRequest.class); 
		
		Connection connection = null;
		 //Loading the JDBC driver for MySql
        Class.forName("com.mysql.jdbc.Driver");
        
        int i=0;
        //Getting a connection to the database. Change the URL parameters
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "testuser", "test");

        //Creating a statement object
        Statement stmt = connection.createStatement();

        String username = yourBean.getUserSession().getUserId();
        
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
	
	public void setCurrentCreditCard(int cc) throws Exception
	{
		
		this.currentCreditCard = cc;
		if (cc==-1) return;
		Object[] newarr = this.realCcList.toArray();
		CreditCard cc1 = (CreditCard) newarr[this.currentCreditCard];
		this.ccCardExpiry = cc1.getCardExpiryDate();
		this.ccCardHolder = cc1.getCardHolderName();
		this.ccCardNumber = cc1.getCardNumber();
		
		Connection connection = null;
		  //Loading the JDBC driver for MySql
		Class.forName("com.mysql.jdbc.Driver");
    
		int i=0;
		//Getting a connection to the database. Change the URL parameters
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "testuser", "test");

	    //Creating a statement object
	    Statement stmt = connection.createStatement();
	    
	    
	    
	    FacesContext facesContext = FacesContext.getCurrentInstance();
		SignInRequest yourBean =
		facesContext.getApplication().evaluateExpressionGet(facesContext,
		"#{signInRequest}", SignInRequest.class); 
	    
	    
	    
	    
	    String t0="SELECT paymentid FROM test.creditcard WHERE " +
		"card_number ='" + this.ccCardNumber + "' AND card_holder_name='" 
		+ this.ccCardHolder + "' AND expiry_date='" + this.ccCardExpiry +		
		"';";
		stmt.executeQuery(t0);
		ResultSet rs = stmt.getResultSet();
		rs.beforeFirst();
		String pid=null;
		while (rs.next())
		{
			pid = rs.getString(1);
			Statement stmt2 = connection.createStatement();
			String t1 = "SELECT user_name FROM test.user_payment_relation WHERE "
				+ "idUSER_PAYMENT_RELATION='" + pid + "';";
			stmt2.executeQuery(t1);
			ResultSet rs2 = stmt2.getResultSet();
			rs2.last();
			String username = rs2.getString(1);			
			if (username.equals(yourBean.getUserSession().getUserId()))
			{
				
				this.oldpaymentid = pid;
				break;
			}
			
		}
		Statement stmt3 = connection.createStatement();
		String t3 = "SELECT idcreditcard FROM test.creditcard WHERE " +
			"card_number ='" + this.ccCardNumber + "' AND card_holder_name='" 
			+ this.ccCardHolder + "' AND  expiry_date='" + this.ccCardExpiry +	
			"' AND paymentid='" + pid + "';";
		stmt3.executeQuery(t3);
		rs = stmt3.getResultSet();
		rs.last();
		this.oldccid = rs.getString(1);
		
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
		Object[] newarr = this.realCcList.toArray();
		CreditCard cc = (CreditCard) newarr[this.currentCreditCard];
		setCcCardExpiry(cc.getCardExpiryDate());
		return ccCardExpiry;
	}
	public void setCcCardExpiry(String cc)
	{
		
		this.ccCardExpiry = cc;
	}
	
	public String goPrepaid()
	{
		
		
		return "signedin_editprepaid";
	}
	
	// add new credit card
	public String goCreditCard()
	{
		
		return "signedin_newcreditcard";
		
	}
	public String goEditCard()
	{
		
		
		return "signedin_editcreditcard";
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
	        	
	        /*
	        String t0 = "SELECT paymentid FROM test.creditcard WHERE card_number='" +
 			 this.ccCardNumber + "';";
	        stmt.executeQuery(t0);
	        ResultSet rs = stmt.getResultSet();
	        int vl = rs.getInt(1);
	        String t1 = "DELETE FROM test.creditcard WHERE card_number= '" 
	    		+ this.ccCardNumber + "');";
	        int count = stmt.executeUpdate(t1);
	        
	        String t2 = "DELETE FROM test.user_payment_relation WHERE payment_id= '" 
	    		+ String.valueOf(vl) + "');";
	        int count2 = stmt.executeUpdate(t1);
	        */
			String t3 = "UPDATE test.creditcard SET card_number='" + this.ccCardNumber 
			+ "', card_holder_name='" + this.ccCardHolder + "', expiry_date='" +
			this.ccCardExpiry + "' WHERE idcreditcard='"+ this.oldccid + "';";
			
			int count3 = stmt.executeUpdate(t3);
			
			
			
			
			
			
			
		
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
	        String vl = rs.getString(1);
	        String t1 = "DELETE FROM test.creditcard WHERE card_number= '" 
	    		+ this.ccCardNumber + "';";
	        int count = stmt.executeUpdate(t1);
	        
	        String t2 = "DELETE FROM test.user_payment_relation WHERE payment_id= '" 
	    		+ String.valueOf(vl) + "';";
	        int count2 = stmt.executeUpdate(t1);
	        
	        
			
		return "paymentMethodRequest";
	}
	
}
