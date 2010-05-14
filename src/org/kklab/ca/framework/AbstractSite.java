package org.kklab.ca.framework;

import java.util.Vector;

public abstract class AbstractSite<E> implements Site { // 抽象サイト
	protected E value; // サイトの値
	protected Rule<E> rule; // サイトの更新ルール

	protected AbstractSite(final Rule<E> rule) { // ルールのみをコンストラクタ
		this.rule = rule;		
	}

	protected AbstractSite(final E value, final Rule<E> rule) { // 値とルールを設定するコンストラクタ
		this.value = value;
		this.rule = rule;
	}

	public abstract Site update(final Vector<Site> neighbors); // 更新（抽象メソッド）
	
	public abstract int intValue(); // サイトの状態を表す整数値
	
	public static int numberOfStates() { // サイトの状態を表す整数値の最大値
		return 0;
	}

	public final Object getValue() { // サイトの値を取得
		return value;
	}

	public void setValue(E value) { // サイトの値を設定
		this.value = value;
	}
}
