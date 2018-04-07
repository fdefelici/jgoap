package it.fdf.goap;

import java.util.ArrayList;
import java.util.List;

public class ASGGoalNode extends ASGNode {

	private GGoal _goal;

	public ASGGoalNode(GGoal aGoal) {
		super(null);
		_goal = aGoal;
	}

	public GState asState() {
		return _goal.expectations();
	}

	public List<ASGNode> neighborsFrom(List<GAction> _actions) {
		return new ArrayList<>();
	}

	public void addActionTo(List<GAction> actions) {
	}

	@Override
	public int g() {
		return 0;
	}
}
