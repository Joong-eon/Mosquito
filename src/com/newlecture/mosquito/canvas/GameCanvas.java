package com.newlecture.mosquito.canvas;

import java.awt.Canvas;
import java.awt.Graphics;

public class GameCanvas extends Canvas {

	final int sleepTime = 17; 
	
	public void start() {
		Runnable sub = new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					gameUpdate();	
					repaint();
					try {
						Thread.sleep(sleepTime);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
					}
				}

			}
		};
		
		Thread th = new Thread(sub);
		th.start();
	}
	
	
	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		paint(g);
	}
	
	// update 스레드에서 처리 해줘야 할 업데이트 내용을 구현 (없을 수도 있음)
	public void gameUpdate() {
		
	}
}
