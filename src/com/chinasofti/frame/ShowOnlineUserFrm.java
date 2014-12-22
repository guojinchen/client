package com.chinasofti.frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


import com.chinasofti.bean.User;
import com.chinasofti.model.OnlineUsers;

public class ShowOnlineUserFrm extends JFrame{
//	private static final TableColumnModel ColumnNames = null;
//	private static final Object[][] Object = null;
	private JPanel jp;
	private JButton jb1 ,jb2;
	private JTable jt;

	private JList  friendList =null;

	public ShowOnlineUserFrm(){

		setBounds(200, 200, 505, 380);
		jp = new JPanel();
		jp.setBounds(200, 200, 500, 380);
		jp.setBackground(Color.white);
		jp.setLayout(null);
		jt = new JTable();

		Object[] obj1 = OnlineUsers.getInstance().getOnlineUsers();	
		Object[][] obj2 = new Object[(obj1 == null || obj1.length <= 10) ? 10 : obj1.length][9];
		if(obj1.length != 0) {				
			for(int j= 0; j<obj1.length; j++ ) {
				System.out.println(obj1[0]);
				System.out.println("11111");
//				for(int i = 0; i<9;i++){
					obj2[j][0] = ((User) obj1[j]).getUsername();
					obj2[j][1] = ((User) obj1[j]).getRealname();
					obj2[j][2] = ((User) obj1[j]).getNickName();
					obj2[j][3] = ((User) obj1[j]).getSex();
					obj2[j][4] = ((User) obj1[j]).getPhone();
					obj2[j][5] = ((User) obj1[j]).getE_mail();
					obj2[j][6] = ((User) obj1[j]).getAddress();
					obj2[j][7] = ((User) obj1[j]).getDept();			
					obj2[j][8] = ((User) obj1[j]).getIntriduce();		
//				}
			}
		}


//		Object[][] rowData = { 
//		{"zhangsan", "����", "С��", "��", "124567","benladeng@163.com","����","ɱ�ֲ�","�����൱Σ��"},
//		{"zhangsan", "����", "С��", "��", "124567","benladeng@163.com","����","ɱ�ֲ�","�����൱Σ��"},
//		{"zhangsan", "����", "С��", "��", "124567","benladeng@163.com","����","ɱ�ֲ�","�����൱Σ��"},
//		{"zhangsan", "����", "С��", "��", "124567","benladeng@163.com","����","ɱ�ֲ�","�����൱Σ��"},
//		{"zhangsan", "����", "С��", "��", "124567","benladeng@163.com","����","ɱ�ֲ�","�����൱Σ��"}	
//		};
		Object[] columnNames = {
				"�˺�", "����", "�ǳ�", "�Ա�", "�绰","�ʼ�","��ַ","����","����"};
		jt = new JTable(obj2, columnNames);
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane jsp = new JScrollPane(jt);
		jsp.setLocation(0, 0);
		jsp.setSize(500,300);
		jp.add(jsp);

		jb1=new JButton("ˢ��");
		jb1.setBounds(320, 320,60,25);
		jp.add(jb1);		
		// ���ˢ�°�ť�ļ����¼�
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		jb2=new JButton("�ر�");
		jb2.setBounds(420, 320, 60, 25);
		jp.add(jb2);
		// ��ӹرհ�ť�ļ����¼�
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
				
				dispose();
			}
		});

		this.add(jp);
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);



	}

	public void showOnlineUsers(Object[] ous) {
		if(ous != null && ous.length > 0){

			// ����JList��ʾ������

			friendList.setListData(ous);

			// ���½���,�������µ���������ʾ
			friendList.updateUI();
		}
	}


//	public Object[] getOnlineUser(){
//	//��OnlineUsers�л�ȡ�������б�map
//	Object[] map = OnlineUsers.getInstance().getOnlineUsers();		
//	return map;
//	}

//	public static void main(String[] args) {

//	ShowOnlineUserFrm lf = new ShowOnlineUserFrm();
//	lf.init();
//	lf.setResizable( false);
//	ImageIcon ii = new ImageIcon(ClassLoader.getSystemResource("com/dl20120628/����1.jpg"));
//	Image i = ii.getImage();
//	lf.setIconImage(i);
//	lf.setTitle("��ʾ�����û���Ϣ");
//	lf.setVisible(true);

//	}
}