package com.newlecture.mosquito.service;

import com.newlecture.mosquito.entity.Stage;

public class StageService {
	private Stage currentStage;
	private int currentStageIndex;
	private int totalScore=0;
	
	
	public StageService() {
		//currentStage = ;
		currentStageIndex = DataService.getInstance().getIntValue("default", "stageIndex");
		currentStage = DataService.getInstance().getStageValue(currentStageIndex);

	}
	
	public void setScore() {
		int killScore = currentStage.getKillScore();
		totalScore += killScore;
		
		/*스테이지 완료 및 스테이지 전환 구현하기.
		if(totalScore >= currentStage.getWinScore()) {
			currentStage = Dat
		}*/
	}
}
