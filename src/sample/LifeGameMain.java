package sample;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;

import org.kklab.ca.framework.Site;
import org.kklab.ca.graphics.CAAnimation;
import org.kklab.ca.graphics.Palette;

public class LifeGameMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int size = 50;
		int steps = 180;
		int tile = 10;
		int tick = 300;

		// グライダーの初期格子
		int[] glider = new int[size * size];
		glider[1] = 1;
		glider[size + 2] = 1;
		glider[2 * size] = 1;
		glider[2 * size + 1] = 1;
		glider[2 * size + 2] = 1;
		glider[4 * size + 20] = 1;
		glider[4 * size + 21] = 1;
		glider[4 * size + 22] = 1;

		Site[] init = new LifeGameSite[size * size];
		for (int i = 0; i < size * size; i++) {
			init[i] = new LifeGameSite(glider[i]);
		}

		JFrame frame = new JFrame(); // フレームを作成
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Life Game : Glider and Blink");

		Palette palette = new Palette(); // パレットを作成
		palette.createWBPalette(); // サイトの状態に応じて白黒で表示するパレット

		CAAnimation game = new LifeGame(size, steps, init, frame, 10,
				10, tile, tick, palette, true); // 50x50正方格子で180世代

		ExecutorService scheduler = Executors
				.newSingleThreadExecutor();
		scheduler.execute(game); // 実行

		frame.setVisible(true);
	}

}
