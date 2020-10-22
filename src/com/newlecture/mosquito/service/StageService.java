package com.newlecture.mosquito.service;

import java.awt.Canvas;
import java.util.ArrayList;

import com.newlecture.mosquito.entity.Butterfly;
import com.newlecture.mosquito.entity.Mosquito;
import com.newlecture.mosquito.entity.Stage;
import com.newlecture.mosquito.entity.Timer;
import com.newlecture.mosquito.gui.GameOver;

public class StageService {
	private ArrayList<Mosquito> mosqs;
	private ArrayList<Butterfly> butts;		// 스테이지에 생성되는 모든 모기와 나비
	private Stage stage;
	private int stageIndex;
	private Timer timer;
	private int totalScore=0;
	private GameOver gameOver;
	
	public StageService() {
		gameOver = new GameOver();
		int startIndex = DataService.getInstance().getGameIntValue("default", "stageIndex");	
		changeStage(startIndex);
	}

	public void changeStage(int stageIndex) {
		this.stageIndex = stageIndex;		// 현재 스테이지 바꾸고

		if(mosqs == null) {
			mosqs = new ArrayList<Mosquito>();	
			butts = new ArrayList<Butterfly>();	
		} else {
			mosqs.clear();
			butts.clear();
		}

		// 새로운 스테이지 정보 가져오기
		stage = DataService.getInstance().getStageValue(stageIndex);

		//모기 & 나비 생성
		int mosqCreateCount = stage.getMosqCreateCount();
		int buttCreateCount = stage.getButtCreateCount();

		for (int i = 0; i < mosqCreateCount; i++) {		// 모기
			mosqs.add(new Mosquito());
		}

		for (int i = 0; i < buttCreateCount; i++) {		// 나비
			butts.add(new Butterfly());
		}
	}

	public void setScore() {
		int killScore = stage.getKillScore();
		totalScore += killScore;

		/*스테이지 완료 및 스테이지 전환 구현하기.
		if(totalScore >= currentStage.getWinScore()) {
			currentStage = Dat
		}*/
	}
	public void update() {//스레드에서 계속 호출
		int mosqCreateCount = stage.getMosqCreateCount();
		//System.out.println(mosqCreateCount);
		for (int i = 0; i < mosqCreateCount; i++) {		// 모기
			if(mosqs.get(i).getCurrentDir() == 2) {
				int deleteTimer = mosqs.get(i).getDeleteTimer();
				deleteTimer--;
				mosqs.get(i).setDeleteTimer(deleteTimer);
			}
			
			if(mosqs.get(i).getDeleteTimer() == 0) {
				mosqs.remove(i);
				stage.setMosqCreateCount(--mosqCreateCount);
			}
		}
		
		
		
	/*
		//승리조건 : ArrayList<Mosquito>에 모든 객체들의 hp가 0일때
		for(int i = 0; i<mosqs.size();i++) {
			if(mosqs.get(i).getHp()<=0) {
				this.changeStage(stageIndex++);
			}
		}*/
		//패배조건 : 시간이 0이 되었을 때 or player의 hp가 0이 되었을 때
		/*if((timer.getOneCount() == 0 && timer.getTenCount() == 0) ||
				p1.getHp <= 0 ) {
			gameOver();
		}*/
		
	}

	public GameOver getGameOver() {
		// TODO Auto-generated method stub
		return gameOver;
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

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public int getStageIndex() {
		return stageIndex;
	}

	public void setStageIndex(int currentStageIndex) {
		this.stageIndex = currentStageIndex;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	
}
