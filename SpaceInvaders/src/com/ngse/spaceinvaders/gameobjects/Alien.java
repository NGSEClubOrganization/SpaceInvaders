package com.ngse.spaceinvaders.gameobjects;

import com.ngse.spaceinvaders.resources.images.BufferedImageResource;

public class Alien extends GameObject {

	public Alien(double x, double y, double dX, double dY, int alienImageIndex) {
		super(x, y, dX, dY, BufferedImageResource.Aliens[alienImageIndex]);
		// TODO
	}

	public void moveUpdate() {
		y += dy;
		x += dx;
	}

	public void shoot() {
		// TODO
	}

}
