package ege;

public class CommunicationMethod {
	private Sms sms;
	private Email email;
	
	public Sms getSms()
	{
		return sms;
	}
	public Email getEmail()
	{
		return email;
	}
	public void setSms(Sms newsms)
	{
		this.sms = newsms;
	}
	public void setEmail(Email newemail)
	{
		this.email = newemail;
	}
}
