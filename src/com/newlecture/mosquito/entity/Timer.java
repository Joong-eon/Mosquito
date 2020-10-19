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
	private int w = 44;
	private int h = 68;
	//private int totalCount=100;
	private int timeForTimer = 60;
	private int tenCount = 10;
	private int oneCount = 10;
	
	
	public Timer() {
		try {
			img = ImageIO.read(new File("res/timer_final.png"));//파일이름 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void paint(Graphics g) {
		if(oneCount == 0) {
			tenCount--;
			oneCount = 10;
		}
		g.drawImage(img, 100-44, 100, 100-44+w, 100+h, (tenCount-1)*w, y, (tenCount-1)*w+w, y+h, StageCanvas.instance);
		g.drawImage(img, 100, 100, 100+w, 100+h, (oneCount-1)*w, y, (oneCount-1)*w+w, y+h, StageCanvas.instance);
		//g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
	}
	
	public void update() {
		//1초당 이미지 좌표 변경
		timeForTimer--;
		if(timeForTimer == 0) {
			//totalCount--;
			
			x += w;//십의자리 일의자리 좌표 세팅
			oneCount--;
			timeForTimer = 60;
		}
	}
	
}
