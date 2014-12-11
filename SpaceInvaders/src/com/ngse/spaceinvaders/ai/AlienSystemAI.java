package com.ngse.spaceinvaders.ai;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.ngse.spaceinvaders.Config;
import com.ngse.spaceinvaders.Storyboard;
import com.ngse.spaceinvaders.gameobjects.Alien;
import com.ngse.spaceinvaders.screens.GameScreen;

public class AlienSystemAI {

	protected GameScreen gamescreen;

	private LinkedList<Alien> aliens;

	private int[] levelTimes = { 500, 1500, 2500 };

	public enum AlienPattern {
	}

	public AlienSystemAI(GameScreen gs) {
		// Don't call this method until the currentscreen changed to gamescreen
		gamescreen = gs;
		this.aliens = (LinkedList<Alien>) gamescreen.aliens;
	}

	public void update() {
		defaultMoveUpdate();
		createAlien();
	}

	private void createAlien() {

		switch (gamescreen.Level) {
		case 1:
			doLevel1();
			break;
		case 2:
			doLevel2();
			break;
		case 3:
			doLevel3();
			break;
		}

	}

	private void defaultMoveUpdate() {
		List<Alien> removeAliens = new LinkedList<Alien>();
		for (Alien a : aliens) {
			if (!a.equals(null)) {
				a.moveUpdate();
				if (!a.exists()) {
					removeAliens.add(a);
				}
			}
		}
		for (Alien alien : removeAliens)
			aliens.remove(alien);
	}

	private void doLevel1() {
		// for gameclock values 500-1500
		for (int i = 0; i < Storyboard.Level1Times.length; i++) {
			if (gamescreen.GameClock == Storyboard.Level1Times[i]) {
				gamescreen.aliens.add(new Alien(Storyboard.Level1Xs[i],
						Storyboard.Level1Ys[i], Storyboard.Level1DXs[i],
						Storyboard.Level1DYs[i], Storyboard.Level1Types[i]));
			}
		}
	}

	private void doLevel2() {
		// for gameclock values 1500-2500
		for (int i = 0; i < Storyboard.Level2Times.length; i++) {
			if (gamescreen.GameClock == Storyboard.Level2Times[i]) {
				gamescreen.aliens.add(new Alien(Storyboard.Level2Xs[i],
						Storyboard.Level2Ys[i], Storyboard.Level2DXs[i],
						Storyboard.Level2DYs[i], Storyboard.Level2Types[i]));
			}
		}
	}

	private void doLevel3() {
		// for gameclock values 2500-3500
	}
}