package tw.ed.game;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashSet;
import tw.ed.game.GameUtil;

public class Player {
	protected Image img;
	private ArrayList<Integer> result;
	private int score;
	
	public Player(int i){
		this.img = GameUtil.getImage("img/"+i+".png");
		this.score = 0;
		this.result = new ArrayList<>();
	}
	
	public void throwDice(){
		this.result.clear();
		for(int i = 0; i <4; i++) 
			this.result.add((int)(Math.random() * 6 + 1));
	}
	
	public ArrayList<Integer> showResult (){
		return this.result;
	}
	
	public int scores (){
		score = 0;
		ArrayList<Integer> res = this.result;
		//lambda sort : Big -> small
		res.sort((num1, num2) -> -num1.compareTo(num2));
		//豹子  加權100
		if(res.get(0)==res.get(1) && res.get(1)== res.get(2) && res.get(2)== res.get(3)) return res.get(0) + 100;
		//兩對  回傳大的一雙
		if(res.get(0)==res.get(1) && res.get(2)==res.get(3)) return res.get(0) * 2;
		ArrayList<Integer> temp = new ArrayList<>();
		HashSet<Integer> check = new HashSet<>();
		for(int i = 0; i < res.size(); i++){
			if(temp.contains(res.get(i))) temp.remove(temp.indexOf(res.get(i)));
			else temp.add(res.get(i));
			check.add(res.get(i));
		}
		//回傳分數
		if(check.size() < 4){
			for(int i : temp) { score += i; }
		}
		//四個都不同 回傳0分
		return score;
	}
	
}
