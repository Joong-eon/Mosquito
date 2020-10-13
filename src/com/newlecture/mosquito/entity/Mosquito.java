package com.newlecture.mosquito.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

import com.newlecture.mosquito.canvas.StageCanvas;

public class Mosquito {
	private double x;
	private double y;	

	//�ִϸ��̼��� ���� ����
	private double vx;
	private double vy;	
	private double dx;
	private double dy;	
	private int moveIndex;
	private int speed = 2; //�� ���� �ʿ�
	private int walkTempo = 10; //�� ���� �ʿ�

	//�̹����� ���� ����
	private Image img;
	private int w = 320; 
	private int h = 270; 
	private int timeoutForMoving=30;//�ʱ�ȭ
	Random rand;
	
	//��� ����
	private int hp;
	private int power;
	//������
	public Mosquito() {
		this(100,100); //�� ���� �ʿ�
	}
	public Mosquito(double x, double y) {
		this.x = x;
		this.y = y;
		rand = new Random();
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/mosquito.png");
		this.move(250,250); //�� ���� �ʿ�
	}
	
	//������(move)�Լ�
	private void move(double x, double y) {
		this.dx = x;
		this.dy = y;

		double w = this.dx - this.x;
		double h = this.dy - this.y;
		double d = Math.sqrt(w*w+h*h);
		this.vx = w/d*speed;
		this.vy = h/d*speed;
	}
	
	//������Ʈ(update)�Լ�
	public void update() {

		timeoutForMoving--;
		if(timeoutForMoving == 0) {
			int dx = rand.nextInt(StageCanvas.instance.getWidth()-w)+w/2;
			int dy = rand.nextInt(StageCanvas.instance.getHeight()-h)+h/2;

			this.move(dx,dy);
			timeoutForMoving = rand.nextInt(60)+60; //�� ���� �ʿ�
		}

		if((this.x-1<=this.dx&&this.dx<=this.x+1)&&
				(this.y-1<=this.dy&&this.dy<=this.y+1)) {
			this.vx=0;
			this.vy=0;
			moveIndex = 0;
		}
		this.x += this.vx;
		this.y += this.vy;
	}
	
	//����Ʈ(paint)�Լ�
	public void paint(Graphics g) {

		int w = this.w;
		int h = this.h;
		int x1 = (int) (x- w/2);
		int y1 = (int) (y-h/2);
		int x2 = x1+w;
		int y2 = y1+h;

		if(walkTempo == 0) {
			moveIndex++;
			moveIndex = moveIndex % 5; 
			walkTempo = 3; 
		}
		else
			walkTempo --;
		int offsetX = moveIndex*w;
		if(moveIndex<5) //�� ���� �ʿ�
			offsetX-=1;

		if(vx>0)
			g.drawImage(img, x1, y1, x2, y2, 0+offsetX, 0, w+offsetX, h, StageCanvas.instance);
		if(vx<0)
			g.drawImage(img, x1, y1, x2, y2, 0+offsetX, h, w+offsetX, h+h, StageCanvas.instance);

	}
	
	//��� ����(attack)�Լ�
	public void attack() {
		int power = this.power;
		power = 1; //�� ���� �ʿ�
	}
	
	//��� ü��(hp)�Լ�
//	public void hp() {
//		int hp = this.hp;
//		hp = 2; //����ó��(������������ �ö󰡰Բ�)
//		while(player.attck()) {
//			hp--;
//			
//		}
//		if(hp<=0) {
//			this.die();
//		}
//	}
	
	//��� �����Լ�
//	private void die() {
//		
//		
//	}
}
