package org.kklab.ca.framework;

import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Lattice {
	private static int rows; // 行のサイズ
	private static int columns; // y方向のサイズ
	private static Neighborhood neighborhood = null; // 隣接サイト
	private static Boundaries boundaries = null; // 境界
	private static final int nThreads = 16 * Runtime.getRuntime().availableProcessors();
	private static final ExecutorService executor = Executors.newFixedThreadPool(nThreads);

	private final Vector<Site> sites; // 格子の実体

	public Lattice() { // デフォルト・コンストラクタ
		sites = new Vector<Site>();
	}

	public Lattice(Site[] init) { // コンストラクタ
		sites = new Vector<Site>();
		for (int i = 0; i < rows * columns; i++) { // pattern配列の値を各サイトに設定
			add(init[i]);
		}
	}

	class UpdateRequest {
		private int row;
		private int column;
		private Site site;
		private Vector<Site> neighbors;

		protected UpdateRequest(int row, int column, Site site, Vector<Site> neighbors) {
			this.row = row;
			this.column = column;
			this.site = site;
			this.neighbors = neighbors;
		}

		public int getRow() {
			return row;
		}

		public int getColumn() {
			return column;
		}

		public Site getSite() {
			return site;
		}

		public Vector<Site> getNeighbors() {
			return neighbors;
		}
	}

	class UpdateTask implements Callable<Site> {
		private UpdateRequest request;

		protected UpdateTask(UpdateRequest request) {
			this.request = request;
		}

		public Site call() throws Exception {
			return request.getSite().update(request.getNeighbors());
		}
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see
	 * org.kklab.ca.framework.Lattice#update(org.kklab.ca.framework.AbstractLattice
	 * )
	 */
	public synchronized Lattice update(Lattice next) { // 更新

		Map<Integer, Future<Site>> resultMap = new ConcurrentHashMap<Integer, Future<Site>>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				UpdateRequest request = new UpdateRequest(i, j, get(i, j),
						neighborhood.neighborhood(this, i, j));
				resultMap.put(indexOf(i, j), executor.submit(new UpdateTask(request)));
			}
		}

		for (int i = 0; i < rows * columns; i++) {
			Site site;
			try {
				site = resultMap.get(i).get();
				next.add(site);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		return next;
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.kklab.ca.framework.Lattice#get(int)
	 */
	public synchronized Site get(int index) throws IndexOutOfBoundsException { // 内部インデックスからサイトを取得
		if (index == -1) { // インデックスが(-1)ならnull（吸収端）
			return null;
		} else if (index < -1 || index >= rows * columns) { // 範囲外のインデックスは例外をスロー
			throw new IndexOutOfBoundsException();
		}
		return sites.get(index);
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.kklab.ca.framework.Lattice#get(int, int)
	 */
	public synchronized Site get(int row, int column) { // 位置(i, j)のサイトを取得
		return get(indexOf(row, column));
	}

	public synchronized void set(int index, Site site)  throws IndexOutOfBoundsException {
		if (index != -1)
			sites.set(index, site);
	}
	
	public synchronized void set(int row, int column, Site site) {
		set(indexOf(row, column), site);
	}
	
	public synchronized Vector<Site> getSites() {
		return sites;
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.kklab.ca.framework.Lattice#add(org.kklab.ca.framework.Site)
	 */
	public synchronized void add(final Site site) { // 格子にサイトを追加
		sites.add(site);
	}

	public synchronized int size() {
		return sites.size();
	}

	protected int indexOf(int i, int j) { // 境界条件を元に位置(i, j)の内部インデックスを取得
		return boundaries.indexOf(i, j);
	}

	public static void setNeighborhood(final Neighborhood neighborhood) { // 近傍タイプを設定
		Lattice.neighborhood = neighborhood;
	}

	public static void setBoundaries(final Boundaries boundaries) { // 境界タイプを設定
		Lattice.boundaries = boundaries;
	}

	public static final int getRows() {
		return rows;
	}

	public static final int getColumns() {
		return columns;
	}

	public static void setRows(int rows) {
		Lattice.rows = rows;
	}

	public static void setColumns(final int columns) {
		Lattice.columns = columns;
	}

	public String toString() { // 文字列化
		String s = new String();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				s += get(i, j);
			}
			s += "\n";
		}
		return s;
	}

	@Override
	protected void finalize() throws Throwable {
		executor.shutdown();
		super.finalize();
	}
}
