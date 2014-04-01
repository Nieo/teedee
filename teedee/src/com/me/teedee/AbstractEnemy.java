package com.me.teedee;

import java.util.NoSuchElementException;

/**
 * A most simple enemy unit which implement the interface IEnenmy. Is abstract.
 * @author Fridgeridge
 *
 */
public abstract class AbstractEnemy{
	
	
	/**
	 * The health of the enemy unit
	 */
	private Lives lives;
	
	/**
	 * The speed of the enemy unit
	 */
	private Double speed;
	
	/**
	 * The reward that the enemy gives to the player when it dies
	 */
	private Reward reward;
	/**
	 * The path the enemy unit will go
	 */
	private Path path;
	
	/**
	 * The status effect given to an enemy
	 */
	private Status status;
	
	
	/**
	 * The current position of the enemy unit
	 */
	private Position position;
	
	/**
	 * 
	 */
	private boolean isEnemyAlive = true;
	
	/**
	 * Constructs a new enemy unit. 
	 * @param p The path the enemy unit will go. Is needed.
	 */
	public AbstractEnemy(Path p){
		
		this(p,1.0, new Lives(),new Reward(), new Status(), new Position());
	}
	/**
	 * Constructs a new enemy unit
	 * @param p The path the enemy unit will go. Is needed.
	 * @param sp The speed of the enemy unit.
	 * @param l The health of the enemy unit.
	 * @param r The reward of the enemy unit.
	 * @param s The status effect of the enemy unit.
	 */
	public AbstractEnemy(Path p, double sp, Lives l, Reward r, Status s, Position pos){
		this.path = p;
		
		sp=(sp<0?sp:1);//Checks if the speed is negative. If so sets the new speed to 1.
		this.speed = sp;
		
		this.lives = l;
		
		this.reward = r;
		
		this.setStatusEffect(s);
		
		this.setPosition(pos);
	}
	/**
	 * Constructor utilising an enemy to construct a new enemy
	 * @param a The enemy unit to be used for construction
	 */
	public AbstractEnemy(AbstractEnemy a){
		this(a.path,a.speed,a.lives,a.reward,a.status, a.position);
	}
	
	
	
	
	
	public void takeDamage(int damage) {
		takeDamage(damage,this.getStatusEffect());
	}

	
	public void takeDamage(int damage, Status s) {
		// TODO Add even more logic!
		this.setStatusEffect(s);
		isEnemyAlive=this.lives.lowerLives(damage);
	}

	
	public void move() {
		
		
		//TODO Not sure if this is a correct way of handling things, as of now it just fetch the new position
		try{
		this.setPosition((this.path.next()));
		}catch(NoSuchElementException e){
		
		}
	}

	
	public void die() {
		// TODO Auto-generated method stub

	}

	
	public boolean hasNextEnd() {
		// TODO Auto-generated method stub
		return !path.hasNext();
	}

	
	public Reward getReward() {
		return reward;
	}

	
	public void setStatusEffect(Status s) {
		this.status= s;
	}

	
	public Status getStatusEffect() {
		return this.status;
	}
	
	public void setPosition(Position p){
		this.position=p;
	}

	public Position getPosition(){
		return this.position;
	}
	
}
