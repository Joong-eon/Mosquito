package com.newlecture.mosquito;

import java.awt.Frame;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import com.newlecture.mosquito.entity.Stage;
import com.newlecture.mosquito.service.DataService;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Frame frame = new GameFrame();
		DataService ds = new DataService();
		
		
		// 아래 코드는 DataService의 예시 입니다. 나중에 지울게요.
		// 초기 플레이어 레벨(등급)을 가져오려고 하면 => 플레이어 레벨은 정수형(숫자)이므로 getIntValue()를 사용합니다 
		int playerLevel = DataService.getInstance().getIntValue("default", "playerLevel");		// 오타나면 안돼요,,,
		System.out.println(playerLevel);
		
		// 스테이지 정보를 가져오려고 하면 => 스테이지 정보는 Stage객체에 데이터를 담아 반환합니다. 메소드는 getStageValue()사용!
		Stage defalutStage = DataService.getInstance().getStageValue(1);
		// gameConfig.txt에 stage1에 대한 항목이 존재하면 유효한 값을 반환합니다.
		System.out.printf("스테이지%d에서 생성 가능한 최대 모기 수 : %d\n", 1, defalutStage.mosqMaxCount);
		


	}
}
