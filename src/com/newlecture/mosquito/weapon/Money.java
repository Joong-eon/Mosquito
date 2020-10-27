package com.newlecture.mosquito.weapon;

import com.newlecture.mosquito.service.ImageLoader;

public class Money extends Weapon {
	public Money() {
		this.setType("Money");
		this.setDamage(2);
		this.setImg(ImageLoader.money);
		this.setProb(0.42);
		this.setRange(2);

		this.setWidth(240);
		this.setHeight(223);
		this.setImgX(10);
		this.setImgY(10);
		this.setAttackSpeed(40); // 공격속도는 imgTempo * imgSize 보다 낮을 수 없음
		this.setImgSize(21);
		this.setImgTempo(3);
	}
}
