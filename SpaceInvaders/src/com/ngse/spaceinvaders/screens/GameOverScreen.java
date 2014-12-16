package com.ngse.spaceinvaders.screens;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import com.ngse.spaceinvaders.SpaceInvadersGame;
import com.ngse.spaceinvaders.resources.images.BufferedImageResource;

@SuppressWarnings("serial")
public class GameOverScreen extends Screen {

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.drawImage(BufferedImageResource.GameOverScreen, null, 0, 0);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			SpaceInvadersGame.endProgram();
		case KeyEvent.VK_ENTER:
			SpaceInvadersGame.setScreen(SpaceInvadersGame.startScreen);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	public void actionPerformed(ActionEvent ae) {
		repaint();
	}

}
