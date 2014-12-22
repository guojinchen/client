package com.chinasofti.bean;

import java.io.Serializable;

public class Company implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String corpname;
	private String address;
	private String domain;
	private String email;
	private String phone;
	private String fax;

	@Override
	public String toString() {
		return "Company [address=" + address + ", corpname=" + corpname
				+ ", domain=" + domain + ", email=" + email + ", fax=" + fax
				+ ", phone=" + phone + "]";
	}

	public Company() {
		super();
	}

	public Company(String corpname, String address, String domain,
			String email, String phone, String fax) {
		super();
		this.corpname = corpname;
		this.address = address;
		this.domain = domain;
		this.email = email;
		this.phone = phone;
		this.fax = fax;
	}

	public String getCorpname() {
		return corpname;
	}

	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

}
