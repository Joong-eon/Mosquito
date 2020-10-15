package com.newlecture.mosquito;

import java.awt.Frame;

import com.newlecture.mosquito.canvas.StageCanvas;

public class GameFrame extends Frame {

	public static int canvasWidth = 500;
	public static int canvasHeight = 500;

	public GameFrame() {

		StageCanvas stageCanvas = new StageCanvas();

		this.setSize(canvasWidth, canvasHeight);
		this.setVisible(true);

		this.add(stageCanvas);

	}
}
