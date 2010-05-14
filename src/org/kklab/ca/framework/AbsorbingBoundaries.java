package org.kklab.ca.framework;

public class AbsorbingBoundaries implements Boundaries { // 吸収端境界

	public int indexOf(int row, int column) { // 吸収端のindex
		if (row < 0 || row >= Lattice.getRows() || column < 0 || column >= Lattice.getColumns()) { // 格子の外は(-1)
			return -1;
		} else {
			return row * Lattice.getRows() + column;
		}
	}
}
