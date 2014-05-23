package com.me.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.teedee.Map;

public class MapListener extends ClickListener {
	
	private MapScreen mapscreen;
	private Map map;
	
	public MapListener(MapScreen ms, Map m){
		super();
		mapscreen = ms;
		map = m;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y){
		
		if(map.isRunning()){
			
		}
	}
	
}
