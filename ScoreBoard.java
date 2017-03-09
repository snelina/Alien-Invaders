package jrJava.alienInvaderTWO;

import java.awt.Color;
import java.awt.Graphics2D;

import resources.DrawingBoard;


public class ScoreBoard {

	public static final int SCORE_BOARD_WIDTH = 100;
	public static final int DISPLAY_X = 822;
	public static final int DISPLAY_W = 50;
	public static final int DISPLAY_H = 200;
	public static final int SCORE_BOARD_Y = 110;
	public static final int TORPEDO_LEVEL_Y = 400;
	
	private static int score = 0;
	private static int numOfTorpedos = 20;
	private static long lastTimeTorpedoDepleted;
	
	
	public static void draw(DrawingBoard board){
		
		Graphics2D canvas = board.getCanvas();
		
		canvas.setColor(Color.WHITE);
		canvas.fillRect(DISPLAY_X, SCORE_BOARD_Y, DISPLAY_W, DISPLAY_H);
		int barHeight = score/10;
		if(barHeight>200) barHeight = 200;
		else if(barHeight<0) barHeight = 0;
		canvas.setColor(Color.RED);
		canvas.fillRect(DISPLAY_X, SCORE_BOARD_Y+DISPLAY_H-barHeight, DISPLAY_W, barHeight); 
		canvas.drawString(String.valueOf(score), DISPLAY_X+5, SCORE_BOARD_Y+15);
		
		canvas.setColor(Color.WHITE);
		canvas.fillRect(DISPLAY_X, TORPEDO_LEVEL_Y, DISPLAY_W, DISPLAY_H);
		barHeight = numOfTorpedos*2;
		if(barHeight>200) barHeight = 200;
		else if(barHeight<0) barHeight = 0;
		canvas.setColor(Color.BLUE);
		canvas.fillRect(DISPLAY_X, TORPEDO_LEVEL_Y+DISPLAY_H-barHeight, DISPLAY_W, barHeight); 
		canvas.drawString(String.valueOf(numOfTorpedos), DISPLAY_X+5, TORPEDO_LEVEL_Y+15);
	}
	
	
	public static boolean torpedoAvailable(){
		if(numOfTorpedos>0) return true;
		return false;
	}
	
	
	public static void update(){
		if(numOfTorpedos==0){
			long currentTime = System.currentTimeMillis();
			long lapse = currentTime - lastTimeTorpedoDepleted;
			if(lapse>5000){
				numOfTorpedos = 1;
				score -= 200;
				lastTimeTorpedoDepleted = Long.MAX_VALUE;
			}
			
		}
	}
	
	
	public static void consumeTorpedo(){
		if(numOfTorpedos>0){
			numOfTorpedos--;
			score--;
			if(numOfTorpedos==0){
				lastTimeTorpedoDepleted = System.currentTimeMillis();
			}
		}
	}
	
	
	public static void alienshotDown(){
		numOfTorpedos += 2;
		score += 10;
	}
	
	
	public static void missileShotDown(){
		score += 2;
	}
	
	
}




















