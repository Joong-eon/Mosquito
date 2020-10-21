package com.newlecture.mosquito.weapon;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.newlecture.mosquito.canvas.StageCanvas;

public class Sword extends Weapon {

	public Sword() {
		try {
			this.setImg(ImageIO.read(new File("res/flyswatter.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.setDamage(50);
		this.setType("sword");
		this.setProb(1.0);
		this.setRange(1);
	}
}
