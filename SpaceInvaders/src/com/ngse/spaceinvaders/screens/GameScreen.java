package com.ngse.spaceinvaders.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import com.ngse.spaceinvaders.Config;
import com.ngse.spaceinvaders.SpaceInvadersGame;
import com.ngse.spaceinvaders.ai.AlienSystemAI;
import com.ngse.spaceinvaders.gameobjects.Alien;
import com.ngse.spaceinvaders.gameobjects.AlienBoss;
import com.ngse.spaceinvaders.gameobjects.AlienBullet;
import com.ngse.spaceinvaders.gameobjects.Bullet;
import com.ngse.spaceinvaders.gameobjects.GameObject;
import com.ngse.spaceinvaders.gameobjects.Player;
import com.ngse.spaceinvaders.gameobjects.PlayerBullet;
import com.ngse.spaceinvaders.gameobjects.Upgrade;
import com.ngse.spaceinvaders.handlers.BulletCollisionsHandler;
import com.ngse.spaceinvaders.resources.images.BufferedImageResource;
import com.ngse.spaceinvaders.resources.sounds.Mp3Player;

public class GameScreen extends Screen {

	private static final long serialVersionUID = 1L;
	public int GameClock;
	public int Level;
	private int score = 0;

	// GameObjects:
	public Player player;

	public List<PlayerBullet> playerBullets;

	public List<Alien> aliens;
	public List<AlienBullet> alienBullets;

	public AlienBoss alienBoss;

	public List<Upgrade> upgrades;

	// AlienSystemAI for this game
	public AlienSystemAI ASAI;

	public int life;

	// GameState
	private enum GameState {
		RUNNING, PAUSE
	}

	private GameState gameState;

	/*
	 * Constructor
	 */
	public GameScreen() {
		this.GameClock = 0;
		// Initialize the GameState
		this.gameState = GameState.RUNNING;
		// Initialize the player
		this.player = new Player(0, 0, 0, 0, BufferedImageResource.Spaceship);
		this.player.setX(SpaceInvadersGame.frame.getWidth() / 2
				- player.getImage().getWidth() / 2);
		this.player.setY(SpaceInvadersGame.frame.getHeight() / 2
				- player.getImage().getHeight() / 2);
		// Initialize other GameObjects
		this.playerBullets = new LinkedList<PlayerBullet>();
		this.aliens = new LinkedList<Alien>();
		this.alienBullets = new LinkedList<AlienBullet>();
		this.alienBoss = null;
		this.upgrades = new LinkedList<Upgrade>();

		// Initialize AI's
		this.ASAI = new AlienSystemAI(this);

		// Player's life
		life = Config.PLAYER_START_HEALTH;

	}

	/*
	 * PaintComponent
	 */
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		if (this.gameState == GameState.RUNNING) { // if running
			// Reset the screen
			g2.setColor(Color.BLACK);
			g2.fillRect(0, 0, SpaceInvadersGame.frame.getWidth(),
					SpaceInvadersGame.frame.getHeight());

			/*
			 * Draw the gameobjects
			 */
			player.draw(g2);
			for (PlayerBullet pb : playerBullets) {
				if (!pb.equals(null))
					pb.draw(g2);
			}
			for (Alien a : aliens) {
				if (!a.equals(null))
					a.draw(g2);
			}
			for (AlienBullet ab : alienBullets) {
				if (!ab.equals(null))
					ab.draw(g2);
			}
			if (!(alienBoss == null))
				alienBoss.draw(g2);
			for (Upgrade u : upgrades) {
				if (!u.equals(null))
					u.draw(g2);
			}

			// Print level title
			if (GameClock >= 0 && GameClock < 500) {
				// System.out.println("Printing level 1");
				paintString(g2, "Level 1");
			} else if (GameClock >= 1300 && GameClock < 1500) {
				paintString(g2, "Level 2");
			} else if (GameClock >= 2300 && GameClock < 2500) {
				paintString(g2, "Level 3");
			}

		} else { // if paused
			BufferedImage pausepopup = BufferedImageResource.PausePopup;
			g2.drawImage(pausepopup, null,
					(int) this.getWidth() - pausepopup.getWidth() * 2,
					(int) this.getHeight() - pausepopup.getHeight() * 2);
		}

		// Draw the Player's UI:
		this.drawPlayerUI(g2);
	}

	private void drawPlayerUI(Graphics2D g2) { // XXX check if string placement
		g2.setColor(Color.WHITE); // works
		g2.setFont(new Font("TimesRoman", Font.PLAIN, 12));
		g2.drawString("Score: " + String.valueOf(score), 10, 20);
		g2.drawString("Level: " + String.valueOf(Level), 10, 40);
		g2.drawString("Weapon: " + player.playerweapon.getWeaponName(), 10, 60);

		int width = 20;
		int height = 20;

		if (BufferedImageResource.FullHealth != null) {
			height = BufferedImageResource.FullHealth.getHeight();
			width = BufferedImageResource.FullHealth.getWidth();
		}

		for (int i = 1; i <= Config.PLAYER_START_HEALTH; i++)
			if (i <= player.getHealth()) {
				if (BufferedImageResource.FullHealth != null) {
					g2.drawImage(BufferedImageResource.FullHealth,
							SpaceInvadersGame.frame.getWidth() - 50 - width
									* Config.PLAYER_START_HEALTH + width * i,
							50, null);
				} else {
					g2.setColor(Color.RED);
					g2.fillOval(SpaceInvadersGame.frame.getWidth() - 50 - width
							* Config.PLAYER_START_HEALTH + width * i, 50,
							width, height);
				}
			} else {
				if (BufferedImageResource.LostHealth != null) {
					g2.drawImage(BufferedImageResource.LostHealth,
							SpaceInvadersGame.frame.getWidth() - 50 - width
									* Config.PLAYER_START_HEALTH + width * i,
							50, null);
				} else {
					g2.setColor(Color.GRAY);
					g2.drawOval(SpaceInvadersGame.frame.getWidth() - 50 - width
							* Config.PLAYER_START_HEALTH + width * i, 50,
							width, height);
				}
			}

	}

	private void paintString(Graphics2D g2, String string) {
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("TimesRoman", Font.PLAIN, 50));
		g2.drawString(string, 200, 200);
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
			} else if (gameState.equals(GameState.PAUSE)) {
				SpaceInvadersGame.setScreen(new StartScreen());
			}
			break;
		case KeyEvent.VK_ENTER:
			if (gameState.equals(GameState.PAUSE)) {
				resumeGame();
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
		this.gameState = GameState.PAUSE;
	}

	/*
	 * Resume Game
	 */
	public void resumeGame() {
		this.gameState = GameState.RUNNING;
	}

	/*
	 * ActionPerformed
	 */

	// Constant Timer Updates
	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.gameState == GameState.RUNNING) {
			doGameLogic();

			// Set what level it is now
			if (GameClock >= 500 && GameClock < 1500) {
				Level = 1;
			} else if (GameClock >= 1500 && GameClock < 2500) {
				Level = 2;
			} else if (GameClock >= 2500 && GameClock < 3500) {
				Level = 3;
			}

			GameClock++;
		} else if (this.gameState == GameState.PAUSE) {

		}
		repaint();
	}

	// GameLogic
	private void doGameLogic() {
		// Iterator<PlayerBullet> pbIterate= playerBullets.iterator();

		@SuppressWarnings("unused")
		List<Alien> removeAliens = new LinkedList<Alien>();
		List<PlayerBullet> removeBullets = new LinkedList<PlayerBullet>();

		BulletCollisionsHandler handler = new BulletCollisionsHandler(this);
		handler.update();

		// Update all GameObject Positions
		player.moveUpdate();

		for (PlayerBullet pb : playerBullets) {
			if (!pb.equals(null)) {
				pb.moveUpdate();
				if (!pb.exists()) {
					removeBullets.add(pb);
				}
			}
		}
		for (PlayerBullet bullet : removeBullets)
			playerBullets.remove(bullet);

		for (AlienBullet ab : alienBullets) {
			if (!ab.equals(null))
				ab.moveUpdate();
		}
		if (!(alienBoss == null))
			alienBoss.moveUpdate();
		for (Upgrade u : upgrades) {
			if (!u.equals(null))
				u.moveUpdate();
		}

		ASAI.update();

		// Check if the game is over (life is 0)
		if (this.life <= 0) {
			gameOver();
		}
	}

	/*
	 * Remove objects methods
	 */
	private void gameOver() {
		SpaceInvadersGame.setScreen(SpaceInvadersGame.gameOverScreen);
	}

	public void remove(PlayerBullet object) {
		playerBullets.remove(object);
	}

	public void remove(AlienBullet object) {
		alienBullets.remove(object);
	}

	public void remove(Alien object) {
		aliens.remove(object);
	}

	public void remove(Bullet object) {
		playerBullets.remove(object);
	}

	public void remove(GameObject object) {
		playerBullets.remove(object);
	}

	public void endGame() {
		SpaceInvadersGame.log("Ending game...");
	}

	public void addScore(int addition) {
		score += addition;

	}

}
