package com.newlecture.mosquito;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import com.newlecture.mosquito.entity.Stage;
import com.newlecture.mosquito.service.DataService;

public class Program {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		System.out.println("�躴�� ");
		System.out.println("Thanks!");
		System.out.println("?��?��?��?�� >?��<");
		
		DataService ds = new DataService();
		
		
		// �Ʒ� �ڵ�� DataService�� ���� �Դϴ�. ���߿� ����Կ�.
		// �ʱ� �÷��̾� ����(���)�� ���������� �ϸ� => �÷��̾� ������ ������(����)�̹Ƿ� getIntValue()�� ����մϴ� 
		int playerLevel = DataService.getInstance().getIntValue("default", "playerLevel");		// ��Ÿ���� �ȵſ�,,,
		System.out.println(playerLevel);
		
		// �������� ������ ���������� �ϸ� => �������� ������ Stage��ü�� �����͸� ��� ��ȯ�մϴ�. �޼ҵ�� getStageValue()���!
		Stage defalutStage = DataService.getInstance().getStageValue(1);
		// gameConfig.txt�� stage1�� ���� �׸��� �����ϸ� ��ȿ�� ���� ��ȯ�մϴ�.
		System.out.printf("��������%d���� ���� ������ �ִ� ��� �� : %d\n", 1, defalutStage.mosqMaxCount);
		
		
		
		
		
	}
	
	
	
	

}
