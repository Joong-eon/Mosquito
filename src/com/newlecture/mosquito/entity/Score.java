package com.newlecture.mosquito.entity;

import java.awt.Graphics;
import java.awt.Image;

import com.newlecture.mosquito.canvas.StageCanvas;
import com.newlecture.mosquito.service.ImageLoader;

public class Score {

	private Image img;
	private int x;
	private int y;
	private int width;
	private int height;
	private int score;

	public Score() {
		setImg(ImageLoader.scoreNumber);
		x = 1420;
		y = 50;
		width = 56;
		height = 72;
		score = 0;
	}

	public void paint(Graphics g) {

		for (int i = 0; i < 4; i++) {
			g.drawImage(img, x + width * i, y, x + width * i + width, y + height, 0 + width * i, 0,
					0 + width * i + width, 0 + height, StageCanvas.instance);
		}
	}
	
	public void update() {
		
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
