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

public class Butterfly {

	private double x;
	private double y;

	private double vx;
	private double vy;
	private double dx;
	private double dy;

	private double width;
	private double height;
	private int outRange = 60; //나비가 화면 바깥에서 나오게 하기위한 테두리 영역
	
	private double speed;
	
	// 나비 움직임 변수
	private int movIndex;
	private int movTempo;
	private int timeoutForMoving = 120;
	
	private double lifeTime;
	private int penaltyTime = 5;
	
	private Random rand = new Random();

	public Butterfly() {
		int w = GameFrame.canvasWidth;
		int h = GameFrame.canvasHeight;
		
		// 나비가 화면 바깥에서 나오게하기 위해 상하좌우 60만큼 좌표 추가
		double x = (double) rand.nextInt(w + outRange * 2 + 1) - 60; 
		double y = (double) rand.nextInt(h + outRange * 2 + 1) - 60;

		// 만약 화면 안쪽에 나비가 생성되었을 경우 좌표 다시 설정
		while (-30 < x && x < w + 30 && -30 < y && y < h + 30) {
			x = (double) rand.nextInt(w + outRange * 2 + 1) - 60;
			y = (double) rand.nextInt(h + outRange * 2 + 1) - 60;
		}

		// 객체 좌표, 크기, 속도 설정
		this.x = x;
		this.y = y;

		this.width = 60;
		this.height = 60;
		this.speed = 1;
	}

	public void move(double x, double y) {
		this.dx = x;
		this.dy = y;
		
		double w = this.dx - this.x;
		double h = this.dy - this.y;
		double d = Math.sqrt(w * w + h * h);
		this.vx = w / d * speed;
		this.vy = h / d * speed;
	}

	public void update() {
		timeoutForMoving--;
		if (timeoutForMoving == 0) {
			double width = getWidth();
			double height = getHeight();

			int w = GameFrame.canvasWidth - (int) width;
			int h = GameFrame.canvasHeight - (int) height;
			dx = rand.nextInt(w);
			dy = rand.nextInt(h);

			this.move(dx, dy);
		
			timeoutForMoving = rand.nextInt(60) + 60;// 0~59+60 // 60~119
		}

		x += vx;
		y += vy;

		this.setX(x);
		this.setY(y);
		this.setVx(vx);
		this.setVy(vy);
		this.setMovIndex(movIndex);
	}

	public void paint(Graphics g) {

		int w = (int) this.getWidth();
		int h = (int) this.getHeight();
		int x1 = (int) this.getX();
		int y1 = (int) this.getY();
		int x2 = x1 + w;
		int y2 = y1 + h;
		int movTempo = getMovTempo();
		int movIndex = getMovIndex();
		Image img = ImageLoader.butterfly;
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
		int direction = 0; //나비 이미지 방향 구현
		
		if(x<dx) {
			direction = 60;
		}else {
			direction = 0;
		}
		
		int offsetX = movIndex * w;
		
		
		
		g.drawImage(img, x1, y1, x2, y2, 0 + offsetX, direction , 0 + w + offsetX, direction + h, StageCanvas.instance);
		setMovTempo(movTempo);
		setMovIndex(movIndex);
	}

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

	public int getOutRange() {
		return outRange;
	}

	public void setOutRange(int outRange) {
		this.outRange = outRange;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getMovIndex() {
		return movIndex;
	}

	public void setMovIndex(int movIndex) {
		this.movIndex = movIndex;
	}

	public int getMovTempo() {
		return movTempo;
	}

	public void setMovTempo(int movTempo) {
		this.movTempo = movTempo;
	}

	public int getTimeoutForMoving() {
		return timeoutForMoving;
	}

	public void setTimeoutForMoving(int timeoutForMoving) {
		this.timeoutForMoving = timeoutForMoving;
	}

	public double getLifeTime() {
		return lifeTime;
	}

	public void setLifeTime(double lifeTime) {
		this.lifeTime = lifeTime;
	}

	public int getPenaltyTime() {
		return penaltyTime;
	}

	public void setPenaltyTime(int penaltyTime) {
		this.penaltyTime = penaltyTime;
	}

	public Random getRand() {
		return rand;
	}

	public void setRand(Random rand) {
		this.rand = rand;
	}
}