package com.newlecture.mosquito.canvas;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import com.newlecture.mosquito.gui.TextRow;
import com.newlecture.mosquito.service.ImageLoader;

public class RankCanvas extends Canvas {

	private Image background;
	private TextRow[] titles;
	private TextRow[] contents;
	
	private ArrayList<String[]> rankDatas;
	
//	300 400
	private int leftX;
	private int rightX;
	private int y;
	
	private int space;
	private int rowCount;
	
	public RankCanvas() {
		// TODO Auto-generated constructor stub
		background = ImageLoader.rankBackground;
		
		rankDatas = new ArrayList<>();
		rowCount = 10;
		
		Random ran = new Random();
		// 임의 데이터
		int index = 1;
		
		for(int i=0 ; i<rowCount ; i++) {
			String[] datas = new String[3];
			datas[0] = String.valueOf(index);
			datas[1] = "이름임"+index;
			datas[2] = String.valueOf(ran.nextInt(100) + 1);
			index++;
			rankDatas.add(datas);
		}
		
		
		titles = new TextRow[2];
		contents = new TextRow[rowCount];
		
		String[] titleTexts = new String[] {"순위", "이름","점수"};
		leftX = 300;
		rightX = 800;
		y = 400;
		
		space = 80;
		
		// 타이틀
		titles[0] = new TextRow(titleTexts, leftX, y);
		titles[0].setTitle(true);
		titles[1] = new TextRow(titleTexts, rightX, y);
		titles[1].setTitle(true);
		
		y = 400;		// content 시작 위치

		System.err.println(rankDatas.size());
		int contentY = y;
		for(int i=0 ; i<rankDatas.size() ; i++) {
			if(i == rowCount/2) {
				contentY = 400 + space;
			} else {
				contentY += space;
			}
			
			int x = 0;
			if((i+1)/6 == 0) {
				x = leftX;
			} else {
				x = rightX;
			}
			contents[i] = new TextRow(rankDatas.get(i), x, contentY, "궁서", 40, false);
		}
		
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		Image buf = this.createImage(this.getWidth(), getHeight());
		Graphics bg = buf.getGraphics();

		bg.drawImage(background, 0, 0, this); // 배경이미지
		
		for(int i=0 ; i<titles.length ; i++) {
			titles[i].paint(bg);
		}

		for(int i=0 ; i<contents.length ; i++) {
			contents[i].paint(bg);
		}
		
		g.drawImage(buf, 0, 0, this);
	}
}
