package com.newlecture.mosquito.canvas;

import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.newlecture.mosquito.GameFrame;
import com.newlecture.mosquito.entity.Bug;
import com.newlecture.mosquito.entity.Butterfly;
import com.newlecture.mosquito.entity.Mosquito;
import com.newlecture.mosquito.entity.Player;
import com.newlecture.mosquito.entity.Score;
import com.newlecture.mosquito.entity.Stage;
import com.newlecture.mosquito.entity.Timer;
import com.newlecture.mosquito.gui.Button;
import com.newlecture.mosquito.gui.GameOver;
import com.newlecture.mosquito.gui.WeaponButton;
import com.newlecture.mosquito.gui.listener.ButtonClickedListener;
import com.newlecture.mosquito.gui.listener.MenuButtonClickedAdapter;
import com.newlecture.mosquito.service.DataService;
import com.newlecture.mosquito.service.ImageLoader;
import com.newlecture.mosquito.service.StageService;
import com.newlecture.mosquito.weapon.Weapon;

public class StageCanvas extends Canvas {
	
	private Image weapon1;
	private Image weapon2;
	private Image weapon22;

	// ��ü ����
	public static Canvas instance;
	Thread th;// ������
	
    private Clip bgClip;
	private Clip effectClip;
	private AudioInputStream bgAis;
	private AudioInputStream effectAis;
	private boolean isEffect;
	private boolean isBgm;
	
	
	///여기서 보유무기 이미지 stageService에서 받아오고,

	private StageService stageService;
	private Timer timer;
	private Player p1;
	private WeaponButton[] weapons;
	private Score score;

	private int count = 1;
	
	private ButtonClickedListener clickListener;

	public StageCanvas() {// ������
		instance = this;
		
		isBgm = true;
		isEffect = true;
		
		mosSound("res/sound/mos.wav");
		
		

	
		
		stageService = new StageService();
		timer = new Timer();
		p1 = new Player();
		
		try {
			weapon1 = ImageIO.read(new File("res/spear.png"));//파일이름 
			weapon2 = ImageIO.read(new File("res/flyswatter.png"));
			weapon22 = ImageIO.read(new File("res/flyswatter1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		weapons = new WeaponButton[2];
		weapons[0] = new WeaponButton("spear",weapon1,weapon1, 800,700,135,188);
		weapons[1] = new WeaponButton("flyswatter",weapon2,weapon22, 1050,700,118,141);
		//이벤트 발생시 웨폰버튼에서 이름 가져오고
		//p1.current 정보변경
		
		stageService.getGameOver().addClickListener(new MenuButtonClickedAdapter() {
			
			@Override
			public void onClicked(GameOver gameOver) {
				// TODO Auto-generated method stub
				try {
					GameFrame.getInstance().switchCanvas(StageCanvas.this, MenuCanvas.class);
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
		//p1.getCurrentWp()
		//weaponBtn = new Button(, null, 700, 500, 72, 52);//
		score = new Score();

		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				p1.getCurrentWp().setX(e.getX());
				p1.getCurrentWp().setY(e.getY());
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		// addMouseMotionListener를 사용하면 기존에 override 해놓은 mouseDown 메소드가 안먹힘.
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 커서 이미지 변경
				
				int x = e.getX();
				int y = e.getY();

				if(timer.getOneCount() == 0 && timer.getTenCount() ==0) {
					//게임에서 졌을 때, 지방을 누르게 되면 메뉴캔버스로 돌아감
					if(stageService.getGameOver().contains(x, y)) {
						stageService.getGameOver().getClickListener().onClicked(stageService.getGameOver());
					}
					
				}else if (true == p1.getCurrentWp().isClickable()) {
					System.out.println("클릭됨");
					// 클릭 좌표를 중심으로 range안에 들어어오는 벌레를 잡음
					// 클릭 범위 설정 해야함.(타이머위치, 보유무기 위치)
					// 무기 영역과 비교해서 걸리는 모든 객체 갖고오기 => 범위공격 고려해서 범위에 걸린 모든 벌레 반환
					Mosquito selectedMosq = null;
					Butterfly selectedButt = null;

					int mosqSize = stageService.getMosqs().size();
					for (int i = 0; i < mosqSize; i++) {
						Mosquito mosq = stageService.getMosqs().get(i);
						boolean isWeaponRange = p1.getCurrentWp().isAttackRange(mosq);
						if (true == isWeaponRange) {
							selectedMosq = mosq;
						}
					}

					int buttSize = stageService.getButts().size();
					for (int i = 0; i < buttSize; i++) {
						Butterfly butt = stageService.getButts().get(i);
						boolean isWeaponRange = p1.getCurrentWp().isAttackRange(butt);
						if (true == isWeaponRange) {
							selectedButt = butt;
						}
					}

					boolean isMiss = false;      

					if (selectedMosq != null) { // null이 아니면 찾은거임
						System.out.println("모기 클릭 성공");
						isMiss = p1.attack(selectedMosq);
						
						
						//System.out.println("공격");
					} 

					if(selectedButt != null) {
						isMiss = p1.attack(selectedButt);
						System.out.println("아얏!");
						
					} 

					if(isMiss == true) {// 빗나감
						//miss뜨는 그림효과
						System.out.println("빗나감");
						
					}else//빗나간게 아니라면
						if(selectedMosq.getHp() <= 0) {
							System.out.println("모기 죽음");//현재 모기 죽으면 모기 사라짐.. 왜그럴까
							selectedMosq.setCurrentDir(2);
							//selectedMosq.move(e.getX(), e.getY());
							selectedMosq.setMovIndex(4);
							System.out.println(selectedMosq.getMovIndex());
							//모기 죽는 사운드
							 effect("res/sound/mosdie.wav");
							
							
						}

				}
				
				//super.mouseClicked(e);
			}

			
			@Override
			public void mousePressed(MouseEvent e) {
				for (int i = 0; i < weapons.length; i++) {
					if (true == weapons[i].contains(e.getX(), e.getY())) {
						System.out.println("선택되었습니다");
						weapons[i].getClickListener().onPressed(weapons[i]);
						//weapons[i].getClickListener().onPressed(weapons[i]);
					}
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				for (int i = 0; i < weapons.length; i++) {
					if (true == weapons[i].contains(e.getX(), e.getY())) {
						weapons[i].getClickListener().onReleased(weapons[i]);
						
						for(int j = 0;j<p1.getWeapons().length;j++) {
							//System.out.println(p1.getWeapons()[j]);
							if(p1.getWeapons()[j].getType().equals(weapons[i].getName())) {
								if(weapons[i].getName().equals("flyswatter")) {
									Weapon temp = p1.getWeapons()[j];
									temp.setImg(weapon2);
									p1.setCurrentWp(temp);
								}
								else if(weapons[i].getName().equals("spear")) {
									Weapon temp = p1.getWeapons()[j];
									temp.setImg(weapon1);
									p1.setCurrentWp(temp);
								}
							}							
						}
						p1.getCurrentWp().setX(e.getX());
						p1.getCurrentWp().setY(e.getY());
					}
				}
			}			
		});
		
		// 버튼 배열에 있는 버튼들에게 이벤트를 등록해줌
		for (int i = 0; i < weapons.length; i++) {
			weapons[i].addClickListener(new MenuButtonClickedAdapter() {
				@Override
				public void onClicked(Button target) {
					
				}
			});
		}

	}




//모기 사운드
	private void mosSound(String file) {
		if (isBgm) {
			try {
				bgAis = AudioSystem.getAudioInputStream(new File(file));
				bgClip = AudioSystem.getClip();

				bgClip.open(bgAis);
				bgClip.start();
		

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}		
	
	public void effect(String file) {
		if (isEffect) {
			try {
				effectAis = AudioSystem.getAudioInputStream(new File(file));
				effectClip = AudioSystem.getClip();
				effectClip.open(effectAis);
				effectClip.start();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	 public void setEff(boolean b) {
			isEffect = b;
		}

		public void setBgm(boolean b) {
			isBgm = b;
		}

		public void bgmOff() {
			bgClip.stop();
		}
		
		public void effectStart() {
			if(isEffect == true)
				effectClip.loop(1);
		}





	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub

		// weapon.paint(g);

		// timer.paint(g);
		// super.paint(g);

		Image buf = this.createImage(this.getWidth(), this.getHeight());
		Graphics bg = buf.getGraphics();
		//배경 그려주세요
		
		//게임 실패시...
		if(timer.getOneCount() == 0 && timer.getTenCount() ==0) {
			//지방
			stageService.getGameOver().paint(bg);
			//토탈점수 그려주세요
			
		} else {
			timer.paint(bg);
			score.paint(bg);

			int mosqSize = stageService.getMosqs().size();
			for (int i = 0; i < mosqSize; i++) {
				stageService.getMosqs().get(i).paint(bg);
			}

			int buttSize = stageService.getButts().size();
			for (int i = 0; i < buttSize; i++) {
				stageService.getButts().get(i).paint(bg);
			}
			weapons[0].paint(bg);
			weapons[1].paint(bg);

			p1.getCurrentWp().paint(bg);
		}
		
		g.drawImage(buf, 0, 0, this);//

	}

	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		paint(g);
	}

	public void start() {// ������ ����

		Runnable sub = new Runnable() {

			@Override
			public void run() {

				while (true) {
					// weapon.update();
					timer.update();
					
					stageService.update();
					int mosqSize =  stageService.getMosqs().size();
					for (int i = 0; i<mosqSize ; i++) {
						stageService.getMosqs().get(i).update();
					}
					int buttSize = stageService.getButts().size();
					for(int i = 0; i<buttSize ; i++) {
						stageService.getButts().get(i).update();
					}

					p1.getCurrentWp().update();
					
					repaint();

					try {
						Thread.sleep(17);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};

		th = new Thread(sub);
		th.start();
	

	}
	
	
}
