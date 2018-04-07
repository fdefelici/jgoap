package it.fdf.goap;

import java.util.ArrayList;
import java.util.List;

public class ASGActionNode extends ASGNode {

	private GAction _action;

	public ASGActionNode(GAction each, ASGNode parent) {
		super(parent);
		_action = each;
	}

	@Override
	public GState asState() {
		return parent().asState().mergeWith(_action.results());
	}

	@Override
	public List<ASGNode> neighborsFrom(List<GAction> actions) {
		List<ASGNode> result = new ArrayList<>();
		for (GAction each : actions) {
			if (asState().cover(each.expectations())) result.add(new ASGActionNode(each, this));
		}
		return result;
	}

	@Override
	public void addActionTo(List<GAction> actions) {
		actions.add(_action);
	}

	@Override
	public int g() {
		return parent().g() + _action.cost();
	}

}
