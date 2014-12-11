package com.ngse.spaceinvaders.gameobjects;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import com.ngse.spaceinvaders.SpaceInvadersGame;
import com.ngse.spaceinvaders.screens.GameScreen;

public class GameObject {

	protected double x, y, dx, dy;

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	protected BufferedImage image;
	protected boolean exists = true;

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	// public double getSpeed() {
	// return this.velocity[1];
	// }
	//
	// public void setSpeed(double s) {
	// this.velocity[1] = s;
	// }

	// public double getDirection() {
	// return this.velocity[0];
	// }
	//
	// public void setDirection(double d) {
	// this.velocity[0] = d;
	// }

	// public double getAngle() {
	// return this.velocity[0];
	// }
	//
	// public void setAngle(double d) {
	// this.velocity[0] = d;
	// }

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	// Complex Sets:

	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// Complex Gets:

	/**
	 * Returns a Rectangle2D the is the shape of the game-object. Useful for
	 * collision checks
	 * 
	 * @return Rectangle2D that defines the space the bullet contains
	 */
	public Rectangle2D getHitbox() {
		int width = this.getImage().getWidth();
		int height = this.getImage().getHeight();

		Rectangle2D hitbox = new Rectangle2D.Double(this.getX(), this.getY(),
				width, height);
		return hitbox;
	}

	// Constructor
	public GameObject(double x, double y, double dx, double dy,
			BufferedImage img) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.image = img;
	}

	// /////

	public void draw(Graphics2D g2) {

		g2.drawImage(this.getImage(), null, (int) this.x, (int) this.y);

	}

	public void despawn() {
		GameScreen gs = (GameScreen) SpaceInvadersGame.getCurrentScreen();
		gs.remove(this);
		exists = false;
	}

	public boolean exists() {
		return exists;
	}

}
