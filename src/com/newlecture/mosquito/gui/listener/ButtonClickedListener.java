package com.newlecture.mosquito.gui.listener;

import com.newlecture.mosquito.gui.Button;

public interface ButtonClickedListener {

	void onClicked(Button target);
	void onPressed(Button target);
	void onReleased(Button target);
	
}
