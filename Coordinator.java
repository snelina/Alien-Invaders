package jrJava.alienInvaderTWO;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import resources.DrawingBoard;
import resources.SoundPlayer;
import resources.Timer;


public class Coordinator {

	public static final String RESOURCE_PATH = "jrJava/alienInvader/imagesAndSounds/";
	
	public static final int SCREEN_WIDTH = 900;
	public static final int GAME_AREA_WIDTH = SCREEN_WIDTH - ScoreBoard.SCORE_BOARD_WIDTH;
	public static final int SCREEN_HEIGHT = 750;
	public static final int PAUSE_TIME = 30;
	
	public static boolean gameOver;
	
	
	
	public static void main(String[] args) {
		
		DrawingBoard board = new DrawingBoard(SCREEN_WIDTH, SCREEN_HEIGHT);
		Timer timer = new Timer();
		
		Ship ship = new Ship(300, 610);
		board.addMouseListener(ship);
		board.addMouseMotionListener(ship);
		board.getJFrame().addKeyListener(ship);
		
		board.setBackgroundImage(RESOURCE_PATH + "bgWithScore.png"); 
		SoundPlayer bgSound = new SoundPlayer();
		bgSound.load(RESOURCE_PATH + "bgMusic.wav");
		bgSound.playLoop();
		
		
		while(!gameOver){
			
			board.clear();
		
			AlienManager.move(ship);
			AlienManager.draw(board);
			
			MissileManager.move(ship);
			MissileManager.draw(board);
			
			TorpedoManager.move();
			TorpedoManager.draw(board);
			
			ship.draw(board);
			
			ScoreBoard.update();
			ScoreBoard.draw(board);
			// Aliens.draw(board);
			// Missiles.draw(board);
			// Torpedos.draw(board);
			
			board.repaint();
			timer.pause(PAUSE_TIME);
			
		}
		
		bgSound.stop();
		Image gameOverImage = new ImageIcon(RESOURCE_PATH+"gameOver.png").getImage();
		Graphics2D canvas = board.getCanvas();
		canvas.drawImage(gameOverImage, 150, 200, board);
		board.repaint();
		
	}

}








