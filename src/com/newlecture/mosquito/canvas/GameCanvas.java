package com.newlecture.mosquito.canvas;

import java.awt.Canvas;
import java.awt.Graphics;

public class GameCanvas extends Canvas {

	private final int sleepTime = 17; 
	private boolean isRunThread = true;
	
	public void start() {
		Runnable sub = new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (isRunThread) {
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
	
	public boolean isRunThread() {
		return isRunThread;
	}

	public void stop() {
		this.isRunThread = false;
	}

	// update 스레드에서 처리 해줘야 할 업데이트 내용을 구현 (없을 수도 있음)
	public void gameUpdate() {
		
	}
}
