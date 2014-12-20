package com.ngse.spaceinvaders.resources.images;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageResource {

	/*
	 * GameObjects
	 */
	// Player
	public static BufferedImage Spaceship;
	public static BufferedImage PlayerBullet;
	// Aliens
	public static BufferedImage[] Aliens = new BufferedImage[4];
	public static BufferedImage AlienBullet;
	// Screens
	public static BufferedImage GameOverScreen;
	public static BufferedImage OptionsScreen;
	public static BufferedImage StartScreen;
	// Popups
	public static BufferedImage PausePopup;
	// Images for health points
	public static BufferedImage FullHealth;
	public static BufferedImage LostHealth;
	//powerup box
	public static BufferedImage powerupBox;
	
	
	public static void initImages() {
		String imagespath = "src//com//ngse//spaceinvaders//resources//images//";

		try {
			// GameObjects
			Spaceship = ImageIO.read(new File(imagespath + "spaceship.png"));
			PlayerBullet = ImageIO.read(new File(imagespath
					+ "playerbullet.png"));

			Aliens[0] = ImageIO.read(new File(imagespath + "alien1.png"));
			Aliens[1] = ImageIO.read(new File(imagespath + "alien2.png"));
			Aliens[2] = ImageIO.read(new File(imagespath + "alien3.png"));
			Aliens[3] = ImageIO.read(new File(imagespath + "alien4.png"));
			AlienBullet = ImageIO
					.read(new File(imagespath + "alienbullet.png"));
			
			powerupBox = ImageIO
					.read(new File(imagespath + "power.png"));
			// Screens
			GameOverScreen = ImageIO.read(new File(imagespath
					+ "gameoverscreen.png"));
			OptionsScreen = ImageIO.read(new File(imagespath
					+ "optionsscreen.png"));
			StartScreen = ImageIO
					.read(new File(imagespath + "startscreen.png"));
			// Popups
			PausePopup = ImageIO.read(new File(imagespath + "pausepopup.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
