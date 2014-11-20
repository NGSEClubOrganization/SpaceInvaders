package com.ngse.spaceinvaders.gameobjects;

import com.ngse.spaceinvaders.SpaceInvadersGame;
import com.ngse.spaceinvaders.resources.images.BufferedImageResource;
import com.ngse.spaceinvaders.screens.GameScreen;

public class AlienBullet extends Bullet {

	GameScreen gameScreen;

	public AlienBullet(double x, double y, double speed, double direction) {
		super(x, y, speed, direction, BufferedImageResource.AlienBullet);
		;
		owner = Owner.ALIEN;
		gameScreen = (GameScreen) SpaceInvadersGame.getCurrentScreen();
	}

	public void addToGameScreen() {
		gameScreen.alienBullets.add(this);
	}

	public void removeFromGameScreen() {
		gameScreen.alienBullets.remove(this);
	}

}