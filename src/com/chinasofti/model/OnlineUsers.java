package com.chinasofti.model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import com.chinasofti.bean.Message;
import com.chinasofti.bean.User;

/**
 * �����û��б�����
 * @author Administrator
 *
 */
public class OnlineUsers {

	/**
	 * ���ڱ��������û���Ϣ�ļ���
	 */
	public static Map<User, ObjectOutputStream> users = new HashMap<User, ObjectOutputStream>();

	/*
	 * ���� --- ����ģʽ
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
	 * ��������û���Ϣ
	 * @param user
	 */
	public void add(User user, ObjectOutputStream oos) {
		users.put(user, oos);
	}

	public Map<User, ObjectOutputStream> getUsers(){
		return users;
	}
	/**
	 * ɾ�������û���Ϣ
	 * @param user
	 */
	public void delete(User user) {
		users.remove(user);
	}


	/**
	 * ������Ϣ��ĳ���ͻ���
	 * @param message
	 * @throws IOException 
	 */
	public void sendMessage(Message message) throws IOException {
		System.out.println(message.getReceiver());
		// ��ȡ��Խ����ߵ������
		ObjectOutputStream oos = users.get(message.getReceiver());
		System.out.println(oos);
		if(oos != null) {
			// ������Ϣ
			oos.writeObject(message);
			oos.flush();
		}
	}

	/**
	 * Ⱥ��
	 * @param message
	 * @throws IOException 
	 */
	public void sendToAll(Message message) throws IOException {
		if(users.size() > 0) {
			// ѭ�������û��������
			for(ObjectOutputStream oos : users.values()) {
				// ������Ϣ
				oos.writeObject(message);
				oos.flush();
			}
		}
	}

	/**
	 * ���ص�ǰ���������û�
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

