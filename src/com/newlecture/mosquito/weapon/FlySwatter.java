package com.newlecture.mosquito.weapon;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.newlecture.mosquito.canvas.StageCanvas;

public class FlySwatter extends Weapon{

	public FlySwatter() {
			try {
				this.setImg(ImageIO.read(new File("res/flyswatter.png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			this.setDamage(5);
			this.setType("flyswatter");
			this.setProb(0.7);
			this.setRange(2);
		}



}


