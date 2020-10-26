package com.newlecture.mosquito.weapon;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.newlecture.mosquito.canvas.StageCanvas;
import com.newlecture.mosquito.service.ImageLoader;

public class Spear extends Weapon{
	
	public Spear() {
		this.setType("spear");
		this.setDamage(2);
		this.setImg(ImageLoader.spear);
		this.setProb(0.42);
		this.setRange(2);
		
		this.setWidth(180);
		this.setHeight(223);
		this.setImgX(10);
		this.setImgY(10);
		this.setAttackSpeed(40); //공격속도는 imgTempo * imgSize 보다 낮을 수 없음
		this.setImgSize(11);
		this.setImgTempo(3);
	}
	
}
