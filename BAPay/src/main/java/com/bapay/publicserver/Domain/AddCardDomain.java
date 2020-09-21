package com.bapay.publicserver.Domain;

public class AddCardDomain {
	String cardInfo_id;
	String card_name;
	String card_company;
	String card_num;
	int card_cvc;
	String card_thru;
	String user_id;
	
	public String getCardInfo_id() {
		return cardInfo_id;
	}
	public void setCardInfo_id(String cardInfo_id) {
		this.cardInfo_id = cardInfo_id;
	}
	public String getCard_name() {
		return card_name;
	}
	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}
	public String getCard_company() {
		return card_company;
	}
	public void setCard_company(String card_company) {
		this.card_company = card_company;
	}
	public String getCard_num() {
		return card_num;
	}
	public void setCard_num(String card_num) {
		this.card_num = card_num;
	}
	public int getCard_cvc() {
		return card_cvc;
	}
	public void setCard_cvc(int card_cvc) {
		this.card_cvc = card_cvc;
	}
	public String getCard_thru() {
		return card_thru;
	}
	public void setCard_thru(String card_thru) {
		this.card_thru = card_thru;
	}
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "AddCardDomain [cardInfo_id=" + cardInfo_id + ", card_name=" + card_name + ", card_company="
				+ card_company + ", card_num=" + card_num + ", card_cvc=" + card_cvc + ", card_thru=" + card_thru
				+ ", user_id=" + user_id + "]";
	}
}
