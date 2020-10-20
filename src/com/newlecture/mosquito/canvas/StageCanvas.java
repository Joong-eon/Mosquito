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
import java.util.ArrayList;

import com.newlecture.mosquito.entity.Bug;
import com.newlecture.mosquito.entity.Butterfly;
import com.newlecture.mosquito.entity.Mosquito;
import com.newlecture.mosquito.entity.Player;
import com.newlecture.mosquito.entity.Score;
import com.newlecture.mosquito.entity.Stage;
import com.newlecture.mosquito.entity.Timer;
import com.newlecture.mosquito.service.DataService;
import com.newlecture.mosquito.service.ImageLoader;
import com.newlecture.mosquito.service.StageService;
import com.newlecture.mosquito.weapon.Weapon;

public class StageCanvas extends Canvas {
	// ��ü ����
	public static Canvas instance;
	Thread th;// ������

	private StageService stageService;
	private Timer timer;
	private Player p1;
	private Score score;

	private int count = 1;

	public StageCanvas() {// ������
		instance = this;
		
		stageService = new StageService();
		timer = new Timer();
		p1 = new Player();
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

				if (true == p1.getCurrentWp().isClickable()) {
					// 클릭 좌표를 중심으로 range안에 들어어오는 벌레를 잡음
					// 클릭 범위 설정 해야함.(타이머위치, 보유무기 위치)
					// 무기 영역과 비교해서 걸리는 모든 객체 갖고오기 => 범위공격 고려해서 범위에 걸린 모든 벌레 반환
					Bug selectedBug = null;
					int size = stageService.getBugs().size();
					for (int i = 0; i < size; i++) {
						Bug bug = stageService.getBugs().get(i);
						boolean isWeaponRange = p1.getCurrentWp().isAttackRange(bug);
						if (true == isWeaponRange) {
							selectedBug = bug;
						}
					}

					if (selectedBug != null) { // null이 아니면 찾은거임
						p1.attack(selectedBug);
							if(selectedBug.getHp()<=0) {
								String stageName = "stage" + stageService.getStageIndex() ;
								
								int killScore = DataService.getInstance().getGameIntValue(stageName, "killScore"); //현재 스테이지에 맞는 모기 점수 가져오기
								int nowScore = score.getScore();
								
								score.setScore(nowScore+=killScore);
							}
						
						System.out.println("공격");
					} else {
						// 선택된 bug가 없다 == miss남
					}

				}

				super.mouseClicked(e);
			}
		});

	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub

		// weapon.paint(g);

		// timer.paint(g);
		// super.paint(g);

		Image buf = this.createImage(this.getWidth(), this.getHeight());
		Graphics bg = buf.getGraphics();

		timer.paint(bg);

		int bugSize = stageService.getBugs().size();
		for (int i = 0; i<bugSize ; i++) {
			stageService.getBugs().get(i).paint(bg);
		}
		
		p1.getCurrentWp().paint(bg);
		
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
					
					int bugSize = stageService.getBugs().size();
					for (int i = 0; i<bugSize ; i++) {
						stageService.getBugs().get(i).update();
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
