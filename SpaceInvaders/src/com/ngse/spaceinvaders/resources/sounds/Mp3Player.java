package com.ngse.spaceinvaders.resources.sounds;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Mp3Player {

	private static String filename;
	private static Player player;

	public Mp3Player() {

	}

	public static void play(String inputfilename) {
		filename = inputfilename;
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