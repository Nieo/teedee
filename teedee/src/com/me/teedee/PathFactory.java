package com.me.teedee;

import java.util.ArrayList;
import java.util.List;

public class PathFactory {
	
	public static final int nbrOfPaths = 2;
	
	public static Path createPath(int i){
		switch(i){
		case 1:
			return createPathOne();
		case 2:
			return createPathTwo();
		default:
			return createPathOne();
		}
		
	}
	
	
	private static Path createPathOne(){
		List<Position> pathPositions = new ArrayList<Position>();
		pathPositions.add(new Position(0,490));
		pathPositions.add(new Position(740,490));
		pathPositions.add(new Position(740,300));
		pathPositions.add(new Position(160,300));
		pathPositions.add(new Position(160,90));
		pathPositions.add(new Position(850,90));
		pathPositions.add(new Position(850,720));
		
		return new Path(pathPositions);
	}
	
	private static Path createPathTwo(){
		
		List<Position> pathPositions = new ArrayList<Position>();
		pathPositions.add(new Position(0,300));
		pathPositions.add(new Position(300,300));
		pathPositions.add(new Position(300,250));
		pathPositions.add(new Position(500,250));
		pathPositions.add(new Position(500,350));
		pathPositions.add(new Position(750,350));
		pathPositions.add(new Position(750,00));
		
		return new Path(pathPositions);
	}
	
	
}
