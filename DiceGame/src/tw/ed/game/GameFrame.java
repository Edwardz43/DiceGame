package tw.ed.game;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	//預設遊戲框架
	public void launchFrame(int width, int height){
		//偵測銀幕大小
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screenWidth = screenSize.getWidth();
		double screenHeight = screenSize.getHeight();
		//設定視窗大小
		setSize(width, height);
		//視窗置中
		setLocation((int)screenWidth/2 - width / 2, (int)screenHeight/2 - height /2);
		setVisible(true);
		setDefaultCloseOperation(3);
	}
}