package com.ngse.spaceinvaders.gameobjects;

import com.ngse.spaceinvaders.Config;
import com.ngse.spaceinvaders.SpaceInvadersGame;
import com.ngse.spaceinvaders.resources.images.BufferedImageResource;
import com.ngse.spaceinvaders.screens.GameScreen;

public class Alien extends GameObject {

	enum AlienType {
		BASIC, DUCK, SUICIDE
	}

	private AlienType type;
	private GameScreen gamescreen;

	public Alien(double x, double y, double dX, double dY, int alienImageIndex) {
		super(x, y, dX, dY, BufferedImageResource.Aliens[alienImageIndex]);
		this.gamescreen = (GameScreen) SpaceInvadersGame.getCurrentScreen();

		// Set the alien type
		switch (alienImageIndex) {
		case 0:
			setType(AlienType.BASIC);
		case 1:
			setType(AlienType.DUCK);
		case 2:
			setType(AlienType.SUICIDE);
		default:
			setType(AlienType.BASIC);

		}
	}

	public void moveUpdate() {
		y += dy;
		x += dx;

		// Auto remove
		if (y + this.getImage().getHeight() >= gamescreen.getHeight() || y <= 0
				|| x + this.getImage().getWidth() >= gamescreen.getWidth()
				|| x <= 0) {
			// removeFromGameScreen();
		}

	}

	public void shoot() {
		gamescreen.alienBullets.add(new AlienBullet(this.getX()
				+ this.getImage().getWidth() / 2, this.getY()
				+ this.getImage().getHeight(), Config.ALIEN_BULLET_SPEED,
				4 * Math.PI / 3));
	}

	public AlienType getType() {
		return type;
	}

	public void setType(AlienType type) {
		this.type = type;
	}

}
