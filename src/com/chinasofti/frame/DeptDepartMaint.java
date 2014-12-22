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
import com.chinasofti.model.DeptInfo;
import com.chinasofti.model.UserData;

public class DeptDepartMaint extends JFrame{

	private JLabel department =null;
	private JLabel departdescription =null;
	private JList  listBox = null;
	private JTextField textField1 =null;
	private TextArea textField2 =null;
	private Button  b1 =null;
	private Button  b2 =null;
	private Button  b3 =null;
	private Button  b4 =null;
	
	public DeptDepartMaint() {
		
		JPanel p = new JPanel();
		p.setLayout(null);
		setBounds(400,300,500,400);
		
		setResizable(false);
		
		String title = "部门信息维护";
		setTitle(title) ;
		
		//添加窗口栏图片
		ImageIcon ii = new ImageIcon(ClassLoader.getSystemResource("com/chinasofti/image/心云1.jpg"));
		Image i = ii.getImage();
		setIconImage(i);

		
		//设置透明度（添加）
		p.setBackground(new Color(255,255,255,0));

		//添加背景
		ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("com/chinasofti/image/6.jpg"));
		JLabel imgLabel = new JLabel(img);
		imgLabel.setBounds(0, 0, 400, 480);
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		((JPanel) getContentPane()).setOpaque(false); // 将contentPane设置成透明的


		listBox = new JList();
		listBox.setBounds(10,10,150,350);
		//创建边框
		Border b = BorderFactory.createLineBorder(Color.BLUE);
		listBox.setBorder(b);
		listBox.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				// 获取当前选中的用户信息对象
				String s = (String)listBox.getSelectedValue();
				
				if(s != null) {
					DeptInfo data=new DeptInfo();
					Map<String,Dept> map = data.getDeptMap();
	     			Dept dept= map.get(s);
	     			textField1.setText(dept.getDetpInfo());
	     			textField2.setText(dept.getIntroduce());
				}

			 }
			
		});
		p.add(listBox);
		
		
		department = new JLabel("部门名称：");
		department.setBounds(180,13,70,20);
		p.add(department);
		
		departdescription = new JLabel("部门描述：");
		departdescription.setBounds(180,46,70,20);
		p.add(departdescription);
		
		textField1 =new JTextField();
		textField1.setBounds(255,10,223,25);
		textField1.setFont(new Font("楷书",Font.BOLD,15));
		p.add(textField1);


		textField2 = new TextArea(null,3,20,TextArea.SCROLLBARS_VERTICAL_ONLY);
		textField2.setBounds(180,70,300,252);
		textField2.setFont(new Font("楷书",Font.BOLD,20));
		p.add(textField2);
		
//		b1 = new Button("增加");
//		b1.setBounds(180, 330, 65, 30);
//		p.add(b1);
		
		b2 = new Button("保存");
		b2.setBounds(260, 330, 65, 30);
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dept newDept = new Dept();
				newDept.setDetpInfo(textField1.getText());
				newDept.setIntroduce(textField2.getText());
				DeptInfo data = new DeptInfo();
				Map<String,Dept> map = data.getDeptMap();
				if(map == null) {
					map = new HashMap<String, Dept>();
				}
				map.put(textField1.getText(), newDept);
				data.inPutDeptMap(map);
				
				listBox .setListData(data.getAllDpet().toArray());
				listBox .updateUI();
			}
			});
		p.add(b2);
		
		b3 = new Button("删除");
		b3.setBounds(337, 330, 65, 30);
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeptInfo data = new DeptInfo();
				//找到后删除,再将更新后的写入文件
				Map m = data.getDeptMap();
				m.remove(textField1.getText());
				data.inPutDeptMap(m);
				//再将其更新
				 listBox .setListData(data.getAllDpet().toArray());
				 listBox .updateUI();	
			}
			});
		p.add(b3);
		
//		b4 = new Button("关闭");
//		b4.setBounds(415, 330, 65, 30);
//		p.add(b4);
		
		add(p);
		
		
		
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
	public TextArea getTextField2() {
		return textField2;
	}
	public void setTextField2(TextArea textField2) {
		this.textField2 = textField2;
	}
//	public static void main(String[] args) {
//		
//		DeptDepartMaint dd = new DeptDepartMaint();
//		dd.setResizable(false);
//		dd.setTitle("部门信息维护");
//		
//		ImageIcon ii = new ImageIcon(ClassLoader.getSystemResource("com/dl20120628/笑脸5.jpg"));
//		Image i = ii.getImage();
//		dd.setIconImage(i);
//		
//		dd.setVisible(true);
//		
//	}
}
