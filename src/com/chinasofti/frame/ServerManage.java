
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
		
		String title = "Server管理器";
		setTitle(title) ;
		
		setResizable(false);
	
		
//		//添加窗口栏图片
//		ImageIcon ii = new ImageIcon(ClassLoader.getSystemResource("com/chinasofti/image/心云1.jpg"));
//		Image i = ii.getImage();
//		setIconImage(i);
//
//		
//		//设置透明度（添加）
//		jp.setBackground(new Color(255,255,255,0));
//
//		//添加背景
//		ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("com/chinasofti/image/ 夜.jpg"));
//		JLabel imgLabel = new JLabel(img);
//		imgLabel.setBounds(0, 0, 400, 480);
//		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
//		imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
//		((JPanel) getContentPane()).setOpaque(false); // 将contentPane设置成透明的
		
		// 定义一个主栏
		JMenuBar jmb = new JMenuBar();
		// 定义主栏
		JMenu jm1 = new JMenu("信息维护（M）");
		JMenu jm2 = new JMenu("系统管理（I）");
		JMenu jm3 = new JMenu("帮助（H）");
		// 定义子栏
		JMenuItem jmi0 = new JMenuItem("部门信息维护");

		jmi0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
            //弹出用户信息表并更新用户list
				FrameManagerServer fm = FrameManagerServer.getInstance();

				DeptDepartMaint dd = fm.getDeptDepartMaint();
				 dd.setVisible(true);
				 //获得list和服务器本地用户将其更新到list上面
				 DeptInfo model =new DeptInfo();
				 JList list = dd.getListBox();
				 
				 if(model.getAllDpet() != null) {
					 list.setListData(model.getAllDpet().toArray());
				 } 
				 list.updateUI();
			}
		});
		JMenuItem jmi1 = new JMenuItem("公司信息维护");
		jmi1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				FrameManagerServer fm = FrameManagerServer.getInstance();
				Company comp = new CompanyManager().getComp();
				fm.getCorpManagerFrm(comp).setVisible(true);
			}
		});
		JMenuItem jmi2 = new JMenuItem("用户信息维护");
		jmi2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				//	弹出用户信息表并更新用户list
				FrameManagerServer fm = FrameManagerServer.getInstance();
				UserMsgMaintenance  umm = fm.getUserMsgMaintenance();
				 umm.setVisible(true);
				 //获得list和服务器本地用户将其更新到list上面
				 UserData model =new UserData();
				 JList list = umm.getListBox();
				 if(model.getUsers() != null) {
					 list.setListData( model.getUsers().toArray());
				 }	
				 list.updateUI();
			}
		});
		JMenuItem jmi3 = new JMenuItem("退出");
		jmi3.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		// 将子栏添加到信息维护栏
		jm1.add(jmi0);
		jm1.add(jmi1);
		jm1.add(jmi2);
		jm1.add(jmi3);
		// 将主栏添加到JMenuBar板中
		jmb.add(jm1);
		// 定义子栏
		jmi4 = new JMenuItem("启动服务");
		jmi5 = new JMenuItem("停止服务");
	
		// 将子栏添加到系统管理栏
		jm2.add(jmi4);
		jm2.add(jmi5);
		jmb.add(jm2);

		JMenuItem jmi6 = new JMenuItem("查看配置信息");
		JMenuItem jmi7 = new JMenuItem("关于");
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
		// 创建工具栏
		JToolBar jtb = new JToolBar();
		JButton startButton = new JButton("启动服务");
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 sat = new ServerAcceptThread();
				 Thread myThread = new Thread(sat);
				 myThread.start();
				 count.setText("服务器使用中");
			}
		});
		jtb.add(startButton);
		jtb.addSeparator();
		JButton stopButton = new JButton("停止服务");
		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sat.stop();
				count.setText("服务器停止罢工了");
			}
		});
		jtb.add(stopButton);
		jtb.addSeparator();
		jtb.add(new JButton("查看配置信息"));
		jtb.addSeparator();

		//jtb.add(new JButton("显示在线用户"));
		
		// 查看在线用户列表
		JButton allUsers = new JButton("显示在线用户");
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
		// 添加显示框
		accounttxt = new JTextArea(13, 45);
		accounttxt.setBounds(25, 10, 450, 250);
		accounttxt.setFont(new Font("宋体", Font.PLAIN, 14));
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

