package com.me.teedee;

import java.util.ArrayList;
import java.util.List;

import com.me.teedee.enemies.AbstractEnemy;
import com.me.teedee.towers.AbstractTower;

/**
 * Map handles the running Tower defense game
 *@author Jacob Genander
 */
public class Map {

	private List<AbstractTower> towers = new ArrayList<AbstractTower>();
	private ArrayList<Wave> waves = new ArrayList<Wave>();
	private Path path; 
	private Player player;
	private ArrayList<AbstractEnemy> currentEnemies = new ArrayList<AbstractEnemy>();
	private int waveIndex = 0;
	private boolean playerIsAlive;
	private boolean isRunning = true;

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
		playerIsAlive = true;
	}

	/**
	 * Returns a list of towers that are currently on the map
	 * @return a list of towers that are currently on the map
	 */
	public List<AbstractTower> getTowers() {
		return towers;
	}

	/**
	 * Returns a list containing all the enemies in the current wave
	 * @return a list containing all the enemies in the current wave
	 */
	public List<AbstractEnemy> getEnemies() {
		return currentEnemies;
	}

	public int getWaveIndex() {
		return waveIndex;
	}

	/**
	 * Returns the map's path
	 * @return Path
	 */
	public Path getPath() {
		return path;
	}

	/**
	 * Returns the game's player
	 * @return Player
	 */
	public Player getPlayer() {
		return player;
	}

	public boolean isPlayerAlive() {
		return playerIsAlive;
	}

	public boolean isRunning() {
		return isRunning;
	}

	/**
	 * Replaces the game's player
	 * @param player the player who will be playing the game
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	/**
	 * Setting the current enemies to the next wave's enemies
	 */
	public void nextWave() {
		if(this.waveEnded()) {
			currentEnemies = waves.get(waveIndex).getEnemies();
			for(int i = 0; i < currentEnemies.size(); i++) {
				currentEnemies.get(i).setPosition(new Position(currentEnemies.get(i).getPosition().getX()-100*i,currentEnemies.get(i).getPosition().getY()));
			}
			for(int i = 0; i < towers.size(); i++) {
				towers.get(i).setEnemies(currentEnemies);
			}
			waveIndex++;
		}
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
				if(p.getX() > x1-30 && p.getX() < x2+30 && (p.getY()-y1) > -60 && (p.getY()-y1) < 40)//Math.abs(p.getY()-(y1-11))< 35)
					return false;
			}
			if(x1 == x2){
				if(y1 > y2){
					float tmp = y1;
					y1 = y2;
					y2 = tmp;
				}
				if(p.getY() > y1-30 && p.getY() < y2+30 && (p.getX()-x1) > -65 && (p.getX()-x1) < 35)
					return false;
			}
		}
		return true;
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

	public void sellTower(int index) {
		player.addMoney( (towers.get(index).getSellValue()));
		towers.remove(index);
	}

	public void update(float delta) {
		if(!isRunning){
			return;
		}
		
		this.removeEnemies(); //Must be done first, since the EnemyViews must have a reference to the enemy for deletion
		this.updateEnemiesPositions(delta);
		this.towersShoot(delta);
		this.updateEnemiesStatuses(delta);
	
		if(player.getLives().getCurrentLives()<=0){
			playerIsAlive = false;
		}
	}
	
	public boolean waveEnded(){
		return currentEnemies.isEmpty();
	}

	private void towersShoot(float delta) {
		for(AbstractTower tower : towers) {
			tower.shoot(delta);
		}
	}

	private void removeEnemies() {
		if(!waveEnded()) {
			for(int i = 0; i < currentEnemies.size(); i++) {
				if(!currentEnemies.get(i).isAlive() || currentEnemies.get(i).reachedEnd()) {
					if(!currentEnemies.get(i).reachedEnd()) {
						player.addMoney(currentEnemies.get(i).getEnemyReward().getReward());
					} else {
						player.takeDamage(1);
					}
					currentEnemies.remove(i);
				}
			}
		}
	}

	private void updateEnemiesPositions(float delta) {
		for(int i = 0; i < currentEnemies.size(); i++) {
			currentEnemies.get(i).move(delta);
		}
	}

	public void updateEnemiesStatuses(float delta){
		for(AbstractEnemy ae: currentEnemies){
			ae.updateStatuses(delta);
		}
	}
}
