/**
 * 
 */
package ege.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import ege.User;

/**
 * @author g0701107
 *
 */
public class UserSession implements Serializable, UserSes{
	private String userId;
	private String password;
	
	private String userIdMessage;
	private String passwordMessage;
	
	private String smsNumber;
	
	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	
	
	
	
	
	private boolean signedIn;
	private boolean registering;
	
	private boolean renderSignIn;
	private boolean renderRegister;
	private boolean renderSignOut;
	
	public ege.User getUser() {
		return user;
	}

	public void setUser(ege.User user) {
		this.user = user;
	}

	private ege.User user;
	
	public UserSession() {
		renderSignIn = true;
		renderRegister = true;
		renderSignOut = false;
		
		signedIn = false;
		registering = false;
	}
	
	public String getUserIdMessage() {
		return userIdMessage;
	}

	public void setUserIdMessage(String userIdMessage) {
		this.userIdMessage = userIdMessage;
	}

	public String getPasswordMessage() {
		return passwordMessage;
	}

	public void setPasswordMessage(String passwordMessage) {
		this.passwordMessage = passwordMessage;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() 
	{
		return firstName;
	}
	public void setFirstName(String name)
	{
		this.firstName = name;
	}

	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String name)
	{
		this.lastName = name;
	}
	public String getUserName() 
	{
		return userName;
	}
	public void setUserName(String name)
	{
		this.userName = name;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	
	
	public String getSMSNumber() {
		return smsNumber;
	}
	
	public void setSMSNumber(String sms) {
		this.smsNumber = sms;
	}
	
	public boolean isSignedIn() {
		return signedIn;
	}
	public void setSignedIn(boolean signedIn) {
		this.signedIn = signedIn;
	}
	public boolean isRegistering() {
		return registering;
	}
	public void setRegistering(boolean registered) {
		this.registering = registered;
	}
	
	public boolean isRenderSignIn() {
		return renderSignIn;
	}
	public void setRenderSignIn(boolean renderSignIn) {
		this.renderSignIn = renderSignIn;
	}
	public boolean isRenderRegister() {
		return renderRegister;
	}
	public void setRenderRegister(boolean renderRegister) {
		this.renderRegister = renderRegister;
	}
	
	
	
	public boolean isRenderSignOut() {
		return renderSignOut;
	}

	public void setRenderSignOut(boolean renderSignOut) {
		this.renderSignOut = renderSignOut;
	}
	
	
	
	
	
	
	
}
