package com.me.teedee;



/**
 * A most simple enemy unit which implement the interface IEnenmy. Is abstract.
 * @author Fridgeridge
 *
 */
public abstract class AbstractEnemy {
	/**
	 * The health of the enemy unit
	 */
	private Lives lives;
	
	/**
	 * The speed of the enemy unit
	 */
	private float speed;
	private float xSpeed;
	private float ySpeed;
	
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
	private float stepsTraveled = 0f;
	
	
	
	// Path checkpoint index
	private int i = 0;
	
	private boolean reachedEnd = false;
	
	/**
	 * Constructs a new enemy unit. 
	 * @param p The path the enemy unit will go. Is needed.
	 */	
	public AbstractEnemy(Path p) {
		
		this(p,2.0f, new Lives(),new Reward(100), new Status(), new Position());
	}
	
	/**
	 * Constructs a new enemy unit
	 * @param t The path the enemy unit will go. Is needed.
	 * @param sp The speed of the enemy unit.
	 * @param l The health of the enemy unit.
	 * @param r The reward of the enemy unit.
	 * @param s The status effect of the enemy unit.
	 */
	public AbstractEnemy(Path p, float sp, Lives l, Reward r, Status s, Position pos) {
		this.path = p;
		
		sp = (sp < 0 ? 1 : sp);//Checks if the speed is negative. If so sets the new speed to 1.
		this.speed = sp;
		
		this.lives = l;
		
		this.reward = r;
		
		this.setStatusEffect(s);
		
		xSpeed = speed;
		
		//this.setPosition(path.next());
		this.setPosition(path.getPositions().get(0));
		
		this.nextCheckPoint = path.getPositions().get(0);
	}
	/**
	 * Constructor utilising an enemy to construct a new enemy
	 * @param a The enemy unit to be used for construction
	 */
	public AbstractEnemy(AbstractEnemy a) {
		this(a.path,a.speed,a.lives,a.reward,a.status, a.position);
	}
	
	
	public void takeDamage(int damage) {
		takeDamage(damage,this.getStatusEffect());
	}

	
	public boolean takeDamage(int damage, Status s) {
		// TODO Add even more logic!
		this.setStatusEffect(s);
		isAlive = this.lives.lowerLives(damage);
		return isAlive;
	}

	public void move() {
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
				
				if(Math.abs(dx) > 0.1f) {
					if(dx > 0) {
						xSpeed = -speed;
					} else {
						xSpeed = speed;
					}
				}
				if(Math.abs(dy) > 0.1f) {
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
			position.setxCoordinate(position.getX()+xSpeed);
			position.setyCoordinate(position.getY()+ySpeed);
		}
		//return reachedEnd;
	}
	
	private boolean reachedCheckpoint(Position p) {
		float dx = Math.abs(this.position.getX()-p.getX());
		float dy = Math.abs(this.position.getY()-p.getY());
		return 0.1f > dx && 0.1f > dy;
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
		return this.stepsTraveled;
	}

	public boolean reachedEnd() {
		return reachedEnd;
	}

	public Lives getLives() {
		return lives;
	}
	
	
}
