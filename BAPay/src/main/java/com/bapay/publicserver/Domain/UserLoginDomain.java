package com.bapay.publicserver.Domain;


/**
 * @Role Send Controller refined Data
 * @Made 2020.09.13
 * @author Janghaeng
 *
 */
public class UserLoginDomain {
	private String user_id;
    private String name;
    private int code;
    
 

	public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "UserLoginDomain [keyId=" + user_id + ", name=" + name + ", code=" + code + "]";
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
    
}
