package com.ngse.spaceinvaders.gameobjects;

import java.util.Random;

import javazoom.jl.player.Player;

import com.ngse.spaceinvaders.Config;
import com.ngse.spaceinvaders.SpaceInvadersGame;
import com.ngse.spaceinvaders.ai.AlienSystemAI.AlienPattern;
import com.ngse.spaceinvaders.resources.images.BufferedImageResource;
import com.ngse.spaceinvaders.screens.GameScreen;

public class Alien extends GameObject {
	
	Random rand = new Random();
	
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
			break;
		case 1:
			setType(AlienType.DUCK);
			break;
		case 2:
			setType(AlienType.SUICIDE);
			break;
		default:
			setType(AlienType.BASIC);
			break;
		}
	}

	public void moveUpdate() {

		double px = gamescreen.player.getX();
		double py = gamescreen.player.getY();
		if (gamescreen.GameClock % 75 == 0)
			shoot();

		switch (this.getType()) {

		case BASIC: {
			if (x < 5
					|| x > Config.FRAME_WIDTH - this.getImage().getWidth() - 5) {
				dx *= -1;
			}

			x += dx;
			y += dy;
			break;
		}

		case DUCK: {
			if (x < 5
					|| x > Config.FRAME_WIDTH - this.getImage().getWidth() - 5) {
				dx *= -1;
			}
			x += dx;
			y += dy;
			break;
		}

		case SUICIDE: {
			y = (y + 1 * ((py - y) / 20));
			x = (x + 1 * ((px - x) / 20));
			break;
		}

		}

		if (x > gamescreen.getWidth() || x < 0 || y > gamescreen.getHeight()
				|| y < 0) {
			die();
		}

	}

	public void shoot() {
		gamescreen.alienBullets.add(new AlienBullet(this.getX()
				+ this.getImage().getWidth() / 2, this.getY()
				+ this.getImage().getHeight(), Config.ALIEN_BULLET_SPEED,
				3 * Math.PI / 2));
	}

	public AlienType getType() {
		return type;
	}

	public void setType(AlienType type) {
		this.type = type;
	}

	public void die() {
		// TODO Auto-generated method stub
		SpaceInvadersGame.log("Alien is despawning.");
		despawn();
		if(rand.nextInt(Config.UPGRADE_CHANCE) == 0){
			SpaceInvadersGame.gameScreen.upgrades.add(new Upgrade(
					rand.nextInt(Config.FRAME_WIDTH), rand.nextInt(Config.FRAME_HEIGHT),
					((rand.nextInt(2)+1)*2)-3, 1,
					Config.UPGRADEBOX_SPEED,
					BufferedImageResource.powerupBox));
			SpaceInvadersGame.log("UP-Box has spawned.");
		}
	}

}










