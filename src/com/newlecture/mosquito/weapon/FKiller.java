package com.newlecture.mosquito.weapon;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.newlecture.mosquito.canvas.StageCanvas;

public class FKiller extends Weapon{	
		
		public FKiller() {
			try {
				this.setImg(ImageIO.read(new File("")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.setDamage(5);
			this.setType("fkiller");
			this.setProb(100);
			this.setRange(4);
		}
		
}



