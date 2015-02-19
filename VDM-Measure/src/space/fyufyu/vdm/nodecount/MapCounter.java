package space.fyufyu.vdm.nodecount;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 * Utility to keep multiple counters
 * 
 * @author f-ishikawa
 *
 */
public class MapCounter {

	private HashMap<String, Integer> counterMap = new HashMap<String, Integer>();

	public void count(String key) {
		if (!counterMap.containsKey(key)) {
			counterMap.put(key, 1);
		} else {
			counterMap.put(key, counterMap.get(key) + 1);
		}
	}

	public void count(String key, int count) {
		if (!counterMap.containsKey(key)) {
			counterMap.put(key, count);
		} else {
			counterMap.put(key, counterMap.get(key) + count);
		}
	}

	public Set<String> keySet() {
		return counterMap.keySet();
	}

	public int getValue(String key) {
		if (counterMap.containsKey(key)) {
			return counterMap.get(key);
		} else {
			return 0;
		}
	}
	
	public boolean isEmpty(){
		return counterMap.isEmpty();
	}

	public void merge(MapCounter counter){
		for(String key: counter.keySet()){
			count(key, counter.getValue(key));
		}
	}
	
	@Override
	public String toString() {
		return counterMap.toString();
	}
	
	public static MapCounter merge(Collection<MapCounter> counters){
		MapCounter ret = new MapCounter();
		for(MapCounter counter: counters){
			for(String key: counter.keySet()){
				ret.count(key, counter.getValue(key));
			}
		}
		return ret;
	}

}
