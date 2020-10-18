package com.newlecture.mosquito.entity;

import com.newlecture.mosquito.weapon.Weapon;

public class Player {
	
	//private Weapon[] weapon;
	private int money;
	private int tier;
	private Weapon currentWp;
	private int hp;
	private int score;
	
	//player에 점수를 넣어놓고 인터페이스 구현해서 스테이지 클리어 시점만 알려주게끔 하면 되지 않을까?
	
	
	public Player() {
		tier = 1;//추후 파일 입출력으로 구현
		money = 0;
		//weapon = new Weapon[100];
		hp = 100;
		
		currentWp = new Weapon();
		
	}
	
	
	public boolean attack(Bug bug) {
		//무기 확률 체크
		double prob = Math.random();//0~1 랜덤 실수
		
		if(prob <= currentWp.getProb()) {
			int bugHp = bug.getHp();
			bugHp -= currentWp.getDamage();
			bug.setHp(bugHp);
			return true;
		}else
			return false;

		//currentwp.~~~~~~~~~();
		//mosq.attak(30);		// 해당 모기의 체력을 30 감소시킨당
	}
	
	public void changeWeapon() {
		
	}
	
	
	
	
}
