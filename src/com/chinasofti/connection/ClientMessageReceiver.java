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
	 * ��Կͻ��˵�������
	 */
	private ObjectInputStream ois = null;
	/**
	 * ��Կͻ��˵������
	 */
	private ObjectOutputStream oos = null;
	// ���ڱ�ʶ�Ƿ�ֹͣ����
	private boolean flag = true;
	
	public ClientMessageReceiver(Socket socket) throws IOException {
		// ����Կͻ��˵��������װΪ���������
		oos = new ObjectOutputStream(socket.getOutputStream());
//		oos.writeObject(null);
//		oos.flush();
		// ����Կͻ��˵���������װΪ����������
		ois = new ObjectInputStream(socket.getInputStream());
	}
	
	@Override
	public void run() {
		try {
			// ����û���Ϣ������Model����,�Ѿ����ڵ����Ǳ����
			
			UserData model =new UserData();
			// �����û��б����
			OnlineUsers ou = OnlineUsers.getInstance();
			while(flag) {
				// ���տͻ��˵�����
				Message message = (Message) ois.readObject();
				System.out.println(message.getType());
				// ���ͻ�����Ϣ���ͽ��з��ദ��
				switch(message.getType()) {
					// ��½
					case Message.CLIENT_LOGIN			:
						// ��ȡ�û���Ϣ
						User user = message.getSender();
						// �����˺Ż�ȡ�������˱������֮��Ӧ�û���Ϣ
						User serverUser = model.loadUser(user.getUsername());
						// �ж��˺��Ƿ����,���Ϊnull��֤���˺Ų�����
						if(serverUser != null) {
							// �ж������Ƿ����
							if(user.getPasswrod().equals(serverUser.getPasswrod())) {
								// ��½�ɹ�
								
//								// �������������û����û���½
//								Message toAllMessage = new Message();
//								toAllMessage.setSender(serverUser);
//								toAllMessage.setSendTime(new Date());
//								toAllMessage.setType(Message.SERVER_USER_LOGIN);
//								ou.sendToAll(message);//ͨ�������û��б��ȡ���ǵ������д��ȥ��������
								
								
								// �����û�����Ϣ�����������û��б���
								ou.add(serverUser, oos);
								
								// �����пͻ��˷��͵�ǰ���µ������б�
								// ���������б�����ȡ���е�ǰ���ߵ��û��ļ���
								Object[] ous = ou.getOnlineUsers();
								
								// ���߿ͻ���˵:��½�ɹ�
								Message returnMessage = new Message(Message.SERVER_LOGIN_OK, null, new Date());
								returnMessage.setReceiver(serverUser);
								ou.sendMessage(returnMessage);
								
								// �������б��װ��message�����з��ص��ͻ���
								Message ousMessage = new Message();
								ousMessage.setType(Message.SERVER_LIST);
								ousMessage.setReceiver(message.getSender());
								ousMessage.setExtData(ous);
								ousMessage.setSendTime(new Date());
								ou.sendToAll(ousMessage);
								

								
							} else {
								// ������Ϣ���ͻ���,˵: �������
								Message returnMessage = new Message(Message.SERVER_PASSWORD_FAIL, null, new Date());
								returnMessage.setReceiver(serverUser);
								returnMessage.setContent("�������");
//								ou.sendMessage(returnMessage);
								oos.writeObject(returnMessage);
							}
						} else {
							// ������Ϣ���ͻ���,˵:�˺Ų�����
							// ������Ϣ���ͻ���,˵: �������
							Message returnMessage = new Message(Message.SERVER_USERNAME_NOTFOUND, null, new Date());
							returnMessage.setReceiver(serverUser);
							returnMessage.setContent("�˺Ų�����");
//							ou.sendMessage(returnMessage);
							oos.writeObject(returnMessage);
						}
						break;
					// ������Ϣ
					case Message.CLIENT_SEND            :
						// ת����Ϣ��������
						ou.sendMessage(message);
						
						break;
					// ��ȡ��ǰ���ߺ����б�
					case Message.CLIENT_GET_LIST        :
						
						// ���������б�����ȡ���е�ǰ���ߵ��û��ļ���
						Object[] ous = ou.getOnlineUsers();
						
						// �������б��װ��message�����з��ص��ͻ���
						Message ousMessage = new Message();
						ousMessage.setType(Message.SERVER_LIST);
						ousMessage.setReceiver(message.getSender());
						ousMessage.setExtData(ous);
						ousMessage.setSendTime(new Date());
						oos.writeObject(ousMessage);
						break;
					// �鿴ĳ������
					case Message.CLIENT_GET_USER_INFO  :
					Message friendsMessage = new Message();
						friendsMessage.setType(Message.SERVER_USER_INFO);
						Map map = model.getUserMap();
						//��ȡ�����û���������Ϣ
						User frindUser =(User) map.get(message.getReceiver().getUsername());
						//�Ժ���Ϊ�����ߣ���Ϊ�����ߣ���message��װ
						friendsMessage.setReceiver(frindUser);
						friendsMessage.setSender(message.getReceiver());
						ou.sendMessage(friendsMessage);

						break;
					// �޸ĸ�������
					case Message.CLIENT_UPDATE_INFO     : 
						Map<String, User> m = model.getUserMap();
						m.put(message.getSender().getUsername(), message.getSender());
						model.inPutMap(m);
						Message selfMessage = new Message(Message.SERVER_SELF_INFO,message.getSender(),"");
						ou.sendMessage(selfMessage);
						break;
					// ����Ⱥ��
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
					// �˳����߽�����������������ȥ����������
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
