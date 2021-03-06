package com.me.teedee;

import java.util.ArrayList;

/**
 * A class that can generate a list waves in
 * different difficulties.
 * @author Daniel
 */
public class WaveFactory {

	public static ArrayList<Wave> createWave(int d, Path p){
		switch(d) {
		case 1:
			return creatEasyWave(p);
		case 2:
			return creatMediumWave(p);
		case 3:
			return creatHardWave(p);
		default:
			return creatHardWave(p);
		}
	}

	public static ArrayList<Wave> creatEasyWave(Path path) {
		int[] enemies = {5};

		ArrayList<Wave> waveList = new ArrayList<Wave>();

		for(int i = 0; i < 100; i++) {
			waveList.add(new Wave(path,enemies,i));
			enemies[0] = enemies[0] + 2;
		}
		return waveList;
	}

	public static ArrayList<Wave> creatMediumWave(Path path) {
		int[] enemies = {4,0,0,0,0};

		ArrayList<Wave> waveList = new ArrayList<Wave>();

		waveList.add(new Wave(path,enemies,0));		
		enemies[0] += 2;
		waveList.add(new Wave(path,enemies,1));		
		enemies[0] = 0;
		enemies[1] = 4;
		waveList.add(new Wave(path, enemies, 2));
		enemies[3] = 2;
		waveList.add(new Wave(path, enemies, 3));
		enemies[3] = 0;
		for(int i = 4; i < 50; i++) {
			if(i > 40){
				int[] a = {0,0,0,0,i};
				waveList.add(new Wave(path,a, i*1000));
			} else if(i%10 == 0){
				int[] a = {0,0,0,0,i/10};
				waveList.add(new Wave(path,a, i));
			} else if(i < 6){
				enemies[0] += i;
				enemies[1] += i;
				waveList.add(new Wave(path,enemies,i));
			}	else {	
				enemies[0] += i;
				enemies[1] += i;
				enemies[2] += i;
				enemies[3] += i*2;
				waveList.add(new Wave(path,enemies,i));
			}
		}
		return waveList;
	}

	public static ArrayList<Wave> creatHardWave(Path path) {
		int[] enemies = {5,3,2,1};

		ArrayList<Wave> waveList = new ArrayList<Wave>();

		for(int i = 0; i < 100; i++) {
			waveList.add(new Wave(path,enemies,i));
			enemies[0] = enemies[0] + 2*i;
		}
		return waveList;
	}
}
