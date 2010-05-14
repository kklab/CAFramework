package org.kklab.ca.graphics;

import org.kklab.ca.framework.Lattice;

public class LatticePanel extends TilePanel { // タイル・パネルを使って格子状態を表示するクラス

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LatticePanel(Lattice lattice, int tileSize, Palette palette, boolean hasBorder) {
		super(Lattice.getRows(), Lattice.getColumns(), tileSize, palette, hasBorder);
		setupPanel(lattice);
	}
	
	private void setupPanel(Lattice lattice) {
		int i = 0;
		for (Tile tile : tiles) { // 格子のすべてのサイトのrankに基づいてパレットから色を取り出し、タイルに設定
			tile.setColor(palette.color(lattice.get(i++).intValue()));
		}
	}

}
