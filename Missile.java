package jrJava.alienInvaderTWO;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import resources.DrawingBoard;
import resources.SoundPlayer;


public class Missile {

	public static final int WIDTH_OF_EXPLOSION;
	public static final int WIDTH, HEIGHT;
	//public static final Color COLOR;
	private static Image image, explosionImage;
	
	private int x, y;  // center, bottom
	private final int vy;
	private boolean collidedWithShip;

	private static SoundPlayer sound;


	static {
		image = new ImageIcon(Coordinator.RESOURCE_PATH+"missile.png").getImage();
		explosionImage = new ImageIcon(Coordinator.RESOURCE_PATH+"explosion.png").getImage();
		
		WIDTH = image.getWidth(null);
		HEIGHT = image.getHeight(null);
		//COLOR = Color.blue;
		WIDTH_OF_EXPLOSION = explosionImage.getWidth(null);
		
		sound = new SoundPlayer();
		sound.load("imagesAndSounds/explosion.wav");
	}


	public Missile(int _x, int _y, int _vy){
		x = _x;
		y = _y;
		vy = _vy;
	}


	public boolean isHit(Torpedo torpedo){
		/*
		if(torpedo.getX()>=x-WIDTH/2-Torpedo.WIDTH/2 && 
		   torpedo.getX()<=x+WIDTH/2+Torpedo.WIDTH/2 && 
		   torpedo.getY()>=y-HEIGHT-Torpedo.HEIGHT && 
		   torpedo.getY()<=y) return true;

		return false;
		 */

		if(torpedo.getX()-Torpedo.WIDTH/2 > x+WIDTH/2 ||
				x-WIDTH/2 > torpedo.getX()+Torpedo.WIDTH/2 ||
				torpedo.getY() > y  ||
				y-HEIGHT > torpedo.getY()+Torpedo.HEIGHT) return false;

		return true;

	}


	public int getX(){ return x; }
	public int getY(){ return y; }


	public void move(Ship ship){
		y = y + vy;

		if(ship.isHit(this)){
			sound.play();
			collidedWithShip = true;
		}
	}


	public void draw(DrawingBoard board){
		Graphics2D canvas = board.getCanvas();

		if(collidedWithShip){
			/*
			canvas.setColor(Color.orange);
			canvas.drawOval(x-WIDTH_OF_EXPLOSION/2, y-WIDTH_OF_EXPLOSION/2, 
					        WIDTH_OF_EXPLOSION, WIDTH_OF_EXPLOSION);
		   */
			canvas.drawImage(explosionImage, x-WIDTH_OF_EXPLOSION/2, y-WIDTH_OF_EXPLOSION/2, board);
			Coordinator.gameOver = true;
		}
		else {
			/*
			canvas.setColor(COLOR);
			canvas.drawRect(x-WIDTH/2, y-HEIGHT, WIDTH, HEIGHT);
			*/
			canvas.drawImage(image, x-WIDTH/2, y-HEIGHT, board);
		}
	}

}
















