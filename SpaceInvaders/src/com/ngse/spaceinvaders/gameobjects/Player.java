package com.ngse.spaceinvaders.gameobjects;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.ngse.spaceinvaders.Config;
import com.ngse.spaceinvaders.SpaceInvadersGame;
import com.ngse.spaceinvaders.screens.GameScreen;

public class Player extends GameObject {

	public Player(double x, double y, double direction, double speed,
			BufferedImage image) {
		super(x, y, direction, speed, image);
		playerweapon = new PlayerWeapon(this);
	}

	public PlayerWeapon playerweapon;

	private double boundX = SpaceInvadersGame.frame.getWidth()
			- this.getImage().getWidth();
	private double boundY = SpaceInvadersGame.frame.getHeight() - 2
			* this.getImage().getHeight() - 0.00;

	private enum IDirection {
		POS, NEG, NON
	}

	private IDirection xAxis = IDirection.NON;
	private IDirection yAxis = IDirection.NON;

	// TODO do i need this?
	private enum PlayerBulletType {
		NORMAL
	}

	private PlayerBulletType playerBulletType = PlayerBulletType.NORMAL;

	public void moveUpdate() {

		SpaceInvadersGame.log("DX: " + String.valueOf(this.getDx()));
		SpaceInvadersGame.log("DY: " + String.valueOf(this.getDy()));

		// Bounds check
		if (checkInsideBounds()) {
			move();
		} else {
			keepInsideBounds();
		}

	}

	/*
	 * Change the direction based on key input
	 */
	public void keyPressedEventUpdate(int keycode) {
		switch (keycode) {
		case KeyEvent.VK_W:
			this.yAxis = IDirection.POS;
			break;
		case KeyEvent.VK_S:
			this.yAxis = IDirection.NEG;
			break;
		case KeyEvent.VK_A:
			this.xAxis = IDirection.NEG;
			break;
		case KeyEvent.VK_D:
			this.xAxis = IDirection.POS;
			break;
		case KeyEvent.VK_SPACE:
			shoot(playerBulletType);
		}
	}

	/*
	 * Set directions to NON when release key
	 */
	public void keyReleaseEventUpdate(int keycode) {
		switch (keycode) {
		case KeyEvent.VK_W:
			this.yAxis = IDirection.NON;
			break;
		case KeyEvent.VK_S:
			this.yAxis = IDirection.NON;
			break;
		case KeyEvent.VK_A:
			this.xAxis = IDirection.NON;
			break;
		case KeyEvent.VK_D:
			this.xAxis = IDirection.NON;
			break;
		}
	}

	/*
	 * Checks if Player is inside bounds
	 */
	public boolean checkInsideBounds() {
		return this.getX() >= 0 && this.getX() <= boundX
		// YAxis check
				&& this.getY() >= 0 && this.getY() <= boundY;
	}

	/*
	 * Set player to be inside bounds, (implied: after checkInsideBounds() ==
	 * false)
	 */
	public void keepInsideBounds() {
		// Outside XAxis Bounds
		if (this.getX() <= 0) {
			this.setX(0);
			this.setDx(0);
		} else if (this.getX() >= boundX) {
			this.setX(boundX);
			// this.setDx(this.getDx() * Config.PLAYER_FRICTION);
		}
		// Outside YAxis Bounds
		if (this.getY() <= 0) {
			this.setY(0);
			this.setDy(0);
		} else if (this.getY() >= boundY) {
			this.setY(boundY);
			// this.setDy(this.getDy() * Config.PLAYER_FRICTION);
		}
	}

	/*
	 * Move method
	 */
	public void move() {
		// XAXIS direction account
		if (this.getDx() <= Config.PLAYER_MAX_SPEED) {
			if (xAxis.equals(IDirection.POS)) {
				// Right
				SpaceInvadersGame.log("Got direction: Right; Going Right");
				this.setDx((this.getDx() + Config.PLAYER_SPEED)
						* Config.PLAYER_FRICTION);
			} else if (xAxis.equals(IDirection.NEG)) {
				// Left
				SpaceInvadersGame.log("Got direction: Left; Going left");
				this.setDx((this.getDx() - Config.PLAYER_SPEED)
						* Config.PLAYER_FRICTION);
			} else if (xAxis.equals(IDirection.NON)) {
				// Drag
				this.setDx(this.getDx() * Config.PLAYER_FRICTION);
			}
		}
		// YAXIS direction account
		if (this.getDy() <= Config.PLAYER_MAX_SPEED) {
			if (yAxis.equals(IDirection.NEG)) {
				// Down
				SpaceInvadersGame.log("Got direction: Down; Going Down");
				this.setDy((this.getDy() + Config.PLAYER_SPEED)
						* Config.PLAYER_FRICTION);
			} else if (yAxis.equals(IDirection.POS)) {
				// Up
				SpaceInvadersGame.log("Got direction: Up; Going Up");
				this.setDy((this.getDy() - Config.PLAYER_SPEED)
						* Config.PLAYER_FRICTION);
			} else if (yAxis.equals(IDirection.NON)) {
				// Drag
				this.setDy(this.getDy() * Config.PLAYER_FRICTION);
			}
		}
		// Move
		this.setX(this.getX() + this.getDx());
		this.setY(this.getY() + this.getDy());
	}

	/*
	 * Shooter Method
	 */
	private void shoot(PlayerBulletType pbt) {
		playerweapon.fire();
	}
	
}
