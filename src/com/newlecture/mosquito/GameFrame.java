package com.newlecture.mosquito;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import com.newlecture.mosquito.canvas.FreeCanvas;
import com.newlecture.mosquito.canvas.GameCanvas;
import com.newlecture.mosquito.canvas.MenuCanvas;
import com.newlecture.mosquito.canvas.RankCanvas;
import com.newlecture.mosquito.canvas.StageCanvas;
import com.newlecture.mosquito.gui.PlayerHpBar;

public class GameFrame extends Frame {
	
	private static GameFrame instance;
	
	public static final int STAGE_MENU = 1001;
	public static final int FREE_MENU = 1002;
	public static final int EXIT_MENU = 1003;
	
	public static int canvasWidth = 1500;
	public static int canvasHeight = 1000;
	public String userName;

	public GameFrame() {
		instance = this;
		MenuCanvas menuCanvas = new MenuCanvas();
		
		//add(menuCanvas);
		menuCanvas.start();
		
		this.setSize(canvasWidth, canvasHeight);
		this.setVisible(true);

		this.add(menuCanvas);
		
		// close 코드 
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

	}

	// 메뉴가 바뀌었을때 호출됨/
	public void switchCanvas(GameCanvas oldCanvas, Class newCanvas, boolean checkId) throws InstantiationException, IllegalAccessException {
		boolean change = true;
		if(oldCanvas instanceof MenuCanvas && checkId) {
			
			String id="";
			while(true) {
				
				id = JOptionPane.showInputDialog(null, "사용자 아이디를 입럭하시오.", "USER ID", TEXT_CURSOR);
				if(id == null) {
					System.out.println(id);
					change = false;
					id="";
				}
				
				if((id.length() > 8 || id.length() < 2) && id.length() !=0)
					JOptionPane.showMessageDialog(null, "2자리 이상, 8자리 이하의 이름을 입력하시오.", "Warning",
					        JOptionPane.WARNING_MESSAGE);
				else
					break;
			}

			userName = id;
		}
		
		if(change) {
			GameCanvas canvas = (GameCanvas)newCanvas.newInstance();
			add(canvas);

			canvas.start();
			
			revalidate();//재활성화(다시 유효하게 만든다)
			remove(oldCanvas);
		}
		
	}
	
	//instance 변수 / static 변수
	public static GameFrame getInstance() {
		// TODO Auto-generated method stub
		return instance;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
