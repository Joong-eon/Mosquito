package com.newlecture.mosquito.weapon;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.newlecture.mosquito.canvas.StageCanvas;

public class Spear extends Weapon{
	/*private String type;
	private int damage;
	private double prob;
	private Image img;
	private int isClicked=0;
	private double range = 1;
	
	private int w = 72;
	private int h = 52;
	private int x=0;
	private int y=0;*/
	
	
	public Spear() {
		try {
			this.setImg(ImageIO.read(new File("res/spear.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setDamage(80);
		this.setType("spear");
		this.setProb(0.4);
		this.setRange(1);
	}
	
}
