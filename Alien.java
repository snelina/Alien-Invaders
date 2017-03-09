package jrJava.alienInvaderTWO;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import resources.DrawingBoard;
import resources.SoundPlayer;


public class Alien {

	public static final int WIDTH_OF_EXPLOSION;
	public static final int WIDTH, HEIGHT;
	//public static final int EYE_RADIUS;

	protected static Image[] imageMenu1, imageMenu2;
	protected Image image1, image2;
	protected int imageIndex;
	protected static Image explosionImage;
	
	protected int x, y;
	protected int vx, vy;
	//private final Color bodyColor, eyeColor;
	protected boolean collidedWithShip;
	protected static SoundPlayer sound;


	static {
		
		imageMenu1 = new Image[3];
		imageMenu2 = new Image[3];
		
		imageMenu1[0] = new ImageIcon(Coordinator.RESOURCE_PATH + "blueAlien_1.png").getImage();
		imageMenu1[1] = new ImageIcon(Coordinator.RESOURCE_PATH + "greenAlien_1.png").getImage();
		imageMenu1[2] = new ImageIcon(Coordinator.RESOURCE_PATH + "orangeAlien_1.png").getImage();
		
		imageMenu2[0] = new ImageIcon(Coordinator.RESOURCE_PATH + "blueAlien_2.png").getImage();
		imageMenu2[1] = new ImageIcon(Coordinator.RESOURCE_PATH + "greenAlien_2.png").getImage();
		imageMenu2[2] = new ImageIcon(Coordinator.RESOURCE_PATH + "orangeAlien_2.png").getImage();
		
		
		WIDTH = imageMenu1[0].getWidth(null);
		HEIGHT = imageMenu1[0].getHeight(null);

		explosionImage = new ImageIcon(Coordinator.RESOURCE_PATH + "explosion.png").getImage();
		//EYE_RADIUS = 3;
		WIDTH_OF_EXPLOSION = explosionImage.getWidth(null);

		sound = new SoundPlayer();
		sound.load("imagesAndSounds/explosion.wav"); 
	}


	public Alien(int _x, int _y, int _vx, int _vy){  //, Color _bodyColor, Color _eyeColor){
		x = _x;
		y = _y;
		vx = _vx;
		vy = _vy;
		//bodyColor = _bodyColor;
		//eyeColor = _eyeColor;
		int random = (int) (Math.random()*imageMenu1.length);
		image1 = imageMenu1[random];
		image2 = imageMenu2[random];

	}


	public boolean isHit(Torpedo torpedo){

		if(torpedo.getX()>=x-WIDTH/2 && 
				torpedo.getX()<=x+WIDTH/2 && 
				torpedo.getY()>=y-HEIGHT && 
				torpedo.getY()<=y) return true;

		return false;
	}


	public int getX(){ return x; }
	public int getY(){ return y; }




	public void move(Ship ship){

		if(Math.random()<0.02) {
			Missile missile = new Missile(x, y+Missile.HEIGHT, 2*vy);
			MissileManager.add(missile);
		}

		x = x + vx;
		y = y + vy;

		if(x<WIDTH/2){
			x = WIDTH/2;
			vx = -vx;
		}
		else if(x>Coordinator.GAME_AREA_WIDTH-WIDTH/2){
			x = Coordinator.GAME_AREA_WIDTH-WIDTH/2;
			vx = -vx;
		}

		if(ship.isHit(this)){
			sound.play();
			collidedWithShip = true;
		}
	}


	public void draw(DrawingBoard board){
		Graphics2D canvas = board.getCanvas();

		if(collidedWithShip){
			//canvas.setColor(Color.green);
			//canvas.fillOval(x-WIDTH_OF_EXPLOSION/2, y-WIDTH_OF_EXPLOSION/2, WIDTH_OF_EXPLOSION, WIDTH_OF_EXPLOSION);
			canvas.drawImage(explosionImage, x-WIDTH_OF_EXPLOSION/2, y-WIDTH_OF_EXPLOSION/2, board);
			Coordinator.gameOver = true;
		}
		else {
			/*
			canvas.setColor(bodyColor);
			canvas.drawRect(x-WIDTH/2, y-HEIGHT, WIDTH, HEIGHT);

			canvas.setColor(eyeColor);
			canvas.fillOval(x-WIDTH/4-EYE_RADIUS, y-HEIGHT*3/4, 2*EYE_RADIUS, 2*EYE_RADIUS);
			canvas.fillOval(x+WIDTH/4-EYE_RADIUS, y-HEIGHT*3/4, 2*EYE_RADIUS, 2*EYE_RADIUS);
			 */

			imageIndex++;
			if(imageIndex>=16) imageIndex = 0;

			if(imageIndex<8){
				canvas.drawImage(image2, x-WIDTH/2, y-HEIGHT, board);
			}
			else {
				canvas.drawImage(image1, x-WIDTH/2, y-HEIGHT, board);
			}
		}
	}
}






























