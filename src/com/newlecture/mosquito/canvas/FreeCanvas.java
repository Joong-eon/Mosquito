package com.newlecture.mosquito.canvas;

import java.awt.Canvas;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;

import com.newlecture.mosquito.GameFrame;
import com.newlecture.mosquito.entity.Miss;
import com.newlecture.mosquito.entity.Player;
import com.newlecture.mosquito.entity.Score;
import com.newlecture.mosquito.entity.Timer;
import com.newlecture.mosquito.gui.GameClear;
import com.newlecture.mosquito.gui.GameOver;
import com.newlecture.mosquito.gui.PlayerHpBar;
import com.newlecture.mosquito.gui.WeaponButton;
import com.newlecture.mosquito.gui.listener.ButtonClickedAdapter;
import com.newlecture.mosquito.gui.listener.ButtonClickedListener;
import com.newlecture.mosquito.service.DataService;
import com.newlecture.mosquito.service.FreeService;

public class FreeCanvas extends Canvas{
private Image currentWeapon;
	


	public static Canvas instance;
	Thread th;
	
//	private Clip bgClip;
//	private Clip effectClip;
//	private AudioInputStream bgAis;
//	private AudioInputStream effectAis;
//	private boolean isEffect;
//	private boolean isBgm;
	
	private FreeService freeService;
	private Timer timer;
	private Player player;
	private PlayerHpBar hpBar;
	private ArrayList<Miss> missList;
	private Score score;
	private Image background;
	private int userScore;
	
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
		
		ArrayList wpDir = player.getArrWpDir();
		ArrayList wp = player.getArrWp();
//		weaponImg = new Image[wpDir.size()];
		try {
		currentWeapon =  ImageIO.read(new File("res/spear.jpg"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		weapon= new WeaponButton("hand2",currentWeapon,currentWeapon,800,700,135,188);
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
//		freeService.getGameClear().addClickListener(new MenuButtonClickedAdapter() {
//			public void onClicked(GameClear gameClear) {
//				System.out.println("저장중");
//				try {
//					DataService.save(level, totalScore);
//				}
//			}
//		
//		}
			}
			
			
	
			
	public void start() {
		
		
	}
		
}
