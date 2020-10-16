package com.newlecture.mosquito.canvas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.newlecture.mosquito.GameFrame;
import com.newlecture.mosquito.gui.Button;
import com.newlecture.mosquito.gui.listener.ButtonClickedListener;
import com.newlecture.mosquito.gui.listener.MenuButtonClickedAdapter;
import com.newlecture.mosquito.service.ImageLoader;

public class MenuCanvas extends Canvas {
	public static Canvas instance;

	private Thread th;					// 메뉴 화면이 사라지면 메뉴화면용 스레드도 종료할것이기 때문에 스레드를 멤버변수로 갖고 있을것
	private Button[] buttons;
	private Button stageButton;
	private Button freeButton;
	private Button exitButton;

	private Image menuBtnNormal;
	private Image menuBtnHover;
	private Image menuBtnPressed;
	private Image menuBackground;
	

	public MenuCanvas() {
		// TODO Auto-generated constructor stub
		instance = this;
		
		int btnWidth = 300;
		int btnHeight = 170;
		double sx = 100;
		double sy = 100;
		double space = 10;

		setBackground(Color.GREEN);

		// 메뉴 버튼의 이미지를 받아옴
		menuBtnNormal = ImageLoader.menuBtnNormal;
		menuBtnHover = ImageLoader.menuBtnHover;
		menuBtnPressed = ImageLoader.menuBtnPressed;
		menuBackground = ImageLoader.menuBackground;
		
		// 메뉴 버튼 생성
		stageButton = new Button("stage", menuBtnNormal, sx, sy, btnWidth, btnHeight, 0, 0);
		freeButton = new Button("free", menuBtnNormal, sx, sy + btnHeight, btnWidth, btnHeight, 0, 0);
		exitButton = new Button("exit", menuBtnNormal, sx, sy + btnHeight * 2, btnWidth, btnHeight, 0, 0);

		// 버튼 배열에 넣음
		buttons = new Button[3];
		buttons[0] = stageButton;
		buttons[1] = freeButton;
		buttons[2] = exitButton;

		// 캔버스에서 마우스 이벤트 발생 처리
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				for (int i = 0; i < buttons.length; i++) {
					if (true == buttons[i].contains(e.getX(), e.getY())) {
						buttons[i].getClickListener().onReleased(buttons[i]);
					}
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("mousePressed");
				for (int i = 0; i < buttons.length; i++) {
					if (true == buttons[i].contains(e.getX(), e.getY())) {
						buttons[i].getClickListener().onPressed(buttons[i]);
					}
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

				for (int i = 0; i < buttons.length; i++) {
					if (true == buttons[i].contains(e.getX(), e.getY())) {
						// 이벤트에게 클릭된 버튼의 정보를 넘겨줌. 오버라이드한 함수에서 어떤 버튼이 클릭되었는지 알 수 있도록
						buttons[i].getClickListener().onClicked(buttons[i]);
					}
				}
			}
		});

		// 버튼 배열에 있는 버튼들에게 이벤트를 등록해줌
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].addClickListener(new MenuButtonClickedAdapter() {
				@Override
				public void onClicked(Button target) {
					
					switch(target.getName()) {
					case "stage":
						try {
							GameFrame.getInstance().switchCanvas(MenuCanvas.this, StageCanvas.class);
						} catch (InstantiationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case "free" : 
						break;
					case "exit":
						int result = JOptionPane.showConfirmDialog(MenuCanvas.this, "게임을 종료하시겠습니까?", "게임종료", JOptionPane.OK_CANCEL_OPTION);
						if(0 == result) {		//사용자가 '예'를 눌렀으면 
							System.out.println(result);
							System.exit(0);
						}
						break;
					}
					
				}
			});
		}

	}

	@Override
	public void paint(Graphics g) {
		Image buf = this.createImage(this.getWidth(), getHeight());
		Graphics bg = buf.getGraphics();

		bg.drawImage(menuBackground, 0, 0, this);		//배경이미지
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].paint(bg);
		}
		
		g.drawImage(buf, 0, 0, this);
	}
	
	

	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		//super.update(g);
		paint(g);
	}
	
	public void start() {

		Runnable sub = new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					
					repaint();
					try {
						Thread.sleep(17);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
					}
				}

			}
		};

		th = new Thread(sub);
		th.start();
	}
	

}
