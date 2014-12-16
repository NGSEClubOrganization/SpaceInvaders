package com.ngse.spaceinvaders;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.Timer;

import com.ngse.spaceinvaders.resources.images.BufferedImageResource;
import com.ngse.spaceinvaders.resources.sounds.AudioStreamResource;
import com.ngse.spaceinvaders.screens.GameOverScreen;
import com.ngse.spaceinvaders.screens.GameScreen;
import com.ngse.spaceinvaders.screens.OptionsScreen;
import com.ngse.spaceinvaders.screens.Screen;
import com.ngse.spaceinvaders.screens.StartScreen;

public class SpaceInvadersGame {

	public static JFrame frame;
	public static Timer timer = new Timer(10, null);

	public static GameScreen gameScreen;
	public static OptionsScreen optionScreen;
	public static StartScreen startScreen;
	public static GameOverScreen gameOverScreen;

	public static ExecutorService threadPool;

	public static void main(String[] args) {
		// initFrame
		frame = new JFrame();

		// initHandlers

		// initResources
		BufferedImageResource.initImages();
		AudioStreamResource.initSounds();

		frame.setSize(Config.FRAME_WIDTH, Config.FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		gameScreen = new GameScreen();
		optionScreen = new OptionsScreen();
		startScreen = new StartScreen();
		gameOverScreen = new GameOverScreen();

		// Start Screen of game
		setScreen(startScreen);

		// initThreadPool
		threadPool = Executors.newFixedThreadPool(100);

		timer.start();
		frame.setVisible(true);
	}

	public static Screen getCurrentScreen() {
		return (Screen) frame.getContentPane().getComponent(0);
	}

	public static void setScreen(Screen screen) {
		/*
		 * Reset SpaceInvadersGame JPanel
		 */
		// Remove Key Listeners
		for (KeyListener k : frame.getContentPane().getKeyListeners()) {
			frame.getContentPane().removeKeyListener(k);
		}
		// Remove current actionlisteners
		if (!timer.getActionListeners().equals(null)) {
			for (ActionListener al : timer.getActionListeners()) {
				timer.removeActionListener(al);
			}
		}
		// Stop timer
		if (timer.isRunning()) {
			log("timer isRunning, so stopping in order to reset");
			timer.stop();
		}
		frame.getContentPane().removeAll();

		/*
		 * Add Screen stuff to SpaceInvadersGame JPanel
		 */

		// Set the screen
		frame.getContentPane().add(screen);
		log("Added screen: " + screen.toString());
		frame.addKeyListener(screen);
		log("Added keylistener: " + screen.toString());
		// Add screen as ActionListener
		timer.addActionListener(screen);
		log("Added " + screen.toString() + " as an actionlistener");
		timer.start();
		log("Started timer, timer isRunning: " + timer.isRunning());

		// Revalidating
		frame.getContentPane().revalidate();
		log("Revalidated!");
	}

	/*
	 * Convenient logging message
	 */
	public static void log(String str) {
		System.out.println(str);
	}

	public static void endProgram() {
		System.exit(0);
	}

}
