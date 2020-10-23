package com.newlecture.mosquito.service;

import java.awt.Image;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

// 프로그램에서 사용되는 이미지를 미리 로드해놓는 클래스
// 이미지를 사용하는 클래스 곳곳에 static 생성자를 사용하여 만드는 것이 번거로워 이 클래스에서 한번에 로드함.
public class ImageLoader {
	public static Image menuStageBtnNormal;
	public static Image menuStageBtnPressed;
	public static Image menuFreeBtnNormal;
	public static Image menuFreeBtnPressed;
	public static Image menuExitBtnNormal;
	public static Image menuExitBtnPressed;
	public static Image menuBackground;
	public static Image mosquito;
	public static Image butterfly;
	public static Image scoreNumber;
	public static Image gameOver;
	public static Image gameOverBtn;
	public static Image gameOverBg;
	
	public static Image timerNumber;
	public static Image timerDot;

	public static Image[] stageBackgrounds;
	
	static {
		try {
			// 동기 방식 이미지 로드.
			menuStageBtnNormal = ImageIO.read(new File("res/menu_stage_normal.png"));
			menuStageBtnPressed = ImageIO.read(new File("res/menu_stage_pressed.png"));
			menuFreeBtnNormal = ImageIO.read(new File("res/menu_free_normal.png"));
			menuFreeBtnPressed = ImageIO.read(new File("res/menu_free_pressed.png"));
			menuExitBtnNormal = ImageIO.read(new File("res/menu_exit_normal.png"));
			menuExitBtnPressed = ImageIO.read(new File("res/menu_exit_pressed.png"));

			menuBackground = ImageIO.read(new File("res/menu_bg.jpg"));
		    mosquito = ImageIO.read(new File("res/mosquito.png"));
			butterfly = ImageIO.read(new File("res/butterfly.png"));
			scoreNumber = ImageIO.read(new File("res/scoreNumber.png"));
			
			gameOver = ImageIO.read(new File("res/gameOver.png"));
			gameOverBtn = ImageIO.read(new File("res/gameOver.png"));
			gameOverBg =ImageIO.read(new File("res/gameOverBg.png"));

			timerNumber = ImageIO.read(new File("res/timer_final.png"));
			timerDot = ImageIO.read(new File("res/timer_dot.png"));
					
			int stageCount = DataService.getInstance().getGameIntValue("default", "stageCount");
			stageBackgrounds = new Image[stageCount];
			for(int i=0 ; i<stageCount ; i++) {
				stageBackgrounds[i] = ImageIO.read(new File("res/stage"+(i+1)+"_bg.jpg"));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
