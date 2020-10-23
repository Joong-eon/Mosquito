package com.newlecture.mosquito.gui.listener;

import com.newlecture.mosquito.GameFrame;
import com.newlecture.mosquito.canvas.MenuCanvas;
import com.newlecture.mosquito.canvas.StageCanvas;
import com.newlecture.mosquito.gui.Button;
import com.newlecture.mosquito.gui.GameClear;
import com.newlecture.mosquito.gui.GameOver;
import com.newlecture.mosquito.service.ImageLoader;

public class MenuButtonClickedAdapter implements ButtonClickedListener{

	// 버튼이 클릭 되면 Pressed - Released - Clicked 순서로 호출됨
	@Override
	public void onClicked(Button target) {
		
	}

	@Override
	public void onPressed(Button target) {
		// TODO Auto-generated method stub
		target.setCurrentImg(target.getImgPressed());
	}

	@Override
	public void onReleased(Button target) {
		// TODO Auto-generated method stub
		target.setCurrentImg(target.getImgNormal());
	}

	@Override
	public void onClicked(GameOver gameOver) {
		// TODO Auto-generated method stub
		
	}

	public void onClicked(GameClear gameClear) {
		// TODO Auto-generated method stub
		
	}

}
