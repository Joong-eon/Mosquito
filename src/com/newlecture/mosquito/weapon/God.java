package com.newlecture.mosquito.weapon;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.newlecture.mosquito.canvas.StageCanvas;

public class God extends Weapon{

	public God() {
		try {
			this.setImg(ImageIO.read(new File("")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setDamage(30);
		this.setType("god");
		this.setProb(0.3);
		this.setRange(5);
	}
	
}
