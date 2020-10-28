package com.newlecture.mosquito.weapon;

import com.newlecture.mosquito.service.ImageLoader;

public class StrawShoes extends Weapon {
	public StrawShoes() {
		this.setType("StrawShoes");
		this.setDamage(2);
		this.setImg(ImageLoader.strawShoes);
		this.setProb(0.42);
		this.setRange(2);
		
		this.setWidth(250);
		this.setHeight(211);
		this.setImgX(60);
		this.setImgY(60);
		this.setAttackSpeed(40); //공격속도는 imgTempo * imgSize 보다 낮을 수 없음
		this.setImgSize(11);
		this.setImgTempo(3);
		
	}
}
