package com.chinasofti.frame;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.chinasofti.bean.Company;
import com.chinasofti.model.About;

//import com.chinasofti.bean.User;

/**
 * 窗口管理器
 * 
 * @author Administrator
 * 
 */
public class FrameManagerServer {

	private static FrameManagerServer fm = null;

	private FrameManagerServer() {
	}

	public static FrameManagerServer getInstance() {
		if (fm == null) {
			fm = new FrameManagerServer();
		}
		return fm;
	}

	/**
	 * 关于窗口
	 * 
	 * @author Administrator
	 * 
	 */
	// 所有窗口的集合
	private Map<String, JFrame> frames = new HashMap<String, JFrame>();

	public About getAbout() {

		About about = (About) frames.get("loginFrame");
		if (about == null) {

			about = new About();
			frames.put("about", about);
		}
		return about;
	}

	/**
	 * 获取在线列表用户
	 */
	public ShowOnlineUserFrm getShowOnlineUserFrm() {
		ShowOnlineUserFrm showOnlineUserFrm = (ShowOnlineUserFrm) frames
				.get("showOnlineUserFrm");
		if (showOnlineUserFrm == null) {
			showOnlineUserFrm = new ShowOnlineUserFrm();
			try {
				frames.put("showOnlineUserFrm", showOnlineUserFrm);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return showOnlineUserFrm;
	}

	/**
	 * 获取部门窗口
	 * 
	 * @return
	 */
	public DeptDepartMaint getDeptDepartMaint() {
		DeptDepartMaint deptDepartMaint = (DeptDepartMaint) frames
				.get("loginFrame");
		if (deptDepartMaint == null) {
			deptDepartMaint = new DeptDepartMaint();
			frames.put("deptDepartMaint", deptDepartMaint);
		}
		return deptDepartMaint;
	}

	/**
	 * 获取主窗口
	 * 
	 * @return
	 */
	public ServerManage getMainFrame() {
		ServerManage serverManage = (ServerManage) frames.get("serverManage");
		if (serverManage == null) {
			serverManage = new ServerManage();
			frames.put("serverManage", serverManage);
		}
		return serverManage;
	}

	/**
	 * 获取公司信息维护窗口
	 * 
	 * @param user
	 * @return
	 */
	public CorpManagerFrm getCorpManagerFrm(Company comp) {

		CorpManagerFrm corpManager = (CorpManagerFrm) frames.get("corpManager");
		System.out.println(corpManager);
		if (corpManager == null) {
			corpManager = new CorpManagerFrm();
			frames.put("corpManager", corpManager);
		}

		// 如果公司信息不为空，则将其设定到界面上去
		if (comp != null) {

			corpManager.getCorpnametxt().setText(comp.getCorpname());
			corpManager.getAddresstxt().setText(comp.getAddress());
			corpManager.getDomaintxt().setText(comp.getDomain());
			corpManager.getEmailtxt().setText(comp.getEmail());
			corpManager.getPhonetxt().setText(comp.getPhone());
			corpManager.getFaxtxt().setText(comp.getFax());

		}

		return corpManager;
	}

	/*
	 * 获取用户信息窗口
	 * 
	 */

	public UserMsgMaintenance getUserMsgMaintenance() {
		UserMsgMaintenance userMsgMaintenance = (UserMsgMaintenance) frames
				.get("myInfoFrm");
		if (userMsgMaintenance == null) {
			userMsgMaintenance = new UserMsgMaintenance();
			frames.put("userMsgMaintenance", userMsgMaintenance);
		}
		return userMsgMaintenance;
	}
	//	
	// //获取好友信息窗口
	// public FriendInfoFrm getFriendInfoFrm(User user) {
	// FriendInfoFrm friendInfoFrm = (FriendInfoFrm)frames.get("friendInfoFrm"+
	// user.getUsername());
	// if(friendInfoFrm == null) {
	// friendInfoFrm = new FriendInfoFrm();
	// frames.put("friendInfoFrm"+ user.getUsername(),friendInfoFrm);
	// }
	// return friendInfoFrm;
	// }

}
