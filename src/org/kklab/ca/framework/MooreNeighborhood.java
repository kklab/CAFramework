package org.kklab.ca.framework;

import java.util.Vector;

public class MooreNeighborhood extends Neighborhood { // ムーア近傍
	public Vector<Site> neighborhood(final Lattice lattice, final int row, final int column) {
		Vector<Site> neighbors = new Vector<Site>();
		neighbors.add(lattice.get(row - 1, column));
		neighbors.add(lattice.get(row - 1, column + 1));
		neighbors.add(lattice.get(row, column + 1));
		neighbors.add(lattice.get(row + 1, column + 1));
		neighbors.add(lattice.get(row + 1, column));
		neighbors.add(lattice.get(row + 1, column - 1));
		neighbors.add(lattice.get(row, column - 1));
		neighbors.add(lattice.get(row - 1, column - 1));
		return neighbors;
	}

}
