package org.kklab.ca.graphics;

import java.awt.Color;
import java.util.Vector;

public class Palette { // パレットクラス
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<Color> colors; // パレットの色を格納
	private int steps; // パレットの色数（段階数）

	public Palette() {
		colors = new Vector<Color>();
	}

	public void createHuePalette(float startH, float endH, int steps, float s,
			float b) { // 色相パレット
		this.steps = steps;
		float dH = (endH - startH) / (steps - 1);
		for (int i = 0; i < steps; i++) {
			colors.add(Color.getHSBColor(startH + i * dH, s, b));
		}
	}

	public void createSaturationPalette(float startS, float endS, int steps,
			float h, float b) { // 彩度パレット
		this.steps = steps;
		float dS = (endS - startS) / (steps - 1);
		for (int i = 0; i < steps; i++) {
			colors.add(Color.getHSBColor(h, startS + i * dS, b));
		}
	}

	public void createBrightnessPalette(float startB, float endB, int steps,
			float h, float s) { // 明度パレット
		this.steps = steps;
		float dB = (endB - startB) / (steps - 1);
		for (int i = 0; i < steps; i++) {
			colors.add(Color.getHSBColor(h, s, startB + i * dB));
		}
	}

	public void createGrayScalePalette(float startB, float endB, int steps) { // グレースケール・パレット
		createBrightnessPalette(startB, endB, steps, 0.0f, 0.0f);
	}

	public void createBWPalette() { // 黒白パレット
		this.steps = 2;
		colors.add(Color.BLACK);
		colors.add(Color.WHITE);
	}
	
	public void createWBPalette() { // 白黒パレット
		this.steps = 2;
		colors.add(Color.WHITE);
		colors.add(Color.BLACK);
	}

	public void createCustomPalette(Vector<Color> colors) { // カスタム・パレット
		this.steps = colors.size();
		this.colors = colors;
	}

	public Color color(int index) { // indexで指定された色を取り出す
		return colors.elementAt(index);
	}

	public Vector<Color> getColors() { // すべての色を取り出す
		return colors;
	}
	
	public int getSteps() { // 色数を返す
		return steps;
	}
}
