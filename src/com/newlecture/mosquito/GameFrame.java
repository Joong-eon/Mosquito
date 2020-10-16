package com.newlecture.mosquito;

import java.awt.Canvas;
import java.awt.Frame;
import com.newlecture.mosquito.canvas.MenuCanvas;
import com.newlecture.mosquito.canvas.StageCanvas;

public class GameFrame extends Frame {
	
	private static GameFrame instance;
	
	public static final int STAGE_MENU = 1001;
	public static final int FREE_MENU = 1002;
	public static final int EXIT_MENU = 1003;
	
	public static int canvasWidth = 800;
	public static int canvasHeight = 800;

	public GameFrame() {
		instance = this;
		MenuCanvas menuCanvas = new MenuCanvas();

		add(menuCanvas);
		menuCanvas.start();
		
		this.setSize(canvasWidth, canvasHeight);
		this.setVisible(true);

		this.add(menuCanvas);

	}

	// 메뉴가 바뀌었을때 호출됨/
	public void switchCanvas(Canvas oldCanvas, Class newCanvas) throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		remove(oldCanvas);
		Canvas canvas = (Canvas)newCanvas.newInstance();
		add(canvas);
		if(canvas instanceof StageCanvas) {
			StageCanvas stageCanvas = (StageCanvas) canvas;
			stageCanvas.start();
		} else if(canvas instanceof MenuCanvas) {			
			MenuCanvas menuCanvas = (MenuCanvas) canvas;
			menuCanvas.start();
		}
		revalidate();
	}

	public static GameFrame getInstance() {
		// TODO Auto-generated method stub
		return instance;
	}
}
