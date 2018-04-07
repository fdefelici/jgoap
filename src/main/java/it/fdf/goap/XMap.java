package it.fdf.goap;

import java.util.LinkedHashMap;
import java.util.Map;

public class XMap<TKey, TVal> {

	private Map<TKey, TVal> _pairs;

	public XMap() {
		_pairs = new LinkedHashMap<>();
	}
	
	public void put(TKey key, TVal value) {
		_pairs.put(key, value);
	}

	public boolean hasKey(TKey key) {
		return _pairs.containsKey(key);
	}

	public TVal get(TKey aKey) {
		return _pairs.get(aKey);
	}
	
	public int size() {
		return _pairs.size();
	}

	public boolean isSubsetOf(XMap<TKey, TVal> other) {
		if (size() > other.size()) return false;
		for (TKey eachKey : _pairs.keySet()) {
			if (!other.hasKey(eachKey)) return false;
			if (!other.get(eachKey).equals(_pairs.get(eachKey))) return false;
		}
		return true;
	}

	public void put(XMap<TKey, TVal> another) {
		_pairs.putAll(another._pairs);
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof XMap<?,?>)) return false;
		@SuppressWarnings("unchecked")
		XMap<TKey, TVal> other = (XMap<TKey, TVal>)obj;
		if (size() != other.size()) return false;
		for (TKey eachKey : _pairs.keySet()) {
			TVal val = _pairs.get(eachKey);
			TVal otherVal = other.get(eachKey);
			if (!val.equals(otherVal)) return false;
		}
		return true;
	}
	
	public String toString() {
		return _pairs.toString();
	}

	public XMap<TKey,TVal> deltaTo(XMap<TKey,TVal> other) {
		XMap<TKey, TVal> result = new XMap<>();
		for (TKey eachKey : other._pairs.keySet()) {
			TVal otherVal = other.get(eachKey);
			TVal val = _pairs.get(eachKey);
			if (!otherVal.equals(val)) result.put(eachKey, otherVal);
		}
		return result;
	}
	
}
