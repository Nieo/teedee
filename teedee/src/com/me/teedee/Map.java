package com.me.teedee;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.me.teedee.enemies.AbstractEnemy;
import com.me.teedee.towers.AbstractTower;

/**
@author Jacob Genander
 */
public class Map {

	private List<AbstractTower> towers = new ArrayList<AbstractTower>();
	private ArrayList<Wave> waves = new ArrayList<Wave>();
	private Path path; 
	private Player player;
	private ArrayList<AbstractEnemy> currentEnemies;
	private TiledMap tiledMap;

	//TODO temporary, this can probably be done in a different way
	private int waveIndex = 0;

	/**
	 * Constructs a map containing the given waves, path and player
	 * @param waves a list containing wave objects which are going to be used during the game
	 * @param path the path which enemies will travel on
	 * @param player the object representing the player of the game
	 */
	public Map(ArrayList<Wave> waves, Path path, Player player) {
		this.waves = waves;
		this.path = path;
		this.player = player;
		if(!this.waves.isEmpty()) {
			this.currentEnemies = this.waves.get(0).getEnemies();
			waveIndex++;
		}

		for(int i = 0; i < currentEnemies.size(); i++) {
			currentEnemies.get(i).setPosition(new Position(currentEnemies.get(i).getPosition().getX()-100*i,currentEnemies.get(i).getPosition().getY()));
		}
	}

	/**
	 * Constructs a map containing the given waves, a given TiledMap and player
	 * @param waves a list containing wave objects which are going to be used during the game
	 * @param mapPath the system path to the tmx map
	 * @param player the object representing the player of the game
	 */
	public Map(ArrayList<Wave> waves, String mapPath,Player player) {
		this.waves = waves;
		this.tiledMap = new TmxMapLoader().load(mapPath);
		this.player = player;
		//if(!this.waves.isEmpty()) {
		this.currentEnemies = this.waves.get(0).getEnemies();
		//}
	}

	/**
	 * Returns a list containing all the enemies in the current wave
	 * @return a list containing all the enemies in the current wave
	 */
	public List<AbstractEnemy> getEnemies() {
		return currentEnemies;
	}

	/**
	 * Returns a list of towers that are currently on the map
	 * @return a list of towers that are currently on the map
	 */
	public List<AbstractTower> getTowers() {
		return towers;
	}

	public int getWaveIndex() {
		return waveIndex;
	}

	/**
	 * Builds a tower on a position
	 * @param tower the tower to be built on the map
	 * @param position the position on the map which the tower will be built on
	 */
	public boolean buildTower(AbstractTower tower, Position position) {
		if(player.getMoneyInt() >= tower.getBuildPrice().getPrice() && canBuildHere(position)) {
			tower.setPosition(position);
			this.towers.add(tower);
			player.removeMoney(tower.getBuildPrice().getPrice());

			return true;
		}
		return false;
	}
	private boolean canBuildHere(Position p){
		for(AbstractTower t: towers){
			if(AbstractTower.distance(p, t.getPosition()) < 40 )
				return false;
		}
		List<Position> positions = path.getPositions();
		for(int i = 0; i < positions.size() - 1; i++){
			float x1 = positions.get(i).getX();
			float y1 = positions.get(i).getY();
			float x2 = positions.get(i+1).getX();
			float y2 = positions.get(i+1).getY();
			if(y1 == y2){
				if(x1 > x2){
					float tmp = x1;
					x1 = x2;
					x2 = tmp;
				}
				if(p.getX() > x1-60 && p.getX() < x2+60 && Math.abs(p.getY()-y1+30)< 30)
					return false;
			}
			if(x1 == x2){
				if(y1 > y2){
					float tmp = y1;
					y1 = y2;
					y2 = tmp;
				}
				if(p.getY() > y1-60 && p.getY() < y2+60 && Math.abs(p.getX()-x1+30) < 30)
					return false;
			}
		}
		
		
		return true;
	}
	
	public void sellTower(int index) {
		player.addMoney((int) (towers.get(index).getValue()*0.8));
		towers.remove(index);
	}

	/**
	 * Returns the map's path
	 * @return Path
	 */
	public Path getPath() {
		return path;
	}

	public TiledMap getTiledMap() {
		return this.tiledMap;
	}

	/**
	 * Returns the game's player
	 * @return Player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Replaces the game's player
	 * @param player the player who will be playing the game
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
	public boolean upgradeTower(AbstractTower t){
		if(t.getUpgradePrice().getPrice() <= player.getMoneyInt()){
			if(t.upgrade()){
				player.removeMoney(t.getBuildPrice().getPrice());
				return true;
			}
		}
		return false;
	}
	

	/**
	 * Setting the current enemies to the next wave's enemies
	 */
	public void nextWave() {
		if(currentEnemies.isEmpty()) {
			currentEnemies = waves.get(waveIndex).getEnemies();
			System.out.println(currentEnemies.size()+"");
			for(int i = 0; i < currentEnemies.size(); i++) {
				currentEnemies.get(i).setPosition(new Position(currentEnemies.get(i).getPosition().getX()-100*i,currentEnemies.get(i).getPosition().getY()));
			}

			for(int i = 0; i < towers.size(); i++) {
				towers.get(i).setEnemies(currentEnemies);
			}

			waveIndex++;
		}
	}

	public void updateEnemiesPositions(float delta) {
		for(int i = 0; i < currentEnemies.size(); i++) {
			currentEnemies.get(i).move(delta);
		}
	}

	public void towersShoot(float delta) {
		for(AbstractTower tower : towers) {
			tower.shoot(delta);
		}
	}

	public void removeDeadEnemies() {
		if(!currentEnemies.isEmpty()) {
			for(int i = 0; i < currentEnemies.size(); i++) {
				if(!currentEnemies.get(i).isAlive() || currentEnemies.get(i).reachedEnd()) {
					if(!currentEnemies.get(i).reachedEnd()) {
						player.addMoney(currentEnemies.get(i).getEnemyReward().getReward());
					}
					currentEnemies.remove(i);
				}
			}
		}
	}

	public void update(float delta) {
		playerTakesLife();
		this.removeDeadEnemies(); //Must be done first, since the EnemyViews must have a reference to the enemy for deletion
		this.updateEnemiesPositions(delta);
		this.towersShoot(delta);
		this.updateEnemiesStatuses(delta);
	}
	
	public void updateEnemiesStatuses(float delta){
		for(int i = 0; i < currentEnemies.size();i++){
			Iterator<Status> statusMapIterator = currentEnemies.get(i).getStatusMap().values().iterator();
			while(statusMapIterator.hasNext()){
				Status tempStatus = statusMapIterator.next();
				tempStatus.reduceTimeLeft(delta); //TODO UPDATE_SPEED should not lie here, so how to solve?
				//Remove the status if it has influenced the enemy enough time
				if(tempStatus.getTimeLeft() <= 0){
					statusMapIterator.remove();
				}
			}
		}
	}

	private void playerTakesLife() {			//TODO please change name
		for(int i = 0; i < currentEnemies.size(); i++) {
			if(currentEnemies.get(i).reachedEnd()) {
				player.takeDamage(1); 				// TODO Maybe the player should loose different amount of lives
			}
		}
	}

}
