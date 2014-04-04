package com.me.teedee;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Specifying the path positions
		List<Position> pathPositions = new ArrayList<Position>();
		pathPositions.add(new Position(0,50));
		pathPositions.add(new Position(200,50));
		pathPositions.add(new Position(200,500));
		pathPositions.add(new Position(700,500));
		pathPositions.add(new Position(700,100));
		
		//Creating the path
		Path path = new Path(pathPositions);
		
		//Creating the wave and adding enemies to it
		int[] enemies = {7};
		Wave wave0 = new Wave(path, enemies);
		
		//Adding the wave to the list of waves
		ArrayList waveList = new ArrayList();
		waveList.add(wave0);
		
		//Creating a player
		Player player = new Player();
		
		//Creating the map
		Map map = new Map(waveList, path, player);
		
		map.startGame();

	}

}
