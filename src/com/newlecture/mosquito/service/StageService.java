package com.newlecture.mosquito.service;

import java.awt.Canvas;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import com.newlecture.mosquito.entity.Butterfly;
import com.newlecture.mosquito.entity.Miss;
import com.newlecture.mosquito.entity.MosqAttackListener;
import com.newlecture.mosquito.entity.Mosquito;
import com.newlecture.mosquito.entity.Player;
import com.newlecture.mosquito.entity.Stage;
import com.newlecture.mosquito.entity.Timer;
import com.newlecture.mosquito.gui.GameClear;
import com.newlecture.mosquito.gui.GameOver;
import com.newlecture.mosquito.gui.PlayerHpBar;

public class StageService {
	private ArrayList<Mosquito> mosqs;
	private ArrayList<Butterfly> butts;		// 스테이지에 생성되는 모든 모기와 나비
	private Stage stage;
	private int stageIndex;
	private Timer timer;
	private Player p1;
	private int totalScore=0;
	
	private int currentMosqCount;		// 현재 생성 된 모기 수
	private int mosqDeltaTime;
	private int mosqMaxCount;
	private int mosqCreateCount;
	private int mosqCreateTime;
	
	private int currentButtCount;		// 현재 생성 된 나비 수
	private int buttDeltaTime;
	private int buttMaxCount;
	private int buttCreateCount;
	private int buttCreateTime;
	
			
	private GameOver gameOver;
	private GameClear gameClear;
	private Image gameOverBtn = ImageLoader.gameOverBtn;
	private Image gameClearBtn = ImageLoader.gameClearBtn;
	
	private PlayerHpBar hpBar;
	private boolean isGameClear;
	private boolean isGameOver;
	
	private Image background;
	
	public StageService() {
		this(3);
	}
	
	public StageService(int stageStep) {
		
		stageIndex = stageStep;
		timer = new Timer(this.getStageIndex());
		p1 = new Player();
		hpBar = new PlayerHpBar(p1.getHp());
		isGameClear = false;
		isGameOver = false;
	
		gameOver = new GameOver("gameOver",gameOverBtn, gameOverBtn, 642, 359, 216, 283);
		gameClear = new GameClear("gameClear",gameClearBtn, gameClearBtn, 450, 327, 599, 347);
		System.out.println("gameover, gameclear 객체 생성 완료");
		
		changeStage(stageStep);
	}

	public GameClear getGameClear() {
		return gameClear;
	}

	public void changeStage(int stageIndex) {
		this.stageIndex = stageIndex;		// 현재 스테이지 바꾸고
	
		if(mosqs == null) {
			mosqs = new ArrayList<Mosquito>();	
			butts = new ArrayList<Butterfly>();	
		} else {
			mosqs.clear();
			butts.clear();
		}

		
		// 새로운 스테이지 정보 가져오기
		stage = DataService.getInstance().getStageValue(stageIndex);

		//모기 & 나비 생성		
		currentMosqCount = 0;		// 현재 생성 된 모기 수
		mosqDeltaTime = 0;
		mosqMaxCount = stage.getMosqMaxCount();
		mosqCreateCount = stage.getMosqCreateCount();
		mosqCreateTime = stage.getMosqCreateTime() * 60;			// 60FPS라서 60을 곱함
		createMosquito();
	
		currentButtCount = 0;		// 현재 생성 된 나비 수
		buttDeltaTime = 0;
		buttMaxCount = stage.getButtMaxCount();
		buttCreateCount = stage.getButtCreateCount();
		buttCreateTime = stage.getButtCreateTime() * 60;			// 60FPS라서 60을 곱함
		createButterfly();
		
		background = ImageLoader.stageBackgrounds[stageIndex-1];
	}

	public void setScore() {
		int killScore = stage.getKillScore();
		totalScore += killScore;

		/*스테이지 완료 및 스테이지 전환 구현하기.
		if(totalScore >= currentStage.getWinScore()) {
			currentStage = Dat
		}*/
	}
	
	public void createMosquito() {
		
		if( (currentMosqCount+mosqCreateCount) <= mosqMaxCount) {
			mosqDeltaTime = 0;
			currentMosqCount += mosqCreateCount;
			System.out.println("모기 생성 시작");
			System.out.println("mosqCreateCount : "+mosqCreateCount);
			for (int i = 0 ;i < mosqCreateCount; i++) {
				// 모기
				Mosquito m = new Mosquito();
				m.setMosqAttackListener(new MosqAttackListener() {
					
					@Override
					public void attackListener(int damage) {
						// TODO Auto-generated method stub
						p1.setHp(p1.getHp()-damage);
						hpBar.setHp(p1.getHp());
					}
				});
				mosqs.add(m);
			}
		}
	}
	
	public void createButterfly() {
		if( (currentButtCount+buttCreateCount) < buttMaxCount) {
			buttDeltaTime = 0;

			currentButtCount += buttCreateCount;
			
			for (int i = 0; i < buttCreateCount; i++) {		// 나비
				butts.add(new Butterfly());
			}
		}
	}
	
	public void update() {//스레드에서 계속 호출
		//System.out.println("mosqDeltaTime : "+mosqDeltaTime);
		if(mosqDeltaTime >= mosqCreateTime) {
			createMosquito();
		} else {
			mosqDeltaTime++;
		}
		
		if(buttDeltaTime >= buttCreateTime) {
			createButterfly();
		} else {
			buttDeltaTime++;
		}
		
		if(mosqs.size() > 0) {
			/// 모기 죽을 때 처리		
			//Iterator<Mosquito> it = mosqs.iterator();
			
			for (int i = 0; i < mosqs.size(); i++) {		// 모기
				if(mosqs.get(i).getCurrentDir() == 2) {
					int deleteTimer = mosqs.get(i).getDeleteTimer();
					deleteTimer--;
					mosqs.get(i).setDeleteTimer(deleteTimer);
				}
				
				if(mosqs.get(i).getDeleteTimer() == 0) {
					//mosqs.remove(i);
					mosqs.get(i).setX(-1);
					mosqs.get(i).setY(-1);
					//stage.setMosqCreateCount(--mosqCreateCount);
				}
			}
		}
		
		
		if(butts.size() > 0) {
			
			for (int i = 0; i < butts.size(); i++) {		// 모기
				if(butts.get(i).getCurrentDir() == 2) {
					int deleteTimer = butts.get(i).getDeleteTimer();
					deleteTimer--;
					butts.get(i).setDeleteTimer(deleteTimer);
				}
				
				if(butts.get(i).getDeleteTimer() == 0) {
					butts.get(i).setX(-1);
					butts.get(i).setY(-1);
				}
			}
		}
	}
	
	

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public Player getP1() {
		return p1;
	}

	public void setP1(Player p1) {
		this.p1 = p1;
	}

	public GameOver getGameOver() {
		// TODO Auto-generated method stub
		return gameOver;
	}
	
	public ArrayList<Mosquito> getMosqs() {
		return mosqs;
	}

	public void setMosqs(ArrayList<Mosquito> mosqs) {
		this.mosqs = mosqs;
	}

	public ArrayList<Butterfly> getButts() {
		return butts;
	}

	public void setButts(ArrayList<Butterfly> butts) {
		this.butts = butts;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public int getStageIndex() {
		return stageIndex;
	}

	public void setStageIndex(int currentStageIndex) {
		this.stageIndex = currentStageIndex;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public PlayerHpBar getHpBar() {
		return hpBar;
	}

	public void setHpBar(PlayerHpBar hpBar) {
		this.hpBar = hpBar;
	}

	public boolean isGameClear() {
		return isGameClear;
	}

	public void setGameClear(boolean isGameClear) {
		this.isGameClear = isGameClear;
	}

	public int getMosqMaxCount() {
		return mosqMaxCount;
	}

	public void setMosqMaxCount(int mosqMaxCount) {
		this.mosqMaxCount = mosqMaxCount;
	}

	public int getButtMaxCount() {
		return buttMaxCount;
	}

	public void setButtMaxCount(int buttMaxCount) {
		this.buttMaxCount = buttMaxCount;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}

	public Image getBackground() {
		return background;
	}

	public void setBackground(Image background) {
		this.background = background;
	}
	
	
}
