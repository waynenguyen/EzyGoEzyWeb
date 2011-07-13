package ege;

public class Payment {
	private CreditCard creditCard;
	private Prepaid prepaid;
		
	public CreditCard getCreditCard()
	{
		return creditCard;
	}
	
	public Prepaid getPrepaid()
	{
		return prepaid;
	}
	
	public void setCreditCard(CreditCard newcreditcard)
	{
		this.creditCard = newcreditcard;
	}
	
	public void setPrepaid(Prepaid newprepaid)
	{
		this.prepaid = newprepaid;
	}
	
	
}
