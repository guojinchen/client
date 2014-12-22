package com.chinasofti.model;

import java.io.*;
import java.util.Map;
import java.util.Set;

import com.chinasofti.bean.User;

/**
 * 用户信息的模型类(对用户信息的数据操作)
 * @author Administrator
 *
 */
public class UserData {
	
	
	/*
	 * 用于保存用户信息到服务器本地
	 */
	File Data = new File("D:/MyUserData.txt");


	/**
	 * 根据账号获取用户信息
	 * @param username 账号
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
		
		
	//从服务器本地获得存储数据的容器Map	
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
	//将存放有好友数据的Map放入服务器本地
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
	 * 保存用户信息或 修改用户信息
	 * @param user
	 */
	public void saveOrupdateUser(User user) {
		Map<String,User> map =(Map<String,User>)getUserMap();
		map.put(user.getUsername(),user );
		inPutMap(map );
	}
	
	/**
	 * 删除用户信息
	 * @param user
	 */
	public void deleteUser(String userName) {
		Map<String,User> map =(Map<String,User>)getUserMap();
		map.remove(userName);
		inPutMap(map );
	}
	

	/**
	 * 获取服务器上所有的用户信息
	 * @return
	 */
	public Set<String> getUsers() {
		if(getUserMap() != null ) {
			
			return (Set<String>)getUserMap().keySet();
			
		}
		return null;
		
	}
}
