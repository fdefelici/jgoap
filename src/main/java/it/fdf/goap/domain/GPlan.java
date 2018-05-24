package it.fdf.goap.domain;

import java.util.ArrayList;
import java.util.List;

public class GPlan {

	private List<GAction> _actions = new ArrayList<>();
	private GGoal _goal = null;
	private boolean _isSuccesful = false;
	private boolean _isPerformed = false;
	
	public void perform() {
		if (!hasGoal()) return;
		_isPerformed = true;
		for (GAction each : _actions) {
			boolean isSuccess = each.perform();
			if (!isSuccess) {
				_isSuccesful = false;
				return;
			}
		}
		_isSuccesful = true;
	}

	public int actionSize() {
		return _actions.size();
	}

	public GAction actionAt(int index) {
		return _actions.get(index);
	}

	public void addAction(GAction anAction) {
		_actions.add(anAction);
	}

	public boolean hasActions() {
		return _actions.isEmpty();
	}

	public boolean hasGoal() {
		return getGoal() != null;
	}

	public GGoal getGoal() {
		return _goal;
	}

	public void setGoal(GGoal aGoal) {
		_goal = aGoal;
	}

	public boolean isSuccesful() {
		return isPerformed() && _isSuccesful;
	}
	
	public boolean isAborted() {
		return isPerformed() && !_isSuccesful;
	}
	
	public boolean isPerformed() {
		return _isPerformed;
	}

}
