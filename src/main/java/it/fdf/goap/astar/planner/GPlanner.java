package it.fdf.goap.astar.planner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import it.fdf.goap.astar.node.ASGNode;
import it.fdf.goap.astar.node.adapter.ASGGoalNode;
import it.fdf.goap.astar.node.adapter.ASGStateNode;
import it.fdf.goap.domain.GAction;
import it.fdf.goap.domain.GGoal;
import it.fdf.goap.domain.GPlan;
import it.fdf.goap.domain.GState;

public class GPlanner {

	private GState _state;
	private List<GAction> _actions;
	private List<GGoal> _goals;

	public GPlanner() {
		_state = new GState();
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
		sortByLowerCost(_goals);
		
		GPlan result = new GPlan();
		for (GGoal eachGoal : _goals) {			
			List<ASGNode> openNodes = new ArrayList<>();
			List<ASGNode> closedNodes = new ArrayList<>();
			
			ASGNode startNode = new ASGStateNode(_state);
			ASGNode endNode = new ASGGoalNode(eachGoal); 
			openNodes.add(startNode);
			
			while (!openNodes.isEmpty()) {
				ASGNode pickedNode = pickNodeWithLowerCostFrom(openNodes);
				openNodes.remove(pickedNode);
				closedNodes.add(pickedNode);
				
				if (pickedNode.canReach(endNode)) {
					endNode.parent(pickedNode);
					break;
				}
				
				List<ASGNode> neighbors = pickedNode.neighborsFrom(_actions);
				for (ASGNode eachNeigh : neighbors) {
					if (closedNodes.contains(eachNeigh)) continue;
					computeHestimatedCost(eachNeigh, endNode);
					if (isAlreadyOpenWithLessCost(eachNeigh, openNodes)) continue;
					openNodes.add(eachNeigh);
				}
			}
			
			if (!endNode.hasParent()) continue; // SOLUTION NOT FOUND FOR THE GOAL. TRY WITH THE NEXT ONE.	
			result = buildPlanFrom(endNode);
			result.setGoal(eachGoal);
			break;
		}
		return result;
	}

	private void sortByLowerCost(List<GGoal> goals) {
		Collections.sort(goals, new Comparator<GGoal>() {
			public int compare(GGoal o1, GGoal o2) {
				if (o1.cost() < o2.cost()) return -1;
				if (o1.cost() > o2.cost()) return 1;
				return 0;
			}
		});
	}

	private void computeHestimatedCost(ASGNode node, ASGNode endNode) {
		int stateDiff = node.asState().deltaTo(endNode.asState()).size();
		node.costH(stateDiff);
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
			else if (each.costF() < result.costF()) result = each;
		}
		return result;
	}

	private boolean isAlreadyOpenWithLessCost(ASGNode eachNeigh, List<ASGNode> openedNodes) {
		for (ASGNode each : openedNodes) {
			if (each.equals(eachNeigh) && each.costG() < eachNeigh.costG()) return true; 
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
