package com.chinasofti.frame;

import java.awt.Button;
import java.awt.Color;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import com.chinasofti.bean.Company;
import com.chinasofti.model.CompanyManager;

public class CorpManagerFrm extends JFrame{
	private Panel panel = null;
	
	private JLabel corpNamelbl = null;        //名称JLabel框
	private JLabel addresslbl = null;         //地址JLabel框
	private JLabel domainlbl = null;          //域名JLabel框
	private JLabel emaillbl = null;           //邮件JLabel框
	private JLabel phonelbl = null;           //电话JLabel框
	private JLabel faxlbl = null;             //传真JLabel框
	
	private JTextField corpnametxt = null;    //名称文本框
	private JTextField addresstxt= null;      //地址文本框
	private JTextField domaintxt= null;       //域名文本框
	private JTextField emailtxt= null;        //邮件文本框
	private JTextField phonetxt= null;        //电话文本框
	private JTextField faxtxt= null;          //传真文本框
	
	private Button updatebtn = null;           //更新按钮
	
	
	public JTextField getCorpnametxt() {
		return corpnametxt;
	}

	public void setCorpnametxt(JTextField corpnametxt) {
		this.corpnametxt = corpnametxt;
	}

	public JTextField getAddresstxt() {
		return addresstxt;
	}

	public void setAddresstxt(JTextField addresstxt) {
		this.addresstxt = addresstxt;
	}

	public JTextField getDomaintxt() {
		return domaintxt;
	}

	public void setDomaintxt(JTextField domaintxt) {
		this.domaintxt = domaintxt;
	}

	public JTextField getEmailtxt() {
		return emailtxt;
	}

	public void setEmailtxt(JTextField emailtxt) {
		this.emailtxt = emailtxt;
	}

	public JTextField getPhonetxt() {
		return phonetxt;
	}

	public void setPhonetxt(JTextField phonetxt) {
		this.phonetxt = phonetxt;
	}

	public JTextField getFaxtxt() {
		return faxtxt;
	}

	public void setFaxtxt(JTextField faxtxt) {
		this.faxtxt = faxtxt;
	}

	private Button exitbtn = null;             //退出按钮
	
	public CorpManagerFrm(){

		init();
		
	}
	
	public void init() {
		
		JPanel panel  = new JPanel();
		//设置窗口名字
		String title = "公司信息维护";
		setTitle(title) ;
		//设置窗口出现的位置和大小
		setBounds(200,200,300,300);
		panel.setLayout(null);
		
		boolean b = false;
		setResizable(b);
		
		//添加窗口栏图片
		ImageIcon ii = new ImageIcon(ClassLoader.getSystemResource("com/chinasofti/image/心云1.jpg"));
		Image i = ii.getImage();
		setIconImage(i);

		
		//设置透明度（添加）
		panel.setBackground(new Color(255,255,255,0));

		//添加背景
		ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("com/chinasofti/image/美女.jpg"));
		JLabel imgLabel = new JLabel(img);
		imgLabel.setBounds(0, 0, 400, 480);
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		((JPanel) getContentPane()).setOpaque(false); // 将contentPane设置成透明的

		
		corpNamelbl = new JLabel();
		corpNamelbl.setText("名称：");
		corpNamelbl.setBounds(20, 20, 50, 20);   									
		corpnametxt = new JTextField();
		corpnametxt.setBounds(70, 20, 150, 20);
//		corpnametxt.setText("名称");	
		panel.add(corpNamelbl);
		panel.add(corpnametxt);
		
		addresslbl = new JLabel();
		addresslbl.setText("地址：");
		addresslbl.setBounds(20, 50, 50, 20);   									
		addresstxt = new JTextField();
		addresstxt.setBounds(70, 50, 150, 20);
//		addresstxt.setText("北京");	
		panel.add(addresslbl);
		panel.add(addresstxt);
		
		domainlbl = new JLabel();
		domainlbl.setText("域名：");
		domainlbl.setBounds(20, 80, 50, 20);   									
		domaintxt = new JTextField();
		domaintxt.setBounds(70, 80, 150, 20);
//		domaintxt.setText("http://...");	
		panel.add(domainlbl);
		panel.add(domaintxt);
		
		emaillbl = new JLabel();
		emaillbl.setText("电邮：");
		emaillbl.setBounds(20, 110, 50, 20);   									
		emailtxt = new JTextField();
		emailtxt.setBounds(70, 110, 150, 20);
		emailtxt.setText("...@163.com");	
		panel.add(emaillbl);
		panel.add(emailtxt);
	
		phonelbl = new JLabel();
		phonelbl.setText("电话：");
		phonelbl.setBounds(20, 140, 50, 20);   									
		phonetxt = new JTextField();
		phonetxt.setBounds(70, 140, 150, 20);
//		phonetxt.setText("****-******");	
		panel.add(phonelbl);
		panel.add(phonetxt);
		
		faxlbl = new JLabel();
		faxlbl.setText("传真：");
		faxlbl.setBounds(20,170, 50, 20);   									
		faxtxt = new JTextField();
		faxtxt.setBounds(70,170, 150, 20);
//		faxtxt.setText("***-*******");	
		panel.add(faxlbl);
		panel.add(faxtxt);
		
		updatebtn = new Button("提交");
		updatebtn.setBounds(80, 210, 60, 20);
		//单击提交按钮，就界面上的相关信息保存到本地
		updatebtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				String corpname = corpnametxt.getText();
				String address  = addresstxt.getText();
				String domain   = domaintxt.getText();
				String email   = emailtxt.getText();
				String phone   = phonetxt.getText();
				String fax    = faxtxt.getText();
				
				Company company = new Company(corpname, address, domain, email, phone, fax);
				
				new CompanyManager().add(company);
			}
		
		});
		panel.add(updatebtn);
		
		exitbtn = new Button("关闭");
		exitbtn.setBounds(150, 210, 60, 20);
         exitbtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				
		     setVisible(true);
		     dispose();
			}
		
		});



		panel.add(exitbtn);

		add(panel);
		
	}
		
//	public static void main(String[] args) {
//			
//			CorpManagerFrm  cmf =new CorpManagerFrm ();
//			//显示窗口
//			cmf.setVisible(true);
//	}
}
