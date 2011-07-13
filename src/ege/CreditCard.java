package ege;
import java.sql.*;
public class CreditCard {
	private String cardNumber;
	private String cardHolderName;
	private String cardExpiryDate;
	
	public String getCardNumber()
	{
		return cardNumber;
	}
	public String getCardHolderName()
	{
		return cardHolderName;
	}
	public String getCardExpiryDate()
	{
		return cardExpiryDate;
	}
	public void setCardExpiryDate(String expirydate)
	{
		this.cardExpiryDate = expirydate;
	}
	
	public void setCardNumber(String newCardNumber)
	{
		this.cardNumber = newCardNumber;
	}
	public void setCardHolderName(String newCardHolderName)
	{
		

		this.cardHolderName = newCardHolderName;
	}
	
}
