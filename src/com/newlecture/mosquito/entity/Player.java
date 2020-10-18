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
	
	public void attack() {
		//currentWp.
	}
	
	public void changeWeapon() {
		
	}
	
	
	
	
}
