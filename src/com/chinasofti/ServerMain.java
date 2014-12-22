package com.chinasofti;

import com.chinasofti.connection.ServerAcceptThread;
import com.chinasofti.frame.FrameManagerServer;

public class ServerMain {
	public static void main(String[] args) {
//		ServerAcceptThread sat = new ServerAcceptThread();
//		new Thread(sat).start();
	FrameManagerServer fms=FrameManagerServer.getInstance();
	fms.getMainFrame().setVisible(true);
	}
}
