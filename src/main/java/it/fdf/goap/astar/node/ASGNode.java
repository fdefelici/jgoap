package it.fdf.goap.astar.node;

import java.util.List;

import it.fdf.goap.domain.GAction;
import it.fdf.goap.domain.GState;

public abstract class ASGNode {

	public abstract GState asState();
	public abstract List<ASGNode> neighborsFrom(List<GAction> _actions);
	abstract public int costG();
	
	abstract public void addActionTo(List<GAction> actions);
	
	private ASGNode _parent;
	private int _costH;
	
	public ASGNode(ASGNode parent) {
		_parent = parent;
	}

	public ASGNode parent() {
		return _parent;
	}

	public boolean canReach(ASGNode another) {
		return asState().cover(another.asState());	
	}
	public boolean equals(Object obj) {
		if (!(obj instanceof ASGNode)) return false;
		return asState().equals(((ASGNode)obj).asState());
	}

	public int costF() {
		return costG() + costH(); 
	}
			
	public void parent(ASGNode aNode) {
		_parent = aNode;
	}

	public boolean hasParent() {
		return _parent != null;
	}
	public void costH(int h) {
		_costH = h;
	}
	
	public int costH() {
		return _costH;
	}

}
