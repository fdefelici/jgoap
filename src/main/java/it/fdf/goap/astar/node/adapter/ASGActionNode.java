package it.fdf.goap.astar.node.adapter;

import java.util.List;

import it.fdf.goap.astar.node.ASGNode;
import it.fdf.goap.domain.GAction;
import it.fdf.goap.domain.GState;

public class ASGActionNode extends ASGNode {

	private GAction _action;

	public ASGActionNode(GAction each) {
		_action = each;
	}

	@Override
	public GState resultState() {
		return parent().resultState().mergeWith(_action.results());
	}
	
	@Override
	public GState entryState() {
		return _action.expectations();
	}
	
	@Override
	public void addActionTo(List<GAction> actions) {
		actions.add(_action);
	}

	@Override
	public int costG() {
		return parent().costG() + _action.cost();
	}
}
