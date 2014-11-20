package com.ngse.spaceinvaders.gameobjects;

import com.ngse.spaceinvaders.SpaceInvadersGame;
import com.ngse.spaceinvaders.resources.images.BufferedImageResource;
import com.ngse.spaceinvaders.screens.GameScreen;

public class PlayerBullet extends Bullet {

	GameScreen gameScreen;

	public PlayerBullet(double x, double y, double speed, double direction) {
		super(x, y, speed, direction, BufferedImageResource.PlayerBullet);
		owner = Owner.PLAYER;
		if (SpaceInvadersGame.getCurrentScreen() instanceof GameScreen) {
			gameScreen = (GameScreen) SpaceInvadersGame.getCurrentScreen();
		} else {
			SpaceInvadersGame.log("Trying to add a bullet outside of GameScreen");
		}
	}

	public void addToGameScreen() {
		gameScreen.playerBullets.add(this);
	}

	public void removeFromGameScreen() {
		gameScreen.playerBullets.remove(this);
	}

}
