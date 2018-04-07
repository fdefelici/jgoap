package it.fdf.goap;

public class GState {

	private String _name;
	private XMap<String, Boolean> _map;

	public GState(String name) {
		_name = name;
		_map = new XMap<>();
	}

	public void set(String name, boolean value) {
		_map.put(name, value);
	}

	public boolean cover(GState another) {
		return another._map.isSubsetOf(_map);
	}
	
	@Override
	public String toString() {
		return _map.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		GState other = (GState)obj;
		//return other._name.equals(_name);
		return _map.equals(other._map);
	}

	public GState mergeWith(GState another) {
		GState result = new GState(_name+"+"+another._name);
		result._map.put(_map);
		result._map.put(another._map);
		return result;
	}

}
