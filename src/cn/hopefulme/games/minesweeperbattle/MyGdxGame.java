package cn.hopefulme.games.minesweeperbattle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;

import cn.hopefulme.games.minesweeperbattle.demo.DemoScreen;
import cn.hopefulme.games.minesweeperbattle.demo.MAssetManager;

public class MyGdxGame extends Game {
	
	public MAssetManager mAssetManager;
	private DemoScreen screen;
	
	@Override
	public void create () {
		mAssetManager = new MAssetManager();
		mAssetManager.load();
		screen = new DemoScreen();
		setScreen(screen);
		Gdx.graphics.setContinuousRendering(false);
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		getScreen().render(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		if (null != mAssetManager)
			mAssetManager.dispose();
		if (null != screen)
			screen.dispose();
	}
	
	public static AssetManager getAssetManager() {
		MyGdxGame game = (MyGdxGame)Gdx.app.getApplicationListener();
	    return game.mAssetManager;
	}
}
