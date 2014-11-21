package com.ngse.spaceinvaders.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import com.ngse.spaceinvaders.Config;
import com.ngse.spaceinvaders.SpaceInvadersGame;
import com.ngse.spaceinvaders.resources.images.BufferedImageResource;
import com.ngse.spaceinvaders.resources.sounds.Mp3Player;

@SuppressWarnings("serial")
public class StartScreen extends Screen {

	// Adjusters
	private int adjustY = -20;
	private int adjustYLineSpacing = 30;

	// Cursor object
	private Rectangle2D cursor = new Rectangle2D.Double();
	private int cursorPosition;

	// Possible cursor positions
	private double[] cursorPositionsY = new double[3];

	public StartScreen() {
		// initialize cursorPositions:
		for (int i = 0; i <= cursorPositionsY.length - 1; i++) {
			cursorPositionsY[i] = Config.FRAME_HEIGHT / 2
					+ (200 + i * adjustYLineSpacing) + adjustY
					- Config.CURSOR_HEIGHT;
		}

		// initialize size and position for cursor and the current position var:
		cursorPosition = 1;
		cursor.setRect(Config.FRAME_WIDTH / 2 - 100,
				cursorPositionsY[cursorPosition - 1], Config.CURSOR_WIDTH,
				Config.CURSOR_HEIGHT);

	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		// Draw the Image
		g2.drawImage(BufferedImageResource.StartScreen, null, 0, 0);

		// Draw the text
		g2.setColor(Color.WHITE);
		drawStringCenteredX("START", Config.FRAME_HEIGHT / 2
				+ (200 + 0 * adjustYLineSpacing) + adjustY, g2);
		drawStringCenteredX("OPTIONS", Config.FRAME_HEIGHT / 2
				+ (200 + 1 * adjustYLineSpacing + adjustY), g2);
		drawStringCenteredX("QUIT", Config.FRAME_HEIGHT / 2
				+ (200 + 2 * adjustYLineSpacing + adjustY), g2);

		// Draw the cursor
		drawCursor(g2);

	}

	private void drawStringCenteredX(String str, int y, Graphics2D g2) {
		g2.setFont(new Font("Verdana", Font.BOLD, 20));
		FontRenderContext frc = g2.getFontRenderContext();
		Font f = g2.getFont();
		g2.setFont(f);
		Rectangle2D b = f.getStringBounds(str, frc);

		g2.drawString(str, (int) (this.getWidth() / 2 - b.getWidth() / 2), y);
	}

	private void drawCursor(Graphics2D g2) {
		cursor.setRect(Config.FRAME_WIDTH / 2 - 100,
				cursorPositionsY[cursorPosition - 1], Config.CURSOR_WIDTH,
				Config.CURSOR_HEIGHT);

		g2.setColor(Color.RED);
		g2.fill(cursor);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			if (cursorPosition > 1)
				cursorPosition -= 1;
			break;
		case KeyEvent.VK_DOWN:
			if (cursorPosition < 3)
				cursorPosition += 1;
			break;
		case KeyEvent.VK_ENTER:
			selectMenuOption();
		default:
			break;
		}
		repaint();
	}

	private void selectMenuOption() {

		switch (cursorPosition) {
		case 1:
			// switch to gamescreen
			System.out.println("Trying to switch to gamescreen");
			SpaceInvadersGame.setScreen(new GameScreen());
			break;
		case 2:
			// switch to optionsscreen
			System.out.println("Trying to switch to optionsscreen");
			SpaceInvadersGame.setScreen(new OptionsScreen());
			break;
		case 3:
			// quit
			System.exit(0);
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		/*
		 * Mp3Player player = new Mp3Player();
		 * player.setFilename("lazer");player.play();
		 */
	}

}
