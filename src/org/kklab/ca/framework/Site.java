package org.kklab.ca.framework;

import java.util.Vector;

public interface Site { // サイト・インターフェース
	public abstract Site update(final Vector<Site> neighbors); // 更新（抽象メソッド）
	public abstract int intValue(); // サイトの状態を表す整数値
	public Object getValue();
}
