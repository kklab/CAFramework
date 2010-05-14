package org.kklab.ca.graphics;

import java.util.Vector;

import org.kklab.ca.framework.Lattice;

public class LatticeAnimation extends LatticePanel implements Runnable { // 格子アニメーション

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<Lattice> lattices; // 全ステップの格子を格納
	private int tick; // 更新間隔(ミリ秒単位）

	public LatticeAnimation(Vector<Lattice> lattices, int tileSize, int tick, Palette palette, boolean hasBorder) { // コンストラクタ
		super(lattices.get(0), tileSize, palette, hasBorder);
		this.lattices = lattices;
		this.tick = tick;
	}

	public int getTick() {
		return tick;
	}
	
	public void run() { // Runnableインターフェースのrunメソッドを実装
		for (Lattice lattice : lattices) { // ステップ数だけ更新
			while (lattice.size() < Lattice.getRows() * Lattice.getColumns()) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			int j = 0;
			for (Tile tile : getTiles()) { // タイルの色をサイトの状態に応じてパレットから取り出し設定
				tile.setColor(palette.color(lattice.get(j++).intValue()));
			}
			repaint(0, 0, 0, getWidth(), getHeight()); // パネルの全領域を再ペイント
			try {
				Thread.sleep(tick); // tick(ms)だけスリープ
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
