package com.newlecture.mosquito.entity;

// 값을 담기 위해서만 쓰이는 구조체 같은 정보 클래스
// 값을 담고 있는 것 외에는 별다른 기능이 없어
// 변수의 접근제어자를 public으로 설정함. 

public class Stage {
	public int stageIndex;			// 현재 스테이지 인덱스
	
	public int mosqCreateCount;		// 스테이지 당 최대 생성 가능한 모기 수
	public int mosqMaxCount;		// 생성 주기에 따라 생성되는 모기 수
	public int mosqCreateTime;		// 모기 생성주기
	
	public int buttMaxCount;		// 스테이지 당 최대 생성 가능한 나비 수
	public int buttCreateCount;		// 생성 주기에 따라 생성되는 나비 수
	public int buttCreateTime; 		// 나비 생성 주기
	
	
	public Stage() {
		super();
		mosqMaxCount = 0;
		mosqCreateCount = 0;
		mosqCreateTime = 0;
		
		buttMaxCount = 0;
		buttCreateCount = 0;
		buttCreateTime = 0;
	}
	
}
