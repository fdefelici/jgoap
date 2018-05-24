package it.fdf.goap.astar.node.adapter;

import java.util.List;

import it.fdf.goap.astar.node.ASGNode;
import it.fdf.goap.domain.GAction;
import it.fdf.goap.domain.GGoal;
import it.fdf.goap.domain.GState;

public class ASGGoalNode extends ASGNode {

	private GGoal _goal;

	public ASGGoalNode(GGoal aGoal) {
		_goal = aGoal;
	}

	@Override
	public GState resultState() {
		return _goal.expectations();
	}
	
	@Override
	public GState entryState() {
		return _goal.expectations();
	}

	public void addActionTo(List<GAction> actions) {
	}

	@Override
	public int costG() {
		return 0;
	}
}
