package cn.hopefume.minesweeperbattle.demo;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MinesweeperGroup extends Group {

	private Block[][] blocks;
	private int rows, cols;
	
	private Block flagBlock;
	private Block askBlock;
	
	private Vector2 selected; 
	
	public MinesweeperGroup (int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		blocks = new Block[rows][cols];
		for (int i = 0; i < rows; i ++) {
			for (int j = 0; j < cols; j ++) {
				blocks[i][j] = new Block();
				blocks[i][j].setPosition(80 * i, 80 * j);
				addActor(blocks[i][j]);
			}
		}
		int mineCount = 0;
		while (mineCount < 20) {
			int i = (int) (Math.random() * rows);
			int j = (int) (Math.random() * cols);
			if (blocks[i][j].value != -1) {
				blocks[i][j].value = -1;
				mineCount ++;
			}
		}
		for (int i = 0; i < rows; i ++) {
			for (int j = 0; j < cols; j ++) {
				if (blocks[i][j].value == -1)
					continue;
				for (int m = i - 1; m <= i + 1; m ++) {
					if (m < 0 || m >= rows)
						continue;
					for (int n = j - 1; n <= j + 1; n ++) {
						if (n < 0 || n >= cols || (i == m && j == n))
							continue;
						if (blocks[m][n].value == -1)
							blocks[i][j].value ++;
					}
				}
			}
		}
		this.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				int i = (int) (x / 80);
				int j = (int) (y / 80);
				if (! blocks[i][j].isOpened)
					openblock(i, j);
			}
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				// TODO Auto-generated method stub
				int i = (int) (x / 80);
				int j = (int) (y / 80);
				if (blocks[i][j].isOpened)
					return false;
				selected = new Vector2(i, j);
				showFlagAndAsk();
				return true;
			}
			@Override
			public void touchDragged(InputEvent event, float x, float y, int pointer) {
				// TODO Auto-generated method stub
			}
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				// TODO Auto-generated method stub
				Block hitedBlock = (Block) hit(x, y, true);
				if (getSelectedBlock() == hitedBlock)
					openSelected();
				else if (flagBlock == hitedBlock)
					flagSelected();
				else if (askBlock == hitedBlock)
					askSelected();
				hideFlagAndAsk();
			}
		});
		flagBlock = new Block();
		flagBlock.setTextureRegion(Block.closedblockTextureAtlas.findRegion("flag"));
		askBlock = new Block();
		askBlock.setTextureRegion(Block.closedblockTextureAtlas.findRegion("ask"));
		hideFlagAndAsk();
		addActor(flagBlock);
		addActor(askBlock);
	}
	
	private void hideFlagAndAsk() {
		flagBlock.setVisible(false);
		askBlock.setVisible(false);
	}
	
	private void showFlagAndAsk() {
		if (null == selected) return;
		int i = (int)selected.x;
		int j = (int)selected.y;
		flagBlock.setPosition(i * 80, j * 80);
		askBlock.setPosition(i * 80, j * 80);
		if (i > rows / 2)
			flagBlock.moveBy(-80, 0);
		else
			flagBlock.moveBy(80, 0);
		if (j > cols / 2)
			askBlock.moveBy(0, -80);
		else
			askBlock.moveBy(0, 80);
		flagBlock.setVisible(true);
		askBlock.setVisible(true);
	}
	
	private Block getSelectedBlock() {
		return null == selected ? null : blocks[(int)selected.x][(int)selected.y];
	}
	
	private void openSelected() {
		if (null == selected) return;
		openblock((int)selected.x, (int)selected.y);
	}
	
	private void flagSelected() {
		Block block = getSelectedBlock();
		if (null == block) return;
		block.setTextureRegion(Block.closedblockTextureAtlas.findRegion("flag"));
	}
	
	private void askSelected() {
		Block block = getSelectedBlock();
		if (null == block) return;
		block.setTextureRegion(Block.closedblockTextureAtlas.findRegion("ask"));
	}
	
	private void openblock(int i, int j) {
		Block block = blocks[i][j];
		block.isOpened = true;
		switch (block.value) {
		case -1:
			block.setTextureRegion(Block.closedblockTextureAtlas.findRegion("explodedMine"));
			openRestMines(block);
			break;
		case 0:
			block.setTextureRegion(Block.openedblockTextureAtlas.findRegion("highlight"));
			openNearest(i, j);
			break;
		case 1:
			block.setTextureRegion(Block.openedblockTextureAtlas.findRegion("one"));
			break;
		case 2:
			block.setTextureRegion(Block.openedblockTextureAtlas.findRegion("two"));
			break;
		case 3:
			block.setTextureRegion(Block.openedblockTextureAtlas.findRegion("three"));
			break;
		case 4:
			block.setTextureRegion(Block.openedblockTextureAtlas.findRegion("four"));
			break;
		case 5:
			block.setTextureRegion(Block.openedblockTextureAtlas.findRegion("five"));
			break;
		case 6:
			block.setTextureRegion(Block.openedblockTextureAtlas.findRegion("six"));
			break;
		case 7:
			block.setTextureRegion(Block.openedblockTextureAtlas.findRegion("seven"));
			break;
		case 8:
			block.setTextureRegion(Block.openedblockTextureAtlas.findRegion("eight"));
			break;
		}
	}
	
	private void openRestMines (Block exploded) {
		for (int i = 0; i < rows; i ++) {
			for (int j = 0; j < cols; j ++) {
				Block block = blocks[i][j];
				if (block.value != -1 || exploded == block)
					continue;
				block.isOpened = true;
				block.setTextureRegion(Block.closedblockTextureAtlas.findRegion("mine"));
			}
		}
	}
	
	private void openNearest (int i, int j) {
		for (int m = i - 1; m <= i + 1; m ++) {
			if (m < 0 || m >= rows)
				continue;
			for (int n = j - 1; n <= j + 1; n ++) {
				if (n < 0 || n >= cols || (i == m && j == n) || blocks[m][n].isOpened)
					continue;
				openblock(m, n);
			}
		}
	}
	
}
