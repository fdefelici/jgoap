package it.fdf.goap;

import java.util.List;

public abstract class ASGNode {

	public abstract GState asState();
	public abstract List<ASGNode> neighborsFrom(List<GAction> _actions);
	abstract public int g();
	abstract public void addActionTo(List<GAction> actions);
	
	private ASGNode _parent;
	
	public ASGNode(ASGNode parent) {
		_parent = parent;
	}

	public ASGNode parent() {
		return _parent;
	}

	public boolean cover(ASGNode another) {
		return asState().cover(another.asState());	
	}
	public boolean equals(Object obj) {
		if (!(obj instanceof ASGNode)) return false;
		return asState().equals(((ASGNode)obj).asState());
	}

	public int f() {
		return g(); //G + H 
	}
			
	public void parent(ASGNode aNode) {
		_parent = aNode;
	}

	public boolean hasParent() {
		return _parent != null;
	}

}
