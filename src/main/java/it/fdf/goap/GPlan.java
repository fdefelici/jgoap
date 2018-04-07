package it.fdf.goap;

import java.util.ArrayList;
import java.util.List;

public class GPlan {

	private List<GAction> _actions = new ArrayList<>();
	
	public void perform() {
		
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

}
