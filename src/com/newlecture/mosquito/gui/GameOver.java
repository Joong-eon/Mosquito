package com.newlecture.mosquito.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;

import com.newlecture.mosquito.canvas.MenuCanvas;
import com.newlecture.mosquito.gui.listener.ButtonClickedListener;
import com.newlecture.mosquito.service.ImageLoader;

public class GameOver extends Button { //이름("gameOver, 이미지(gameOver), 이미지(gameOver),놓일좌표x(x-width/2), 놓일좌표y(y-height/2), 넓이(216), 높이(283)


		public GameOver(String name, Image normalImg, Image pressedImg, double x, double y, int width, int height) {
			super(name, normalImg, pressedImg, x, y, width, height);
		}
		

		
		
}
