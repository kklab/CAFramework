package org.kklab.ca.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import javax.swing.JPanel;

public class TilePanel extends JPanel { // タイルを表示するパネル
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int rows; // 行の数
	private int columns; // 列の数
	private int tileSize; // タイルのサイズ（ピクセル）
	private int width; // パネルの幅（ピクセル）
	private int height; // パネルの高さ（ピクセル）
	protected Vector<Tile> tiles = new Vector<Tile>(); // タイルを格納するベクトル
	protected Palette palette; // パネル付属のパレット
	private boolean hasBorder = true;

	public static final int TITLEBAR_HEIGHT = 22; // タイトルバーの高さ

	public TilePanel(int rows, int columns, int tileSize, Palette palette, boolean hasBorder) { // コンストラクタ
		this.rows = rows;
		this.columns = columns;
		this.tileSize = tileSize;
		this.palette = palette;
		this.hasBorder = hasBorder;
		this.width = columns * tileSize;
		this.height = rows * tileSize;

		for (int i = 0; i < rows; i++) { // 無色のタイルを行×列だけ作成
			for (int j = 0; j < columns; j++) {
				add(new Tile(i, j, tileSize, null));
			}
		}
	}

	public void add(Tile tile) { // タイルを追加
		tiles.add(tile);
	}

	public int getWidth() { // 幅を返す
		return width;
	}

	public int getHeight() { // 高さを返す
		return height;
	}

	public int getRows() { // 行数を返す
		return rows;
	}

	public int getColumns() { // 列数を返す
		return columns;
	}

	public int getTileSize() { // タイルサイズを返す
		return tileSize;
	}

	public Palette getPalette() {
		return palette;
	}
	
	public void setHasBorder(boolean hasBorder) {
		this.hasBorder = hasBorder;
	}

	public Vector<Tile> getTiles() { // すべてのタイルを返す
		return tiles;
	}

	@Override
	protected void paintComponent(Graphics g) { // パネルの描画
		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		for (Tile tile : tiles) {
			g2.setPaint(tile.getColor());
			g2.fill(tile);
		}
		if (hasBorder) {
			g2.setPaint(Color.BLACK);
			g2.draw(new Rectangle2D.Double(0, 0, width - 1, height - 1));
		}
	}
}
