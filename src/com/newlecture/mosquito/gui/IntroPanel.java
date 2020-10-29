package com.newlecture.mosquito.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.net.MalformedURLException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;

import com.newlecture.mosquito.GameFrame;

public class IntroPanel extends JPanel {
	private Image img;
	private AudioInputStream introAis;
	private Clip introClip;

	public IntroPanel() throws MalformedURLException {
		img = Toolkit.getDefaultToolkit().createImage("res/introMovie.gif");
		introSound("res/sound/introMovie.wav");
		System.err.println("생성됨");
	}

	@Override
	public void paint(Graphics g) {

		paintComponent(g);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (img != null) {
			g.drawImage(img, 0, 0, GameFrame.canvasWidth, GameFrame.canvasHeight, this);
		}
	}
	
	private void introSound(String file) {

		try {
			introAis = AudioSystem.getAudioInputStream(new File(file));
			introClip = AudioSystem.getClip();

			introClip.open(introAis);
			introClip.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void introSoundOff() {
		introClip.stop();
	}

}
