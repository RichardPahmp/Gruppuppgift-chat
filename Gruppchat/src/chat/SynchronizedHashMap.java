package chat;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class SynchronizedHashMap<K,V> {
	private HashMap<K,V> map = new HashMap<K,V>();
	
	public synchronized V put(K key, V value) {
		return map.put(key, value);
	}
	
	public synchronized V remove(K key) {
		return map.remove(key);
	}
	
	public synchronized void clear() {
		map.clear();
	}
	
	public synchronized V get(K key) {
		return map.get(key);
	}
	
	public synchronized Set<K> keySet() {
		return map.keySet();
	}
	
	public synchronized Collection<V> values(){
		return map.values();
	}
	
	public synchronized boolean containsKey(Object key) {
		return map.containsKey(key);
	}
	
	public synchronized boolean containsValue(Object value) {
		return map.containsValue(value);
	}
}
