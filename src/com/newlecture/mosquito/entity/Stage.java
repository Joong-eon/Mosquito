package com.newlecture.mosquito.entity;

// ���� ��� ���ؼ��� ���̴� ����ü ���� ���� Ŭ����
// ���� ��� �ִ� �� �ܿ��� ���ٸ� ����� ����
// ������ ���������ڸ� public���� ������. 

public class Stage {
	public int stageIndex;			// ���� �������� �ε���
	
	public int mosqCreateCount;		// �������� �� �ִ� ���� ������ ��� ��
	public int mosqMaxCount;		// ���� �ֱ⿡ ���� �����Ǵ� ��� ��
	public int mosqCreateTime;		// ��� �����ֱ�
	
	public int buttMaxCount;		// �������� �� �ִ� ���� ������ ���� ��
	public int buttCreateCount;		// ���� �ֱ⿡ ���� �����Ǵ� ���� ��
	public int buttCreateTime; 		// ���� ���� �ֱ�
	
	
	public Stage() {
		super();
		mosqMaxCount = 0;
		mosqCreateCount = 0;
		mosqCreateTime = 0;
		
		buttMaxCount = 0;
		buttCreateCount = 0;
		buttCreateTime = 0;
	}
	
}
