package com.ngse.spaceinvaders.handlers;


import com.ngse.spaceinvaders.SpaceInvadersGame;
import com.ngse.spaceinvaders.gameobjects.Alien;
import com.ngse.spaceinvaders.gameobjects.AlienBullet;
import com.ngse.spaceinvaders.gameobjects.PlayerBullet;
import com.ngse.spaceinvaders.screens.GameScreen;

public class BulletCollisionsHandler {
	
	protected GameScreen screen;
	
	public BulletCollisionsHandler(GameScreen screen) {
		this.screen = screen;
	}

	
	public static void init() {
		
	}
	
	
	public void update() {		
		checkBulletCollisions();
	}


	private void checkBulletCollisions() {
		playerBulletCollisions();
		alienBulletCollisions();
	}


	private void alienBulletCollisions() {
		for (AlienBullet bullet: screen.alienBullets) {
			if (bullet.getBulletHitbox().intersects(screen.player.getHitbox())){
				screen.player.loseHealth(); //TODO implement a loseHealth() method in Player
				log("An alien bullet hit player."); //XXX debug script, remove later
			}
		}
		
	}


	private void playerBulletCollisions() {
		for (PlayerBullet bullet: screen.playerBullets) {
			for (Alien alien : screen.aliens) {
				if (bullet.getBulletHitbox().intersects(alien.getHitbox())) {
					alien.die(); //TODO implement a die() method in Alien
					log("Player Bullet hit an alien."); //XXX debug script, remove later
				}
			}
			if (screen.alienBoss != null && bullet.getBulletHitbox().intersects(screen.alienBoss.getHitbox())){
				screen.alienBoss.die(); //TODO implement a die() method in AlienBoss
				log("A player bullet hit a boss"); //XXX debug output, remove later
			}
		}
		
	}
	
	private void log(String str) {
		SpaceInvadersGame.log(str);
	}


}
