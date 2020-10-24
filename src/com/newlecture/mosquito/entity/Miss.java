package com.newlecture.mosquito.entity;

import java.awt.Graphics;
import java.awt.Image;

import com.newlecture.mosquito.canvas.StageCanvas;
import com.newlecture.mosquito.service.ImageLoader;

public class Miss {
	   private Image img;
	   private int x;
	   private int y;
	   private int width;
	   private int height;
	   private int tempo;
	   
	   public Miss(int x, int y) {
	      
	      setImg(ImageLoader.miss);
	      
	      this.x = x; 
	      this.y = y;
	      width = 65;
	      height = 35;
	            
	   }


	   public void paint(Graphics g) {
	      int x1 = x - width/2;
	      int y1 = y - width/2;
	      int x2 = x1+ width;
	      int y2 = y1 + height;
	      
	      
	      g.drawImage(img, x1, y1, x2, y2, 0, 0, 0+width, 0+height, StageCanvas.instance);
	   }
	   
	   public void update() {
	      tempo++;
	      
	      if(tempo%3 == 0) {
	         x++;
	         y++;
	      }   
	         
	   }
	   
	   public void setImg(Image img) {
	      this.img = img;
	   }
	   
	}