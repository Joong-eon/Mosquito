package com.newlecture.mosquito.weapon;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.newlecture.mosquito.canvas.StageCanvas;

public class Fan extends Weapon{
	public Fan() {
		try {
			this.setImg(ImageIO.read(new File("")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setDamage(20);
		this.setType("fan");
		this.setProb(0.5);
		this.setRange(3);
	}
	
}
