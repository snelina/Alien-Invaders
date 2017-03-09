package jrJava.alienInvaderTWO;

import java.util.ArrayList;

import resources.DrawingBoard;


public class MissileManager {

	
	private static ArrayList<Missile> missiles = new ArrayList<Missile>();
	
	
	public static void move(Ship ship){
		for(int i=missiles.size()-1; i>=0; i--){
			Missile each = missiles.get(i);
			each.move(ship);
			
			// if gets out of screen , remove it.
			if(each.getY()>0.95*Coordinator.SCREEN_HEIGHT){
				missiles.remove(i);
			}
			
			// if hits the target, remove it.
		}
	}
	
	
	public static void draw(DrawingBoard board){
		for(int i=0; i<missiles.size(); i++){
			Missile each = missiles.get(i);
			each.draw(board);
		}
	}
	

	public static void add(Missile missile){
		missiles.add(missile);
	}
	
	
	public static boolean isHit(Torpedo torpedo){
		
		for(int i=missiles.size()-1; i>=0 ; i--){
			Missile each = missiles.get(i);
			if(each.isHit(torpedo)){
				missiles.remove(i);
				return true;
			}
		}
		
		return false;
	}
	
}





















