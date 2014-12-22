package com.chinasofti.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.Map;

import com.chinasofti.bean.Message;
import com.chinasofti.bean.User;
import com.chinasofti.model.OnlineUsers;
import com.chinasofti.model.UserData;

public class ClientMessageReceiver implements Runnable {

	/**
	 * 针对客户端的输入流
	 */
	private ObjectInputStream ois = null;
	/**
	 * 针对客户端的输出流
	 */
	private ObjectOutputStream oos = null;
	// 用于标识是否停止监听
	private boolean flag = true;
	
	public ClientMessageReceiver(Socket socket) throws IOException {
		// 将针对客户端的输出流封装为对象输出流
		oos = new ObjectOutputStream(socket.getOutputStream());
//		oos.writeObject(null);
//		oos.flush();
		// 将针对客户端的输入流封装为对象输入流
		ois = new ObjectInputStream(socket.getInputStream());
	}
	
	@Override
	public void run() {
		try {
			// 针对用户信息操作的Model对象,已经存在的我们保存的
			
			UserData model =new UserData();
			// 在线用户列表对象
			OnlineUsers ou = OnlineUsers.getInstance();
			while(flag) {
				// 接收客户端的请求
				Message message = (Message) ois.readObject();
				System.out.println(message.getType());
				// 按客户端消息类型进行分类处理
				switch(message.getType()) {
					// 登陆
					case Message.CLIENT_LOGIN			:
						// 获取用户信息
						User user = message.getSender();
						// 根据账号获取服务器端保存的与之对应用户信息
						User serverUser = model.loadUser(user.getUsername());
						// 判断账号是否存在,如果为null则证明账号不存在
						if(serverUser != null) {
							// 判断密码是否相等
							if(user.getPasswrod().equals(serverUser.getPasswrod())) {
								// 登陆成功
								
//								// 提醒其他在线用户该用户登陆
//								Message toAllMessage = new Message();
//								toAllMessage.setSender(serverUser);
//								toAllMessage.setSendTime(new Date());
//								toAllMessage.setType(Message.SERVER_USER_LOGIN);
//								ou.sendToAll(message);//通过在线用户列表获取他们的输出流写出去：：：：
								
								
								// 将该用户的信息保存在在线用户列表中
								ou.add(serverUser, oos);
								
								// 向所有客户端发送当前最新的在线列表
								// 根据在线列表对象获取所有当前在线的用户的集合
								Object[] ous = ou.getOnlineUsers();
								
								// 告诉客户端说:登陆成功
								Message returnMessage = new Message(Message.SERVER_LOGIN_OK, null, new Date());
								returnMessage.setReceiver(serverUser);
								ou.sendMessage(returnMessage);
								
								// 将在线列表封装到message对象中返回到客户端
								Message ousMessage = new Message();
								ousMessage.setType(Message.SERVER_LIST);
								ousMessage.setReceiver(message.getSender());
								ousMessage.setExtData(ous);
								ousMessage.setSendTime(new Date());
								ou.sendToAll(ousMessage);
								

								
							} else {
								// 返回消息给客户端,说: 密码错误
								Message returnMessage = new Message(Message.SERVER_PASSWORD_FAIL, null, new Date());
								returnMessage.setReceiver(serverUser);
								returnMessage.setContent("密码错误");
//								ou.sendMessage(returnMessage);
								oos.writeObject(returnMessage);
							}
						} else {
							// 返回消息给客户端,说:账号不存在
							// 返回消息给客户端,说: 密码错误
							Message returnMessage = new Message(Message.SERVER_USERNAME_NOTFOUND, null, new Date());
							returnMessage.setReceiver(serverUser);
							returnMessage.setContent("账号不存在");
//							ou.sendMessage(returnMessage);
							oos.writeObject(returnMessage);
						}
						break;
					// 发送消息
					case Message.CLIENT_SEND            :
						// 转发消息到接受者
						ou.sendMessage(message);
						
						break;
					// 获取当前在线好友列表
					case Message.CLIENT_GET_LIST        :
						
						// 根据在线列表对象获取所有当前在线的用户的集合
						Object[] ous = ou.getOnlineUsers();
						
						// 将在线列表封装到message对象中返回到客户端
						Message ousMessage = new Message();
						ousMessage.setType(Message.SERVER_LIST);
						ousMessage.setReceiver(message.getSender());
						ousMessage.setExtData(ous);
						ousMessage.setSendTime(new Date());
						oos.writeObject(ousMessage);
						break;
					// 查看某人资料
					case Message.CLIENT_GET_USER_INFO  :
					Message friendsMessage = new Message();
						friendsMessage.setType(Message.SERVER_USER_INFO);
						Map map = model.getUserMap();
						//获取好友用户的最新信息
						User frindUser =(User) map.get(message.getReceiver().getUsername());
						//以好友为发送者，我为接受者，将message封装
						friendsMessage.setReceiver(frindUser);
						friendsMessage.setSender(message.getReceiver());
						ou.sendMessage(friendsMessage);

						break;
					// 修改个人资料
					case Message.CLIENT_UPDATE_INFO     : 
						Map<String, User> m = model.getUserMap();
						m.put(message.getSender().getUsername(), message.getSender());
						model.inPutMap(m);
						Message selfMessage = new Message(Message.SERVER_SELF_INFO,message.getSender(),"");
						ou.sendMessage(selfMessage);
						break;
					// 发起群聊
					case Message.CLIENT_SEND_ALL_MESSAGE:
						
						for(User u:OnlineUsers.users.keySet()){
							System.out.println("++++++++++++++");
							System.out.println(u.getUsername());
							System.out.println(OnlineUsers.users.get(u));
							
							OnlineUsers.users.get(u).writeObject(message);
							OnlineUsers.users.get(u).flush();
						
						}
						System.out.println("++++++++++++++");
						
						break;
					// 退出下线将起名字在主窗口消去下线人名单
					case Message.CLIENT_EXIT            : 
						ou.delete(message.getSender());
						Object[] Data= ou.getOnlineUsers();
						Message listMessage = new Message();
						listMessage.setType(Message.SERVER_LIST );
						listMessage.setExtData(Data);
						ou.sendToAll(listMessage);
						break;
					
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
