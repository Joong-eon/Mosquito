package com.newlecture.mosquito.gui;

import java.awt.Graphics;
import java.awt.Image;

import com.newlecture.mosquito.canvas.MenuCanvas;
import com.newlecture.mosquito.gui.listener.GameOverClickedListener;

public class GameOver {

	// 화면상에서 버튼의 좌표 
	private int x;
	private int y;

	// 버튼의 크기
		private int width;
		private int height;
		
		//이미지
		private Image img;
		// 이미지의 크기
		private int imgWidth;
		private int imgHeight;
		
		// 버튼을 사용할 곳에서 해당 리스너의 메소드를 오버라이드 해야 "버튼이 클릭 되었을 떄" 처리가 가능함
		private GameOverClickedListener clickListener;
		
		public GameOver() {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			
			this.img = img;
			this.imgWidth = img.getWidth(null);
			this.imgHeight = img.getHeight(null);
			
			clickListener = null;
		}
		
		public void paint(Graphics g) {
			x = 200;
			y = 200;
			int w = this.getWidth();
			int h = this.getHeight();
			
		g.drawImage(img, x, y, x+width, y+height,
				0, 0, imgWidth, imgHeight, MenuCanvas.instance);
		}
//		
//		public boolean contains(int x, int y) {
//			boolean result = false;
//			
//			if( this.x <= x && x <= this.x+width 
//					&& this.y <= y && y <= this.y+height  ) {
//				result = true;
//			}
//			
//			return result;
//		}
//		
//		// 외부(ex:캔버스)에서 버튼이 클릭 됐을 때 호출
//		public void onGameOverClicked() {
//			if(null != clickListener) {
//				clickListener.onClicked(this);		// 버튼이 클릭 되면 리스너 함수가 실행된다.  => 캔버스 말고도 이 리스너에 등록된 함수들이 호출됨
//			}
//		}
		
		private int getY() {
			// TODO Auto-generated method stub
			return x;
		}

		private int getX() {
			// TODO Auto-generated method stub
			return y;
		}

		private int getHeight() {
			// TODO Auto-generated method stub
			return height;
		}

		private int getWidth() {
			// TODO Auto-generated method stub
			return width;
		}
		public GameOverClickedListener getClickListener() {
			return clickListener;
		}
		
		public void addClickListener(GameOverClickedListener clickListener) {
			this.clickListener = clickListener;
		}
}
