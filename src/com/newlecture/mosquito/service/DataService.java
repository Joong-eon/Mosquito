package com.newlecture.mosquito.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import com.newlecture.mosquito.entity.Butterfly;
import com.newlecture.mosquito.entity.Mosquito;
import com.newlecture.mosquito.entity.Stage;

// 데이터 입출력을 담당하는 서비스 객체
public class DataService {

	// [default] : 
	// [stageN] : N번째 스테이지의 기본 정보를 저장.
	private String gameFileName;
	private String userFileName;
	
	
	// map이란 -> Key(이름), Value(데이터)로 자료를 저장 할 수 있는 컬렉션의 일종
	// TreeMap<[defalut], TreeMap<playerLevel,1>> 
	private TreeMap<String, TreeMap<String, String>> allGameDatas;
	private TreeMap<String, TreeMap<String, String>> allUserDatas;
	private static DataService instance;
	

	public DataService() {
		super();
		instance = this;
		
		allGameDatas = new TreeMap<String, TreeMap<String, String>>();
		allUserDatas = new TreeMap<String, TreeMap<String, String>>();

		gameFileName = "data/gameConfig.txt";
		userFileName = "data/userConfig.txt";
		try {
			loadConfig(gameFileName);		// GameConfig.txt 파일 읽어옴
			loadConfig(userFileName);		// UserConfig.txt 파일을 읽어옴
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static DataService getInstance() {
		return instance;
	}
	

	// data 폴더에 존제하는 gameConfig 파일을 읽어옴
	private void loadConfig(String filePath) throws IOException {
		FileInputStream fis = new FileInputStream(filePath);
		Scanner scan = new Scanner(fis);
		
		TreeMap<String, TreeMap<String, String>> allDatas;
		
		if(true == filePath.equals(userFileName)) {
			allDatas = allUserDatas;
		} else {
			allDatas = allGameDatas;
		}
		
		String title = "";
		TreeMap<String, String> datas = null;

		while (scan.hasNextLine()) {
			String line = scan.nextLine();

			line.trim();
			String firstText = line.substring(0, 1);
			if (true == firstText.equals("[")) { // [로 시작하는 라인은 어떤 항목인지를 알려주는 텍스트
				// 새로운 항목을 넣을 건데 기존 항목 데이터가 있으면 전체 데이터에 넣어줌
				if (datas != null) {
					// 전체 데이터에 추가
					allGameDatas.put(title, datas);
					datas = null;
					title = "";
				}

				title = line.substring(1, line.length() - 1);
				System.out.println("change Title : " + title);

			} else if (false == title.equals("")) {
				if (datas == null) { // 세부 항목 읽어올거라 담을 공간 만들어놓음
					datas = new TreeMap<String, String>();
				}
				String[] contents = line.split("=");
				datas.put(contents[0], contents[1]);

			}
		}

		// 다음줄이 없거나, 타이틀이 바뀌면
		if (false == title.equals("") || null != datas) {
			allGameDatas.put(title, datas);
			datas = null;
			title = "";
		}

		// 출력 확인용
//		for (String key : allDatas.keySet()) {
//			System.out.println("[" + key + "]");
//			TreeMap<String, String> contents = allDatas.get(key);
//			
//			for (String key2 : contents.keySet()) {
//				String value = contents.get(key2);
//				System.out.printf("%s = %s\n", key2, value);
//			}
//			
//		}

		scan.close();
		fis.close();
	}
	
	public static void save() throws IOException {
		// 추후 개발 예정
	}
	
	
	public Stage getStageValue(int stageIndex) {
		Stage stage = new Stage();
		
		String key = "stage"+stageIndex;
		TreeMap<String, String> datas = allGameDatas.get(key);
		if(null != datas) {
			String mosqCreateCount = datas.get("mosqCreateCount");
			String mosqMaxCount = datas.get("mosqMaxCount");
			String mosqCreateTime = datas.get("mosqCreateTime");

			String buttMaxCount = datas.get("buttMaxCount");
			String buttCreateCount = datas.get("buttCreateCount");
			String buttCreateTime = datas.get("buttCreateTime");
			
			String killScore = datas.get("killScore");


			// 값 넣기
			stage.mosqCreateCount = Integer.parseInt(mosqCreateCount);
			stage.mosqMaxCount = Integer.parseInt(mosqMaxCount);
			stage.mosqCreateTime = Integer.parseInt(mosqCreateTime);
			
			// 값 넣기
			stage.buttMaxCount = Integer.parseInt(buttMaxCount);
			stage.buttCreateCount = Integer.parseInt(buttCreateCount);
			stage.buttCreateTime = Integer.parseInt(buttCreateTime);
			
			stage.killScore = Integer.parseInt(killScore);
		}
		
		return stage;
	}
	
	
	public int getGameIntValue(String key, String attribute) {
		String data = allGameDatas.get(key).get(attribute);
		int value = 0;
		if(false == data.equals("")) {
			value = Integer.parseInt(data);
		}
		return value;
	}
	
	public String getGameStringValue(String key, String attribute) {
		String result = allGameDatas.get(key).get(attribute);
		return result;
	}	
	
	
	public int getUserIntValue(String key, String attribute) {
		String data = allUserDatas.get(key).get(attribute);
		int value = 0;
		if(false == data.equals("")) {
			value = Integer.parseInt(data);
		}
		return value;
	}
	
	
	public String getUserStringValue(String key, String attribute) {
		String result = allUserDatas.get(key).get(attribute);
		return result;
	}	
	
	
	
}
