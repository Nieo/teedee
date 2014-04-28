package com.me.teedee;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

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
		if(player.getMoneyInt() >= tower.getPrice().getPrice()) {
			tower.setPosition(position);
			this.towers.add(tower);
			player.removeMoney(tower.getPrice().getPrice());

			return true;
		}
		return false;
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

	public void updateEnemiesPositions() {
		for(int i = 0; i < currentEnemies.size(); i++) {
			currentEnemies.get(i).move();
		}
	}

	public void towersShoot() {
		for(AbstractTower tower : towers) {
			tower.shoot();
		}
	}

	public void removeDeadEnemies() {
		if(!currentEnemies.isEmpty()) {
			for(int i = 0; i < currentEnemies.size(); i++) {
				if(!currentEnemies.get(i).isAlive() || currentEnemies.get(i).reachedEnd()) {
					if(!currentEnemies.get(i).reachedEnd()) {
						player.addMoney(currentEnemies.get(i).getReward().getReward());
					}
					currentEnemies.remove(i);
				}
			}
		}
	}

	public void update() {
		playerTakesLife();
		this.removeDeadEnemies(); //Must be done first, since the EnemyViews must have a reference to the enemy for deletion
		this.updateEnemiesPositions();
		this.towersShoot();
	}

	private void playerTakesLife() {			//TODO please change name
		for(int i = 0; i < currentEnemies.size(); i++) {
			if(currentEnemies.get(i).reachedEnd()) {
				player.takeDamage(1); 				// TODO Maybe the player should loose different amount of lives
			}
		}
	}

}
