package ege.beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import ege.User;

public interface UserSes {
	
	public String getUserIdMessage();

	public void setUserIdMessage(String userIdMessage);

	public String getPasswordMessage();

	public void setPasswordMessage(String passwordMessage);

	public String getUserId();
	public void setUserId(String userId);
	public String getPassword();
	public void setPassword(String password);
	
	public String getSMSNumber();
	public void setSMSNumber(String sms);
	
	public String getFirstName();
	public void setFirstName(String sms);
	
	public String getLastName();
	public void setLastName(String sms);
	
	public String getUserName();
	public void setUserName(String sms);
	
	public String getEmail();
	public void setEmail(String email);
	
	public boolean isSignedIn();
	public void setSignedIn(boolean signedIn);
	public boolean isRegistering();
	public void setRegistering(boolean registered);
	
	
	
	
	
	public boolean isRenderSignIn();
	public void setRenderSignIn(boolean renderSignIn);
	
	public boolean isRenderRegister();
	public void setRenderRegister(boolean renderRegister);
	
	public boolean isRenderSignOut();
	public void setRenderSignOut(boolean renderSignOut);
}
