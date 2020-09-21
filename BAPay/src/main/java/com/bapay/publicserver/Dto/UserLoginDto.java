package com.bapay.publicserver.Dto;

public class UserLoginDto {
    private String keyId;

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	@Override
	public String toString() {
		return "UserLoginDto [keyId=" + keyId + "]";
	}
    
}
