package com.me.teedee.enemies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.me.teedee.Lives;
import com.me.teedee.Path;
import com.me.teedee.Position;
import com.me.teedee.Reward;
import com.me.teedee.Status;
import com.me.teedee.towers.AbstractTower;



/**
 * A most simple enemy unit which implement the interface IEnenmy. Is abstract.
 * @author Fridgeridge
 *
 */
public abstract class AbstractEnemy implements Comparable<AbstractEnemy>{
	/**
	 * The health of the enemy unit
	 */
	private Lives lives;
	
	/**
	 * The speed of the enemy unit in points per second
	 */
	private float speed;
	private float defaultSpeed;
	
	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public void setLives(Lives lives) {
		this.lives = lives;
	}

	private float xSpeed;
	private float ySpeed;
	
	/**
	 * The reward that the enemy gives to the player when it dies
	 */
	private Reward reward;
	
	public void setReward(Reward reward) {
		this.reward = reward;
	}

	/**
	 * The overall status effect of the statuses in the enemys status list.
	 */
	private Status status; //TODO Will probably not be needed, since all statuses are stored in a list and the overall status is calculated
	
//	/**
//	 * A list containing all the enemy's applied status effects
//	 */
//	private ArrayList<Status> statusList;
	
	private HashMap<AbstractTower,Status> statusMap = new HashMap<AbstractTower, Status>();
		
	/**
	 * The current position of the enemy unit
	 */
	private Position position;
	
	/**
	 * Boolean to check if the enemy is dead or alive
	 */
	private boolean isAlive = true;

	/**
	 * The path the enemy will take
	 */
	private Path path;
	private Position nextCheckPoint;
	
	/**
	 * Vector that describes the direction to travel for the enemy unit
	 */
	
	/**
	 * Describes the distance traveled of the enemy unit
	 */
	private float distanceTraveled = 0f;
	
	
	
	// Path checkpoint index
	private int i = 0;
	
	private boolean reachedEnd = false;
	
	/**
	 * Constructs a new enemy unit. 
	 * @param p The path the enemy unit will go. Is needed.
	 */	
	public AbstractEnemy(Path p) {
		
		this(p,120f, new Lives(),new Reward(100), new Position());
	}
	
	/**
	 * Constructs a new enemy unit
	 * @param t The path the enemy unit will go. Is needed.
	 * @param sp The speed of the enemy unit.
	 * @param l The health of the enemy unit.
	 * @param r The reward of the enemy unit.
	 * @param s The status effect of the enemy unit.
	 */
	public AbstractEnemy(Path p, float sp, Lives l, Reward r, Position pos) {
		this.path = p;
		
		sp = (sp < 0 ? 1 : sp);//Checks if the speed is negative. If so sets the new speed to 1.
		this.speed = sp;
		this.defaultSpeed = sp;
		
		this.lives = l;
		
		this.reward = r;
		xSpeed = speed;
		this.statusMap = new HashMap<AbstractTower,Status>();
		this.setPosition(path.getPositions().get(0));
		this.nextCheckPoint = path.getPositions().get(0);
	}
	/**
	 * Constructor utilising an enemy to construct a new enemy
	 * @param a The enemy unit to be used for construction
	 */
	public AbstractEnemy(AbstractEnemy a) {
		this(a.path,a.speed,a.lives,a.reward, a.position);
	}
	
	public boolean takeDamage(int damage, Status s) {
		// TODO Add even more logic!
		isAlive = this.lives.lowerLives(damage);
		return isAlive;
	}
	
	public void addTowerStatus(AbstractTower tower,Status status){
		this.statusMap.put(tower, status);
	}
	
	public Status getOverallStatus(){
		float overallSpeedRatio = 1;
		float overallDamagePerSecond = 0;

		Iterator<Status> statusMapIterator = statusMap.values().iterator();
		while(statusMapIterator.hasNext()){
			Status tempStatus = statusMapIterator.next();
			overallSpeedRatio = overallSpeedRatio*tempStatus.getSpeedRatio();
			overallDamagePerSecond += tempStatus.getDamagePerSecond();
		}
		return new Status(overallSpeedRatio,overallDamagePerSecond,10f);
	}

	public void move(float delta) {
		reachedEnd = false;
		//xSpeed = 1;	//TODO debug
		if(reachedCheckpoint(nextCheckPoint)) {
			if(i+1 < path.getPositions().size()) {
				i++;
				nextCheckPoint = path.getPositions().get(i);
				xSpeed = 0;
				ySpeed = 0;
				
				float dx = position.getX()-nextCheckPoint.getX();
				float dy = position.getY()-nextCheckPoint.getY();
				
				if(Math.abs(dx) > 3f) {
					if(dx > 0) {
						xSpeed = -speed;
					} else {
						xSpeed = speed;
					}
				}
				if(Math.abs(dy) > 3f) {
					if(dy > 0) {
						ySpeed = -speed;
					} else {
						ySpeed = speed;
					}
				}
			} else {
				reachedEnd = true;
				//return reachedEnd;
			}
		}
		
		if(!reachedEnd) {
			position.setxCoordinate(position.getX()+xSpeed*(float)getOverallStatus().getSpeedRatio()*delta);
			position.setyCoordinate(position.getY()+ySpeed*(float)getOverallStatus().getSpeedRatio()*delta);
		}
		//return reachedEnd;
	}
	
	private boolean reachedCheckpoint(Position p) {
		float dx = Math.abs(this.position.getX()-p.getX());
		float dy = Math.abs(this.position.getY()-p.getY());
		return 3f > dx && 3f > dy;
	}

	public Reward getEnemyReward() {
		return reward;
	}

	
	public void setStatus(Status s) {
		this.status= s;
	}

	public HashMap<AbstractTower,Status> getStatusMap() {
		return this.statusMap;
	}
	
//	public Status getStatusEffect() {
//		return this.status;
//	}
	
	public void setPosition(Position p) {
		this.position=p;
	}

	public Position getPosition() {
		return this.position;
	}
	
	public boolean isAlive() {
		return this.isAlive;
	}
	
	public float getStepsTraveled() {
		return this.distanceTraveled;
	}

	public boolean reachedEnd() {
		return reachedEnd;
	}

	public Lives getLives() {
		return lives;
	}
	
	public int compareTo(AbstractEnemy ae){
		if(this.distanceTraveled < ae.distanceTraveled){
			return -1;
		}else if(this.distanceTraveled == ae.distanceTraveled){
			return 0;
		}else{
			return 1;
		}
	}
	
	
}
