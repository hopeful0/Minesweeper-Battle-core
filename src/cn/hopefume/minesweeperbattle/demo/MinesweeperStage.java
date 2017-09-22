package cn.hopefume.minesweeperbattle.demo;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MinesweeperStage extends Stage {

	private MinesweeperGroup minesweeperGroup;
	
	public MinesweeperStage() {
		super(new FitViewport(1280, 1720));
		minesweeperGroup = new MinesweeperGroup(16, 16);
		addActor(minesweeperGroup);
	}
	
	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
	}
	
}
