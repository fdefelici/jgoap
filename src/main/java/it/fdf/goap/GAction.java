package it.fdf.goap;

public class GAction {

	private String _name;
	private GState _expectations;
	private GState _results;
	private int _cost;

	public GAction(String name) {
		_name = name;
		_cost = 0;
		_expectations = new GState();
		_results = new GState();
	}

	public void expect(String name, boolean value) {
		_expectations.set(name, value);
	}
	public void result(String name, boolean value) {
		_results.set(name, value);
	}

	public GState expectations() {
		return _expectations;
	}

	public GState results() {
		return _results;
	}

	public void cost(int i) {
		_cost = i;
	}

	public int cost() {
		return _cost;
	}
	
	@Override
	public String toString() {
		return _name + ": " + expectations() + " => " + results();
	}
}
