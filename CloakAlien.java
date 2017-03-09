package jrJava.alienInvaderTWO;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;

import resources.DrawingBoard;


public class CloakAlien extends Alien {

	protected int cloakBegin = 100;
	protected int cloakComplete = 350;
	protected int cloakStop = 550;
	
	
	public CloakAlien(int _x, int _y, int _vx, int _vy){
		super(_x, _y, _vx, _vy);
	}
	
	

	public void draw(DrawingBoard board){
		Graphics2D canvas = board.getCanvas();

		if(collidedWithShip){
			canvas.drawImage(explosionImage, x-WIDTH_OF_EXPLOSION/2, y-WIDTH_OF_EXPLOSION/2, board);
			Coordinator.gameOver = true;
		}
		else {
			
			Composite original = canvas.getComposite();
			
			float transparency = 1.0f - ((float)y-cloakBegin)/(cloakComplete-cloakBegin); 
			if(transparency>1.0) transparency = 1.0f;
			else if(transparency<0.0) transparency = 0.0f;
			
			if(y>=cloakStop) transparency = 1.0f;
			
			Composite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency);
			canvas.setComposite(composite);
			
			imageIndex++;
			if(imageIndex>=16) imageIndex = 0;

			if(imageIndex<8){
				canvas.drawImage(image2, x-WIDTH/2, y-HEIGHT, board);
			}
			else {
				canvas.drawImage(image1, x-WIDTH/2, y-HEIGHT, board);
			}
			
			canvas.setComposite(original);
		}
	}
	
}









