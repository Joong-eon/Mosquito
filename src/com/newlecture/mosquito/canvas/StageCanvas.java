package com.newlecture.mosquito.canvas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

import com.newlecture.mosquito.GameFrame;
import com.newlecture.mosquito.entity.Bug;
import com.newlecture.mosquito.entity.Butterfly;
import com.newlecture.mosquito.entity.Miss;
import com.newlecture.mosquito.entity.Mosquito;
import com.newlecture.mosquito.entity.Player;
import com.newlecture.mosquito.entity.Score;
import com.newlecture.mosquito.entity.Stage;
import com.newlecture.mosquito.entity.Timer;
import com.newlecture.mosquito.gui.Button;
import com.newlecture.mosquito.gui.GameClear;
import com.newlecture.mosquito.gui.GameOver;
import com.newlecture.mosquito.gui.PlayerHpBar;
import com.newlecture.mosquito.gui.WeaponButton;
import com.newlecture.mosquito.gui.listener.ButtonClickedAdapter;
import com.newlecture.mosquito.gui.listener.ButtonClickedListener;
import com.newlecture.mosquito.service.DataService;
import com.newlecture.mosquito.service.ImageLoader;
import com.newlecture.mosquito.service.StageService;



import com.newlecture.mosquito.weapon.StrawShoes;
import com.newlecture.mosquito.weapon.Weapon;

public class StageCanvas extends Canvas {

	private Image weapon1;
	private Image weapon2;
	private Image weapon22;
	private Image[] weaponImg;

	// ü
	public static Canvas instance;
	Thread th;//

	private Clip bgClip;
	private Clip effectClip;
	private AudioInputStream bgAis;
	private AudioInputStream effectAis;
	private Clip mosClip;
	private AudioInputStream mosAis;

	/// 여기서 보유무기 이미지 stageService에서 받아오고,

	private StageService stageService;
	private Timer timer;
	private Player player;
	private PlayerHpBar hpBar;

	private WeaponButton[] weapons;
	private ArrayList<Miss> missList;
	private Score score;
	private int stageStep;
	private int userLevel;
	private int userScore;

	// 스테이지별 배경
	private Image background;
	// 현재 스테이지를 표시하는 텍스트 이미지
	private Image stageText;
	private Image stageNumber;

	private int killCount = 0;

	private ButtonClickedListener clickListener;

	public StageCanvas() {//

		instance = this;

		stageStep = 1;

		mosSound("res/sound/mos.wav");

		stageService = new StageService();
		timer = stageService.getTimer();
		player = stageService.getP1();
		hpBar = stageService.getHpBar();
		missList = new ArrayList<Miss>();

		// 현재 스테이지에 맞는 백그라운드를 가져옴
		int stageIndex = stageService.getStageIndex();
		background = ImageLoader.stageBackgrounds[stageIndex - 1];
		stageText = ImageLoader.stageText;
		stageNumber = ImageLoader.stageNumber;

		ArrayList wpDir = player.getArrWpDir();
		ArrayList wp = player.getArrWp();
		weaponImg = new Image[wpDir.size()];

		for (int i = 0; i < wpDir.size(); i++) {
			try {
				weaponImg[i] = ImageIO.read(new File((String) wpDir.get(i)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 해당 레벨에 보유한 무기 갯수만큼 for문 돌려서 버튼 생성. 버튼 생성 위치도 변수화 해야함.
		weapons = new WeaponButton[wpDir.size()];

		for (int i = 0; i < wpDir.size(); i++) {
			weapons[i] = new WeaponButton((String) wp.get(i), weaponImg[i], weaponImg[i], 800 + 350 * i, 700, 135, 188);
		}
		// 이벤트 발생시 웨폰버튼에서 이름 가져오고
		// p1.current 정보변경
		score = new Score();
		userLevel = DataService.getInstance().getPlayerIntValue("player", "level");
		userScore = player.getUserTotalScore();
		stageService.getGameOver().addClickListener(new ButtonClickedAdapter() {
			// 이벤트 리스너 객체가 캔버스 생성할때는 되지만 스테이지 2로 넘어가면서 새로
			// 생성한 stageService에서는 객체 생성을 안하고 있음... 그래서 문제
			@Override
			public void onClicked(GameOver gameOver) {
				System.out.println("onClicked 실행");
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

		stageService.getGameClear().addClickListener(new ButtonClickedAdapter() {

			@Override
			public void onClicked(GameClear gameClear) {
				System.out.println("저장중");

				try {
					DataService.save(userLevel, player.getUserTotalScore());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("저장 완료");
				stageStep++;
				// stageService.changeStage(stageStep);
				ButtonClickedListener gameOverListener = stageService.getGameOver().getClickListener();
				ButtonClickedListener gameClearListener = stageService.getGameClear().getClickListener();
				int hp = hpBar.getHp();
				stageService = new StageService(stageStep);
				System.out.println("stageIndex : " + stageService.getStageIndex());
				stageService.setTimer(new Timer(stageService.getStageIndex()));
				timer = stageService.getTimer();
				player = stageService.getP1();
				hpBar = stageService.getHpBar();
				hpBar.setHp(hp);
				stageService.getGameOver().addClickListener(gameOverListener);
				stageService.getGameClear().addClickListener(gameClearListener);
				killCount = 0;

				System.out.println(timer.getLimitTime());
			}

		});

		// p1.getCurrentWp()
		// weaponBtn = new Button(, null, 700, 500, 72, 52);//

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				player.getCurrentWp().setX(e.getX());
				player.getCurrentWp().setY(e.getY());
			}
		});

		// addMouseMotionListener를 사용하면 기존에 override 해놓은 mouseDown 메소드가 안먹힘.
		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				player.getCurrentWp().setImgLoading(true);//무기 이미지 실행
				for (int i = 0; i < weapons.length; i++) {
					if (true == weapons[i].contains(e.getX(), e.getY())) {
						System.out.println("선택되었습니다");
						weapons[i].getClickListener().onPressed(weapons[i]);
						// weapons[i].getClickListener().onPressed(weapons[i]);
					}
				}
				
				// 현재 무기 소리
				// player.getCurrentWp().getBgm();
				// effectSound("res/sound/hand.wav");
				// 커서 이미지 변경
				int x = e.getX();
				int y = e.getY(); 

				if (((timer.getOneCount() == 0 && timer.getTenCount() == 0) || player.getHp() <= 0)
						&& killCount != stageService.getMosqMaxCount()) {
					// 게임에서 졌을 때, 지방을 누르게 되면 메뉴캔버스로 돌아감
					mosSoundOff();

					if (stageService.getGameOver().contains(x, y)) {
						stageService.setGameOver(false);
						stageService.getGameOver().getClickListener().onClicked(stageService.getGameOver());
					}

				} else if (killCount == stageService.getMosqMaxCount() && stageService.isGameOver() == false) {
					// 게임에서 이겼을 때, 풍악짤나오고, 누르면 다음 스테이지로 넘어감
					mosSoundOff();
					clearSound("res/sound/gameclear.wav");
					if (stageService.getGameClear().contains(x, y)) {
						stageService.setGameClear(false);
						stageService.getGameClear().getClickListener().onClicked(stageService.getGameClear());
					}

				} else if (true == player.getCurrentWp().isClickable()) {
					// 클릭 좌표를 중심으로 range안에 들어어오는 벌레를 잡음
					// 클릭 범위 설정 해야함.(타이머위치, 보유무기 위치)
					// 무기 영역과 비교해서 걸리는 모든 객체 갖고오기 => 범위공격 고려해서 범위에 걸린 모든 벌레 반환
					Mosquito selectedMosq = null;
					Butterfly selectedButt = null;

					int mosqSize = stageService.getMosqs().size();
					for (int i = 0; i < mosqSize; i++) {
						Mosquito mosq = stageService.getMosqs().get(i);
						boolean isWeaponRange = player.getCurrentWp().isAttackRange(mosq);
						if (true == isWeaponRange) {
							selectedMosq = mosq;
						}
					}

					int buttSize = stageService.getButts().size();
					for (int i = 0; i < buttSize; i++) {
						Butterfly butt = stageService.getButts().get(i);
						boolean isWeaponRange = player.getCurrentWp().isAttackRange(butt);
						if (true == isWeaponRange) {
							selectedButt = butt;
						}
					}

					boolean isMiss = false;

					if (selectedMosq != null) { // null이 아니면 찾은거임
						System.out.println("모기 클릭 성공");
						isMiss = player.attack(selectedMosq);

						// System.out.println("공격");
					}

					if (selectedButt != null) {
						isMiss = player.attack(selectedButt);
						System.out.println("아얏!");

					}

					if (isMiss == true) {// 빗나감
						// miss뜨는 그림효과
						missList.add(new Miss(x, y));
						effectSound("res/sound/miss.wav");
						System.out.println("빗나감");

					} else {// 빗나간게 아니라면
						if (selectedMosq != null) {
							if (selectedMosq.getHp() <= 0 && selectedMosq.getCurrentDir() != 2) {
								killCount++;
								String stageName = "stage" + stageService.getStageIndex();

								int killScore = DataService.getInstance().getGameIntValue(stageName, "killScore");
								int nowScore = score.getScore();
								score.setScore(nowScore += killScore);
								player.setUserTotalScore(player.getUserTotalScore() + killScore);
								if (player.getUserTotalScore() % 1000 == 0 && player.getUserTotalScore() / 100 != 0)
									System.out.println("레벨 업! 현재 레벨 : " + (++userLevel));
								selectedMosq.setMovIndex(4);
								selectedMosq.setCurrentDir(2);

								System.out.println(killCount);
							}

						} else if (selectedButt != null) {

							if (selectedButt.getHp() <= 0) {
								System.out.println("나비 사망");
								System.out.println("10초 감소");
								selectedButt.setCurrentDir(2);
								selectedButt.setMovIndex(4);
								timer.setTenCount(timer.getTenCount() - 1);
							}
							System.out.println("공격");
						}
					}
				}

				// super.mouseClicked(e);
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				for (int i = 0; i < weapons.length; i++) {
					if (true == weapons[i].contains(e.getX(), e.getY())) {
						weapons[i].getClickListener().onReleased(weapons[i]);

						for (int j = 0; j < player.getWeapons().length; j++) {
							if (player.getWeapons()[j].getType().equals(weapons[i].getName())) {
								if (weapons[i].getName().equals("flyswatter"))
									player.setCurrentWp(player.getWeapons()[j]);
								else if (weapons[i].getName().equals("strawShoes"))
									player.setCurrentWp(player.getWeapons()[j]);
							}
						}
						player.getCurrentWp().setX(e.getX());
						player.getCurrentWp().setY(e.getY());
					}
				}
			}
		});

		// 버튼 배열에 있는 버튼들에게 이벤트를 등록해줌
		for (int i = 0; i < weapons.length; i++) {
			weapons[i].addClickListener(new ButtonClickedAdapter() {
				@Override
				public void onClicked(Button target) {

				}
			});
		}

	}

	// 모기 사운드
	private void mosSound(String file) {

		try {
			mosAis = AudioSystem.getAudioInputStream(new File(file));
			mosClip = AudioSystem.getClip();

			mosClip.open(mosAis);
			mosClip.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mosSoundOff() {
		mosClip.stop();
	}

	private void effectSound(String file) {

		try {
			effectAis = AudioSystem.getAudioInputStream(new File(file));
			effectClip = AudioSystem.getClip();

			effectClip.open(effectAis);
			effectClip.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clearSound(String file) {

		try {
			bgAis = AudioSystem.getAudioInputStream(new File(file));
			bgClip = AudioSystem.getClip();

			bgClip.open(bgAis);
			bgClip.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paint(Graphics g) {

		Image buf = this.createImage(this.getWidth(), this.getHeight());
		Graphics bg = buf.getGraphics();
		// 배경 그려주세요
		bg.drawImage(stageService.getBackground(), 0, 0, null);

		// 스테이지 텍스트 전시
		{
			bg.drawImage(stageText, 30, 30, null);
			int index = stageService.getStageIndex() - 1;
			int sX1 = 70 * index;
			int sY1 = 70 * (index / 5);
			int sX2 = sX1 + 70;
			int sY2 = sY1 + 70;
			bg.drawImage(stageNumber, 20 + stageText.getWidth(null), 30, 90 + stageText.getWidth(null), 100, sX1, sY1,
					sX2, sY2, null);
		}

		// 게임 실패시...
		if (((timer.getOneCount() == 0 && timer.getTenCount() == 0) || player.getHp() <= 0)
				&& stageService.isGameClear() == false) {
			// 지방
			stageService.setGameOver(true);
			stageService.getGameOver().paint(bg);
			// 토탈점수 그려주세요

		} else if (killCount == stageService.getMosqMaxCount() && stageService.isGameOver() == false) {

			stageService.setGameClear(true);
			stageService.getGameClear().paint(bg);
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

			if (missList != null) {
				int missSize = missList.size();
				for (int i = 0; i < missSize; i++) {
					missList.get(i).paint(bg);
				}
			}

			player.getCurrentWp().paint(bg);

			hpBar.paint(bg);
		}

		g.drawImage(buf, 0, 0, this);
	}

	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		paint(g);
	}

	public void start() {//

		Runnable sub = new Runnable() {

			@Override
			public void run() {

				while (true) {
					timer.update();

					int mosqSize = stageService.getMosqs().size();
					for (int i = 0; i < mosqSize; i++) {
						stageService.getMosqs().get(i).update();
					}

					int buttSize = stageService.getButts().size();
					for (int i = 0; i < buttSize; i++) {
						stageService.getButts().get(i).update();
					}

					if (missList != null) {

						for (int i = 0; i < missList.size(); i++) {

							missList.get(i).update();

						}
						for (int i = 0; i < missList.size(); i++) {
							if (missList.get(i).getDelTime() < 0) {
								missList.remove(i);
							}
						}
					}

					player.getCurrentWp().update();
					repaint();

					try {
						Thread.sleep(17);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					stageService.update();
				}
			}
		};

		th = new Thread(sub);
		th.start();

	}
}
