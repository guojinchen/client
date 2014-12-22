package com.chinasofti.connection;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Map;
import java.util.Set;

import com.chinasofti.bean.User;
import com.chinasofti.frame.FrameManagerServer;
import com.chinasofti.frame.ServerManage;
import com.chinasofti.model.OnlineUsers;

/**
 * �û�ѭ�����տͻ��˵���������
 * @author Administrator
 *
 */
public class ServerAcceptThread implements Runnable {
	// ���ڱ�ʶ�Ƿ�ֹͣ����
	private boolean flag = true;
	
	/**
	 * ֹͣ�������˶Կͻ�������ļ���
	 */
	public void stop() {
		flag = false;
		//�ر������̵߳����������
		Map<User, ObjectOutputStream> map = OnlineUsers.getInstance().getUsers();
		
		Set<User> users = map.keySet();
		for(User user : users) {
			try {
				map.get(user).close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
	}
	
	public void run() {
		
		ServerSocket ss = null;
		ServerManage sm = FrameManagerServer.getInstance().getMainFrame();
		try {
			// �����������˶���
			ss = new ServerSocket(11111);
			sm.getAccounttxt().append("�������Ѿ���������\r\n");
			
			while(flag) {
				try {
					
					sm.getAccounttxt().append("���ڵȴ��ͻ������ӣ�\r\n");
					ss.setSoTimeout(4000);
					// ���տͻ��˵�����
					Socket s = ss.accept();
					sm.getAccounttxt().append(s.getInetAddress() + "�Ѿ�������������ϸ��ϢΪ��" + s + "\r\n");
					// �������տͻ�����Ϣ�߳�
					ClientMessageReceiver cmr = new ClientMessageReceiver(s);
					// �����߳�
					new Thread(cmr).start();
					
				} catch (SocketException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				} 

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ss.close();
				sm.getAccounttxt().append("�������Ѿ��رգ�����\r\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	} 
}
