package com.newlecture.mosquito.weapon;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.newlecture.mosquito.canvas.StageCanvas;
import com.newlecture.mosquito.service.ImageLoader;

public class Fan extends Weapon {
	public Fan() {
		this.setType("Fan");
		this.setDamage(2);
		this.setImg(ImageLoader.fan);
		this.setProb(0.42);
		this.setRange(2);

		this.setWidth(100);
		this.setHeight(136);
		this.setImgX(75);
		this.setImgY(60);
		this.setAttackSpeed(40); // 공격속도는 imgTempo * imgSize 보다 낮을 수 없음
		this.setImgSize(23);
		this.setImgTempo(1);
	}
}
