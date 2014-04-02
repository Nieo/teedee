package com.me.teedee;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

/**
 * A most basic enemy, extends abstract enemy
 * @author Fridgeridge
 *
 */
public class BasicEnemy extends AbstractEnemy {

	public BasicEnemy(TiledMapTileLayer t) {
		super(t);
		// TODO Auto-generated constructor stub
	}

	public BasicEnemy(TiledMapTileLayer t, float sp, Lives l, Reward r, Status s, Position pos){
		super(t,sp,l,r,s,pos);
		//TODO
	}
	
	public BasicEnemy(BasicEnemy b){
		super(b);
	}
	
}
