package com.newlecture.mosquito.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

import com.newlecture.mosquito.canvas.StageCanvas;
import com.newlecture.mosquito.service.ImageLoader;

public class Mosquito extends Bug {
//	private double x;
//	private double y;	
//
//	// 애니메이션을 위한 변수
//	private double vx;
//	private double vy;	
//	private double dx;
//	private double dy;	
//	private int moveIndex;
//	private int speed = 2; //�� ���� �ʿ�
//	private int walkTempo = 10; //�� ���� �ʿ�

//	private static String mosquito;
	
	//이미지 변수
	Image img = ImageLoader.mosquito ;//	private int w = 320; 
//	private int h = 270; 
//	private int timeoutForMoving=30;//�ʱ�ȭ
	private Random rand=new Random();	
	//모기 스탯변수
	private int hp;
	private int power;
	
	//생성자
	public Mosquito() {
	//	this(100,100); //랜덤값으로 수정
	}
	public Mosquito(double x, double y) {
		super(x,y,320,270,"res/mosquito.png");
//		this.x = x;
//		this.y = y;
//	rand = new Random();
//		Toolkit tk = Toolkit.getDefaultToolkit();
//		img = tk.getImage("res/mosquito.png");
//		this.move(250,250); //랜덤값으로 수정
	}
	
	//움직임 함수
//	private void move(double x, double y) {
//		this.dx = x;
//		this.dy = y;
//
//		double w = this.dx - this.x;
//		double h = this.dy - this.y;
//		double d = Math.sqrt(w*w+h*h);
//		this.vx = w/d*speed;
//		this.vy = h/d*speed;
//	}
	
	//업데이트 함수
		
		
//	public void update() {
//
//		timeoutForMoving--;
//		if(timeoutForMoving == 0) {
//			
//			double width = getWidth();
//			double height = getHeight();
////			int w = StageCanvas.instance.getWidth()-(int)width;
////			int h = StageCanvas.instance.getHeight()-(int)height;
//			
//			int w = StageCanvas.instance.getWidth()-(int)width;
//     		int h = StageCanvas.instance.getHeight()-(int)height;
//			
////			int dx = rand.nextInt(w)+(int)this.getWidth()/2;
////			int dy = rand.nextInt(h)+(int)this.getHeight()/2;
//     		
//     		int dx = rand.nextInt(w);
//			int dy = rand.nextInt(h);
//
//			this.move(dx,dy);
//			timeoutForMoving = rand.nextInt(60)+60; //랜덤값 수정필요
//		}
//			double x = getX();
//			double y = getY();
////			double dx = getDx();
////			double dy = getDy();
//			double vx = getVx();
//			double vy = getVy();
//			int movIndex = getMovIndex();
//
////		if((x-1<=dx&&dx<=x+1)&&
////				(y-1<=dy&&dy<=y+1)) {
////			vx=0;
////			vy=0;
////			movIndex = 0;
////			timeoutForMoving = 1;
////		}
//		x += vx;
//		y += vy;
//		
//        this.setX(x); 
//		
//		this.setY(y);
//		this.setVx(vx);
//		this.setVy(vy);
//		
//		this.setMovIndex(movIndex);
//	}
//	
//	//페인트 함수
	public void paint(Graphics g) {

		int w = (int)this.getWidth();
		int h = (int)this.getHeight();
		int x1 = (int)this.getX()- w/2;
		int y1 = (int)this.getY()-h/+13;
		int x2 = x1+w;
		int y2 = y1+h;
		int walkTempo = getWalkTempo();
		int movIndex = getMovIndex();
		
	
		double vx = getVx();
		double vy = getVy();
		
		Image img = getImg();

		if(walkTempo == 0) {
			movIndex++;
			movIndex = movIndex % 5; 
			walkTempo = 3; 
		}
		else
			walkTempo --;
		int offsetX = movIndex*w;
		if(movIndex<5) 
			offsetX-=1;

		if(vx>0)
			g.drawImage(img, x1, y1, x2, y2, 0+offsetX, 0, w+offsetX, h, StageCanvas.instance);
		if(vx<0)
			g.drawImage(img, x1, y1, x2, y2, 0+offsetX, h, w+offsetX, h+h, StageCanvas.instance);
		setWalkTempo(walkTempo);
		setMovIndex(movIndex);

	}
	
	//공격함수
	public void attack() {
		int power = this.power;
		power = 1; //변수 처리
	}
	
	@Override
	protected Image getImage() {
		// TODO Auto-generated method stub
		return img;
	}
	
	//모기 체력하수
//	public void hp() {
//		int hp = this.hp;
//		hp = 2; //변수처리해서 스테이지마다 값 변화
//		while(player.attck()) {
//			hp--;
//			
//		}
//		if(hp<=0) {
//			this.die();
//		}
//	}
	
	//모기 죽는 함수
//	private void die() {
//		
//		
//	}
}
