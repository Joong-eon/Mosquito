package com.newlecture.mosquito.canvas;

import java.awt.Canvas;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;

import com.newlecture.mosquito.entity.Butterfly;
import com.newlecture.mosquito.entity.Stage;
import com.newlecture.mosquito.entity.Timer;
import com.newlecture.mosquito.service.MosquitoService;
import com.newlecture.mosquito.weapon.Weapon;

public class StageCanvas extends Canvas{
	//��ü ����
	public static Canvas instance;
	Thread th;//������
	
	private MosquitoService mosqs;
	private Weapon weapon;
	private Butterfly butt;
	private Stage stage1;
	private Timer timer;
	//private StageService stages;
	
	private int count=1;
	
	
	public StageCanvas() {//������
		instance = this;
		
		mosqs = new MosquitoService();
		weapon = new Weapon();
		butt = new Butterfly();
		stage1 = new Stage();
		timer = new Timer();
		
	}


	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
		//weapon.paint(g);
		
		
		//timer.paint(g);
		//super.paint(g);
		
		Image buf = this.createImage(this.getWidth(), this.getHeight());
		Graphics bg = buf.getGraphics();

		timer.paint(bg);
		butt.paint(bg);
		g.drawImage(buf, 0, 0, this);//
		
	}


	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		paint(g);
	}
	
	public boolean mouseDown(Event evt, int x, int y) {
		
		weapon.setIsClicked(1);
		return super.mouseDown(evt, x, y);
	}
	
	public void start() {//������ ����

		Runnable sub = new Runnable() {

			@Override
			public void run() {

				while (true) {
					//weapon.update();
					timer.update();
					butt.update();
					repaint();
					
					try {
						Thread.sleep(17);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};

		th = new Thread(sub);
		th.start();
	}
}
