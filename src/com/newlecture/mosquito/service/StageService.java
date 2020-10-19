package com.newlecture.mosquito.service;

import java.util.ArrayList;

import com.newlecture.mosquito.entity.Bug;
import com.newlecture.mosquito.entity.Stage;

public class StageService {
	private ArrayList<Bug> bugs;		// 스테이지에 생성되는 모든 모기와 나비
	private Stage stage;
	private int stageIndex;
	private int totalScore=0;
	
	
	public StageService() {
		//currentStage = ;
		stageIndex = DataService.getInstance().getIntValue("default", "stageIndex");
		stage = DataService.getInstance().getStageValue(stageIndex);
		bugs = new ArrayList<Bug>();
		
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

	public void setStage(Stage currentStage) {
		this.stage = currentStage;
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
