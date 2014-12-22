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
		

		String title = "�û���Ϣά��";
		setTitle(title) ;
		setResizable(false);
		//��Ӵ�����ͼƬ
		ImageIcon ii = new ImageIcon(ClassLoader.getSystemResource("com/chinasofti/image/����1.jpg"));
		Image i = ii.getImage();
		setIconImage(i);

		
		//����͸���ȣ���ӣ�
		p.setBackground(new Color(255,255,255,0));

		//��ӱ���
		ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("com/chinasofti/image/����.jpg"));
		JLabel imgLabel = new JLabel(img);
		imgLabel.setBounds(0, 0, 500, 400);
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		((JPanel) getContentPane()).setOpaque(false); // ��contentPane���ó�͸����
		
		 rb = new JRadioButton("��");
			rb1 = new JRadioButton("Ů");
			p.add(rb);
			p.add(rb1);
		
		listBox = new JList();
		listBox.setBounds(10,10,150,350);
		//�����߿�
		Border b = BorderFactory.createLineBorder(Color.BLACK);
		listBox.setBorder(b);
		listBox.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				// ��ȡ��ǰѡ�е��û���Ϣ����
				String s = (String)listBox.getSelectedValue();
				
				if(s != null) {
					UserData Data = new UserData();
					User user = Data.getUserMap().get(s);
					textField1.setText(user.getUsername());
					passwordTextField.setText(user.getPasswrod());
					textField3 .setText(user.getNickName());
					 if((user.getSex()).equals("��")){
						 rb. setSelected(true);
					 }else{
						 rb1.setSelected(true);
					 }
					 regioncmb.setSelectedItem(user.getDept());
				}

			}
		});
		p.add(listBox);
		
		usernumber = new JLabel("�û�����");
		usernumber.setBounds(180,12,70,20);
		p.add(usernumber);
		
		password = new JLabel("��     �룺");
		password.setBounds(180,72,70,20);
		p.add(password);
		
		username = new JLabel("��     �ƣ�");
		username.setBounds(180,132,70,20);
		p.add(username);
		
		sex = new JLabel("��     ��");
		sex.setBounds(180,192,70,20);
		p.add(sex);
		
		department = new JLabel("��     �ţ�");
		department.setBounds(180,252,70,20);
		p.add(department);
		
		
		textField1 = new JTextField();
		textField1.setBounds(251,8,223,25);
		textField1.setFont(new Font("����",Font.BOLD,15));
		p.add(textField1);
		
		passwordTextField =new TextField();
		passwordTextField.setBounds(251,70,140,25);
		passwordTextField.setFont(new Font("����",Font.BOLD,15));
		passwordTextField.setEchoChar('*');
		p.add(passwordTextField);
		
		textField3 =new JTextField();
		textField3.setBounds(251,130,223,25);
		textField3.setFont(new Font("����",Font.BOLD,15));
		p.add(textField3);
		
		//�����Ů��ѡ��
	
		
		ButtonGroup bg = new ButtonGroup();
		rb.setBounds(251, 192, 60, 20);
		rb1.setBounds(330, 192, 60, 20);
		bg.add(rb);
		bg.add(rb1);
		
//		regionlbl = new JLabel();
//		regionlbl.setText("���ԣ�");
//		regionlbl.setBounds(251, 246, 70, 20);    
//		p.add(regionlbl);
		String[] listData = "������,ѧ����,�г���,����,��Ʒ��,�ƻ���,������Դ��".split(",");
		 regioncmb = new JComboBox(listData);
		regioncmb.setBounds(251, 252, 220, 20);
		p.add(regioncmb);
	
		b1 = new JButton("ɾ��");
		b1.setBounds(260, 330, 65, 30);
		b1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			UserData data = new UserData();
			//�ҵ���ɾ��,�ٽ����º��д���ļ�
			Map<String, User> m = data.getUserMap();
			m.remove(textField1.getText());
			data.inPutMap(m);
			//�ٽ������
			 listBox .setListData(data.getUsers().toArray());
			 listBox .updateUI();
		}
		});
		p.add(b1);
		
		b2 = new JButton("����");
		b2.setBounds(337, 330, 65, 30);
		b2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
//			�������û���Ϣȫ��д����User��ӵ�Map��д���ļ�
			User newUser = new User();
			newUser.setUsername(textField1.getText());
			newUser.setPasswrod(passwordTextField.getText());
			newUser.setNickName(textField3 .getText());
			if(rb.isSelected()){
				newUser.setSex("��");
			}else{
				newUser.setSex("Ů");
			}
			newUser.setDept((String)regioncmb.getSelectedItem());
			UserData data = new UserData();
			//���Ӻ�д���ļ�ȥ
			//����ǵ�һ�ν���ϵͳ���½�һ��map������ӵ����ݣ�������ǵ�һ����ֱ�ӱ��浽����map��
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
		b4 = new JButton("��ʼ��");
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
		dd.setTitle("�û���Ϣά��");
		
		ImageIcon ii = new ImageIcon("d:/psb.jpg");
		Image i = ii.getImage();
		dd.setIconImage(i);
		
		dd.setVisible(true);
		
	}
}

