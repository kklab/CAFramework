package sample;
import java.util.Vector;

import org.kklab.ca.framework.Rule;
import org.kklab.ca.framework.Site;


public class LifeGameRule extends Rule<Integer> {

	@Override
	public Integer adapt(Integer value, Vector<Site> neighbors) {
		int lives = 0;
		int result = LifeGameSite.DEAD; // デフォルトは「死」
		for (Site n : neighbors) { // 生きてるサイトの数を数える
			lives += (Integer) n.getValue();
		}
		if (lives == 2) { // 生きているサイトが2つならサイトの値は不変
			result = value;
		} else if (lives == 3) { // 生きているサイトが3つならサイトは「生」
			result = LifeGameSite.ALIVE;
		}
		return result;
	}

}
