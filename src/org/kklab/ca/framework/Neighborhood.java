package org.kklab.ca.framework;

import java.util.Vector;

public abstract class Neighborhood { // 近傍（抽象クラス）
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	public static final int NE = 4;
	public static final int SE = 5;
	public static final int SW = 6;
	public static final int NW = 7;
	public static final int Nn = 8;
	public static final int Ee = 9;
	public static final int Ss = 10;
	public static final int Ww = 11;

	public abstract Vector<Site> neighborhood(final Lattice lattice, final int row, final int column); // 位置(i, j)の隣接サイトを取得（抽象メソッド）
}
