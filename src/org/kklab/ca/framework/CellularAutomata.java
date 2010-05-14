package org.kklab.ca.framework;

import java.util.Iterator;
import java.util.Vector;

public abstract class CellularAutomata implements Runnable { // セルラ・オートマトン（抽象クラス）
	protected final int steps; // ステップ数
	protected final Vector<Lattice> lattices = new Vector<Lattice>(); // 格子の記録
	protected int currentStep;

	protected CellularAutomata(final int rows, final int columns, final int steps, final Neighborhood neighborhood, final Boundaries boundaries, final Site[] init) { // コンストラクタ
		this.steps = steps;
		Lattice.setRows(rows);
		Lattice.setColumns(columns);
		Lattice.setNeighborhood(neighborhood);
		Lattice.setBoundaries(boundaries);
		lattices.add(new Lattice(init));
		for (int i = 0; i < steps; i++) {
			lattices.add(new Lattice()); // ステップ数分の格子を用意
		}
		currentStep = 0;
	}

	public void run() { // セルラ・オートマトンの実行
		Iterator<Lattice> it = lattices.iterator();
		Lattice current = it.next();
		while (it.hasNext()) {
			processLattice(current);
			displayLattice(current);
			Lattice next = current.update(it.next()); // 現在の格子状態から次の格子状態をルールに従って更新
			current = next;
			currentStep++;
		}
		processLattice(current);
		displayLattice(current);
		summarize();
	}

	protected void processLattice(Lattice lattice) {
		// 統計処理等を行うフックメソッド
	}

	protected void displayLattice(Lattice current) {
		// 表示を行うフックメソッド
	}

	protected void summarize() {
		// 最終処理
	}
	public synchronized Vector<Lattice> getLattices() { // 格子の記録を返す
		return lattices;
	}

	public synchronized Lattice get(final int index) { // index番目のステップの格子
		return lattices.get(index);
	}
}
