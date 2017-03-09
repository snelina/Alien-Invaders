package jrJava.alienInvaderTWO;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import resources.DrawingBoard;
import resources.SoundPlayer;


public class Torpedo {

	public static final int WIDTH_OF_EXPLOSION, WIDTH_OF_SMALL_EXPLOSION;
	public static final int WIDTH;
	public static final int HEIGHT;
	//public static final Color COLOR;
	private static Image image, explosionImage, smallExplosionImage;
	
	
	private int x, y;  // center, top
	private int ySpeed;
	private boolean collidedWithAlien;
	private boolean collidedWithMissile;
	private static SoundPlayer sound;
	

	static {
		
		image = new ImageIcon(Coordinator.RESOURCE_PATH+"torpedo.png").getImage();
		explosionImage = new ImageIcon(Coordinator.RESOURCE_PATH+"explosion.png").getImage();
		smallExplosionImage = new ImageIcon(Coordinator.RESOURCE_PATH+"explosion_small.png").getImage();
		
		WIDTH = image.getWidth(null);
		HEIGHT = image.getHeight(null);
		//COLOR = Color.blue;
		WIDTH_OF_EXPLOSION = explosionImage.getWidth(null);
		WIDTH_OF_SMALL_EXPLOSION = smallExplosionImage.getWidth(null);
		
		sound = new SoundPlayer();
		sound.load("imagesAndSounds/explosion.wav"); 
	}


	public Torpedo(int _x, int _y, int _ySpeed){
		x = _x;
		y = _y;
		ySpeed = _ySpeed;
	}


	public int getX(){ return x; }
	public int getY(){ return y; }


	public void move(){

		y = y + ySpeed;

		if(AlienManager.isHit(this)){
			ScoreBoard.alienshotDown();
			collidedWithAlien = true;
			sound.play();
		}
		
		if(MissileManager.isHit(this)){
			collidedWithMissile = true;
			ScoreBoard.missileShotDown();
			sound.play();
		}
	}


	public void draw(DrawingBoard board){
		Graphics2D canvas = board.getCanvas();

		if(collidedWithAlien){
			/*
			canvas.setColor(Color.yellow);
			canvas.fillOval(x-WIDTH_OF_EXPLOSION/2, y-WIDTH_OF_EXPLOSION/2, WIDTH_OF_EXPLOSION, WIDTH_OF_EXPLOSION);
			*/
			canvas.drawImage(explosionImage, x-WIDTH_OF_EXPLOSION/2, y-WIDTH_OF_EXPLOSION/2, board);
			TorpedoManager.remove(this);
		}
		else if(collidedWithMissile){
			/*
			canvas.setColor(Color.red);
			canvas.fillOval(x-WIDTH_OF_EXPLOSION/4, y-WIDTH_OF_EXPLOSION/4, WIDTH_OF_EXPLOSION/2, WIDTH_OF_EXPLOSION/2);
			*/
			canvas.drawImage(smallExplosionImage, x-WIDTH_OF_SMALL_EXPLOSION/2, y-WIDTH_OF_SMALL_EXPLOSION/2, board);
			TorpedoManager.remove(this);
		}
		else {
			/*
			canvas.setColor(COLOR);
			canvas.drawRect(x-WIDTH/2, y, WIDTH, HEIGHT);
			*/
			canvas.drawImage(image, x-WIDTH/2, y, board);
		}
	}
}













