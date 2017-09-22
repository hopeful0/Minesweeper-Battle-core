package cn.hopefume.minesweeperbattle.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class MAssetManager extends AssetManager {
	
	public void load() {
		this.load("opened_block.pack", TextureAtlas.class);
		this.load("closed_block.pack", TextureAtlas.class);
		this.finishLoading();
		Block.openedblockTextureAtlas = this.get("opened_block.pack", TextureAtlas.class);
		Block.closedblockTextureAtlas = this.get("closed_block.pack", TextureAtlas.class);

	}
	
}
