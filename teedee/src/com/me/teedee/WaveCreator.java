package com.me.teedee;

import java.util.ArrayList;

/**
 * A class that can generate a list waves in
 * different difficulties.
 * @author Daniel
 */
public class WaveCreator {
	
	/**
	 * TODO
	 * We should fix the enemies array to add all different kind of enemies
	 * when we have created these.
	 * 
	 * Also we problably should come up with a method to properly generate
	 * the wave and not just add more enemies of the same kind in a pattern.
	 * 
	 */
	
	public static ArrayList<Wave> creatEasyWave(Path path) {
		int[] enemies = {5};

		ArrayList<Wave> waveList = new ArrayList<Wave>();
		
		for(int i = 0; i < 100; i++) {
			waveList.add(new Wave(path,enemies));
			enemies[0] = enemies[0] + 2;
			System.out.println(enemies[0]);
		}
		return waveList;
	}
	
	public static ArrayList<Wave> creatMediumWave(Path path) {
		int[] enemies = {5};

		ArrayList<Wave> waveList = new ArrayList<Wave>();
		
		for(int i = 0; i < 100; i++) {
			waveList.add(new Wave(path,enemies));
			enemies[0] = enemies[0] + i;
		}
		return waveList;
	}
	
	public static ArrayList<Wave> creatHardWave(Path path) {
		int[] enemies = {5};

		ArrayList<Wave> waveList = new ArrayList<Wave>();
		
		for(int i = 0; i < 100; i++) {
			waveList.add(new Wave(path,enemies));
			enemies[0] = enemies[0] + 2*i;
		}
		return waveList;
	}
}
