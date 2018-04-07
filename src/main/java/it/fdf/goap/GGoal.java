package it.fdf.goap;

public class GGoal {

	private String _name;
	private GState _expectations;
	private int _cost;

	public GGoal(String name) {
		_name = name;
		_expectations = new GState(_name+"expectations");
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

}
