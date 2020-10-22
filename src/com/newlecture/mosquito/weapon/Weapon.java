package com.newlecture.mosquito.weapon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.newlecture.mosquito.canvas.StageCanvas;
import com.newlecture.mosquito.entity.Bug;

public abstract class Weapon {// 추상클래스 구현

	private String type;
	private int damage;
	private double prob;
	private Image img;//img
	private boolean isClicked = false;
	private int range;

	private int width = 72;
	private int height = 52;
	private int x = 0;
	private int y = 0;

	private int moveIndex = 1;
	private int mouseIndex = 60;
/*
	public Weapon() {
		isClicked = false;
		this.damage = 5;
		this.type = "hand";
		this.prob = 0.35;
		this.range = 1;
	}*/

	public void paint(Graphics g) {

		g.setColor(Color.GREEN);
		g.drawRect(x - range, y - range, range * 2, range * 2);
		g.drawImage(img, x, y, x + width, y + height, 0, 0, width, height, StageCanvas.instance);
	}

	public void update() {
		mouseIndex++;
	}

	public boolean isClickable() {
		if (mouseIndex > 60) { // 1초에 60프레임 반복함. 20 = 60/3 = 약 0.3초
			mouseIndex = 0;
			return true;
		} else
			return false;
	}

	// 웨폰의 공격 범위에 벌레가 들어왔는지 판단하는 메소드
	public boolean isAttackRange(Bug bug) {
		boolean isIntersect = false;
		
		// Bug의 좌표
		/*
		double bX1 = bug.getX();
		double bY1 = bug.getY();
		double bX2 = bug.getX() + bug.getWidth();
		double bY2 = bug.getY() + bug.getHeight();*/
		double bX1 = bug.getX()-bug.getWidth()/2;
		double bY1 = bug.getY()-bug.getHeight()/2;
		double bX2 = bug.getX() + bug.getWidth()/2;
		double bY2 = bug.getY() + bug.getHeight()/2;

		// weapon의 공격 범위
		double mX1 = x - range;
		double mY1 = y - range;
		double mX2 = x + range;
		double mY2 = y + range;
		
		if ( ( (bX1 <= mX1 && mX1 <= bX2) || (bX1 <= mX2 && mX2 <= bX2) ) 
				&&  ( (bY1 <= mY1 && mY1 <= bY2) || (bY1 <= mY2 && mY2 <= bY2) ) ) {
			isIntersect = true;
		} else {
			isIntersect = false;
		}

		return isIntersect;
	}

	public void setIsClicked(boolean isClicked) {// 클릭 쿨타임 설정
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

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
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

	public int getMoveIndex() {
		return moveIndex;
	}

	public void setMoveIndex(int moveIndex) {
		this.moveIndex = moveIndex;
	}

	public boolean getIsClicked() {
		return isClicked;
	}
	
	

}
