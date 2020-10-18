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
	private int power;
	
	//생성자
	public Mosquito() {
	//	this(100,100); //랜덤값으로 수정
		super();
		this.setWidth(320);
		this.setHeight(270);
		setImg(ImageLoader.mosquito);
		this.setDirection((int)this.getWidth());
	}	
	
//	//페인트 함수
	public void paint(Graphics g) {
		int w = (int)this.getWidth();
		int h = (int)this.getHeight();
		int x1 = (int)this.getX() - w/2;
		int y1 = (int)this.getY() - h/2;
		int x2 = x1 + 60;
		int y2 = y1 + 60;
		int walkTempo = getWalkTempo();
		int movIndex = getMovIndex();
	
		Image img = getImg();
		
		if(walkTempo == 0) {
			movIndex++;
			movIndex = movIndex % 5; 
			walkTempo = 3; 
		}
		else
			walkTempo--;
		int offsetX = movIndex*w;
		if(movIndex<5) 
			offsetX-=1;
		
		g.drawImage(img, x1, y1, x2, y2, 0+offsetX, this.getDirection(), w+offsetX, this.getDirection() + h, StageCanvas.instance);
		
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

	

	
}
