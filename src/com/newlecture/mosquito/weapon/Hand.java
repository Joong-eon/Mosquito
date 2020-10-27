package com.newlecture.mosquito.weapon;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.newlecture.mosquito.service.ImageLoader;

public class Hand extends Weapon {

	public Hand() {

		this.setType("Hand");
		this.setDamage(2);
		this.setImg(ImageLoader.hand);
		this.setProb(0.42);
		this.setRange(2);

		this.setWidth(180);
		this.setHeight(89);
		this.setImgX(90);
		this.setImgY(23);
		this.setAttackSpeed(40); // 공격속도는 imgTempo * imgSize 보다 낮을 수 없음
		this.setImgSize(18);
		this.setImgTempo(1);
	}

}
