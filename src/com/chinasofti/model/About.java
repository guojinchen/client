package com.chinasofti.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;

public class About extends JFrame {
	private JTabbedPane jtp = null;
	private JPanel jp1 =null;
	private JPanel jp2 =null;
	private JPanel jp3 =null;
	private Label jlb1,jlb2,jlb3,jlb4,jlb5;
	private JLabel jb6 =null;
	private JLabel  jb7 =null;
	
	public About() {
		setBounds(400,300,500,400);
		jtp = new JTabbedPane();
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		
		jp1.setLayout(null);
		jtp.add("详细信息",jp1);
		jtp.add("关于",jp2);
		
		jp1.setBounds(100, 100, 400, 300);
		jp2.setBounds(100, 100, 400, 300);
		jp3.setBounds(100, 100, 400, 300);
		jp3.setBackground(Color.BLUE);
		
		
		jlb1=new Label("企业即时通系统");
		jlb1.setBounds(250,20, 180,40);
		jlb1.setFont(new Font("宋体",Font.BOLD,24));
		jp1.add(jlb1);
				
		jlb2=new Label("版本号：1.0");
		jlb2.setBounds(220, 70, 100, 20);
		jlb2.setFont(new Font("宋体",Font.BOLD,15));
		jp1.add(jlb2);
		
		jlb3=new Label("操作系统：Microsoft Windows NT 5.0");
		jlb3.setBounds(210, 120, 300, 20);
		jlb3.setFont(new Font("宋体",Font.BOLD,15));
		jp1.add(jlb3);
		
		jlb4=new Label("开发环境：JAVA ECLIPSE");
		jlb4.setBounds(210, 185, 300, 20);
		jlb4.setFont(new Font("宋体",Font.BOLD,15));
		jp1.add(jlb4);
		
		jlb5=new Label("开发者：Angel_霸^_^霸团队");
		jlb5.setBounds(220, 251, 210, 20);
		jlb5.setFont(new Font("宋体",Font.BOLD,15));
		jp1.add(jlb5);
		
		
		jb6 = new JLabel(new ImageIcon(ClassLoader.getSystemResource("com/chinasofti/image/7.jpg")));
//		headPic.setText("照片");
		Border br = BorderFactory.createLineBorder(Color.BLACK);
		jb6.setBorder(br);
		jb6.setBounds(10, 10, 150, 300);
//		jlb6=new Label();
//		jlb6.setBounds(10, 10, 150, 300);
//		jlb6.setBackground(Color.black);
		
		jb7 = new JLabel(new ImageIcon(ClassLoader.getSystemResource("com/chinasofti/image/团队.jpg")));
//		headPic.setText("照片");
		Border br1 = BorderFactory.createLineBorder(Color.BLACK);
		jb7.setBorder(br1);
		jb7.setBounds(0,0, 400, 300);
		jp2.setBackground(new Color(255,255,255,0));
		
//		jp1.add(jp3);
		jp1.add(jb6);
		jp2.add(jb7);
		add(jtp);
		
	//	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
	public static void main(String[] args) {
		About lf = new About();
		lf.setResizable( false);
		ImageIcon ii = new ImageIcon(ClassLoader.getSystemResource("com/chinasofti/image/心云1.jpg"));
		Image i = ii.getImage();
		lf.setIconImage(i);
		lf.setTitle("关于");
		lf.setVisible(true);
		
	}
}


