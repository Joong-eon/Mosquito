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

	
//	private static String butterfly;
//	private double x;
//	private double y;
//
//	private double vx;
//	private double vy;
//	private double dx;
//	private double dy;
//
//	private double width;
//	private double height;
//	private int outRange = 60; //나비가 화면 바깥에서 나오게 하기위한 테두리 영역
//	
//	private double speed;
//	
//	// 나비 움직임 변수
//	private int movIndex;
//	private int walkTempo;
	
//	private int movTempo;
//	private int timeoutForMoving = 120;
	
	
	private Image img = ImageLoader.butterfly;
	
	private double lifeTime;
	private int penaltyTime = 5;
	
	private Random rand = new Random();
	
//	static{
//		try {
//			img = ImageIO.read(new File("res/butterfly.png"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	
	
	public Butterfly() {  
		
	}

	public Butterfly(double x, double y) {
//		int w = GameFrame.canvasWidth;
//		int h = GameFrame.canvasHeight;
//		
//		// 나비가 화면 바깥에서 나오게하기 위해 상하좌우 60만큼 좌표 추가
//		double x = (double) rand.nextInt(w + outRange * 2 + 1) - 60; 
//		double y = (double) rand.nextInt(h + outRange * 2 + 1) - 60;
//
//		// 만약 화면 안쪽에 나비가 생성되었을 경우 좌표 다시 설정
//		while (-30 < x && x < w + 30 && -30 < y && y < h + 30) {
//			x = (double) rand.nextInt(w + outRange * 2 + 1) - 60;
//			y = (double) rand.nextInt(h + outRange * 2 + 1) - 60;
//		}

		// 객체 좌표, 크기, 속도 설정
		
		super(x,y,60,60, "res/butterfly.png");
//		this.x = x;
//		this.y = y;
//
//		this.width = 60;
//		this.height = 60;
		//this.speed = 1;
	}

//	public void move(double x, double y) {
//		this.dx = x;
//		this.dy = y;
//		
//		double w = this.dx - this.x;
//		double h = this.dy - this.y;
//		double d = Math.sqrt(w * w + h * h);
//		this.vx = w / d * speed;
//		this.vy = h / d * speed;
//	}

//	public void update() {
//		timeoutForMoving--;
//		if (timeoutForMoving == 0) {
//			double width = getWidth(); 
//			double height = getHeight();
//
//			int w = GameFrame.canvasWidth - (int) width;
//			int h = GameFrame.canvasHeight - (int) height;
//			int dx = rand.nextInt(w);
//			int dy = rand.nextInt(h);
//
//			this.move(dx, dy);
//		
//			timeoutForMoving = rand.nextInt(60) + 60;// 0~59+60 // 60~119
//		}
//		
//		double x = getX();
//		double y = getY();
//		double vx = getVx();
//		double vy = getVy();
//		int movIndex = getMovIndex();
//		x += vx;
//		y += vy;
//
//		this.setX(x);
//		this.setY(y);
//		this.setVx(vx);
//		this.setVy(vy);
//		this.setMovIndex(movIndex);
//	}

	public void paint(Graphics g) {

		int w = (int) this.getWidth();
		int h = (int) this.getHeight();
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
		int direction = 0; //나비 이미지 방향 구현
		
		double x = getX();
		double y = getY();
		double dx = getDx();
		
		if(x<dx) {
			direction = 60;
		}else {
			direction = 0;
		}
		
		int offsetX = movIndex * w;
		
		
		
		g.drawImage(img, x1, y1, x2, y2, 0 + offsetX, direction , 0 + w + offsetX, direction + h, StageCanvas.instance);
		setWalkTempo(movTempo);
		setMovIndex(movIndex);
	}


	@Override
	protected Image getImage() {
		// TODO Auto-generated method stub
		return img;
	}
}