package com.newlecture.mosquito.service;

import java.awt.Canvas;
import java.awt.Image;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;

import com.newlecture.mosquito.entity.Butterfly;
import com.newlecture.mosquito.entity.Free;
import com.newlecture.mosquito.entity.Miss;
import com.newlecture.mosquito.entity.MosqAttackListener;
import com.newlecture.mosquito.entity.Mosquito;
import com.newlecture.mosquito.entity.Player;
import com.newlecture.mosquito.entity.Score;
import com.newlecture.mosquito.entity.Stage;
import com.newlecture.mosquito.entity.Timer;
import com.newlecture.mosquito.gui.GameClear;
import com.newlecture.mosquito.gui.GameOver;
import com.newlecture.mosquito.gui.PlayerHpBar;
import com.newlecture.mosquito.gui.listener.ButtonClickedListener;

public class FreeService {
	private ArrayList<Mosquito> mosqs;
	private ArrayList<Butterfly> butts;
	private Free free;
	private Timer timer;
	private Player p1;
	private int totalScore = 0;
	private GameOver gameOver;
	private GameClear gameClear;
	private Image gameOverBtn = ImageLoader.gameOverBtn;
	private Image gameClearBtn = ImageLoader.gameClearBtn;
	private String freeStage;
	private boolean isGameOver;
	
	

	
	public FreeService() {
		freeStage = "freeStage";
		timer = new Timer(freeStage);
		p1 = new Player();
		
		gameOver = new GameOver("gameOver",gameOverBtn, gameOverBtn, 642, 359, 216, 283);
		gameClear = new GameClear("gameClear",gameClearBtn, gameClearBtn, 450, 327, 599, 347);
	}
	
	public GameClear getGameClear() {
		return gameClear;
	}
	
	public void freeStage(String freeStage) {
		this.freeStage = freeStage;
		if(mosqs == null) {
			mosqs = new ArrayList<Mosquito>();
			butts = new ArrayList<Butterfly>();
		} else {
			mosqs.clear();
			butts.clear();
		}
		
		free = DataService.getInstance().getFreeValue(freeStage);
		
		int mosqCreateCount = free.getMosqCreateCount();
		int buttCreateCount = free.getButtCreateCount();
		
		for(int i =0; i<mosqCreateCount; i++) {
			mosqs.add(new Mosquito());
			mosqs.get(i).setMosqAttackListener(new MosqAttackListener() {
				
				@Override
				public void attackListener(int damage) {
					p1.setHp(p1.getHp()-damage);
					
				}
			});
		}
		
		for(int i = 0; i<buttCreateCount; i++) {
			butts.add(new Butterfly());
		}
	}
	
	public void setScore() {
		int killScore = free.getKillScore();
		totalScore +=killScore;
	}
	
	public void update() {
		int mosqCreateCount = free.getMosqCreateCount();
		int buttCreateCount = free.getButtCreateCount();
		
		for(int i = 0; i<mosqCreateCount; i++) {
			if(mosqs.get(i).getCurrentDir() == 2) {
				int deleteTimer = mosqs.get(i).getDeleteTimer();
				deleteTimer--;
				mosqs.get(i).setDeleteTimer(deleteTimer);
			}
			
			if(mosqs.get(i).getDeleteTimer() == 0) {
				mosqs.remove(i);
				free.setMosqCreateCount(--mosqCreateCount);
			}
		}
		
		for (int i = 0; i < buttCreateCount; i++) {		// 모기
			if(butts.get(i).getCurrentDir() == 2) {
				int deleteTimer = butts.get(i).getDeleteTimer();
				deleteTimer--;
				butts.get(i).setDeleteTimer(deleteTimer);
			}
			
			if(butts.get(i).getDeleteTimer() == 0) {
				butts.remove(i);
				free.setButtCreateCount(--buttCreateCount);
			}
		}
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public Free getFree() {
		return free;
	}

	public void setFree(Free free) {
		this.free = free;
	}

	public ArrayList<Mosquito> getMosqs() {
		return mosqs;
	}

	public void setMosqs(ArrayList<Mosquito> mosqs) {
		this.mosqs = mosqs;
	}

	public ArrayList<Butterfly> getButts() {
		return butts;
	}

	public void setButts(ArrayList<Butterfly> butts) {
		this.butts = butts;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public Player getP1() {
		return p1;
	}

	public void setP1(Player p1) {
		this.p1 = p1;
	}

	public GameOver getGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}
	
	


	public void setGameClear(GameClear gameClear) {
		this.gameClear = gameClear;
	}

	public int getMosqMaxCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isGameOver() {
		// TODO Auto-generated method stub
		return isGameOver;
	}

}
