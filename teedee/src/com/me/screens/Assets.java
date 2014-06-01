package com.me.screens;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

	public static final AssetManager manager = new AssetManager();

	public static void load() {
		//Screen backgrounds
		manager.load("data/MainMenu.png", Texture.class);
		manager.load("data/TeeDee.png", Texture.class);
		manager.load("data/GAME_OVER_Screen.png", Texture.class);

		//Maps for DifficultySelectScreen
		manager.load("map/spaceMapThumbnail.png", Texture.class);
		manager.load("map/explosionMapSmall.png", Texture.class);
		manager.load("map/nebulaMapSmall.png", Texture.class);
		manager.load("map/galaxySmall.png", Texture.class);

		manager.load("map/spaceMapSmallS.png", Texture.class);
		manager.load("map/explosionMapSmallS.png", Texture.class);
		manager.load("map/nebulaMapSmallS.png", Texture.class);
		manager.load("map/galaxySmallS.png", Texture.class);

		//Big Maps
		manager.load("map/map.png", Texture.class);
		manager.load("map/explosionMap.png", Texture.class);
		manager.load("map/nebulaMap.png", Texture.class);
		manager.load("map/galaxyMap.png", Texture.class);

		//Enemies
		manager.load("img/firstEnemy.png", Texture.class);
		manager.load("img/fastEnemy.png", Texture.class);
		manager.load("img/slowEnemy.png", Texture.class);
		manager.load("img/shield.png", Texture.class);
		manager.load("img/green.png", Texture.class);
		manager.load("img/red.png", Texture.class);

		//Towers
		manager.load("img/firstDragon.png", Texture.class);
		manager.load("img/firstDragon1.png", Texture.class);
		manager.load("img/firstDragon2.png", Texture.class);
		manager.load("img/firstDragon3.png", Texture.class);
		manager.load("img/firstDragon4.png", Texture.class);

		manager.load("img/bloodDragon.png", Texture.class);
		manager.load("img/bloodDragon1.png", Texture.class);
		manager.load("img/bloodDragon2.png", Texture.class);

		manager.load("img/hydra.png", Texture.class);	//TODO remove?
		manager.load("img/hydra2.png", Texture.class);
		manager.load("img/hydra3.png", Texture.class);
		manager.load("img/hydra4.png", Texture.class);
		manager.load("img/hydra5.png", Texture.class);
		manager.load("img/hydra6.png", Texture.class);

		manager.load("img/iceDragon.png", Texture.class);
		manager.load("img/iceDragon1.png", Texture.class);
		manager.load("img/iceDragon2.png", Texture.class);

		manager.load("img/RNGTower.png", Texture.class);
		manager.load("img/RNGTower1.png", Texture.class);
		manager.load("img/RNGTower2.png", Texture.class);

		manager.load("img/shockwave.png", Texture.class);
		manager.load("img/shockwave1.png", Texture.class);
		manager.load("img/shockwave2.png", Texture.class);

		//Bullets
		manager.load("img/fireBullet.png", Texture.class);
		manager.load("img/blackHoleBIG.png", Texture.class);
		manager.load("img/greenBullet.png", Texture.class);
		manager.load("img/IceBullet.png", Texture.class);
		manager.load("img/laserBeam.png", Texture.class);
		manager.load("img/shockwaveBullet.png", Texture.class);

		//GUI
		manager.load("img/buildTable.png", Texture.class);
		manager.load("img/infoTable.png", Texture.class);
		manager.load("img/radius200.png", Texture.class);
		manager.load("img/radius200_red.png", Texture.class);
		manager.load("img/pathTile1.png", Texture.class);
		manager.load("img/unknown.png", Texture.class);
		manager.load("data/speaker_louder_32.png", Texture.class);
		manager.load("data/speaker_off_32.png", Texture.class);
		manager.load("data/Play.png", Texture.class);
		manager.load("data/FastForward.png", Texture.class);
	}

	public static void dispose() {
		manager.dispose();
	}
}
