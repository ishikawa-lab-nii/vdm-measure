package space.fyufyu.vdm.nodecount;

import java.util.HashMap;
import java.util.Set;

/**
 * Utility to keep multiple counters
 * 
 * @author f-ishikawa
 *
 */
class MapCounter {

	private HashMap<String, Integer> counterMap = new HashMap<String, Integer>();

	void count(String key) {
		if (!counterMap.containsKey(key)) {
			counterMap.put(key, 1);
		} else {
			counterMap.put(key, counterMap.get(key) + 1);
		}
	}

	void count(String key, int count) {
		if (!counterMap.containsKey(key)) {
			counterMap.put(key, count);
		} else {
			counterMap.put(key, counterMap.get(key) + count);
		}
	}

	Set<String> keySet() {
		return counterMap.keySet();
	}

	int getValue(String key) {
		if (counterMap.containsKey(key)) {
			return counterMap.get(key);
		} else {
			return 0;
		}
	}

	@Override
	public String toString() {
		return counterMap.toString();
	}

}
