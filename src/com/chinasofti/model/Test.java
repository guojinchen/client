package com.chinasofti.model;

import java.io.*;
import java.util.*;

import com.chinasofti.bean.User;
public class Test {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		ObjectOutputStream oos = null;
		try {
			 oos = new ObjectOutputStream(new FileOutputStream("D:/MyUserData.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		User i = new User();
		i.setUsername("jiangxing");
		i.setPasswrod("123");
//		Dept i1 = new Dept("资源部","123");
//		Dept i2 = new Dept("技术部","123");
		Map m = new HashMap();
		m.put(i.getUsername(),i);
//		m.put(i1.getDetpInfo(),i1);
//		m.put(i2.getDetpInfo(),i2);
			try {
				oos.writeObject(m);
			} catch (IOException e) {

				e.printStackTrace();
			}	
}
}
