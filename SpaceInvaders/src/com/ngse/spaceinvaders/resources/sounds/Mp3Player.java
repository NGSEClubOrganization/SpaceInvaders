package com.ngse.spaceinvaders.resources.sounds;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.concurrent.Executors;

import com.ngse.spaceinvaders.SpaceInvadersGame;

import javazoom.jl.player.Player;

public class Mp3Player {

	private static String filename;
	private static Player player;

	public static void play(String inputfilename) {
		filename = inputfilename;

		SpaceInvadersGame.threadPool.submit(new Runnable() {

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
		});

	}
}