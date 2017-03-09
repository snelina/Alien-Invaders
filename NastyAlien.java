package jrJava.alienInvaderTWO;



public class NastyAlien extends Alien {

	public NastyAlien(int _x, int _y, int _vx, int _vy){
		super(_x, _y, _vx, _vy);
	}
	
	
	public void move(Ship ship){
		
		if(Math.random()<0.02) {
			Missile missile1 = new Missile(x, y+Missile.HEIGHT, 2*vy);
			MissileManager.add(missile1);
			Missile missile2 = new Missile(x-WIDTH/4, y+Missile.HEIGHT, 2*vy);
			MissileManager.add(missile2);
			Missile missile3 = new Missile(x+WIDTH/4, y+Missile.HEIGHT, 2*vy);
			MissileManager.add(missile3);
		}

		//x = x + vx;
		x = (98*x + 2*ship.getX())/100;
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
	
	
}
