package it.fdf.goap.astar.node.adapter;

import java.util.ArrayList;
import java.util.List;

import it.fdf.goap.astar.node.ASGNode;
import it.fdf.goap.domain.GAction;
import it.fdf.goap.domain.GGoal;
import it.fdf.goap.domain.GState;

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
	public int costG() {
		return 0;
	}
}
