package sample;
import java.util.Vector;

import org.kklab.ca.framework.AbstractSite;
import org.kklab.ca.framework.Rule;
import org.kklab.ca.framework.Site;

public class LifeGameSite extends AbstractSite<Integer> { // ライフゲーム・サイト
	public static final int ALIVE = 1; // 「生」サイトを表す定数
	public static final int DEAD = 0; // 「死」サイトを表す定数
	private static Rule<Integer> rule = new LifeGameRule();

	public LifeGameSite() { // デフォルト・コンストラクタ
		super(rule);
		this.value = DEAD; // デフォルトは「死」
	}

	public LifeGameSite(int value) { // valueで初期化
		super(value, rule);
	}

	@Override
	public Site update(Vector<Site> neighbors) { // 隣接サイトを元に更新
		return new LifeGameSite(rule.adapt(value, neighbors));
	}

	public String toString() { // 文字列化
		return Integer.toString(value);
	}

	public static int numberOfStates() { // サイトの状態数
		return 2;
	}

	@Override
	public int intValue() {
		return value;
	}
}
