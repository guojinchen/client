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
 * 用户循环接收客户端的连接请求
 * @author Administrator
 *
 */
public class ServerAcceptThread implements Runnable {
	// 用于标识是否停止监听
	private boolean flag = true;
	
	/**
	 * 停止服务器端对客户端请求的监听
	 */
	public void stop() {
		flag = false;
		//关闭所有线程的输入输出流
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
			// 创建服务器端对象
			ss = new ServerSocket(11111);
			sm.getAccounttxt().append("服务器已经启动！！\r\n");
			
			while(flag) {
				try {
					
					sm.getAccounttxt().append("正在等待客户端连接！\r\n");
					ss.setSoTimeout(4000);
					// 接收客户端的请求
					Socket s = ss.accept();
					sm.getAccounttxt().append(s.getInetAddress() + "已经连接上来，详细信息为：" + s + "\r\n");
					// 启动接收客户端消息线程
					ClientMessageReceiver cmr = new ClientMessageReceiver(s);
					// 启动线程
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
				sm.getAccounttxt().append("服务器已经关闭！！！\r\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	} 
}
