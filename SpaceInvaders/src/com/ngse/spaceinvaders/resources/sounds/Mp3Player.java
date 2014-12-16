package com.ngse.spaceinvaders.resources.sounds;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import com.ngse.spaceinvaders.SpaceInvadersGame;

import javazoom.jl.player.Player;

public class Mp3Player {

	private static String filename;
	private static Player player;

	public static void play(String inputfilename) {
		filename = inputfilename;
		System.out.println("Trying to play laser");

		SpaceInvadersGame.threadPool
				.execute(new RunnablePlay(player, filename));

	}
}

class RunnablePlay implements Runnable {

	private Player player;
	private String filename;

	public RunnablePlay(Player player, String filename) {
		this.player = player;
		this.filename = filename;
	}

	@Override
	public void run() {
		try {
			BufferedInputStream buffer = new BufferedInputStream(
					new FileInputStream(
							"src//com//ngse//spaceinvaders//resources//sounds//"
									+ filename + ".mp3"));
			player = new Player(buffer);
			player.play();
		} catch (Exception e) {

			System.out.println(e);
		}
	}

}
