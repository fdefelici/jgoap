package it.fdf.goap.task;

import it.fdf.goap.task.IGTask;

public class MockGTask implements IGTask {
	
	public static MockGTask failing() {
		return new MockGTask(false);
	}
	
	public static MockGTask succesful() {
		return new MockGTask(true);
	}
	
	private boolean _result;

	private MockGTask(boolean isSuccess) {
		_result = isSuccess;
	}
	
	private boolean _hasBeenPerformed;
	
	@Override
	public boolean perform() {
		_hasBeenPerformed = true;
		return _result;
	}

	public boolean hasBeenPerfomed() {
		return _hasBeenPerformed;
	}

}
