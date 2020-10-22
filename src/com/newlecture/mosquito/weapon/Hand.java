package com.newlecture.mosquito.weapon;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Hand extends Weapon {
	
	public Hand() {
		
		
		/*
		try {
			this.setImg(ImageIO.read(new File("")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		
		
		
		this.setDamage(5);
		this.setType("hand");
		this.setProb(0.35);
		this.setRange(1);
	}
	
	
}
