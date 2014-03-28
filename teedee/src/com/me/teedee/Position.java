package com.me.teedee;



public class Position {
	private int xCoordinate;
	private int yCoordinate;
	
	public Position(){
		xCoordinate = 0;
		yCoordinate = 0;
	}
	public Position(int x, int y){
		xCoordinate = x;
		yCoordinate = y;
	}
	public int getxCoordinate() {
		return xCoordinate;
	}
	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}
	public int getyCoordinate() {
		return yCoordinate;
	}
	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
}
