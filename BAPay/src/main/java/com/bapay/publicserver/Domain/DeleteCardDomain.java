package com.bapay.publicserver.Domain;

public class DeleteCardDomain {
	String cardInfo_id;

	public String getCardInfo_id() {
		return cardInfo_id;
	}

	public void setCardInfo_id(String cardInfo_id) {
		this.cardInfo_id = cardInfo_id;
	}

	@Override
	public String toString() {
		return "DeleteCardDomain [cardInfo_id=" + cardInfo_id + "]";
	}
}
