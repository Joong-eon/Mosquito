package com.newlecture.mosquito.gui;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.newlecture.mosquito.canvas.MenuCanvas;
import com.newlecture.mosquito.gui.listener.ButtonClickedListener;

public class Button {

	// 버튼 이름(버튼 구분을 위함. id 역할)
	private String name;
		
	// 화면상에서 버튼의 좌표 
	private double x;
	private double y;
	
	// 버튼의 크기
	private int width;
	private int height;
	
	// 이미지 파일에서 버튼에 적용할 이미지의 좌표 (source 좌표)
	private Image img;
	
	// 이미지의 크기
	private int imgWidth;
	private int imgHeight;

	// 버튼을 사용할 곳에서 해당 리스너의 메소드를 오버라이드 해야 "버튼이 클릭 되었을 떄" 처리가 가능함
	private ButtonClickedListener clickListener;
	
	public Button(String name, Image img, double x, double y, int width, int height) {
		super();
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.img = img;
		this.imgWidth = img.getWidth(null);
		this.imgHeight = img.getHeight(null);
		
		clickListener = null;
	}
	
	public void paint(Graphics g) {// bg

		int w = this.getWidth();
		int h = this.getHeight();
		
		int x1 = (int)this.getX();
		int y1 = (int)this.getY();
		int x2 = (int)x1 + w;
		int y2 = (int)y1 + h;
		
		
		g.drawImage(img, (int)x, (int)y, (int)x+width , (int)y+height, 
				(int)0, (int)0, (int)imgWidth, (int)imgHeight, MenuCanvas.instance);				
	}
	
	public boolean contains(double x, double y) {
		boolean result = false;
		
		if( this.x <= x && x <= this.x+width 
				&& this.y <= y && y <= this.y+height  ) {
			result = true;
		}
		
		return result;
	}
	
	// 외부(ex:캔버스)에서 버튼이 클릭 됐을 때 호출
	public void onButtonClicked() {
		if(null != clickListener) {
			clickListener.onClicked(this);		// 버튼이 클릭 되면 리스너 함수가 실행된다.  => 캔버스 말고도 이 리스너에 등록된 함수들이 호출됨
		}
	}
	
	public void update() {
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	
	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public ButtonClickedListener getClickListener() {
		return clickListener;
	}

	public void addClickListener(ButtonClickedListener clickListener) {
		this.clickListener = clickListener;
	}
	

}
