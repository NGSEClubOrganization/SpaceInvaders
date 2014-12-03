package com.ngse.spaceinvaders.handlers;


import java.util.LinkedList;
import java.util.List;

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
		List<Alien> removeAliens = new LinkedList();
		List<PlayerBullet> removeBullets = new LinkedList();
		for (PlayerBullet bullet: screen.playerBullets) {
			for (Alien alien : screen.aliens) {
				if (bullet.getBulletHitbox().intersects(alien.getHitbox())) {
					removeAliens.add(alien);
					removeBullets.add(bullet);
					//alien.die(); 
					//bullet.despawn();
					log("Player Bullet hit an alien."); //XXX debug script, remove later
				}
			}
			if (screen.alienBoss != null && bullet.getBulletHitbox().intersects(screen.alienBoss.getHitbox())){
				screen.alienBoss.die(); //TODO implement a die() method in AlienBoss
				removeBullets.add(bullet);
				log("A player bullet hit a boss"); //XXX debug output, remove later
				screen.alienBoss.die();
			}
		}
		
		for (PlayerBullet bullet : removeBullets) 
			bullet.despawn();
		for (Alien alien : removeAliens)
			alien.die(); //TODO implement a die() method in Alien
		
	}
	
	private void log(String str) {
		SpaceInvadersGame.log(str);
	}


}
