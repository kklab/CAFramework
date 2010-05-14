package org.kklab.ca.graphics;

import javax.swing.JFrame;

import org.kklab.ca.framework.Boundaries;
import org.kklab.ca.framework.CellularAutomata;
import org.kklab.ca.framework.Lattice;
import org.kklab.ca.framework.Neighborhood;
import org.kklab.ca.framework.Site;

public class CAAnimation extends CellularAutomata {
	private LatticeAnimation latticeAnimation;

	protected CAAnimation(final int rows, final int columns, final int steps, final Neighborhood neighborhood, final Boundaries boundaries, final Site[] init, final JFrame frame, final int offsetX,
			final int offsetY, final int tileSize, final int tick, final Palette palette,
			final boolean hasBorder) {
		super(rows, columns, steps, neighborhood, boundaries, init);
		latticeAnimation = new LatticeAnimation(getLattices(), tileSize, tick,
				palette, hasBorder);
		frame.getContentPane().add(latticeAnimation); // フレームにアニメーション・パネルを追加
		frame.setBounds(offsetX, offsetY, latticeAnimation.getWidth(),
						latticeAnimation.getHeight()
								+ LatticeAnimation.TITLEBAR_HEIGHT);
	}

	@Override
	protected void displayLattice(Lattice lattice) {
		int[] value = new int[lattice.size()];

		int i = 0;
		for (Site site : lattice.getSites()) {
			value[i++] = site.intValue();
		}
		
		i = 0;
		for (Tile tile : latticeAnimation.getTiles()) { // タイルの色をサイトの状態に応じてパレットから取り出し設定
			tile.setColor(latticeAnimation.getPalette().color(
					value[i++]));
		}
		latticeAnimation.repaint(0, 0, 0, latticeAnimation.getWidth(),
				latticeAnimation.getHeight()); // パネルの全領域を再ペイント

		try {
			Thread.sleep(latticeAnimation.getTick()); // tick(ms)だけスリープ
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
}
