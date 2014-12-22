package com.chinasofti.frame;

import java.io.Serializable;

public class Dept implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String detpInfo ;
	String introduce;
	public Dept(){}
	@Override
	public String toString() {
		return "DeptInfo [detpInfo=" + detpInfo + ", introduce=" + introduce
				+ "]";
	}
	public Dept(String detpInfo, String introduce) {
		super();
		this.detpInfo = detpInfo;
		this.introduce = introduce;
	}
	public String getDetpInfo() {
		return detpInfo;
	}
	public void setDetpInfo(String detpInfo) {
		this.detpInfo = detpInfo;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	

}
