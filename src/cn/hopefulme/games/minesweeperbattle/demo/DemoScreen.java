package cn.hopefulme.games.minesweeperbattle.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class DemoScreen implements Screen {

	private MinesweeperStage minesweeperStage;
	
	public DemoScreen() {
		// TODO Auto-generated constructor stub
		minesweeperStage = new MinesweeperStage();
		Gdx.input.setInputProcessor(minesweeperStage);
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		minesweeperStage.act(delta);
		minesweeperStage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		minesweeperStage.getViewport().update(width, height);
		Gdx.graphics.requestRendering();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
