package com.newlecture.mosquito.weapon;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.newlecture.mosquito.canvas.StageCanvas;

public class StrawShoes extends Weapon{

	public StrawShoes() {
		try {
			this.setImg(ImageIO.read(new File("")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.setDamage(2);
		this.setType("strawshoes");
		this.setProb(0.4);
		this.setRange(2);
	}

}
