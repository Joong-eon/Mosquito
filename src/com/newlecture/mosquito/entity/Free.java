package com.newlecture.mosquito.entity;

public class Free {   
   
   public int stageIndex;         // 현재 스테이지 인덱스
   public int limitTime;         // 스테이지 시간
   
   public int mosqCreateCount;      // 스테이지 당 최대 생성 가능한 모기 수
   public int mosqMaxCount;      // 생성 주기에 따라 생성되는 모기 수
   public int mosqCreateTime;      // 모기 생성주기
   
   public int buttMaxCount;      // 스테이지 당 최대 생성 가능한 나비 수
   public int buttCreateCount;      // 생성 주기에 따라 생성되는 나비 수
   public int buttCreateTime;       // 나비 생성 주기
   
   public int killScore;
//   public int winScore;
   

   


public Free() {
   super();
   mosqMaxCount = 0;
   mosqCreateCount = 0;
   mosqCreateTime = 0;
   
   buttMaxCount = 0;
   buttCreateCount = 0;
   buttCreateTime = 0;
//   killScore = 0;
}


public int getStageIndex() {
   return stageIndex;
}


public void setStageIndex(int stageIndex) {
   this.stageIndex = stageIndex;
}


public int getMosqCreateCount() {
   return mosqCreateCount;
}


public void setMosqCreateCount(int mosqCreateCount) {
   this.mosqCreateCount = mosqCreateCount;
}


public int getMosqMaxCount() {
   return mosqMaxCount;
}


public void setMosqMaxCount(int mosqMaxCount) {
   this.mosqMaxCount = mosqMaxCount;
}




public int getButtMaxCount() {
   return buttMaxCount;
}


public void setButtMaxCount(int buttMaxCount) {
   this.buttMaxCount = buttMaxCount;
}


public int getButtCreateCount() {
   return buttCreateCount;
}


public void setButtCreateCount(int buttCreateCount) {
   this.buttCreateCount = buttCreateCount;
}


public int getButtCreateTime() {
   return buttCreateTime;
}


public void setButtCreateTime(int buttCreateTime) {
   this.buttCreateTime = buttCreateTime;
}


public int getKillScore() {
   return killScore;
}


public void setKillScore(int killScore) {
   this.killScore = killScore;
}


public int getMosqCreateTime() {
	// TODO Auto-generated method stub
	return mosqCreateTime;
}







}