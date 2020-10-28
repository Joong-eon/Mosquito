package com.newlecture.mosquito.weapon;

import com.newlecture.mosquito.service.ImageLoader;

public class Bow extends Weapon {
	public Bow() {

		this.setType("Bow");
		this.setDamage(2);
		this.setImg(ImageLoader.bow);
		this.setProb(0.42);
		this.setRange(2);

		this.setWidth(348);
		this.setHeight(533);
		this.setImgX(3);
		this.setImgY(3);
		this.setAttackSpeed(40); // 공격속도는 imgTempo * imgSize 보다 낮을 수 없음
		this.setImgSize(23);
		this.setImgTempo(1);
	}

}
