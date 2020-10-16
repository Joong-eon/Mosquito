package com.newlecture.mosquito.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.newlecture.mosquito.canvas.StageCanvas;

public class Timer {
	
	private Image img;
	
	private int x=0;
	private int y=0;
	private int w = 39;
	private int h = 44;
	
	private int timeForTimer = 120;
	
	
	public Timer() {
		try {
			img = ImageIO.read(new File("res/timer1.jpg"));//파일이름 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void paint(Graphics g) {
		g.drawImage(img, 100, 100, 100+w, 100+h, x, y, x+w, y+h, StageCanvas.instance);
		//g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
	}
	
	public void update() {
		//1초당 이미지 좌표 변경
		timeForTimer--;
		if(timeForTimer == 0) {
			x += w;//십의자리 일의자리 좌표 세팅
			
			timeForTimer = 120;
		}
	}
	
}
