package com.chinasofti.frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.chinasofti.bean.User;
import com.chinasofti.model.UserData;



public class UserMsgMaintenance extends JFrame {
	private JLabel usernumber =null;
	private JLabel password =null;
	private JLabel username =null;
	private JLabel sex =null;
	private JLabel department =null;
	private JLabel regionlbl =null;
	private JList  listBox = null;		

	private JTextField textField1 =null;
	private TextField passwordTextField =null;
	private JTextField textField3 =null;
	private JButton  b1 =null;
	private JButton  b2 =null;
	private JButton  b3 =null;
	private JButton  b4 =null;
	private JRadioButton rb = null;
	private JRadioButton rb1 = null;
	private JComboBox regioncmb = null;
	
	public JRadioButton getRb() {
		return rb;
	}
	public void setRb(JRadioButton rb) {
		this.rb = rb;
	}
	public JRadioButton getRb1() {
		return rb1;
	}
	public void setRb1(JRadioButton rb1) {
		this.rb1 = rb1;
	}
	public JComboBox getRegioncmb() {
		return regioncmb;
	}
	public void setRegioncmb(JComboBox regioncmb) {
		this.regioncmb = regioncmb;
	}
	public UserMsgMaintenance() {
		
		JPanel p = new JPanel();
		p.setLayout(null);
		setBounds(400,300,500,400);
		

		String title = "用户信息维护";
		setTitle(title) ;
		setResizable(false);
		//添加窗口栏图片
		ImageIcon ii = new ImageIcon(ClassLoader.getSystemResource("com/chinasofti/image/心云1.jpg"));
		Image i = ii.getImage();
		setIconImage(i);

		
		//设置透明度（添加）
		p.setBackground(new Color(255,255,255,0));

		//添加背景
		ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("com/chinasofti/image/蝴蝶.jpg"));
		JLabel imgLabel = new JLabel(img);
		imgLabel.setBounds(0, 0, 500, 400);
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		((JPanel) getContentPane()).setOpaque(false); // 将contentPane设置成透明的
		
		 rb = new JRadioButton("男");
			rb1 = new JRadioButton("女");
			p.add(rb);
			p.add(rb1);
		
		listBox = new JList();
		listBox.setBounds(10,10,150,350);
		//创建边框
		Border b = BorderFactory.createLineBorder(Color.BLACK);
		listBox.setBorder(b);
		listBox.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				// 获取当前选中的用户信息对象
				String s = (String)listBox.getSelectedValue();
				
				if(s != null) {
					UserData Data = new UserData();
					User user = Data.getUserMap().get(s);
					textField1.setText(user.getUsername());
					passwordTextField.setText(user.getPasswrod());
					textField3 .setText(user.getNickName());
					 if((user.getSex()).equals("男")){
						 rb. setSelected(true);
					 }else{
						 rb1.setSelected(true);
					 }
					 regioncmb.setSelectedItem(user.getDept());
				}

			}
		});
		p.add(listBox);
		
		usernumber = new JLabel("用户名：");
		usernumber.setBounds(180,12,70,20);
		p.add(usernumber);
		
		password = new JLabel("密     码：");
		password.setBounds(180,72,70,20);
		p.add(password);
		
		username = new JLabel("名     称：");
		username.setBounds(180,132,70,20);
		p.add(username);
		
		sex = new JLabel("性     别：");
		sex.setBounds(180,192,70,20);
		p.add(sex);
		
		department = new JLabel("部     门：");
		department.setBounds(180,252,70,20);
		p.add(department);
		
		
		textField1 = new JTextField();
		textField1.setBounds(251,8,223,25);
		textField1.setFont(new Font("楷书",Font.BOLD,15));
		p.add(textField1);
		
		passwordTextField =new TextField();
		passwordTextField.setBounds(251,70,140,25);
		passwordTextField.setFont(new Font("楷书",Font.BOLD,15));
		passwordTextField.setEchoChar('*');
		p.add(passwordTextField);
		
		textField3 =new JTextField();
		textField3.setBounds(251,130,223,25);
		textField3.setFont(new Font("楷书",Font.BOLD,15));
		p.add(textField3);
		
		//添加男女单选框
	
		
		ButtonGroup bg = new ButtonGroup();
		rb.setBounds(251, 192, 60, 20);
		rb1.setBounds(330, 192, 60, 20);
		bg.add(rb);
		bg.add(rb1);
		
//		regionlbl = new JLabel();
//		regionlbl.setText("来自：");
//		regionlbl.setBounds(251, 246, 70, 20);    
//		p.add(regionlbl);
		String[] listData = "教育部,学术部,市场部,服务部,产品部,计划部,人力资源部".split(",");
		 regioncmb = new JComboBox(listData);
		regioncmb.setBounds(251, 252, 220, 20);
		p.add(regioncmb);
	
		b1 = new JButton("删除");
		b1.setBounds(260, 330, 65, 30);
		b1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			UserData data = new UserData();
			//找到后删除,再将更新后的写入文件
			Map<String, User> m = data.getUserMap();
			m.remove(textField1.getText());
			data.inPutMap(m);
			//再将其更新
			 listBox .setListData(data.getUsers().toArray());
			 listBox .updateUI();
		}
		});
		p.add(b1);
		
		b2 = new JButton("保存");
		b2.setBounds(337, 330, 65, 30);
		b2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
//			将所有用户信息全部写到新User添加到Map，写入文件
			User newUser = new User();
			newUser.setUsername(textField1.getText());
			newUser.setPasswrod(passwordTextField.getText());
			newUser.setNickName(textField3 .getText());
			if(rb.isSelected()){
				newUser.setSex("男");
			}else{
				newUser.setSex("女");
			}
			newUser.setDept((String)regioncmb.getSelectedItem());
			UserData data = new UserData();
			//增加后写入文件去
			//如果是第一次进入系统则新建一个map保存添加的数据，如果不是第一次则直接保存到已有map中
			Map<String, User> m ;
			if(data.getUserMap() != null) {
				m = data.getUserMap();
			}else {
				m = new HashMap<String, User>();
			}
			 m.put(textField1.getText(), newUser);
			 data.inPutMap(m);
			 listBox .setListData(data.getUsers().toArray());
			 listBox .updateUI(); 
		}
	});
		p.add(b2);
		b4 = new JButton("初始化");
		b4.setBounds(395, 66, 80, 30);
		p.add(b4);
		
		add(p);
		
		
	}
	public JLabel getUsernumber() {
		return usernumber;
	}
	public void setUsernumber(JLabel usernumber) {
		this.usernumber = usernumber;
	}
	public JLabel getPassword() {
		return password;
	}
	public void setPassword(JLabel password) {
		this.password = password;
	}
	public JLabel getUsername() {
		return username;
	}
	public void setUsername(JLabel username) {
		this.username = username;
	}
	public JLabel getSex() {
		return sex;
	}
	public void setSex(JLabel sex) {
		this.sex = sex;
	}
	public JLabel getDepartment() {
		return department;
	}
	public void setDepartment(JLabel department) {
		this.department = department;
	}
	public JLabel getRegionlbl() {
		return regionlbl;
	}
	public void setRegionlbl(JLabel regionlbl) {
		this.regionlbl = regionlbl;
	}
	public JList getListBox() {
		return listBox;
	}
	public void setListBox(JList listBox) {
		this.listBox = listBox;
	}
	public JTextField getTextField1() {
		return textField1;
	}
	public void setTextField1(JTextField textField1) {
		this.textField1 = textField1;
	}
	public TextField getPasswordTextField() {
		return passwordTextField;
	}
	public void setPasswordTextField(TextField passwordTextField) {
		this.passwordTextField = passwordTextField;
	}
	public JTextField getTextField3() {
		return textField3;
	}
	public void setTextField3(JTextField textField3) {
		this.textField3 = textField3;
	}
	public JButton getB1() {
		return b1;
	}
	public void setB1(JButton b1) {
		this.b1 = b1;
	}
	public JButton getB2() {
		return b2;
	}
	public void setB2(JButton b2) {
		this.b2 = b2;
	}
	
	public JButton getB3() {
		return b3;
	}
	public void setB3(JButton b3) {
		this.b3 = b3;
	}


	public static void main(String[] args) {
		
		UserMsgMaintenance dd = new UserMsgMaintenance();
		dd.setResizable(false);
		dd.setTitle("用户信息维护");
		
		ImageIcon ii = new ImageIcon("d:/psb.jpg");
		Image i = ii.getImage();
		dd.setIconImage(i);
		
		dd.setVisible(true);
		
	}
}

