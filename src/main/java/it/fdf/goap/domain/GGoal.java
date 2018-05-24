package it.fdf.goap.domain;

public class GGoal {

	private String _name;
	private GState _expectations;
	private int _cost;

	public GGoal(String name) {
		_name = name;
		_expectations = new GState();
	}

	public void expect(String name, boolean value) {
		_expectations.set(name, value);
	}

	public void cost(int aCost) {
		_cost = aCost;
	}

	public int cost() {
		return _cost;
	}

	public GState expectations() {
		return _expectations;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof GGoal)) return false;
		GGoal other = (GGoal) obj;
		if (!_name.equals(other._name)) return false;
		return true;
	}
	
	@Override
	public String toString() {
		return _name + ": [" + _cost +"] "+ expectations();
	}

}
