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
	
	private JLabel corpNamelbl = null;        //����JLabel��
	private JLabel addresslbl = null;         //��ַJLabel��
	private JLabel domainlbl = null;          //����JLabel��
	private JLabel emaillbl = null;           //�ʼ�JLabel��
	private JLabel phonelbl = null;           //�绰JLabel��
	private JLabel faxlbl = null;             //����JLabel��
	
	private JTextField corpnametxt = null;    //�����ı���
	private JTextField addresstxt= null;      //��ַ�ı���
	private JTextField domaintxt= null;       //�����ı���
	private JTextField emailtxt= null;        //�ʼ��ı���
	private JTextField phonetxt= null;        //�绰�ı���
	private JTextField faxtxt= null;          //�����ı���
	
	private Button updatebtn = null;           //���°�ť
	
	
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

	private Button exitbtn = null;             //�˳���ť
	
	public CorpManagerFrm(){

		init();
		
	}
	
	public void init() {
		
		JPanel panel  = new JPanel();
		//���ô�������
		String title = "��˾��Ϣά��";
		setTitle(title) ;
		//���ô��ڳ��ֵ�λ�úʹ�С
		setBounds(200,200,300,300);
		panel.setLayout(null);
		
		boolean b = false;
		setResizable(b);
		
		//��Ӵ�����ͼƬ
		ImageIcon ii = new ImageIcon(ClassLoader.getSystemResource("com/chinasofti/image/����1.jpg"));
		Image i = ii.getImage();
		setIconImage(i);

		
		//����͸���ȣ���ӣ�
		panel.setBackground(new Color(255,255,255,0));

		//��ӱ���
		ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("com/chinasofti/image/��Ů.jpg"));
		JLabel imgLabel = new JLabel(img);
		imgLabel.setBounds(0, 0, 400, 480);
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		((JPanel) getContentPane()).setOpaque(false); // ��contentPane���ó�͸����

		
		corpNamelbl = new JLabel();
		corpNamelbl.setText("���ƣ�");
		corpNamelbl.setBounds(20, 20, 50, 20);   									
		corpnametxt = new JTextField();
		corpnametxt.setBounds(70, 20, 150, 20);
//		corpnametxt.setText("����");	
		panel.add(corpNamelbl);
		panel.add(corpnametxt);
		
		addresslbl = new JLabel();
		addresslbl.setText("��ַ��");
		addresslbl.setBounds(20, 50, 50, 20);   									
		addresstxt = new JTextField();
		addresstxt.setBounds(70, 50, 150, 20);
//		addresstxt.setText("����");	
		panel.add(addresslbl);
		panel.add(addresstxt);
		
		domainlbl = new JLabel();
		domainlbl.setText("������");
		domainlbl.setBounds(20, 80, 50, 20);   									
		domaintxt = new JTextField();
		domaintxt.setBounds(70, 80, 150, 20);
//		domaintxt.setText("http://...");	
		panel.add(domainlbl);
		panel.add(domaintxt);
		
		emaillbl = new JLabel();
		emaillbl.setText("���ʣ�");
		emaillbl.setBounds(20, 110, 50, 20);   									
		emailtxt = new JTextField();
		emailtxt.setBounds(70, 110, 150, 20);
		emailtxt.setText("...@163.com");	
		panel.add(emaillbl);
		panel.add(emailtxt);
	
		phonelbl = new JLabel();
		phonelbl.setText("�绰��");
		phonelbl.setBounds(20, 140, 50, 20);   									
		phonetxt = new JTextField();
		phonetxt.setBounds(70, 140, 150, 20);
//		phonetxt.setText("****-******");	
		panel.add(phonelbl);
		panel.add(phonetxt);
		
		faxlbl = new JLabel();
		faxlbl.setText("���棺");
		faxlbl.setBounds(20,170, 50, 20);   									
		faxtxt = new JTextField();
		faxtxt.setBounds(70,170, 150, 20);
//		faxtxt.setText("***-*******");	
		panel.add(faxlbl);
		panel.add(faxtxt);
		
		updatebtn = new Button("�ύ");
		updatebtn.setBounds(80, 210, 60, 20);
		//�����ύ��ť���ͽ����ϵ������Ϣ���浽����
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
		
		exitbtn = new Button("�ر�");
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
//			//��ʾ����
//			cmf.setVisible(true);
//	}
}
