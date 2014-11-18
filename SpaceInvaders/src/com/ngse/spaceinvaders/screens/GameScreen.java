package com.ngse.spaceinvaders.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.ngse.spaceinvaders.SpaceInvadersGame;
import com.ngse.spaceinvaders.gameobjects.Alien;
import com.ngse.spaceinvaders.gameobjects.AlienBoss;
import com.ngse.spaceinvaders.gameobjects.AlienBullet;
import com.ngse.spaceinvaders.gameobjects.Player;
import com.ngse.spaceinvaders.gameobjects.PlayerBullet;
import com.ngse.spaceinvaders.gameobjects.Upgrade;
import com.ngse.spaceinvaders.resources.images.BufferedImageResource;

@SuppressWarnings("serial")
public class GameScreen extends Screen {

	// Displays all the game's components (player, enemies, bullets, etc.) as
	// well as health, score, etc.

	private GameScreen gamescreen;

	public Player player;
	public PlayerBullet[] playerbullets = new PlayerBullet[999];
	public int numberOfPlayerBullets;

	public Alien[] aliens = new Alien[999];
	public int numberOfAliens;

	public AlienBullet[] alienbullets = new AlienBullet[999];
	public int numberOfAlienBullets;

	public AlienBoss alienboss;

	public Upgrade[] upgrades = new Upgrade[999];
	public int numberOfUpgrades;

	private enum GameState {
		RUNNING, PAUSE
	}

	private GameState gameState;

	/*
	 * Constructor
	 */
	public GameScreen() {
		this.gamescreen = this;
		// Initialize the GameState
		gameState = GameState.RUNNING;
		// Initialize the player
		player = new Player(0, 0, 0, 0, BufferedImageResource.Spaceship);
		player.setX(SpaceInvadersGame.frame.getWidth() / 2
				- player.getImage().getWidth() / 2);
		player.setY(SpaceInvadersGame.frame.getHeight() / 2
				- player.getImage().getHeight() / 2);
		numberOfPlayerBullets = 0;
		// Initialize Aliens
	}

	/*
	 * PaintComponent
	 */
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		// Reset the screen
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, SpaceInvadersGame.frame.getWidth(),
				SpaceInvadersGame.frame.getHeight());

		// Do game logic
		// this.doGameLogic();

		/*
		 * Draw the gameobjects
		 */
		// Player stuff
		player.draw(g2);
		SpaceInvadersGame.log("There are " + numberOfPlayerBullets
				+ " of playerbullets to draw");
		;
		for (int i = 0; i <= numberOfPlayerBullets; i++) {
			if (playerbullets[i] == null) {
				SpaceInvadersGame.log("Null playerbullet");
				break;
			}
			playerbullets[i].draw(g2);
		}
	}

	/*
	 * Adding Objects to Screen
	 */
	public void addPlayerBullet(PlayerBullet pb) {
		numberOfPlayerBullets++;
		playerbullets[numberOfPlayerBullets - 1] = pb;
	}

	public void addAlien(Alien alien) {
		numberOfAliens++;
		aliens[numberOfAliens - 1] = alien;
	}

	public void addAlienBullet(AlienBullet ab) {
		numberOfAlienBullets++;
		alienbullets[numberOfAlienBullets - 1] = ab;
	}

	public void addUpgrade(Upgrade upgrade) {
		numberOfUpgrades++;
		upgrades[numberOfUpgrades - 1] = upgrade;
	}

	/*
	 * KeyEvents
	 */

	// User's KeyInput: Moving the Player
	@Override
	public void keyPressed(KeyEvent e) {
		doKeyPressedEventUpdates(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		doKeyReleasedEventUpdates(e);
	}

	public void doKeyPressedEventUpdates(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			if (gameState.equals(GameState.RUNNING)) {
				pauseGame();
				gameState = GameState.PAUSE;
			} else if (gameState.equals(GameState.PAUSE)) {
				SpaceInvadersGame.setScreen(new StartScreen());
			}
			break;
		case KeyEvent.VK_ENTER:
			if (gameState.equals(GameState.PAUSE)) {
				gameState = GameState.RUNNING;
				SpaceInvadersGame.setScreen(gamescreen);
			}
			break;
		}
		// If Running Gamestate:
		if (gameState.equals(GameState.RUNNING)) {
			// Player
			player.keyPressedEventUpdate(e.getKeyCode());
		} else if (gameState.equals(GameState.PAUSE)) {

		}
	}

	public void doKeyReleasedEventUpdates(KeyEvent e) {
		// If Running Gamestate:
		if (gameState.equals(GameState.RUNNING)) {
			// Player
			player.keyReleaseEventUpdate(e.getKeyCode());
		} else if (gameState.equals(GameState.PAUSE)) {

		}
	}

	/*
	 * Pause popup
	 */
	public void pauseGame() {
		SpaceInvadersGame.timer.stop();
		Graphics2D g2 = (Graphics2D) SpaceInvadersGame.frame.getContentPane()
				.getGraphics();
		BufferedImage pausepopup = BufferedImageResource.PausePopup;
		g2.drawImage(pausepopup, null, (int) SpaceInvadersGame.frame
				.getContentPane().getWidth() - pausepopup.getWidth() * 2,
				(int) SpaceInvadersGame.frame.getContentPane().getHeight()
						- pausepopup.getHeight() * 2);
	}

	/*
	 * ActionPerformed
	 */
	// Constant Timer Updates
	@Override
	public void actionPerformed(ActionEvent e) {
		doGameLogic();
		repaint();
	}

	private void doGameLogic() {
		// TODO:
		// Update all GameObject Positions

		player.moveUpdate();
		for (int i = 0; i <= numberOfPlayerBullets - 1; i++) {
			playerbullets[i].moveUpdate();
		}
	}

}
