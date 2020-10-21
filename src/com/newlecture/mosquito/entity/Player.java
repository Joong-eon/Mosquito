package com.newlecture.mosquito.entity;

import com.newlecture.mosquito.weapon.FlySwatter;
import com.newlecture.mosquito.weapon.Hand;
import com.newlecture.mosquito.weapon.Spear;
import com.newlecture.mosquito.weapon.Weapon;

public class Player {
	
	//private Weapon[] weapon;
	private int money;
	private int tier;
	private Weapon wp;
	private Weapon currentWp;
	private int hp;
	private int score;
	private Weapon[] weapons;
	
	
	//player에 점수를 넣어놓고 인터페이스 구현해서 스테이지 클리어 시점만 알려주게끔 하면 되지 않을까?
	
	public Player() {
		tier = 1;//추후 파일 입출력으로 구현
		money = 0;
		//weapon = new Weapon[100];
		hp = 100;
		
		wp = new Hand();
		currentWp = wp;
		weapons = new Weapon[3];
		weapons[0] = currentWp;
		weapons[1] = new Spear();
		weapons[2] = new FlySwatter();
		//setImg;
		
		//나중에 정리해야함 일단 기능만 확인
		
		
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


	public int getMoney() {
		return money;
	}


	public void setMoney(int money) {
		this.money = money;
	}


	public int getTier() {
		return tier;
	}


	public void setTier(int tier) {
		this.tier = tier;
	}


	public Weapon getCurrentWp() {
		return currentWp;
	}


	public void setCurrentWp(Weapon currentWp) {
		this.currentWp = currentWp;
	}


	public int getHp() {
		return hp;
	}


	public void setHp(int hp) {
		this.hp = hp;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}
	
	public Weapon[] getWeapons() {
		return weapons;
	}


	public void setWeapons(Weapon[] weapons) {
		this.weapons = weapons;
	}


	public Weapon getWp() {
		return wp;
	}


	public void setWp(Weapon wp) {
		this.wp = wp;
	}
	
	
}
