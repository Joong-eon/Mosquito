package com.newlecture.mosquito.weapon;

import com.newlecture.mosquito.service.ImageLoader;

public class RiceStraw extends Weapon {
	public RiceStraw() {
		this.setType("riceStraw");
		this.setDamage(2);
		this.setImg(ImageLoader.riceStraw);
		this.setProb(0.42);
		this.setRange(2);
		
		this.setWidth(120);
		this.setHeight(217);
		this.setImgX(60);
		this.setImgY(60);
		this.setAttackSpeed(40); //공격속도는 imgTempo * imgSize 보다 낮을 수 없음
		this.setImgSize(13);
		this.setImgTempo(2);
		
	}
}
