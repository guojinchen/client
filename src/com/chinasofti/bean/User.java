package com.chinasofti.bean;

import java.io.Serializable;

/**
 * 用户类
 * 
 * @author Administrator
 * 
 */
public class User implements Serializable {
	private static final long serialVersionUID = 511142319526449270L;

	/**
	 * 账号
	 */
	private String username;
	/**
	 * 密码
	 */
	private String passwrod;
	/**
	 * 昵称
	 */
	private String nickName;

	// 真实的姓名

	private String realname;

	// 电话

	private String phone;

	// 个人简介

	private String intriduce;

	// 所属部门

	private String dept;

	// 电子邮件

	private String eMail;
	//地址
	private String address;
	
	private String headPic;

	// 性别

	public User(String username, String passwrod, String nickName,
			String realname, String phone, String intriduce, String dept,
			String mail, String address, String headPic, String sex) {
		super();
		this.username = username;
		this.passwrod = passwrod;
		this.nickName = nickName;
		this.realname = realname;
		this.phone = phone;
		this.intriduce = intriduce;
		this.dept = dept;
		eMail = mail;
		this.address = address;
		this.headPic = headPic;
		this.sex = sex;
	}

	public String getEMail() {
		return eMail;
	}

	public void setEMail(String mail) {
		eMail = mail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	private String sex;

	public User(String username, String passwrod, String nickName,
			String realname,String phone, String intriduce, String dept,
			String eMail,String address, String sex) {
		super();
		this.username = username;
		this.passwrod = passwrod;
		this.nickName = nickName;
		this.realname = realname;
		this.phone = phone;
		this.intriduce = intriduce;
		this.dept = dept;
		this.eMail = eMail;
		this.address = address;
		this.sex = sex;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIntriduce() {
		return intriduce;
	}

	public void setIntriduce(String intriduce) {
		this.intriduce = intriduce;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getE_mail() {
		return eMail;
	}

	public void setE_mail(String eMail) {
		this.eMail = eMail;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public User() {
		super();
	}

	public User(String username, String passwrod, String nickName) {
		super();
		this.username = username;
		this.passwrod = passwrod;
		this.nickName = nickName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public String getNickName() {
		return nickName;
	}

	public String getPasswrod() {
		return passwrod;
	}

	public String getUsername() {
		return username;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setPasswrod(String passwrod) {
		this.passwrod = passwrod;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return nickName + "("+dept+")";
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
}
