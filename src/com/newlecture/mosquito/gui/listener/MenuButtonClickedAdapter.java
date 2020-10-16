package com.newlecture.mosquito.gui.listener;

import com.newlecture.mosquito.GameFrame;
import com.newlecture.mosquito.canvas.MenuCanvas;
import com.newlecture.mosquito.canvas.StageCanvas;
import com.newlecture.mosquito.gui.Button;
import com.newlecture.mosquito.service.ImageLoader;

public class MenuButtonClickedAdapter implements ButtonClickedListener{

	// 버튼이 클릭 되면 Pressed - Released - Clicked 순서로 호출됨
	@Override
	public void onClicked(Button target) {
		
	}

	@Override
	public void onPressed(Button target) {
		// TODO Auto-generated method stub
		target.setImg(ImageLoader.menuBtnPressed);
		System.out.println("btnPressed");
	}

	@Override
	public void onReleased(Button target) {
		// TODO Auto-generated method stub
		target.setImg(ImageLoader.menuBtnNormal);
		System.out.println("btnReleased");
	}

}
