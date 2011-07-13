package ege.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public class PrepaidRequest implements PrepaidReq {
	private long balance;
	private long topUpAmount;
	private List lit;
	
	
	
	public long getBalance()
	{
		return balance;
	}
	public void setBalance(long setBalance)
	{
		this.balance = setBalance;
	}
	public long getTopUpAmount()
	{
		return this.topUpAmount;
	}
	public void  setTopUpAmount(long topup)
	{
		this.topUpAmount = topup;
	}
	
	public List getLit() 
	{
		List topUpAmountList = new ArrayList();
		
		
			topUpAmountList.add(new SelectItem("10", "10"));
			topUpAmountList.add(new SelectItem("20","20"));
			topUpAmountList.add(new SelectItem("50", "50"));
			topUpAmountList.add(new SelectItem("100","100"));
			
		return topUpAmountList;
	}
	public String topUp()
	{
		setBalance( this.balance+ this.topUpAmount);
		
		return "prepaid";
	}
	
	public String saveAndGoBack()
	{
		// save to the database;
		return "choose";
		
	}
	@Override
	public void setUserSession(UserSes userSession) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserSes getUserSession() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
