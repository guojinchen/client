package com.chinasofti.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.chinasofti.bean.Company;


public class CompanyManager {
	
	File Data =new File("e:/��ҵ��ʱͨ/Mycompany.txt");
	
	/**
	 * ����˾��Ϣ����д�뱾���ļ�
	 * @param company Ҫд�Ķ���
	 * @return
	 */
	public boolean add(Company company) {
		
		Data.getParentFile().mkdirs();
		
		FileOutputStream os = null;
		ObjectOutputStream oos = null;
		try {
			
			os = new FileOutputStream(Data);
			oos = new ObjectOutputStream(os);
			
			oos.writeObject(company);
			oos.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				os.close();
				oos.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return true;
	}

	
	public Company getComp() {
		
		Company company = null;
		
		if(Data.exists()) {

			//����Ѿ����ڸù�˾����ϸ��Ϣ�ͽ������ϵͳ
			FileInputStream fis = null;
			ObjectInputStream ois = null;
			
			try {
				fis = new FileInputStream(Data);
				ois = new ObjectInputStream(fis);
				company = (Company) ois.readObject();
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					fis.close();
					ois.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		return company;	
	}
}
