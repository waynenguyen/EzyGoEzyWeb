package ege.beans;

public interface SignInReq extends FormReq{
	public String getUserId();
	public void setUserId(String userId) ;
	public String getPassword() ;
	public void setPassword(String password);
	
	public String getUserIdMessage();
	public void setUserIdMessage(String userIdMessage);
	public String getPasswordMessage();
	public void setPasswordMessage(String passwordMessage);
	
	public String register();
	public String signIn();
}
