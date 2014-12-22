package com.chinasofti.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * ����������ͻ�������Ϣ��������Ϣ����
 * @author Administrator
 *
 */
public class Message implements Serializable {
	private static final long serialVersionUID = 1632749104903396187L;
	
	// 0: �����½
	public static final int CLIENT_LOGIN = 0;
	// 1: ��ȡ���ߺ����б�
	public static final int CLIENT_GET_LIST = 1;
	// 2: ������Ϣ��ĳ��
	public static final int CLIENT_SEND = 2;
	// 3: �鿴ĳ������
	public static final int CLIENT_GET_USER_INFO = 3;
	// 4: �鿴��������
	public static final int CLIENT_GET_SELF_INFO = 4;
	// 5: �޸ĸ�������
	public static final int CLIENT_UPDATE_INFO = 5;
	// 6: ����Ⱥ��
	public static final int CLIENT_SEND_ALL_MESSAGE = 6;
	// 7: �˳�
	public static final int CLIENT_EXIT = 7;
	
	// ��½�ɹ�
	public static final int SERVER_LOGIN_OK           = 20;
	// �˺Ų�����
	public static final int SERVER_USERNAME_NOTFOUND  = 21;
	// �������
	public static final int SERVER_PASSWORD_FAIL      = 22;
	// �����б�
	public static final int SERVER_LIST               = 23;
	// �û���Ϣ
	public static final int SERVER_USER_INFO          = 24;
	// ������Ϣ
	public static final int SERVER_SELF_INFO          = 25;
	// �޸ĳɹ�
	public static final int SERVER_UPDATE_OK          = 26;
	//�޸�ʧ��
	
	public static final int SERVER_UPDATE_FIAL        = 27;
	// ��½����
	public static final int SERVER_USER_LOGIN         = 28;
	// ��������
	public static final int SERVER_USER_LOGOUT        = 29;
	
	/**
	 * ��Ϣ����
	 * 0: �����½
	 * 1: ��ȡ���ߺ����б�
	 * 2: ������Ϣ��ĳ��
	 * 3: �鿴ĳ������
	 * 4: �鿴��������
	 * 5: �޸ĸ�������
	 * 6: ����Ⱥ��
	 * 7: �˳�
	 */
	private int type;
	/**
	 * ������
	 */
	private User sender;
	/**
	 * ������
	 */
	private User receiver;
	/**
	 * ����ʱ��
	 */
	private Date sendTime;
	/**
	 * ���͵�����
	 */
	private String content;
	/**
	 * ��������
	 */
	private Object extData;
	
	public Message() {
		super();
	}
	public Message(int type, User sender, Date sendTime) {
		super();
		this.type = type;
		this.sender = sender;
		this.sendTime = sendTime;
	}
	public Message(int type, User sender, User receiver, Date sendTime,
			String content) {
		super();
		this.type = type;
		this.sender = sender;
		this.receiver = receiver;
		this.sendTime = sendTime;
		this.content = content;
	}
	public Message(int type,User receiver ,String content){
		this.type = type;
		this.receiver = receiver;
		this.content = content;
	}

	public String getContent() {
		return content;
	}
	public User getReceiver() {
		return receiver;
	}
	public User getSender() {
		return sender;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public int getType() {
		return type;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setExtData(Object extData) {
		this.extData = extData;
	}
	public Object getExtData() {
		return extData;
	}
	@Override
	public String toString() {
		return "Message [type=" + type + ", sender=" + sender + ", receiver="
				+ receiver + ", sendTime=" + sendTime + ", content=" + content
				+ "]";
	}
}
