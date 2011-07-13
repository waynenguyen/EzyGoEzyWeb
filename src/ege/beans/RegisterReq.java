package ege.beans;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;



public interface RegisterReq extends FormReq{
	public void validateUserId(FacesContext context,
			UIComponent componentToValidate,
			Object value);
	
	public void validatePassword(FacesContext context,
			UIComponent componentToValidate,
			Object value);
	
	public void validateFirstName(FacesContext context,
			UIComponent componentToValidate,
			Object value);
	
	public void validateLastName(FacesContext context,
			UIComponent componentToValidate,
			Object value);
	
	public void validateAddress(FacesContext context,
			UIComponent componentToValidate,
			Object value);
	
	public void validateEmail(FacesContext context,
			UIComponent componentToValidate,
			Object value);
	
	
	public String getUserId() ;

	public void setUserId(String userId) ;

	public String getPassword() ;

	public void setPassword(String password) ;

	public String getFirstName() ;

	public void setFirstName(String firstName) ;

	public String getLastName() ;

	public void setLastName(String lastName) ;

	public String getAddress();

	public void setAddress(String address) ;
	
	public String getUserName();
	public void setUserName(String username);
	public boolean isRenderRegister() ;

	public void setRenderRegister(boolean renderRegister) ;
	
	public String register() ;

	
}
