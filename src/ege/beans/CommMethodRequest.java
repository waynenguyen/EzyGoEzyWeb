package ege.beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class CommMethodRequest implements CommMethodReq{
	private UserSes userSession;
	private String smsNumber;
	@Override
	public UserSes getUserSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUserSession(UserSes userSession) {
		this.userSession = userSession;
	}

	
	public void setSmsNumber(String sms)
	{
		
		this.smsNumber = sms;
	}
	public String getSmsNumber()
	{
		return smsNumber;
	}
	
	public String next() {
		if (smsNumber.length()<5)
		{
			FacesContext facesContext;
			facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null , new FacesMessage("Your number cannot contain less than 5 digits. Please enter again", 
					"Please choose another one."));
			return "choosecommmethod";
		}
		for (int i=0; i< this.smsNumber.length(); i++)
		{
			if (!Character.isDigit(smsNumber.charAt(i)))
			{
				FacesContext facesContext;
				facesContext = FacesContext.getCurrentInstance();
    			facesContext.addMessage(null , new FacesMessage("Your number cannot contain letters. Please enter again", 
    					"Please choose another one."));
				return "choosecommmethod";
			}
		}
		
		
		
		return "choosePaymentMethod";
	}

	
	public String back() {	
		
		return "register";
	}
	
	public String addNumber() {
		if (smsNumber.length()<5)
		{
			FacesContext facesContext;
			facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null , new FacesMessage("Your number cannot contain less than 5 digits. Please enter again", 
					"Please choose another one."));
			return "choosecommmethod";
		}
		for (int i=0; i< this.smsNumber.length(); i++)
		{
			if (!Character.isDigit(smsNumber.charAt(i)))
			{
				FacesContext facesContext;
				facesContext = FacesContext.getCurrentInstance();
    			facesContext.addMessage(null , new FacesMessage("Your number cannot contain letters. Please enter again", 
    					"Please choose another one."));
				return "choosecommmethod";
			}
		}
		this.userSession.setSMSNumber(this.smsNumber);
		
		return "choose1";
	}
	

	
	
}
