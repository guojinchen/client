package com.chinasofti.model;

import java.io.*;
import java.util.*;


import com.chinasofti.bean.User;
import com.chinasofti.frame.Dept;
public class DeptInfo {
	
	File Data =new File("e:/企业即时通/MyDeptInfo.txt");
	
	@SuppressWarnings("unchecked")
	public Map<String,Dept> getDeptMap() {
		Map<String,Dept>  deptInfo = null ;
		if(Data.exists()) {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Data));
				deptInfo =(Map<String,Dept>)ois.readObject();
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		 return deptInfo; 
	}	
	//将存放有好友数据的Map放入服务器本地
	public void inPutDeptMap(Map<String,Dept>  map ){
		
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
	
	public Set<String> getAllDpet() {
		Map<String,Dept> map =(Map<String,Dept>)getDeptMap();
		if(map != null) {
			return (Set<String>)map.keySet();
		}else {
			return null;
		}
		
	}
}
	



