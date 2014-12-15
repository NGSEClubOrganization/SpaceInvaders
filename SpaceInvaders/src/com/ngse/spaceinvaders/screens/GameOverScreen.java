package com.ngse.spaceinvaders.screens;

import java.awt.Graphics;
import java.awt.Graphics2D;

import com.ngse.spaceinvaders.resources.images.BufferedImageResource;

@SuppressWarnings("serial")
public class GameOverScreen extends Screen {

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.drawImage(BufferedImageResource.GameOverScreen, null, 0, 0);
	}

}
