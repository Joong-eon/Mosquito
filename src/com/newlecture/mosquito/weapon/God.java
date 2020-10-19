package com.newlecture.mosquito.weapon;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.newlecture.mosquito.canvas.StageCanvas;

public class God {
	private String type;
	private int damage;
	private double prob;
	private Image img;
	private int isClicked=0;
	private double range = 5;
	
	private int w = 72;
	private int h = 52;
	private int x=0;
	private int y=0;
	
	
	public God() {
		try {
			img = ImageIO.read(new File(""));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		damage = 30;
		type = "god";
		prob = 0.3;
	}
	
	public void paint(Graphics g) {
		
	}
	
	public void update() {
		
	}
	
	public void cursor() {//마우스 위치에 따라 이동 이동
	
	}
}
