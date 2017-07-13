package tw.ed.game;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	//�w�]�C���ج[
	public void launchFrame(int width, int height){
		//�����ȹ��j�p
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screenWidth = screenSize.getWidth();
		double screenHeight = screenSize.getHeight();
		//�]�w�����j�p
		setSize(width, height);
		//�����m��
		setLocation((int)screenWidth/2 - width / 2, (int)screenHeight/2 - height /2);
		setVisible(true);
		setDefaultCloseOperation(3);
	}
}