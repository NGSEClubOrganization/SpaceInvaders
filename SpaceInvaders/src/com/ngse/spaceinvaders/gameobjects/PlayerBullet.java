package com.ngse.spaceinvaders.gameobjects;

import com.ngse.spaceinvaders.resources.images.BufferedImageResource;

public class PlayerBullet extends Bullet {
	public PlayerBullet(double x, double y, double speed, double direction) {
		super(x, y, speed, direction, BufferedImageResource.PlayerBullet);
		owner = Owner.PLAYER;
	}

}
