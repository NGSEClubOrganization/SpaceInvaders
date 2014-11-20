package com.ngse.spaceinvaders.gameobjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import com.ngse.spaceinvaders.SpaceInvadersGame;
import com.ngse.spaceinvaders.screens.GameScreen;

/**
 * Base class for all bullets.
 */
public class Bullet extends GameObject {
	public enum Owner {
		PLAYER, ALIEN
	}

	// //width and height of bullet
	// protected int width = 6;
	// protected int height = 15;

	// angle the bullet is pointing
	protected double angle;

	// who shot the bullet, and final values indicating which means which
	protected Owner owner;

	/**
	 * Initiates bullet at a given point
	 * 
	 * @param x
	 *            x coordinate of the CENTER of the bullet.
	 * @param y
	 *            y coordinate of the bullet
	 * @param speed
	 *            the absolute speed of the bullet
	 * @param direction
	 *            the angle at which the bullet is moving. Give in radians
	 * @param file
	 *            the image that will be the bullet
	 */

	public Bullet(double x, double y, double angle, double speed,
			BufferedImage img) {
		super(x, y, speed * Math.cos(angle), speed * -1 * Math.sin(angle), img);
		// initialise instance variables
		this.angle = angle;
	}

	// stolen from the internet
	protected void resize() {
		BufferedImage outputImage = new BufferedImage(this.image.getWidth(),
				this.getImage().getHeight(), this.getImage().getType());

		// scales the input image to the output image
		Graphics2D g2d = outputImage.createGraphics();
		g2d.drawImage(this.image, 0, 0, this.image.getWidth(), this.getImage()
				.getHeight(), null);
		g2d.dispose();

		this.image = outputImage;

	}

	public void moveUpdate() {// moves up at a given speed
		SpaceInvadersGame.log("Updating movement for a bullet");
		y += dy;
		x += dx;
		
		
	}

	public void draw(Graphics2D g2) {
		// saves normal rotation/position of the Graphics2D
		AffineTransform old = g2.getTransform();

		// rotates the Graphics2D to draw the bullet at the correct angle and
		// point
		g2.rotate(Math.PI / 2 - angle, this.getXCoord(), this.getYCoord());

		// draws a red rectangle if no image is found
		if (this.image != null) {
			g2.setColor(Color.RED);
			g2.fillRect(this.getXCoord(), this.getYCoord(),
					this.image.getWidth(), this.image.getHeight());
		} else
			g2.drawImage(this.image, null, getXCoord(), getYCoord());

		// resets the rotation/position of the Graphics2D
		// if this is not done, anything drawn after in the same paint method
		// will have the same rotation
		g2.setTransform(old);
	}

	public Owner getOwner() {
		return owner;
	}

	/**
	 * Returns a polygon the is the shape of the bullet. Useful for collision
	 * checks
	 * 
	 * @return Polygon that defines the space the bullet contains
	 */
	public Polygon getBulletHitbox() {
		double easyAngle = Math.PI / 2 - angle;
		int width = this.image.getWidth();
		int height = this.getImage().getHeight();

		int[] xCoords = {
				(int) x,
				(int) (x + width * Math.cos(easyAngle)),
				(int) (x + width * Math.cos(easyAngle) - height
						* Math.sin(easyAngle)),
				(int) (x - height * Math.sin(easyAngle)) };
		int[] yCoords = {
				(int) y,
				(int) (y + width * Math.sin(easyAngle)),
				(int) (y + width * Math.sin(easyAngle) + height
						* Math.cos(easyAngle)),
				(int) (y + height * Math.cos(easyAngle)) };
		Polygon hitbox = new Polygon(xCoords, yCoords, 4);
		return hitbox;
	}

	/**
	 * Returns x position of the bullet as an integer
	 * 
	 * @return x position of the bullet
	 */
	public int getXCoord() {
		return (int) x;
	}

	public double getAngle() {
		return angle;
	}

	/**
	 * Returns y position of the bullet as an integer
	 * 
	 * @return y position of the bullet
	 */
	public int getYCoord() {
		return (int) y;
	}

	// public int getWidth() {
	// return width;
	// }
	// public int getHeight() {
	// return height;
	// }
	
	
	
	
}