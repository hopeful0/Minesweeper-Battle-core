package cn.hopefume.minesweeperbattle.demo;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Block extends Actor {
	
	public static TextureAtlas openedblockTextureAtlas;
	public static TextureAtlas closedblockTextureAtlas;
	private TextureRegion mTextureRegion;
	
	public int value;
	public boolean isOpened;
	
	public Block() {
		this(0);
	}
	
	public Block(int value) {
		this.value = value;
		this.isOpened = false;
		mTextureRegion = closedblockTextureAtlas.findRegion("normal");
		setBounds(0, 0, 80, 80);
	}
	
	public void setTextureRegion(TextureRegion mTextureRegion) {
		this.mTextureRegion = mTextureRegion;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		batch.draw(mTextureRegion, getX(),getY(), getOriginX(), getOriginY(), getWidth(), getWidth(), getScaleX(), getScaleY(), getRotation());
	}
	
}
