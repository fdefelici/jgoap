package it.fdf.goap;

import java.util.ArrayList;
import java.util.List;

public class GPlanner {

	private GState _state;
	private List<GAction> _actions;
	private List<GGoal> _goals;

	public GPlanner() {
		_state = new GState("void-state");
		_actions = new ArrayList<>();
		_goals = new ArrayList<>();
	}
	
	public void setState(GState state) {
		_state = state;
	}

	public void addAction(GAction anAction) {
		_actions.add(anAction);
	}
	
	public void addGoal(GGoal aGoal) {
		_goals.add(aGoal);
	}

	public GPlan plan() {		
		GGoal pickedGoal = pickGoalWithLowerCost();
		if (pickedGoal == null) return new GPlan();
		
		List<ASGNode> openNodes = new ArrayList<>();
		List<ASGNode> closedNodes = new ArrayList<>();
		
		ASGNode startNode = new ASGStateNode(_state);
		ASGNode endNode = new ASGGoalNode(pickedGoal); 
		openNodes.add(startNode);
		
		while (!openNodes.isEmpty()) {
			ASGNode pickedNode = pickNodeWithLowerCostFrom(openNodes);
			openNodes.remove(pickedNode);
			closedNodes.add(pickedNode);
			
			if (pickedNode.cover(endNode)) {
				endNode.parent(pickedNode);
				break;
			}
			
			List<ASGNode> neighbors = pickedNode.neighborsFrom(_actions);
			for (ASGNode eachNeigh : neighbors) {
				if (closedNodes.contains(eachNeigh)) continue;
				if (isAlreadyOpenWithLessCost(eachNeigh, openNodes)) continue;
				openNodes.add(eachNeigh);
			}
			
		}
		
		return buildPlanFrom(endNode);
	}

	private GPlan buildPlanFrom(ASGNode endNode) {
		ASGNode current = endNode;
		List<GAction> lastToFirst = new ArrayList<>();
		while(current.hasParent()) {
			ASGNode parent = current.parent();
			parent.addActionTo(lastToFirst);
			current = parent;
		}
		
		GPlan result = new GPlan();
		if (lastToFirst.isEmpty()) return result;
		
		for (int i = lastToFirst.size()-1; i >= 0; i--) {
			result.addAction(lastToFirst.get(i));
		}
		return result;
	}

	
	private ASGNode pickNodeWithLowerCostFrom(List<ASGNode> openNodes) {
		ASGNode result = null;
		for (ASGNode each : openNodes) {
			if (result == null) result = each;
			else if (each.f() < result.f()) result = each;
		}
		return result;
	}

	private boolean isAlreadyOpenWithLessCost(ASGNode eachNeigh, List<ASGNode> openedNodes) {
		for (ASGNode each : openedNodes) {
			if (each.equals(eachNeigh) && each.g() < eachNeigh.g()) return true; 
		}
		return false;
	}

	private GGoal pickGoalWithLowerCost() {
		GGoal result = null;
		for (GGoal each : _goals) {
			if (result == null) result = each;
			else if (each.cost() < result.cost()) result = each;
		}
		return result;
	}

}
