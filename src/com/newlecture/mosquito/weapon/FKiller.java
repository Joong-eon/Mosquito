package com.newlecture.mosquito.weapon;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.newlecture.mosquito.canvas.StageCanvas;

public class FKiller {
	
	
		
		private String type;
		private int damage;
		private double prob;
		private Image img;
		private int isClicked=0;
		private double range = 0.8;
		
		private int w = 72;
		private int h = 52;
		private int x=0;
		private int y=0;
		
		private int moveTempo = 6;
		private int moveIndex = 1;
		
		
		public FKiller() {
			try {
				img = ImageIO.read(new File("res/hand2.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			damage = 10;
			type = "hand";
			prob = 0.3;
		}
		
		public void paint(Graphics g) {
			
			if(isClicked == 1) {
				if(moveTempo == 0) {
					moveIndex++;
					moveTempo = 6;
				}
				else
					moveTempo--;
			}
				
			
			int moveImg = moveIndex*w;
			
			g.drawImage(img, x, y, x+w, y+h, moveImg-w, 0, moveImg, h, StageCanvas.instance);
			
			if(moveIndex == 8) {
				moveIndex = 1;
				isClicked = 0;
			}
		}
		
		public void update() {
			
		}
		
		/*
		public void move(int x, int y) {//모기를 잡는 행위를 하는것
			System.out.println(x+"  ,"+ y);
			this.x = x;
			this.y = y;
		}*/
		
		public void cursor() {//마우스 위치에 따라 이동 이동
			
		}


	}



