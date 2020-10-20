package com.newlecture.mosquito.service;

import java.util.ArrayList;

import com.newlecture.mosquito.entity.Bug;
import com.newlecture.mosquito.entity.Butterfly;
import com.newlecture.mosquito.entity.Mosquito;
import com.newlecture.mosquito.entity.Stage;

public class StageService {
	private ArrayList<Bug> bugs;		// 스테이지에 생성되는 모든 모기와 나비
	private Stage stage;
	private int stageIndex;
	private int totalScore=0;
	
	
	public StageService() {
		int startIndex = DataService.getInstance().getGameIntValue("default", "stageIndex");	
		changeStage(startIndex);
	}
	
	public void changeStage(int stageIndex) {
		this.stageIndex = stageIndex;		// 현재 스테이지 바꾸고
		
		if(bugs == null) {
			bugs = new ArrayList<Bug>();	
		} else {
			bugs.clear();
		}
		
		// 새로운 스테이지 정보 가져오기
		stage = DataService.getInstance().getStageValue(stageIndex);
		
		//모기 & 나비 생성
		int mosqCreateCount = stage.getMosqCreateCount();
		int buttCreateCount = stage.getButtCreateCount();

		for (int i = 0; i < mosqCreateCount; i++) {		// 모기
			bugs.add(new Mosquito());
		}
		
		for (int i = 0; i < buttCreateCount; i++) {		// 나비
			bugs.add(new Butterfly());
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

	public ArrayList<Bug> getBugs() {
		return bugs;
	}

	public void setBugs(ArrayList<Bug> bugs) {
		this.bugs = bugs;
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
