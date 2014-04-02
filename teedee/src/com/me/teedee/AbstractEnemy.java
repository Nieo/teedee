package com.me.teedee;

import java.util.NoSuchElementException;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

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
	private float speed;
	
	/**
	 * The reward that the enemy gives to the player when it dies
	 */
	private Reward reward;
	
	/**
	 * The status effect given to an enemy
	 */
	private Status status;
		
	/**
	 * The current position of the enemy unit
	 */
	private Position position;
	
	/**
	 * Boolean to check if the enemy is dead or alive
	 */
	private boolean isEnemyAlive = true;

	/**
	 * The path the enemy will take
	 */
	private TiledMapTileLayer tmtl;
	
	/**
	 * Vector that describes the direction to travel for the enemy unit
	 */
	private Vector2 vector;
	
	/**
	 * Describes the distance traveled of the enemy unit
	 */
	private int stepsTraveled=0;
	
	/**
	 * Constructs a new enemy unit. 
	 * @param p The path the enemy unit will go. Is needed.
	 */
	public AbstractEnemy(TiledMapTileLayer t){
		
		this(t,1.0f, new Lives(),new Reward(), new Status(), new Position());
	}
	/**
	 * Constructs a new enemy unit
	 * @param t The path the enemy unit will go. Is needed.
	 * @param sp The speed of the enemy unit.
	 * @param l The health of the enemy unit.
	 * @param r The reward of the enemy unit.
	 * @param s The status effect of the enemy unit.
	 */
	public AbstractEnemy(TiledMapTileLayer t, float sp, Lives l, Reward r, Status s, Position pos){
		this.tmtl = t;
		
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
		this(a.tmtl,a.speed,a.lives,a.reward,a.status, a.position);
	}
	
	
	
	//////Methods/////////////////////////////////////////////////77
	
	public void takeDamage(int damage) {
		takeDamage(damage,this.getStatusEffect());
	}

	
	public void takeDamage(int damage, Status s) {
		// TODO Add even more logic!
		this.setStatusEffect(s);
		isEnemyAlive=this.lives.lowerLives(damage);
	}

	public boolean changeDirection(float x, float y, String direction) {
		return this.tmtl.getCell((int) (x / tmtl.getTileWidth()), (int) (y / tmtl.getTileWidth()))
				.getTile().getProperties().containsKey(direction);
	}

	
	public void move(float delta) {
		
		boolean moveUp = false;
		boolean moveDown = false;
		boolean moveRight = false;
		boolean moveLeft = false;

		
		if(vector.x > speed) {
			vector.x = speed;
		} else if(vector.x < -speed) {
			vector.x = -speed;
		}

		position.setxCoordinate((position.getxCoordinate() + vector.x * delta));
		position.setyCoordinate((position.getyCoordinate() + vector.y * delta));
		
		stepsTraveled++;
		
		if(vector.x != 0) {
			moveUp = changeDirection(position.getxCoordinate(),position.getyCoordinate(), "up");

			if(!moveUp)
				moveDown = changeDirection(position.getxCoordinate(),position.getyCoordinate(), "down");
		}

		if(vector.y != 0) {
			moveRight = changeDirection(position.getxCoordinate(),position.getyCoordinate(), "right");

			if(!moveRight)
				moveLeft = changeDirection(position.getxCoordinate(),position.getyCoordinate(), "left");
		}


		if(moveUp) {
			vector.x = 0;
			vector.y = speed;
		} else if(moveDown) {
			vector.x = 0;
			vector.y = -speed;
		} else if(moveRight) {
			vector.x = speed;
			vector.y = 0;
		} else if(moveLeft) {
			vector.x = -speed;
			vector.y = 0;
		}
	}
	

	
	public void die() {
		this.isEnemyAlive=false;
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
	
	public boolean enemyIsAlive(){
		return this.isEnemyAlive;
	}
	
	public int getStepsTraveled(){
		return this.stepsTraveled;
	}
	
}
