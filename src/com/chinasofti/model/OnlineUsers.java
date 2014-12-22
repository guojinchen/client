package com.chinasofti.model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import com.chinasofti.bean.Message;
import com.chinasofti.bean.User;

/**
 * 在线用户列表容器
 * @author Administrator
 *
 */
public class OnlineUsers {

	/**
	 * 用于保存在线用户信息的集合
	 */
	public static Map<User, ObjectOutputStream> users = new HashMap<User, ObjectOutputStream>();

	/*
	 * 单例 --- 饱汉模式
	 */
	private static OnlineUsers instance = null;
	private OnlineUsers() {}
	public static OnlineUsers getInstance() {

		if(instance == null) {
			instance = new OnlineUsers();
		}
		return instance;
	}

	/**
	 * 添加在线用户信息
	 * @param user
	 */
	public void add(User user, ObjectOutputStream oos) {
		users.put(user, oos);
	}

	public Map<User, ObjectOutputStream> getUsers(){
		return users;
	}
	/**
	 * 删除在线用户信息
	 * @param user
	 */
	public void delete(User user) {
		users.remove(user);
	}


	/**
	 * 发送消息给某个客户端
	 * @param message
	 * @throws IOException 
	 */
	public void sendMessage(Message message) throws IOException {
		System.out.println(message.getReceiver());
		// 获取针对接受者的输出流
		ObjectOutputStream oos = users.get(message.getReceiver());
		System.out.println(oos);
		if(oos != null) {
			// 发送消息
			oos.writeObject(message);
			oos.flush();
		}
	}

	/**
	 * 群发
	 * @param message
	 * @throws IOException 
	 */
	public void sendToAll(Message message) throws IOException {
		if(users.size() > 0) {
			// 循环所有用户的输出流
			for(ObjectOutputStream oos : users.values()) {
				// 发送消息
				oos.writeObject(message);
				oos.flush();
			}
		}
	}

	/**
	 * 返回当前所有在线用户
	 * @return
	 */
	public Object[] getOnlineUsers() {
		return users.keySet().toArray();
	}


	//	public String getOnlineTable(){
	//		Object[] user =OnlineUsers.getInstance().getOnlineUsers();
	//		String s = null;
	//		for(int i = 0;i <user.length;i++){
	//			s +=((User) user[i]).getUsername()+"\n";
	//		}
	//		return  s;
	//	}
}

