package it.fdf.goap.astar.node.adapter;

import java.util.List;

import it.fdf.goap.astar.node.ASGNode;
import it.fdf.goap.domain.GAction;
import it.fdf.goap.domain.GState;

public class ASGStateNode extends ASGNode {

	private GState _state;

	public ASGStateNode(GState state) {
		_state = state;
	}

	@Override
	public GState resultState() {
		return _state;
	}
	
	@Override
	public GState entryState() {
		return new GState();
	}

	@Override
	public void addActionTo(List<GAction> actions) {
	}

	@Override
	public int costG() {
		return 0;
	}
}
