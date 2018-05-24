package it.fdf.goap.astar.node;

import java.util.List;

import it.fdf.goap.domain.GAction;
import it.fdf.goap.domain.GState;

public abstract class ASGNode {

	private ASGNode _parent;
	private int _costH;
	
	abstract public int costG();
	abstract public GState entryState();
	abstract public GState resultState();
	abstract public void addActionTo(List<GAction> actions);

	public void parent(ASGNode aNode) {
		_parent = aNode;
	}

	public ASGNode parent() {
		return _parent;
	}

	public boolean canReach(ASGNode another) {
		return resultState().cover(another.entryState());	
	}
		
	public boolean equals(Object obj) {
		if (!(obj instanceof ASGNode)) return false;
		return resultState().equals(((ASGNode)obj).resultState());
	}

	public int costF() {
		return costG() + costH(); 
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
