package com.newlecture.mosquito.service;

import java.awt.Image;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

// 프로그램에서 사용되는 이미지를 미리 로드해놓는 클래스
// 이미지를 사용하는 클래스 곳곳에 static 생성자를 사용하여 만드는 것이 번거로워 이 클래스에서 한번에 로드함.
public class ImageLoader {
	public static Image menuBtnNormal;
	public static Image menuBtnHover;
	public static Image menuBtnPressed;
	public static Image menuBackground;
	public static Image mosquito;
	public static Image butterfly;
	
	static {
		try {
			// 동기 방식 이미지 로드.
			menuBtnNormal = ImageIO.read(new File("res/woodButton_normal.png"));
			menuBtnHover = ImageIO.read(new File("res/woodButton_hover.png"));
			menuBtnPressed = ImageIO.read(new File("res/woodButton_pressed.png"));
			menuBackground = ImageIO.read(new File("res/menu_bg.jpg"));
		    mosquito = ImageIO.read(new File("res/mosquito.png"));
			butterfly = ImageIO.read(new File("res/butterfly.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
