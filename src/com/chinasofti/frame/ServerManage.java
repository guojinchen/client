
package com.chinasofti.frame;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.chinasofti.bean.Company;
import com.chinasofti.connection.ServerAcceptThread;
import com.chinasofti.model.CompanyManager;
import com.chinasofti.model.DeptInfo;
import com.chinasofti.model.UserData;
public class ServerManage extends JFrame {
		
/*	public static void main(String[] args) {
		ServerManage sm = new ServerManage();
		sm.setVisible(true);
	}*/
	JTextArea accounttxt = null;
	ServerAcceptThread sat= null;
	JLabel count = null;
	public JLabel getCount() {
		return count;
	}
	public void setCount(JLabel count) {
		this.count = count;
	}
	public ServerManage() {
		
		JMenuItem jmi5 = null;
		JMenuItem jmi4 = null;
		JPanel jp = new JPanel();
		jp.setLayout(null);
		setBounds(400, 300, 500, 400);
		
		String title = "Server������";
		setTitle(title) ;
		
		setResizable(false);
	
		
//		//��Ӵ�����ͼƬ
//		ImageIcon ii = new ImageIcon(ClassLoader.getSystemResource("com/chinasofti/image/����1.jpg"));
//		Image i = ii.getImage();
//		setIconImage(i);
//
//		
//		//����͸���ȣ���ӣ�
//		jp.setBackground(new Color(255,255,255,0));
//
//		//��ӱ���
//		ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("com/chinasofti/image/ ҹ.jpg"));
//		JLabel imgLabel = new JLabel(img);
//		imgLabel.setBounds(0, 0, 400, 480);
//		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
//		imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
//		((JPanel) getContentPane()).setOpaque(false); // ��contentPane���ó�͸����
		
		// ����һ������
		JMenuBar jmb = new JMenuBar();
		// ��������
		JMenu jm1 = new JMenu("��Ϣά����M��");
		JMenu jm2 = new JMenu("ϵͳ����I��");
		JMenu jm3 = new JMenu("������H��");
		// ��������
		JMenuItem jmi0 = new JMenuItem("������Ϣά��");

		jmi0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
            //�����û���Ϣ�������û�list
				FrameManagerServer fm = FrameManagerServer.getInstance();

				DeptDepartMaint dd = fm.getDeptDepartMaint();
				 dd.setVisible(true);
				 //���list�ͷ����������û�������µ�list����
				 DeptInfo model =new DeptInfo();
				 JList list = dd.getListBox();
				 
				 if(model.getAllDpet() != null) {
					 list.setListData(model.getAllDpet().toArray());
				 } 
				 list.updateUI();
			}
		});
		JMenuItem jmi1 = new JMenuItem("��˾��Ϣά��");
		jmi1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				FrameManagerServer fm = FrameManagerServer.getInstance();
				Company comp = new CompanyManager().getComp();
				fm.getCorpManagerFrm(comp).setVisible(true);
			}
		});
		JMenuItem jmi2 = new JMenuItem("�û���Ϣά��");
		jmi2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				//	�����û���Ϣ�������û�list
				FrameManagerServer fm = FrameManagerServer.getInstance();
				UserMsgMaintenance  umm = fm.getUserMsgMaintenance();
				 umm.setVisible(true);
				 //���list�ͷ����������û�������µ�list����
				 UserData model =new UserData();
				 JList list = umm.getListBox();
				 if(model.getUsers() != null) {
					 list.setListData( model.getUsers().toArray());
				 }	
				 list.updateUI();
			}
		});
		JMenuItem jmi3 = new JMenuItem("�˳�");
		jmi3.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		// ��������ӵ���Ϣά����
		jm1.add(jmi0);
		jm1.add(jmi1);
		jm1.add(jmi2);
		jm1.add(jmi3);
		// ��������ӵ�JMenuBar����
		jmb.add(jm1);
		// ��������
		jmi4 = new JMenuItem("��������");
		jmi5 = new JMenuItem("ֹͣ����");
	
		// ��������ӵ�ϵͳ������
		jm2.add(jmi4);
		jm2.add(jmi5);
		jmb.add(jm2);

		JMenuItem jmi6 = new JMenuItem("�鿴������Ϣ");
		JMenuItem jmi7 = new JMenuItem("����");
		jmi7.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				FrameManagerServer.getInstance().getAbout().setVisible(true);
			}
		});
		jm3.add(jmi6);
		jm3.add(jmi7);
		jmb.add(jm3);
		 count = new JLabel();
		count.setBounds(10,260,160,70);
		jp.add(count);
		// ����������
		JToolBar jtb = new JToolBar();
		JButton startButton = new JButton("��������");
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 sat = new ServerAcceptThread();
				 Thread myThread = new Thread(sat);
				 myThread.start();
				 count.setText("������ʹ����");
			}
		});
		jtb.add(startButton);
		jtb.addSeparator();
		JButton stopButton = new JButton("ֹͣ����");
		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sat.stop();
				count.setText("������ֹͣ�չ���");
			}
		});
		jtb.add(stopButton);
		jtb.addSeparator();
		jtb.add(new JButton("�鿴������Ϣ"));
		jtb.addSeparator();

		//jtb.add(new JButton("��ʾ�����û�"));
		
		// �鿴�����û��б�
		JButton allUsers = new JButton("��ʾ�����û�");
		allUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameManagerServer fms = FrameManagerServer.getInstance();
				try{
					fms.getShowOnlineUserFrm().setVisible(true);
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		
		jtb.add(allUsers);
		this.setJMenuBar(jmb);
		this.getContentPane().add(jtb, BorderLayout.NORTH);
		// �����ʾ��
		accounttxt = new JTextArea(13, 45);
		accounttxt.setBounds(25, 10, 450, 250);
		accounttxt.setFont(new Font("����", Font.PLAIN, 14));
		jp.add(accounttxt);
		this.add(jp);
		add(jp);
		
		
		

	}
	public JTextArea getAccounttxt() {
		return accounttxt;
	}
	public void setAccounttxt(JTextArea accounttxt) {
		this.accounttxt = accounttxt;
	}

}

