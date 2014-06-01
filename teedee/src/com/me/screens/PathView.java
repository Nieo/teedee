package com.me.screens;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.me.teedee.Position;
/**
 *A class that hold graphical information about the path which the enemies travels on.
 */
public class PathView {
	private List<Position> pos;
	private Sprite[] tiledPath;

	public PathView(List<Position> list){
		this.pos = list;
		tiledPath = new Sprite[list.size()-1];
		float extension = 0f;
		float x1,x2,y1,y2,dx,dy;
		for(int i=0; i<this.pos.size()-1; i++){
			if(i == this.pos.size()-2)
				extension = 60f;
			x1 = this.pos.get(i).getX();
			x2 = this.pos.get(i+1).getX();
			y1 = this.pos.get(i).getY();
			y2 = this.pos.get(i+1).getY();

			tiledPath[i]=new Sprite(Assets.manager.get("img/pathTile1.png", Texture.class));
			dx = x2-x1;
			dy = y2-y1;
			if(dx > 0)
				tiledPath[i].setBounds(x1, y1, dx, 60);			
			else if(dx < 0)
				tiledPath[i].setBounds(x1+60, y1, dx-extension, 60);
			else if(dy > 0)
				tiledPath[i].setBounds(x1, y1, 60, dy);
			else if(dy < 0)
				tiledPath[i].setBounds(x1, y1+60, 60, dy-extension);
		}
	}

	public Sprite[] getSprites(){
		return tiledPath;	
	}

}
