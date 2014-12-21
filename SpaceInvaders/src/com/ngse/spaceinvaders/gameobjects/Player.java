package com.ngse.spaceinvaders.gameobjects;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.ngse.spaceinvaders.Config;
import com.ngse.spaceinvaders.SpaceInvadersGame;
import com.ngse.spaceinvaders.resources.sounds.Mp3Player;
import com.ngse.spaceinvaders.screens.GameScreen;

public class Player extends GameObject {

	public Player(double x, double y, double direction, double speed,
			BufferedImage image) {
		super(x, y, direction, speed, image);
		playerweapon = new PlayerWeapon(this);
		this.health = Config.PLAYER_START_HEALTH;

		this.boundX = SpaceInvadersGame.frame.getWidth()
				- this.getImage().getWidth();
		this.boundY = SpaceInvadersGame.frame.getHeight() - 2
				* this.getImage().getHeight() - 0.00;
	}

	public PlayerWeapon playerweapon;

	private double boundX;
	private double boundY;

	private enum IDirection {
		POS, NEG, NON
	}

	private int weaponCooldown = 0;
	private boolean shooting = false;

	private boolean up = false;
	private boolean down = false;
	private boolean left = false;
	private boolean right = false;

	// private IDirection xAxis = IDirection.NON;
	// private IDirection yAxis = IDirection.NON;

	private int health;

	public void moveUpdate() {

		// SpaceInvadersGame.log("DX: " + String.valueOf(this.getDx()));
		// SpaceInvadersGame.log("DY: " + String.valueOf(this.getDy()));

		// Bounds check
		if (checkInsideBounds()) {
			move();
		} else {
			keepInsideBounds();
		}

		if (shooting)
			shoot();

	}

	/*
	 * Change the direction based on key input
	 */
	public void keyPressedEventUpdate(int keycode) {
		switch (keycode) {
		case KeyEvent.VK_W:
			up = true;
			// this.yAxis = IDirection.NON;
			break;
		case KeyEvent.VK_S:
			down = true;
			// this.yAxis = IDirection.NON;
			break;
		case KeyEvent.VK_A:
			left = true;
			// this.xAxis = IDirection.NON;
			break;
		case KeyEvent.VK_D:
			right = true;
			// this.xAxis = IDirection.NON;
			break;
		case KeyEvent.VK_SPACE:
			if (!shooting) {
				startShoot();
			}
			break;
		}
	}

	private void startShoot() {
		shooting = true;
		weaponCooldown = 100;
	}

	/*
	 * Set directions to NON when release key
	 */
	public void keyReleaseEventUpdate(int keycode) {
		switch (keycode) {
		case KeyEvent.VK_W:
			up = false;
			// this.yAxis = IDirection.NON;
			break;
		case KeyEvent.VK_S:
			down = false;
			// this.yAxis = IDirection.NON;
			break;
		case KeyEvent.VK_A:
			left = false;
			// this.xAxis = IDirection.NON;
			break;
		case KeyEvent.VK_D:
			right = false;
			// this.xAxis = IDirection.NON;
			break;
		case KeyEvent.VK_SPACE:
			shooting = false;
			break;
		default:
			up = false;
			down = false;
			left = false;
			right = false;
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
		if (this.getX() <= 0 || this.getX() >= boundX) {
			if (this.getX() <= 0) {
				this.setX(0);
			} else if (this.getX() >= boundX) {
				this.setX(boundX);
			}
			this.setDx(0);
		}
		// Outside YAxis Bounds
		if (this.getY() <= 0 || this.getY() >= boundY) {
			if (this.getY() <= 0) {
				this.setY(0);
			} else if (this.getY() >= boundY) {
				this.setY(boundY);
			}
			this.setDy(0);
		}
	}

	/*
	 * Move method
	 */
	public void move() {
		// XAXIS direction account
		if (this.getDx() <= Config.PLAYER_MAX_SPEED) {
			// if (xAxis.equals(IDirection.POS)) {
			if (right) {
				// Right
				// SpaceInvadersGame.log("Got direction: Right; Going Right");
				this.setDx((this.getDx() + Config.PLAYER_SPEED));
			} // else if (xAxis.equals(IDirection.NEG)) {
			else if (left) {
				// Left
				// SpaceInvadersGame.log("Got direction: Left; Going left");
				this.setDx((this.getDx() - Config.PLAYER_SPEED));
			}
			// Drag
			this.setDx(this.getDx() * Config.PLAYER_FRICTION);
		}
		// YAXIS direction account
		if (this.getDy() <= Config.PLAYER_MAX_SPEED) {
			// if (yAxis.equals(IDirection.NEG)) {
			if (down) {
				// Down
				// SpaceInvadersGame.log("Got direction: Down; Going Down");
				this.setDy((this.getDy() + Config.PLAYER_SPEED));
			} // else if (yAxis.equals(IDirection.POS)) {
			else if (up){
				// Up
				// SpaceInvadersGame.log("Got direction: Up; Going Up");
				this.setDy((this.getDy() - Config.PLAYER_SPEED));
			}
			// Drag
			this.setDy(this.getDy() * Config.PLAYER_FRICTION);
		}
		// Move
		this.setX(this.getX() + this.getDx());
		this.setY(this.getY() + this.getDy());
	}

	/*
	 * Shooter Method
	 */
	private void shoot() {
		if (weaponCooldown < playerweapon.getCurrentWeaponCooldown())
			weaponCooldown++;
		else {
			weaponCooldown = 0;
			playerweapon.fire();
			// Mp3Player.play("lazer");
		}
	}

	public void loseHealth() {
		health--;
	}

	public void getHit() {
		if (dy <= 0) {
			dy = 0;
		}
		dy += 10;
	}

	public boolean dead() {
		if (health <= 0) {
			return true;
		} else {
			return false;
		}
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

}
