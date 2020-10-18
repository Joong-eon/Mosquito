package com.newlecture.mosquito.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.newlecture.mosquito.GameFrame;
import com.newlecture.mosquito.canvas.StageCanvas;
import com.newlecture.mosquito.service.ImageLoader;

public class Butterfly extends Bug {

	private Image img;
	
	private double lifeTime;
	private int penaltyTime = 5;
	
	private Random rand = new Random();

	public Butterfly() {  
		// 
		super();
		this.setWidth(60);
		this.setHeight(60);
		setImg(ImageLoader.butterfly);
		this.setDirection(0);
	}

	public void paint(Graphics g) {
		
		//System.out.println(this.getX() + " ,,,,," + this.getY());
		int w = (int) this.getWidth();
		int h = (int) this.getHeight();
		
		//System.out.println(w + " ,,,,, 너비");
		int x1 = (int) this.getX();
		int y1 = (int) this.getY();
		int x2 = x1 + w;
		int y2 = y1 + h;
		int movTempo = getWalkTempo();
		int movIndex = getMovIndex();
		Image img = getImg();	
//		Image img = ImageLoader.butterfly;
		double vx = getVx();
		double vy = getVy();

		//나비 모션 변화
		if (vx != 0 || vy != 0) {
			if (movTempo == 0) {
				movIndex++;
				movIndex = movIndex % 10;

				movTempo = 6;
			} else
				movTempo--;
		}
		 //나비 이미지 방향 구현
		/*
		double x = getX();
		double y = getY();
		double dx = getDx();
		
		if(x<dx) {
			direction = 60;
		}else {
			direction = 0;
		}*/
		
		int offsetX = movIndex * w;
		
		
		
		g.drawImage(img, x1, y1, x2, y2, 0 + offsetX, this.getDirection() , 0 + w + offsetX, this.getDirection() + h, StageCanvas.instance);
		setWalkTempo(movTempo);
		setMovIndex(movIndex);
	}


	@Override
	protected Image getImage() {
		// TODO Auto-generated method stub
		return img;
	}
}