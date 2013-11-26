package pl.edu.agh.pcontology.utils;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public final class CollectionUtils {
	
	/**
	 * Sorts entry set by values in ascending order.
	 * 
	 * @param map
	 * @return sorted entry set.
	 */
	public static <K,V extends Comparable<? super V>>
	SortedSet<Map.Entry<K,V>> sortByValuesAscending(Map<K,V> map) {
	    SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
	        new Comparator<Map.Entry<K,V>>() {
	            @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
	                int result = e1.getValue().compareTo(e2.getValue());
	            	return result != 0 ? result : 1;   //saves equal entries
	            }
	        }
	    );
	    sortedEntries.addAll(map.entrySet());
	    return sortedEntries;
	}
	
	/**
	 * Sorts entry set by values in descending order.
	 * 
	 * @param map
	 * @return sorted entry set.
	 */
	public static <K,V extends Comparable<? super V>>
	SortedSet<Map.Entry<K,V>> sortByValuesDescending(Map<K,V> map) {
	    SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
	        new Comparator<Map.Entry<K,V>>() {
	            @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
	                int result =  e2.getValue().compareTo(e1.getValue());
	                return result != 0 ? result : 1;
	            }
	        }
	    );
	    sortedEntries.addAll(map.entrySet());
	    return sortedEntries;
	}
}
