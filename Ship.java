package jrJava.alienInvaderTWO;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import resources.DrawingBoard;
import resources.SoundPlayer;


public class Ship implements MouseListener, MouseMotionListener, KeyListener  {

	private int x, y; // center, top
	private final int width = 60;
	private final int heigth = 60;
	private final int topWidth = 4;
	private final int middleWidth = 20;
	private final int eachHeight = 20;
	//private final Color color = Color.red;
	private Image image;
	
	
	private int lastX;

	
	private SoundPlayer firingSound;
	

	public Ship(int _x, int _y){
		x = _x;
		y = _y;
		
		//ImageIcon icon = new ImageIcon(Coordinator.RESOURCE_PATH+"ship.png");
		//image = icon.getImage();
		
		image = new ImageIcon(Coordinator.RESOURCE_PATH+"ship.png").getImage();
		
		
		firingSound = new SoundPlayer();
		firingSound.load("imagesAndSounds/laser.wav");
	}

	
	public int getX(){ return x; }
	
	
	public boolean isHit(Missile missile){
		
		int mx = missile.getX();
		int my = missile.getY();
		
		/*
		boolean topRecHit = mx>=x-topWidth/2 && mx<=x+topWidth/2 && my>=y && my<=y+eachHeight;
		if(topRecHit) return true;
		
		boolean midRecHit = mx>=x-middleWidth/2 && mx<=x+middleWidth/2 && my>=y+eachHeight && my<=y+2*eachHeight;
		if(midRecHit) return true;
		
		boolean botRecHit = mx>=x-width/2 && mx<=x+width/2 && my>=y+2*eachHeight && my<=y+3*eachHeight;
		if(botRecHit) return true;
		
		return false;
		*/
		
		if( (mx>=x-topWidth/2 && mx<=x+topWidth/2 && my>=y && my<=y+eachHeight) ||
		    (mx>=x-middleWidth/2 && mx<=x+middleWidth/2 && my>=y+eachHeight && my<=y+2*eachHeight) ||
		    (mx>=x-width/2 && mx<=x+width/2 && my>=y+2*eachHeight && my<=y+3*eachHeight) ) return true;
		
		return false;
		
	}
	
	
	public boolean isHit(Alien alien){
		
		int ax = alien.getX();
		int ay = alien.getY();
		int aw = alien.WIDTH;
		int ah = alien.HEIGHT;
		
		/*
		if( (ax >= x-topWidth/2-aw/2 && ax <= x+topWidth/2+aw/2 && ay >= y && ay <= y+eachHeight+ah) ||
		    (ax >= x-middleWidth/2-aw/2 && ax <= x+middleWidth/2+aw/2 && ay >= y+eachHeight && ay <= y+2*eachHeight+ah) ||
		    (ax >= x-width/2-aw/2 && ax <= x+width/2+aw/2 && ay >= y+2*eachHeight && ay <= y+3*eachHeight+ah) ){
			return true;
		}
		
		return false;
		*/
		
		if( (ax < x-topWidth/2-aw/2 || ax > x+topWidth/2+aw/2 || ay < y || ay > y+eachHeight+ah) &&
   		    (ax < x-middleWidth/2-aw/2 || ax > x+middleWidth/2+aw/2 || ay < y+eachHeight || ay > y+2*eachHeight+ah) &&
		    (ax < x-width/2-aw/2 || ax > x+width/2+aw/2 || ay < y+2*eachHeight || ay > y+3*eachHeight+ah) ){
			return false;
		}
		
		return true;
	}
	
		
	

	public void draw(DrawingBoard board){
		Graphics2D canvas = board.getCanvas();
		/*
		canvas.setColor(color);
		canvas.drawRect(x-topWidth/2, y, topWidth, eachHeight);
		canvas.drawRect(x-middleWidth/2, y+eachHeight, middleWidth, eachHeight);
		canvas.drawRect(x-width/2, y+2*eachHeight, width, eachHeight);
		*/
		canvas.drawImage(image, x-width/2, y, board);
	}


	public void mousePressed(MouseEvent e){ 
		lastX = e.getX();
	}


	public void mouseDragged(MouseEvent e){ 
		int mouseX = e.getX();
		int diff = mouseX - lastX;
		x = x + diff;

		if(x<width/2){
			x = width/2;
		}
		else if(x>Coordinator.GAME_AREA_WIDTH-width/2){
			x = Coordinator.GAME_AREA_WIDTH-width/2;
		}

		lastX = mouseX;
	}



	public void mouseMoved(MouseEvent e){ }


	public void mouseReleased(MouseEvent e){ }
	public void mouseClicked(MouseEvent e){ }
	public void mouseEntered(MouseEvent e){ }
	public void mouseExited(MouseEvent e){ }


	public void keyPressed(KeyEvent e){ 

		if(e.getKeyCode()==KeyEvent.VK_SPACE){
			
			if(!ScoreBoard.torpedoAvailable()) return;
			ScoreBoard.consumeTorpedo();
			
			Torpedo torpedo = new Torpedo(x, y-Torpedo.HEIGHT, -5);
			TorpedoManager.add(torpedo);
			firingSound.play();
		}
	}


	public void keyReleased(KeyEvent e){ }
	public void keyTyped(KeyEvent e){ }


}
















