package com.newlecture.mosquito.service;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundLoader {
	
	public static Clip mainClip;
	public static AudioInputStream mainBg;

	
	/*
	static {
		try {
			mainBg = AudioSystem.getAudioInputStream(new File("res/sound/mainBgm.wav"));
			mainClip = AudioSystem.getClip();

			mainClip.open(mainBg);
			mainClip.start();
			
			System.out.println("sound good");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("err");
		}
			
		}
	public void bgmOff() {
		mainClip.stop();
	}
*/
	
}


