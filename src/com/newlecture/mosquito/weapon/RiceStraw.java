package com.newlecture.mosquito.weapon;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.newlecture.mosquito.canvas.StageCanvas;
import com.newlecture.mosquito.service.ImageLoader;

public class RiceStraw extends Weapon {
	private boolean click;

	public boolean isClick() {
		return click;
	}

	public void setClick(boolean click) {
		this.click = click;
	}

	private Image img;
	private int width = 120;
	private int height = 217;
	private int x = 300;
	private int y = 300;

	private int moveIndex = 0;
	private int mouseIndex = 60;
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	private int tempo = 0;

	public RiceStraw() {

		img = ImageLoader.riceStraw;
		click = false;

	}

	public void paint(Graphics g) {
		int x1 = x - 60;
		int y1 = y - 60;
		int w = width;
		int h = height;
		int mov = moveIndex;
		g.drawImage(img, x1, y1, x1 + w, y1 + h, 0 + w * mov, 0, 0 + w * mov + w, 0 + h, StageCanvas.instance);
	}

	public void update() {

		if (click) {

			tempo++;
			if (tempo % 3 == 0) {
				int mov = moveIndex;
				mov++;
				if (mov > 12) {
					click = false;
					mov = 0;
				}
				moveIndex = mov;
			}
		}
	}

	public boolean isClickable() {
		if (click == false) { // 1초에 60프레임 반복함. 20 = 60/3 = 약 0.3초
			return true;
		} else
			return false;
	}

}
