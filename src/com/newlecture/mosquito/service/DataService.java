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

// ������ ������� ����ϴ� ���� ��ü
public class DataService {

	// [default] : 
	// [stageN] : N��° ���������� �⺻ ������ ����.
	
	private String gameFileName;
	private String userFileName;
	
	
	// map�̶� -> Key(�̸�), Value(������)�� �ڷḦ ���� �� �� �ִ� �÷����� ����
	// TreeMap<[defalut], TreeMap<playerLevel,1>> 
	private TreeMap<String, TreeMap<String, String>> allDatas;
	private static DataService instance;
	
	public DataService() {
		super();
		instance = this;
		
		allDatas = new TreeMap<String, TreeMap<String, String>>();
		gameFileName = "data/gameConfig.txt";
		userFileName = "data/userConfig.txt";
		try {
			loadGameConfig();		// GameConfig.txt ���� �о��
			loadUserConfig();		// UserConfig.txt ������ �о��
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static DataService getInstance() {
		return instance;
	}
	

	// data ������ �����ϴ� gameConfig ������ �о��
	private void loadGameConfig() throws IOException {
		FileInputStream fis = new FileInputStream("data/gameConfig.txt");
		Scanner scan = new Scanner(fis);

		String title = "";
		TreeMap<String, String> datas = null;

		while (scan.hasNextLine()) {
			String line = scan.nextLine();

			line.trim();
			String firstText = line.substring(0, 1);
			if (true == firstText.equals("[")) { // [�� �����ϴ� ������ � �׸������� �˷��ִ� �ؽ�Ʈ
				// ���ο� �׸��� ���� �ǵ� ���� �׸� �����Ͱ� ������ ��ü �����Ϳ� �־���
				if (datas != null) {
					// ��ü �����Ϳ� �߰�
					allDatas.put(title, datas);
					datas = null;
					title = "";
				}

				title = line.substring(1, line.length() - 1);
				System.out.println("change Title : " + title);

			} else if (false == title.equals("")) {
				if (datas == null) { // ���� �׸� �о�ðŶ� ���� ���� ��������
					datas = new TreeMap<String, String>();
				}
				String[] contents = line.split("=");
				datas.put(contents[0], contents[1]);

			}
		}

		// �������� ���ų�, Ÿ��Ʋ�� �ٲ��
		if (false == title.equals("") || null != datas) {
			allDatas.put(title, datas);
			datas = null;
			title = "";
		}

		// ��� Ȯ�ο�
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
	
	private void loadUserConfig() {
		// TODO Auto-generated method stub
		
		// UserConfig.txt ������ �о��
		// GameConfig ���ϰ� �и��� ���� : UserConfig ���� ���� ���� ������ ���� ����� ���� ����Ǵ� ���� ���� �Ͱ��Ƽ� ���� �и���
		// (GameConfig�� ���� �ϸ� ���� �ѹ��� ���� ���ص� �Ǵ°� �����ؾ��ϴ� ���ʿ��� ���� ����)
	}
	
	public static void save() throws IOException {
		// ���� ���� ����
	}
	
	
	public Stage getStageValue(int stageIndex) {
		Stage stage = new Stage();
		
		String key = "stage"+stageIndex;
		TreeMap<String, String> datas = allDatas.get(key);
		if(null != datas) {
			String mosqCreateCount = datas.get("mosqCreateCount");
			String mosqMaxCount = datas.get("mosqMaxCount");
			String mosqCreateTime = datas.get("mosqCreateTime");

			String buttMaxCount = datas.get("buttMaxCount");
			String buttCreateCount = datas.get("buttCreateCount");
			String buttCreateTime = datas.get("buttCreateTime");

			// �� �ֱ�
			stage.mosqCreateCount = Integer.parseInt(mosqCreateCount);
			stage.mosqMaxCount = Integer.parseInt(mosqMaxCount);
			stage.mosqCreateTime = Integer.parseInt(mosqCreateTime);
			
			// �� �ֱ�
			stage.buttMaxCount = Integer.parseInt(buttMaxCount);
			stage.buttCreateCount = Integer.parseInt(buttCreateCount);
			stage.buttCreateTime = Integer.parseInt(buttCreateTime);
		}
		
		return stage;
	}
	
	
	public int getIntValue(String key, String attribute) {
		String data =  allDatas.get(key).get(attribute);
		int value = 0;
		if(false == data.equals("")) {
			value = Integer.parseInt(data);
		}
		return value;
	}
	
	
	public String getStringValue(String key, String attribute) {
		String result =  allDatas.get(key).get(attribute);
		return result;
	}	
	
}
