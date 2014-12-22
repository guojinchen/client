package com.chinasofti.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 服务器端与客户端做信息交互的消息对象
 * @author Administrator
 *
 */
public class Message implements Serializable {
	private static final long serialVersionUID = 1632749104903396187L;
	
	// 0: 代表登陆
	public static final int CLIENT_LOGIN = 0;
	// 1: 获取在线好友列表
	public static final int CLIENT_GET_LIST = 1;
	// 2: 发送消息给某人
	public static final int CLIENT_SEND = 2;
	// 3: 查看某人资料
	public static final int CLIENT_GET_USER_INFO = 3;
	// 4: 查看自身资料
	public static final int CLIENT_GET_SELF_INFO = 4;
	// 5: 修改个人资料
	public static final int CLIENT_UPDATE_INFO = 5;
	// 6: 发起群聊
	public static final int CLIENT_SEND_ALL_MESSAGE = 6;
	// 7: 退出
	public static final int CLIENT_EXIT = 7;
	
	// 登陆成功
	public static final int SERVER_LOGIN_OK           = 20;
	// 账号不存在
	public static final int SERVER_USERNAME_NOTFOUND  = 21;
	// 密码错误
	public static final int SERVER_PASSWORD_FAIL      = 22;
	// 在线列表
	public static final int SERVER_LIST               = 23;
	// 用户信息
	public static final int SERVER_USER_INFO          = 24;
	// 个人信息
	public static final int SERVER_SELF_INFO          = 25;
	// 修改成功
	public static final int SERVER_UPDATE_OK          = 26;
	//修改失败
	
	public static final int SERVER_UPDATE_FIAL        = 27;
	// 登陆提醒
	public static final int SERVER_USER_LOGIN         = 28;
	// 下线提醒
	public static final int SERVER_USER_LOGOUT        = 29;
	
	/**
	 * 消息类型
	 * 0: 代表登陆
	 * 1: 获取在线好友列表
	 * 2: 发送消息给某人
	 * 3: 查看某人资料
	 * 4: 查看自身资料
	 * 5: 修改个人资料
	 * 6: 发起群聊
	 * 7: 退出
	 */
	private int type;
	/**
	 * 发送者
	 */
	private User sender;
	/**
	 * 接收者
	 */
	private User receiver;
	/**
	 * 发送时间
	 */
	private Date sendTime;
	/**
	 * 发送的内容
	 */
	private String content;
	/**
	 * 额外数据
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
