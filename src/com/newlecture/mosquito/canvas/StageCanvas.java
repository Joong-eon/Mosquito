package com.newlecture.mosquito.canvas;

import java.awt.Canvas;
import java.awt.Event;
import java.awt.Graphics;

import com.newlecture.mosquito.entity.Butterfly;
import com.newlecture.mosquito.entity.Stage;
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
	//private StageService stages;
	
	
	public StageCanvas() {//������
		mosqs = new MosquitoService();
		weapon = new Weapon();
		butt = new Butterfly();
		stage1 = new Stage();
		
		
	}


	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
	}


	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		super.update(g);
	}
	
	public boolean mouseDown(Event evt, int x, int y) {
		
		
		return super.mouseDown(evt, x, y);
	}
	
	public void start() {//������ ����

		Runnable sub = new Runnable() {

			@Override
			public void run() {

				while (true) {
					
				}
			}
		};

		th = new Thread(sub);
		th.start();
	}
	
	
}
