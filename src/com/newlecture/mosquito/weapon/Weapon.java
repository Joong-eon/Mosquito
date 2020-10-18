package com.newlecture.mosquito.weapon;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.newlecture.mosquito.canvas.StageCanvas;

public class Weapon {//추상클래스 구현
	
	private String type;
	private int damage;
	private double prob;
	private Image img;
	private int isClicked=0;
	private double range = 0.8;
	
	private int w = 72;
	private int h = 52;
	private int x=0;
	private int y=0;
	
	private int moveTempo = 6;
	private int moveIndex = 1;
	
	
	public Weapon() {
		try {
			img = ImageIO.read(new File("res/hand2.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		damage = 10;
		type = "hand";
		prob = 0.3;
	}
	
	public void paint(Graphics g) {
		System.out.println(1);
		if(isClicked == 1) {
			if(moveTempo == 0) {
				moveIndex++;
				moveTempo = 6;
			}
			else
				moveTempo--;
		}
			
		
		int moveImg = moveIndex*w;
		
		g.drawImage(img, x, y, x+w, y+h, moveImg-w, 0, moveImg, h, StageCanvas.instance);
		
		if(moveIndex == 8) {
			moveIndex = 1;
			isClicked = 0;
		}
	}
	
	public void update() {
		
	}
	
	
	public void move(int x, int y) {//모기를 잡는 행위를 하는것
		System.out.println(x+"  ,"+ y);
		this.x = x;
		this.y = y;
	}
	
	public void cursor() {//마우스 위치에 따라 이동 이동
		
	}

	public void setIsClicked(int isClicked) {// 클릭 쿨타임 설정
		this.isClicked = isClicked;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public double getProb() {
		return prob;
	}

	public void setProb(double prob) {
		this.prob = prob;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public double getRange() {
		return range;
	}

	public void setRange(double range) {
		this.range = range;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getMoveTempo() {
		return moveTempo;
	}

	public void setMoveTempo(int moveTempo) {
		this.moveTempo = moveTempo;
	}

	public int getMoveIndex() {
		return moveIndex;
	}

	public void setMoveIndex(int moveIndex) {
		this.moveIndex = moveIndex;
	}

	public int getIsClicked() {
		return isClicked;
	}
}
