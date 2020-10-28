package com.newlecture.mosquito;

import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import com.newlecture.mosquito.entity.Stage;
import com.newlecture.mosquito.gui.PlayerHpBar;
import com.newlecture.mosquito.service.DataService;

public class Program {

	public static void main(String[] args) {
		// 데이터를 관리하는 서비스가 먼저 만들어져야함   => 캔버스를 만들거나 이미지를 로드 할 때 데이터가 필요한 경우 때문
		DataService ds = new DataService();
		Frame frame = new GameFrame();
		
		/*마우스 커서 숨기는 코드
		Toolkit tk = Toolkit.getDefaultToolkit();
		Cursor invisCursor = tk.createCustomCursor(tk.createImage(""),new Point(), null);
		frame.setCursor(invisCursor);*/
		
		// 아래 코드는 DataService의 예시 입니다. 나중에 지울게요.
		// 초기 플레이어 레벨(등급)을 가져오려고 하면 => 플레이어 레벨은 정수형(숫자)이므로 getIntValue()를 사용합니다 
		int playerLevel = DataService.getInstance().getGameIntValue("default", "playerLevel");		// 오타나면 안돼요,,,
		System.out.println(playerLevel);
		
		// 스테이지 정보를 가져오려고 하면 => 스테이지 정보는 Stage객체에 데이터를 담아 반환합니다. 메소드는 getStageValue()사용!
		Stage defalutStage = DataService.getInstance().getStageValue(1);
		// gameConfig.txt에 stage1에 대한 항목이 존재하면 유효한 값을 반환합니다.
		System.out.printf("스테이지%d에서 생성 가능한 최대 모기 수 : %d\n", 1, defalutStage.mosqMaxCount);
		


	}
}