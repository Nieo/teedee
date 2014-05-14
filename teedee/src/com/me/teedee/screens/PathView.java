package com.me.teedee.screens;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.me.teedee.Position;

public class PathView {
	private List<Position> pos;
	private Sprite[] tiledPath;
	
	public PathView(List<Position> list){
		this.pos = list;
		tiledPath = new Sprite[list.size()];
		
		for(int i=0; i<this.pos.size()-1; i++){
			float x1,x2,y1,y2,dx,dy;//TODO Leaves a square to be rendered
			x1 = this.pos.get(i).getX();
			x2 = this.pos.get(i+1).getX();
			y1 = this.pos.get(i).getY();
			y2=this.pos.get(i+1).getY();

			tiledPath[i]=new Sprite(new Texture("img/pathTile.png"));
			dx = x2-x1;
			dy = y2-y1;
			if(dx > 0)
				tiledPath[i].setBounds(x1, y1, dx, 60);			
			else if(dx < 0)
				tiledPath[i].setBounds(x1, y1, dx, 60);
			else if(dy > 0)
				tiledPath[i].setBounds(x1, y1, 60, dy);
			else if(dy < 0)
				tiledPath[i].setBounds(x1, y1+60, 60, dy-60);
		}
		
		
			
		}
	

	public Sprite[] getSprites(){
		return tiledPath;	
	}
	

}
	
	
	

