package com.newlecture.mosquito;

import java.awt.Frame;

import com.newlecture.mosquito.canvas.StageCanvas;

public class GameFrame extends Frame {
	
	public GameFrame() {
		
		StageCanvas stageCanvas = new StageCanvas();
		stageCanvas.start();
		
		this.setSize(500,500);
		this.setVisible(true);
			
		this.add(stageCanvas);
	}
}
