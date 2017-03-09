package jrJava.alienInvaderTWO;

import java.util.ArrayList;

import resources.DrawingBoard;


public class TorpedoManager {

	private static ArrayList<Torpedo> torpedos = new ArrayList<Torpedo>();
	//private static ArrayList<Torpedo> torpedos = new ArrayList<Torpedo>();
	
	public static void draw(DrawingBoard board){
		for(int i=0; i<torpedos.size(); i++){
			Torpedo each = torpedos.get(i);
			each.draw(board);
		}
	}
	
	
	
	public static void move(){
		for(int i=torpedos.size()-1; i>=0; i--){
			Torpedo each = torpedos.get(i);
			each.move();
			
			if(each.getY()<30){
				torpedos.remove(i);
				// i--;
			}
		}
	}
	
	
	public static void add(Torpedo torpedo){
		torpedos.add(torpedo);
	}
	
	
	public static void remove(Torpedo torpedo){
		torpedos.remove(torpedo);
	}
	
	
}











