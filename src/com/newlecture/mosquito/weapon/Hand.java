package com.newlecture.mosquito.weapon;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Hand extends Weapon {
	
	public Hand() {
		
		this.setDamage(5);
		this.setType("hand");
		this.setProb(1);
		this.setRange(1);
	}
	
	
}
