package com.newlecture.mosquito.canvas;

import java.awt.Canvas;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;

import com.newlecture.mosquito.GameFrame;
import com.newlecture.mosquito.entity.Butterfly;
import com.newlecture.mosquito.entity.Miss;
import com.newlecture.mosquito.entity.Mosquito;
import com.newlecture.mosquito.entity.Player;
import com.newlecture.mosquito.entity.Score;
import com.newlecture.mosquito.entity.Timer;
import com.newlecture.mosquito.gui.Button;
import com.newlecture.mosquito.gui.GameClear;
import com.newlecture.mosquito.gui.GameOver;
import com.newlecture.mosquito.gui.PlayerHpBar;
import com.newlecture.mosquito.gui.WeaponButton;
import com.newlecture.mosquito.gui.listener.ButtonClickedAdapter;
import com.newlecture.mosquito.gui.listener.ButtonClickedListener;
import com.newlecture.mosquito.service.DataService;
import com.newlecture.mosquito.service.FreeService;
import com.newlecture.mosquito.service.ImageLoader;

public class FreeCanvas extends Canvas{

	public static Canvas instance;
	Thread th;

	//	private Clip bgClip;
	//	private Clip effectClip;
	//	private AudioInputStream bgAis;
	//	private AudioInputStream effectAis;
	//	private boolean isEffect;
	//	private boolean isBgm;

	private FreeService freeService;
	private Image[] weaponImg;
	private WeaponButton[] weapons;
	private Timer timer;
	private Player player;
	private PlayerHpBar hpBar;
	private ArrayList<Miss> missList;
	private Score score;
	private Image background;
	private int userScore;
	private Random rand;
	private int userLevel;

	private WeaponButton weapon;


	private ButtonClickedListener clickListener;

	public FreeCanvas() {
		instance = this;
		//		isBgm = true;
		//		isEffect = true;

		freeService = new FreeService();
		timer = freeService.getTimer();
		player = freeService.getP1();
		hpBar = new PlayerHpBar(player.getHp());
		missList = new ArrayList<Miss>();

		background = ImageLoader.stageBackgrounds[rand.nextInt(3)+1];
		//왜 만든거임?
		ArrayList wpDir = player.getArrWpDir();
		ArrayList wp = player.getArrWp();
		weaponImg = new Image[wpDir.size()];

		for (int i = 0; i < wpDir.size(); i++){ 
			try {
				weaponImg[i] = ImageIO.read(new File((String) wpDir.get(i)));
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}

		// 해당 레벨에 보유한 무기 갯수만큼 for문 돌려서 버튼 생성. 버튼 생성 위치도 변수화 해야함.
		weapons = new WeaponButton[wpDir.size()];

		score = new Score();

		userScore = player.getUserTotalScore();
		freeService.getGameOver().addClickListener(new ButtonClickedAdapter() {

			public void onClicked(GameOver gameOver) {
				try {
					GameFrame.getInstance().switchCanvas(FreeCanvas.this, MenuCanvas.class);

				}catch(InstantiationException e) {
					e.printStackTrace();

				}catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}

		});

		freeService.getGameClear().addClickListener(new ButtonClickedAdapter() {
			//
			@Override
			public void onClicked(GameClear gameClear) {
				System.out.println("저장중");
				try {
					DataService.save(userLevel, player.getUserTotalScore());//레벨 말고, 점수기록
				}catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("저장 완료");
			}

		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				player.getCurrentWp().setX(e.getX());
				player.getCurrentWp().setY(e.getY());
			}
		});
//		addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				// 커서 이미지 변경
//
//				int x = e.getX();
//				int y = e.getY();
//				System.out.println(freeService.getMosqs().size());
//				if ((timer.getOneCount() == 0 && timer.getTenCount() == 0)
//						||(freeService.getMosqs().size() == 0)) {
//					// 게임에서이겼을 때, 지방을 누르게 되면 메뉴캔버스로 돌아감
//					if (freeService.getGameOver().contains(x, y)) {
//						freeService.getGameOver().getClickListener().onClicked(freeService.getGameOver());
//					}
//
//				} else if (player.getHp() <= 0) {
//					// 게임에서 졌을 때, 풍악짤나오고, 누르면 다음 스테이지로 넘어감
//					if (freeService.getGameClear().contains(x, y)) {
//						freeService.setGameClear(false);
//						freeService.getGameClear().getClickListener().onClicked(freeService.getGameClear());
//					}
//
//				} else if (true == player.getCurrentWp().isClickable()) {
//					// 클릭 좌표를 중심으로 range안에 들어어오는 벌레를 잡음
//					// 클릭 범위 설정 해야함.(타이머위치, 보유무기 위치)
//					// 무기 영역과 비교해서 걸리는 모든 객체 갖고오기 => 범위공격 고려해서 범위에 걸린 모든 벌레 반환
//					Mosquito selectedMosq = null;
//					Butterfly selectedButt = null;
//
//					int mosqSize = freeService.getMosqs().size();
//					for (int i = 0; i < mosqSize; i++) {
//						Mosquito mosq = freeService.getMosqs().get(i);
//						boolean isWeaponRange = player.getCurrentWp().isAttackRange(mosq);
//						if (true == isWeaponRange) {
//							selectedMosq = mosq;
//						}
//					}
//
//					int buttSize = freeService.getButts().size();
//					for (int i = 0; i < buttSize; i++) {
//						Butterfly butt = freeService.getButts().get(i);
//						boolean isWeaponRange = player.getCurrentWp().isAttackRange(butt);
//						if (true == isWeaponRange) {
//							selectedButt = butt;
//						}
//					}
//
//					boolean isMiss = false;
//
//					if (selectedMosq != null) { // null이 아니면 찾은거임
//						System.out.println("모기 클릭 성공");
//						isMiss = player.attack(selectedMosq);
//
//						// System.out.println("공격");
//					}
//
//					if (selectedButt != null) {
//						isMiss = player.attack(selectedButt);
//						System.out.println("아얏!");
//
//					}
//
//					if (isMiss == true) {// 빗나감
//						// miss뜨는 그림효과
//						missList.add(new Miss(x, y));
//						System.out.println("빗나감");
//
//					} else {// 빗나간게 아니라면
//						if (selectedMosq != null) {
//							if (selectedMosq.getHp() <= 0) {
//								String stageName = "stage" + freeService.getStageIndex();
//
//								int killScore = DataService.getInstance().getGameIntValue(stageName, "killScore");
//								int nowScore = score.getScore();
		
//								score.setScore(nowScore += killScore);
//								player.setUserTotalScore(player.getUserTotalScore() + killScore);
//								if (player.getUserTotalScore() % 1000 == 0 && player.getUserTotalScore() / 100 != 0)
//									System.out.println("레벨 업! 현재 레벨 : " + (++userLevel));
//								selectedMosq.setCurrentDir(2);
//								selectedMosq.setMovIndex(4);
//							}
//
//						} else if (selectedButt != null) {
//
//							if (selectedButt.getHp() <= 0) {
//								System.out.println("나비 사망");
//								System.out.println("10초 감소");
//								selectedButt.setCurrentDir(2);
//								selectedButt.setMovIndex(4);
//								timer.setTenCount(timer.getTenCount() - 1);
//							}
//							System.out.println("공격");
//						}
//					}
//
//
//					// super.mouseClicked(e);
//				}
//			}
//		}
	}



	
			public void start() {

			}
	}
		
	
