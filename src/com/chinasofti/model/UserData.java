package com.chinasofti.model;

import java.io.*;
import java.util.Map;
import java.util.Set;

import com.chinasofti.bean.User;

/**
 * �û���Ϣ��ģ����(���û���Ϣ�����ݲ���)
 * @author Administrator
 *
 */
public class UserData {
	
	
	/*
	 * ���ڱ����û���Ϣ������������
	 */
	File Data = new File("D:/MyUserData.txt");


	/**
	 * �����˺Ż�ȡ�û���Ϣ
	 * @param username �˺�
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
		
		
	//�ӷ��������ػ�ô洢���ݵ�����Map	
	@SuppressWarnings("unchecked")
	public Map<String,User> getUserMap() {
		   Map<String,User> users = null ;

			if(Data.exists()) {
				try {
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Data));
					users =(Map<String,User>)ois.readObject();
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}

			 return users; 
	}	
	//������к������ݵ�Map�������������
	public void inPutMap(Map<String,User> map ){
		
		Data.getParentFile().mkdirs();
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Data));
			oos.writeObject(map);
			oos.flush();
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	public User loadUser(String username) {
		if(getUserMap() != null) {
			return (User)getUserMap().get(username);
		}
		return null;
	}
	
	/**
	 * �����û���Ϣ�� �޸��û���Ϣ
	 * @param user
	 */
	public void saveOrupdateUser(User user) {
		Map<String,User> map =(Map<String,User>)getUserMap();
		map.put(user.getUsername(),user );
		inPutMap(map );
	}
	
	/**
	 * ɾ���û���Ϣ
	 * @param user
	 */
	public void deleteUser(String userName) {
		Map<String,User> map =(Map<String,User>)getUserMap();
		map.remove(userName);
		inPutMap(map );
	}
	

	/**
	 * ��ȡ�����������е��û���Ϣ
	 * @return
	 */
	public Set<String> getUsers() {
		if(getUserMap() != null ) {
			
			return (Set<String>)getUserMap().keySet();
			
		}
		return null;
		
	}
}
