package it.fdf.goap.domain;

import it.fdf.goap.task.MockGTask;
import junit.framework.TestCase;

public class GPlanTest extends TestCase {
	
	public void testSkipPlanWithoutGoal() {
		GPlan plan = new GPlan();
		MockGTask task1 = MockGTask.failing();
		plan.addAction(new GAction("act1", task1));
		MockGTask task2 = MockGTask.succesful();
		plan.addAction(new GAction("act2", task2));
		
		plan.perform();
		assertFalse(task1.hasBeenPerfomed());
		assertFalse(task2.hasBeenPerfomed());
		assertFalse(plan.hasGoal());
		assertFalse(plan.isPerformed());
		assertFalse(plan.isSuccesful());
		assertFalse(plan.isAborted());
	}
	
	public void testSuccesfulPlan() {
		GPlan plan = new GPlan();
		plan.setGoal(new GGoal("goal1"));
		MockGTask task1 = MockGTask.succesful();
		plan.addAction(new GAction("act1", task1));
		MockGTask task2 = MockGTask.succesful();
		plan.addAction(new GAction("act2", task2));
		
		plan.perform();
		assertTrue(task1.hasBeenPerfomed());
		assertTrue(task2.hasBeenPerfomed());
		assertTrue(plan.hasGoal());
		assertTrue(plan.isPerformed());
		assertTrue(plan.isSuccesful());
		assertFalse(plan.isAborted());
	}
	
	public void testFailingPlan() {
		GPlan plan = new GPlan();
		plan.setGoal(new GGoal("goal1"));
		MockGTask task1 = MockGTask.failing();
		plan.addAction(new GAction("act1", task1));
		MockGTask task2 = MockGTask.succesful();
		plan.addAction(new GAction("act2", task2));
		
		plan.perform();
		assertTrue(task1.hasBeenPerfomed());
		assertFalse(task2.hasBeenPerfomed());
		assertTrue(plan.hasGoal());
		assertTrue(plan.isPerformed());
		assertFalse(plan.isSuccesful());
		assertTrue(plan.isAborted());
	}

}
