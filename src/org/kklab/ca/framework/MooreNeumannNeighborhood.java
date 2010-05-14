package org.kklab.ca.framework;

import java.util.Vector;

public class MooreNeumannNeighborhood extends Neighborhood { // ムーア近傍
	public Vector<Site> neighborhood(final Lattice lattice, final int row, final int column) {
		Vector<Site> neighbors = new Vector<Site>();
		neighbors.add(lattice.get(row - 1, column)); // 北
		neighbors.add(lattice.get(row, column + 1)); // 東
		neighbors.add(lattice.get(row + 1, column)); // 南
		neighbors.add(lattice.get(row, column - 1)); // 西
		neighbors.add(lattice.get(row - 1, column + 1)); // 北東
		neighbors.add(lattice.get(row + 1, column + 1)); // 南東
		neighbors.add(lattice.get(row + 1, column - 1)); // 南西
		neighbors.add(lattice.get(row - 1, column - 1)); // 北西
		neighbors.add(lattice.get(row - 2, column)); // 北の北
		neighbors.add(lattice.get(row, column + 2)); // 東の東
		neighbors.add(lattice.get(row + 2, column)); // 南の南
		neighbors.add(lattice.get(row, column - 2)); // 西の西
		return neighbors;
	}

}
