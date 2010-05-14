package sample;
import javax.swing.JFrame;

import org.kklab.ca.framework.Boundaries;
import org.kklab.ca.framework.Lattice;
import org.kklab.ca.framework.MooreNeighborhood;
import org.kklab.ca.framework.Neighborhood;
import org.kklab.ca.framework.PeriodicBoundaries;
import org.kklab.ca.framework.Site;
import org.kklab.ca.graphics.CAAnimation;
import org.kklab.ca.graphics.Palette;

public class LifeGame extends CAAnimation { // ライフゲームCA
	private static Neighborhood neighborhood = new MooreNeighborhood();
	private static Boundaries boundaries = new PeriodicBoundaries();

	public LifeGame(final int size, final int steps, final Site[] init,
			final JFrame frame, final int offsetX, final int offsetY,
			final int tileSize, final int tick, final Palette palette,
			final boolean hasBorder) {
		super(size, size, steps, neighborhood, boundaries, init, frame, offsetX, offsetY, tileSize, tick, palette, hasBorder); // 親クラスのコンストラクタ：ステップ数と初期格子をセット
	}

	public String toString() { // 文字列化
		String s = new String();
		for (Lattice lattice : lattices) {
			s += lattice + "\n";
		}
		return s;
	}
}
