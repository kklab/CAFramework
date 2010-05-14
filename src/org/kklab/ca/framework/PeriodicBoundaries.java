package org.kklab.ca.framework;

public class PeriodicBoundaries implements Boundaries { // 周期的境界

	public int indexOf(int row, int column) {
		if (row < 0) { // 端の隣は反対側の端
			row += Lattice.getRows();
		} else if (row >= Lattice.getRows()) {
			row -= Lattice.getRows();
		}
		if (column < 0) { // 端の隣は反対側の端
			column += Lattice.getColumns();
		} else if (column >= Lattice.getColumns()) {
			column -= Lattice.getColumns();
		}
		return row * Lattice.getColumns() + column;
	}
}
