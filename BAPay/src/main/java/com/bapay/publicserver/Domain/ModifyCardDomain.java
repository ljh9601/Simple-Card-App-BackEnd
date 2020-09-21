package com.bapay.publicserver.Domain;

public class ModifyCardDomain {
	String cardInfo_id;
	String new_name;
	
		
	public String getNew_name() {
		return new_name;
	}
	public void setNew_name(String new_name) {
		this.new_name = new_name;
	}
	public String getCardInfo_id() {
		return cardInfo_id;
	}
	public void setCardInfo_id(String cardInfo_id) {
		this.cardInfo_id = cardInfo_id;
	}
}
