package org.kklab.ca.graphics;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class Tile extends Rectangle2D.Double { // 正方形タイルクラス
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int size; // サイズ
	private Color color; // 色

	public Tile(int row, int column, int size, Color color) { // 行(row)・列(column)を指定してタイルを作成
		super((double)(column * size), (double)(row * size), (double)size, (double)size);
		this.size = size;
		this.color = color;
	}

	public Color getColor() { // 色を返す
		return color;
	}

	public void setColor(Color color) { // 色を設定
		this.color = color;
	}

	public int getSize() { // サイズを返す
		return size;
	}
}
