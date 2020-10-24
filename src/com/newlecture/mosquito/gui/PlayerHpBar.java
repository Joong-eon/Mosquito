package com.newlecture.mosquito.gui;

import java.awt.Color;
import java.awt.Graphics;

public class PlayerHpBar {
	private int hp;
	
	public PlayerHpBar(int hp) {
		this.hp = hp;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(800, 100, 300, 50);
		g.setColor(Color.RED);
		g.fillRect(800, 100, hp*3, 50);
	}
}
