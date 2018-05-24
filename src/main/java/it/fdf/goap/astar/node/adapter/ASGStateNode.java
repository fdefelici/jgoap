package it.fdf.goap.astar.node.adapter;

import java.util.ArrayList;
import java.util.List;

import it.fdf.goap.astar.node.ASGNode;
import it.fdf.goap.domain.GAction;
import it.fdf.goap.domain.GState;

public class ASGStateNode extends ASGNode {

	private GState _state;

	public ASGStateNode(GState state) {
		super(null);
		_state = state;
	}

	public GState asState() {
		return _state;
	}

	public List<ASGNode> neighborsFrom(List<GAction> actions) {
		List<ASGNode> result = new ArrayList<>();
		for (GAction each : actions) {
			if (asState().cover(each.expectations())) result.add(new ASGActionNode(each, this));
		}
		return result;
	}

	@Override
	public void addActionTo(List<GAction> actions) {
	}

	@Override
	public int costG() {
		return 0;
	}
}
