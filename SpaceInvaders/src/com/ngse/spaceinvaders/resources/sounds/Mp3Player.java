package com.ngse.spaceinvaders.resources.sounds;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

//import javazoom.jl.player.Player;

public class Mp3Player {

	private String filename;
	private Player player;

	public Mp3Player() {
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void play() {
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
	
	public static void main(String[] args) {
		Mp3Player player = new Mp3Player();
		player.setFilename("lazer");
		player.play();
	}

}