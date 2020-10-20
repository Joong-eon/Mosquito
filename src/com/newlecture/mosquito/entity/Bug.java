package com.newlecture.mosquito.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import com.newlecture.mosquito.GameFrame;

public abstract class Bug{
	
	
	private double x;
	private double y;
	private int hp;

	

	// 애니메이션을 위한 변수
	private double vx;
	private double vy;
	private double dx;
	private double dy;
	private double width;
	private double height;
	private Image img;

	private int timeoutForMoving=30;
	private int movIndex ;
	private int speed ;
	private int walkTempo ;
	private int outRange ;
	private int direction;
	private int currentDir;

	
	private Random rand = new Random();
	


	public Bug() {   // 모기, 나비 초기값.
		int w = GameFrame.canvasWidth;
		int h = GameFrame.canvasHeight;
		
		// 나비가 화면 바깥에서 나오게하기 위해 상하좌우 60만큼 좌표 추가
		this.x = (double) rand.nextInt(w + outRange * 2 + 1) - 60; 
		this.y = (double) rand.nextInt(h + outRange * 2 + 1) - 60;
		
		// 만약 화면 안쪽에 나비가 생성되었을 경우 좌표 다시 설정
		while (-30 < x && x < w + 30 && -30 < y && y < h + 30) {
			x = (double) rand.nextInt(w + outRange * 2 + 1) - 60;
			y = (double) rand.nextInt(h + outRange * 2 + 1) - 60;
		}
		
		
		movIndex = 0;
		speed = 1;
		walkTempo = 6;
	}

	protected abstract Image getImage() ;
		
	
	public void move(double x, double y) {
	    this.dx = x;
		this.dy = y;

		// 동일한 속도로 이동하는 단위벡터
		double w = this.dx - this.x;
		double h = this.dy - this.y;
		double d = Math.sqrt(w*w + h*h);
		this.vx = w/d*speed;
		this.vy = h/d*speed;

	}

	public  void update() {
		timeoutForMoving--;
		if (timeoutForMoving == 0) {
			double width = (int) this.width;
			double height = (int) this.height;

			int w = GameFrame.canvasWidth - (int) width;
			int h = GameFrame.canvasHeight - (int) height;
			int dx = rand.nextInt(w);
			int dy = rand.nextInt(h);
			//왼쪽
			this.move(dx, dy);
			
			if(this.x < dx && currentDir == 0) {//모기의 방향 설정
				currentDir = 1;
				if(direction == 0)
					direction = (int)width;
				else
					direction = 0;
			}
			else if(this.x >= dx && currentDir == 1) {
				currentDir = 0;
				if(direction == 0)
					direction = (int)width;
				else
					direction = 0;
			}

			timeoutForMoving = rand.nextInt(60) + 60;// 0~59+60 // 60~119
		}

		x += vx;
		y += vy;
		

	}
	
	public boolean isSelected(int x, int y) {
		
		if((x > this.x-this.width/2 && x < this.x+this.width/2)
				&& (y > this.y-this.height/2 && x < this.y+this.height/2)) {
			return true;
		}else
			return false;
	}
	
	
	
	public abstract void paint(Graphics g);
	
	

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public int getMovIndex() {
		return movIndex;
	}

	public void setMovIndex(int movIndex) {
		this.movIndex = movIndex;
	}

	public int getSpeed() {
		return speed;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getWalkTempo() {
		return walkTempo;
	}

	public void setWalkTempo(int walkTempo) {
		this.walkTempo = walkTempo;
	}
	public int getTimeoutForMoving() {
		return timeoutForMoving;
	}
	public void setTimeoutForMoving(int timeoutForMoving) {
		this.timeoutForMoving = timeoutForMoving;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

}


