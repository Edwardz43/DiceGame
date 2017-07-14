package tw.ed.game;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JPanel;

public class DiceGame extends JPanel{
	private int viewW, viewH;
	private JPanel menuBar;
	private JButton start, throwDice;
	private int gameStage;
	private boolean isOver;
	private Player[] players = new Player[4];
	private ArrayList<Integer>rank = new ArrayList<>();
	private int winner;
	
	public DiceGame () {
		menuBar = new JPanel();
		menuBar.setLayout(new FlowLayout());
		start = new JButton("開始遊戲");
		throwDice = new JButton("請先按下\"Start\"");
		menuBar.add(throwDice,FlowLayout.LEFT);
		menuBar.add(start,FlowLayout.LEFT);
		start.addActionListener((e) ->startGame());
		throwDice.addActionListener((e) -> throwDice());
		for(int i = 0; i < 4; i++){ players[i] = new Player(i); }
		add(menuBar, BorderLayout.EAST);
	}
	private void throwDice() {
		if(gameStage > 0){
			Player p = players[gameStage-1];
			p.throwDice();
			if(p.scores() == 0){
				throwDice.setText("BG...Throw again !");
			}else{
				rank.add(p.scores());
				if(gameStage == 4){
					HashMap<Integer, String> temp = new HashMap<>();
					for(int i : rank){
						temp.put(rank.get(rank.indexOf(i)), ""+(rank.indexOf(i) + 1));
					}
					rank.sort((num1, num2) -> -num1.compareTo(num2));
					for(int i : rank) System.out.println(i);
					if(rank.get(0) == rank.get(1) || rank.get(0) == rank.get(2)){
						System.out.println("Winner is : Tied...");
					}else  {
						System.out.println("Winner is : player" +temp.get(rank.get(0)));
						winner = Integer.parseInt(temp.get(rank.get(0)));
					}
				}
				gameStage = (gameStage >= 4)? 0:gameStage+1;
				
				if(gameStage == 0) {
					throwDice.setText(
							(winner==0)?"Tied...Try again !":"Winner is : Player" + winner);
					start.setText("One More Game");
					isOver = true;
					System.out.println("isOver :" +isOver);
				} 
				else throwDice.setText("玩家"+ gameStage);
				repaint();
			}
		}
	}
	
	protected void startGame() {
		if(gameStage == 0) {
			isOver = false;
			winner = 0;
			System.out.println("isOver :" +isOver);
			for(int i = 0; i < 4; i++){ players[i] = new Player(i); }
			rank.clear();
			gameStage = 1;
			start.setText("開始遊戲");
			throwDice.setText("玩家"+ gameStage);
			repaint();
		}
	}
	public static void main(String[] args) {
		GameFrame myFrame = new GameFrame();
		DiceGame myGame = new DiceGame();
		myFrame.add(myGame);
		myFrame.launchFrame(800, 600);
	}
	
	@Override
	public void paintComponent(Graphics g){
		viewW = getWidth(); viewH=getHeight();
		Graphics2D g2d= (Graphics2D)g;
		g2d.clearRect(0, 0, viewW, viewH);
		for(int i = 0; i < players.length; i++){
			Player p = players[i];
			ArrayList<Integer> res =  p.showResult();
			if(isOver == true && gameStage == 0 && i + 1 == winner){
				g2d.drawImage(players[i].img, 
						50, 50 + i *120, 150, 150+ i *120,
						96, 0, 192, 96, null);
			}else if(isOver == true && gameStage == 0 && i + 1 != winner){
				g2d.drawImage(players[i].img, 
						50, 50 + i *120, 150, 150+ i *120,
						288, 96, 384, 192, null);
			}else{
				g2d.drawImage(players[i].img, 
						50, 50 + i *120, 150, 150+ i *120,
						0, 0, 96, 96, null);
			}
			
			for(int j = 0; j <res.size(); j++){
				String path = "img/dice"+res.get(j)+".png";
				Image img = GameUtil.getImage(path);
				g2d.drawImage(img, 
						200 + j*50,     70 + i *120, 
						200 + (j+1)*50, 120+ i *120,
						0, 0, 225, 225, null);
			}
		}
	}
}
