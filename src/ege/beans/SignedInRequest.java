package ege.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.faces.context.FacesContext;

import ege.CreditCard;

public class SignedInRequest implements SignedInReq{
	private String userId;
	private UserSes userSession;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public void setUserSession(UserSes userSession) {
		this.userSession = userSession;
	}
	
	@Override
	public UserSes getUserSession() {
		return userSession;
	}
	
	public SignedInRequest() {
		
	}
	public String SignIn()
	{
		return "signedin";
	}
	public String Register()
	{
		return "register12";
	}
	public String cancel()
	{
		return "signedin";
	}
	public String edit() throws Exception
	{
		Connection connection = null;
		  //Loading the JDBC driver for MySql
        Class.forName("com.mysql.jdbc.Driver");
        
        int i=0;
        //Getting a connection to the database. Change the URL parameters
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "testuser", "test");

        //Creating a statement object
        Statement stmt = connection.createStatement();

    	FacesContext facesContext = FacesContext.getCurrentInstance();
		SignedIn_PersonalRequest yourBean =
		facesContext.getApplication().evaluateExpressionGet(facesContext,
		"#{signedIn_PersonalRequest}", SignedIn_PersonalRequest.class); 
		
		SignInRequest yourBean1 =
		facesContext.getApplication().evaluateExpressionGet(facesContext,
		"#{signInRequest}", SignInRequest.class);
		
		stmt.executeQuery("SELECT first_name, last_name,email, address, password FROM test.user WHERE user_name ='" + yourBean1.getUserId()+ "';");
		ResultSet rs = stmt.getResultSet();
		rs.last();
		String firstname = rs.getString(1);
		String lastname = rs.getString(2);
		String email = rs.getString(3);
		String address = rs.getString(4);
		String password = rs.getString(5);
		yourBean.setUserName(yourBean1.getUserId());
		yourBean.setAddress(address);
		yourBean.setEmail(email);
		yourBean.setFirstName(firstname);
		yourBean.setLastName(lastname);
		yourBean.setPassword(password);
		return "editpersonalinfo";
	}
	public String edit1() throws Exception
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SignedIn_CommMethodRequest yourBean =
		facesContext.getApplication().evaluateExpressionGet(facesContext,
		"#{signedIn_CommMethodRequest}", SignedIn_CommMethodRequest.class); 
		
		SignInRequest yourBean1 =
			facesContext.getApplication().evaluateExpressionGet(facesContext,
			"#{signInRequest}", SignInRequest.class);
		Connection connection = null;
		  //Loading the JDBC driver for MySql
    Class.forName("com.mysql.jdbc.Driver");
    
    int i=0;
    //Getting a connection to the database. Change the URL parameters
    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "testuser", "test");

    //Creating a statement object
    Statement stmt = connection.createStatement();
	String t0="SELECT idUSER_COMMUNICATION_RELATION FROM test.user_communication_relation WHERE " +
	"user_name ='" + yourBean1.getUserId()+	"' AND comm_id='1';";
	stmt.executeQuery(t0);
	ResultSet rs = stmt.getResultSet();
	String commid = null;
	try {
	rs.last();
	 commid = rs.getString(1);
	}
	catch (Exception x)
	{
		
	}
		
	stmt.executeQuery("SELECT phone_number FROM test.sms WHERE " +
			"user_comm_relationid ='" + commid +"';");
	rs = stmt.getResultSet();
	try {
	rs.last();
	String ttt = rs.getString(1);
	yourBean.setSmsNumber(ttt);
	} catch (Exception x)
	{
		
	}
	
	String tdelete = "DELETE FROM test.sms WHERE " +
	"user_comm_relationid ='" + commid +"';";
	stmt.executeUpdate(tdelete);
	
	 tdelete = "DELETE FROM test.user_communication_relation WHERE "
	+ "user_name ='" + yourBean1.getUserId()+	"' AND comm_id='1';";
	stmt.executeUpdate(tdelete);
	
	
	
		
		return "editcommmethodinfo";
	}
	public String edit3() throws Exception
	{
		Connection connection = null;
		  //Loading the JDBC driver for MySql
      Class.forName("com.mysql.jdbc.Driver");
      
      int i=0;
      //Getting a connection to the database. Change the URL parameters
      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "testuser", "test");

      //Creating a statement object
      Statement stmt = connection.createStatement();

  	FacesContext facesContext = FacesContext.getCurrentInstance();
		SignedIn_PrepaidRequest yourBean =
		facesContext.getApplication().evaluateExpressionGet(facesContext,
		"#{signedIn_PrepaidRequest}", SignedIn_PrepaidRequest.class); 
		
		SignInRequest yourBean1 =
		facesContext.getApplication().evaluateExpressionGet(facesContext,
		"#{signInRequest}", SignInRequest.class);
	
		
		String t0="SELECT idUSER_PAYMENT_RELATION FROM test.user_payment_relation WHERE " +
		"user_name ='" + yourBean1.getUserId()+	"' AND payment_id='1';";
		stmt.executeQuery(t0);
		ResultSet rs = stmt.getResultSet();
		rs.last();
		String paymentid = rs.getString(1);
		
		stmt.executeQuery("SELECT balance FROM test.prepaid WHERE " +
				"paymentid ='" + paymentid +"';");
		rs = stmt.getResultSet();
		rs.last();
		long balance = rs.getLong(1);
		yourBean.setBalance(balance);
		
		
		
		ResultSet rs1 = null;

		SignedIn_PaymentMethodRequest yourBean2 =
		facesContext.getApplication().evaluateExpressionGet(facesContext,
		"#{signedIn_PaymentMethodRequest}", SignedIn_PaymentMethodRequest.class);
		yourBean2.clearall();
		while (true)
		{
			String t1="SELECT idUSER_PAYMENT_RELATION FROM test.user_payment_relation WHERE " +
			"user_name ='" + yourBean1.getUserId()+	"' AND payment_id='2';";
			stmt.executeQuery(t1);
			 rs1= stmt.getResultSet();
			rs1.beforeFirst();
			while (rs1.next())
			{
				String creditpaymentid = rs1.getString(1);
				Statement stmt2 = connection.createStatement();

				stmt2.executeQuery("SELECT card_number, card_holder_name, expiry_date, idcreditcard FROM test.creditcard WHERE " +
						"paymentid ='" + creditpaymentid +"';");
				ResultSet rs2 = stmt2.getResultSet();
				try
				{
				rs2.last();
				String cardnumber = rs2.getString(1);
				String cardholdername = rs2.getString(2);
				String expirydate = rs2.getString(3);
				String ccid = rs2.getString(4);
				CreditCard cc = new CreditCard();
				cc.setCardExpiryDate(expirydate);
				cc.setCardHolderName(cardholdername);
				cc.setCardNumber(cardnumber);
				
				yourBean2.setoldpaymentid(creditpaymentid);
				yourBean2.addCCWithoutDB(cc);
				}
				catch (Exception e)
				{}
			}
			break;
		}
		
		
		
		
		
		
		
		
		
		
		
		return "editpaymentmethod";
	}
	public String logOut()
	{
		return "index";
	}
	public String update()
	{
		// TODO: update the db here
		return "signedin";
		
	}
	
}
