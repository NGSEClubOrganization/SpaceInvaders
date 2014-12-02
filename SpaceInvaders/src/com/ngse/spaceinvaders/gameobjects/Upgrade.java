package com.ngse.spaceinvaders.gameobjects;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.ngse.spaceinvaders.Config;

public class Upgrade extends GameObject {
	
	private double speed;
	private int l; //l = length
	private Random rand = new Random();
	
	public Upgrade(double x, double y, double dx, double dy, double speed,
			BufferedImage image) {
		
		
		super(x, y, dx, dy, image);
		
		this.x = x;
		this.y = y;
		
		this.speed = speed;
		
	}
	
	public Upgrade(BufferedImage image){
		
		
		super(0,0,0,0,image);
		
		x = rand.nextInt(  (  Config.FRAME_WIDTH   )  -20  ) +10;
		y = rand.nextInt(  (  Config.FRAME_HEIGHT  )  -20  ) +10;
		
		dx = (    (   rand.nextInt(2)  *2)   -3 );
        dy = (    (   rand.nextInt(2)  *2)   -3 );
		
		speed = 5;
		
	}
	
	

	public void moveUpdate() {
		x += dx * (speed);
        y += dy * (speed);
	}
	
	
	public Rectangle2D.Double getBox(){
        l = 5;
        return new Rectangle2D.Double(x-l, y-l, l, l);
    }
    
    public void checkBoundary(){
        if (    (x-l > 0 && x+l < Config.FRAME_WIDTH) == false  ){
            dx *= -1;
        }
        
        if (    (y-l > 0 && y+l < Config.FRAME_HEIGHT) == false  ){
            dy *= -1;
        }
    }
    
    public void checkPlayer(){
        //TODO
        //getHitbox()
        
    }
	
	
	
}
