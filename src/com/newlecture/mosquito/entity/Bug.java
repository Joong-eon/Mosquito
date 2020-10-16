package com.newlecture.mosquito.entity;

import java.awt.Graphics;
import java.awt.Image;

public abstract class Bug{
	private double x;
	private double y;

	// 애니메이션을 위한 변수
	private double vx;
	private double vy;
	private double dx;
	private double dy;
	private double width;
	private double height;
	private Image img;

	private int movIndex = 0;
	private int speed = 1;
	private int walkTempo = 6;

	public Bug() {
		this(0,0,0,0,null);
	}
	public Bug(double x, double y, String imgSrc) {
		this(x,y,0,0,imgSrc);
	}

	public  Bug(double x, double y, int width, int height, String imgSrc) {
	//	Toolkit tk = Toolkit.getDefaultToolkit();//비동기
		img =getImage();//tk.getImage(imgSrc);

		this.x=x;
		this.y=y;
		this.width = width;
		this.height = height;

	}

	protected abstract Image getImage() ;//서비스함수가아닌 자식에게만 공유 할 것은 protected
		
	
	public void move(double x, double y) {
//			this.x = x;
//			this.y = y;
	    this.dx = x;
		this.dy = y;

		// 동일한 속도로 이동하는 단위벡터
		double w = this.dx - this.x;
		double h = this.dy - this.y;
		double d = Math.sqrt(w*w + h*h);
		this.vx = w/d*speed;
		this.vy = h/d*speed;

	}

	public abstract void update(); //자식에게 강요

	public abstract void paint(Graphics g);
	
	public boolean isSelected(int x, int y) {
		int w = (int)this.width;
		int h = (int)this.height;
		int x1 = (int)this.x - w/2;
		int y1 = (int)this.y- h+13;
		int x2 = x1+w;
		int y2 = y1+h;

		if((x1  < x && x < x2)  
				&& (y1 < y && y < y2))
			return true;

		return false;
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


}



