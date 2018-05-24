package it.fdf.goap.planner;

import it.fdf.goap.astar.planner.GPlanner;
import it.fdf.goap.domain.GAction;
import it.fdf.goap.domain.GGoal;
import it.fdf.goap.domain.GPlan;
import it.fdf.goap.domain.GState;
import junit.framework.TestCase;

public class GPlannerTest extends TestCase {

	public void testEmptyPlanner() {
		GPlanner planner = new GPlanner();
		GPlan plan = planner.plan();
		assertFalse(plan.hasGoal());
		assertEquals(0, plan.actionSize());
	}
	
	public void testAlreadySatisfiedGoal() {
		GState currentStatus = new GState();
		currentStatus.set("isMoving", false);
		currentStatus.set("hasTarget", false);
		
		GAction stopMove = new GAction("stop-moving");
		stopMove.expect("hasTarget", false);
		stopMove.expect("isMoving", true);
		stopMove.result("isMoving", false);
		
		GAction acquireTarget = new GAction("acquire-target");
		acquireTarget.expect("hasTarget", false);
		acquireTarget.expect("isMoving", false);
		acquireTarget.result("hasTarget", true);
		
		GAction followTarget = new GAction("follow-target");
		followTarget.expect("hasTarget", true);
		followTarget.expect("isMoving", false);
		followTarget.result("isMoving", true);
		
		GGoal idle = new GGoal("idle");
		idle.cost(1);
		idle.expect("isMoving", false);
		
		GGoal follow = new GGoal("follow");
		follow.cost(3);
		follow.expect("hasTarget", true);
		follow.expect("isMoving", true);
		
		GPlanner planner = new GPlanner();
		planner.setState(currentStatus);
		planner.addAction(stopMove);
		planner.addAction(acquireTarget);
		planner.addAction(followTarget);
		planner.addGoal(idle);
		planner.addGoal(follow);
		
		GPlan plan = planner.plan();
		assertTrue(plan.hasGoal());
		assertEquals(idle, plan.getGoal());
		assertEquals(0, plan.actionSize());
	}
	
	public void testReachGoalWithOneAction() {
		GState currentStatus = new GState();
		currentStatus.set("isMoving", true);
		currentStatus.set("hasTarget", true);
		
		GAction stopMove = new GAction("stop-moving");
		stopMove.expect("hasTarget", true);
		stopMove.expect("isMoving", true);
		stopMove.result("isMoving", false);
		stopMove.result("hasTarget", false);
		
		GAction acquireTarget = new GAction("acquire-target");
		acquireTarget.expect("hasTarget", false);
		acquireTarget.expect("isMoving", false);
		acquireTarget.result("hasTarget", true);
		
		GAction followTarget = new GAction("follow-target");
		followTarget.expect("hasTarget", true);
		followTarget.expect("isMoving", false);
		followTarget.result("isMoving", true);
		
		GGoal idle = new GGoal("idle");
		idle.cost(1);
		idle.expect("isMoving", false);
		
		GGoal follow = new GGoal("follow");
		follow.cost(3);
		follow.expect("hasTarget", true);
		follow.expect("isMoving", true);
		
		GPlanner planner = new GPlanner();
		planner.setState(currentStatus);
		planner.addAction(stopMove);
		planner.addAction(acquireTarget);
		planner.addAction(followTarget);
		planner.addGoal(idle);
		planner.addGoal(follow);
		
		GPlan plan = planner.plan();
		assertTrue(plan.hasGoal());
		assertEquals(idle, plan.getGoal());
		assertEquals(1, plan.actionSize());
		assertEquals(stopMove, plan.actionAt(0));
	}

	
	public void testReachGoalWithTwoActions() {
		GState currentStatus = new GState();
		currentStatus.set("isMoving", false);
		currentStatus.set("hasTarget", false);
		
		GAction stopMove = new GAction("stop-moving");
		stopMove.expect("hasTarget", false);
		stopMove.expect("isMoving", true);
		stopMove.result("isMoving", false);
		
		GAction acquireTarget = new GAction("acquire-target");
		acquireTarget.expect("hasTarget", false);
		acquireTarget.expect("isMoving", false);
		acquireTarget.result("hasTarget", true);
		
		GAction followTarget = new GAction("follow-target");
		followTarget.expect("hasTarget", true);
		followTarget.expect("isMoving", false);
		followTarget.result("isMoving", true);
		
		GGoal idle = new GGoal("idle");
		idle.cost(10);
		idle.expect("isMoving", false);
		
		GGoal follow = new GGoal("follow");
		follow.cost(3);
		follow.expect("hasTarget", true);
		follow.expect("isMoving", true);
		
		GPlanner planner = new GPlanner();
		planner.setState(currentStatus);
		planner.addAction(stopMove);
		planner.addAction(acquireTarget);
		planner.addAction(followTarget);
		planner.addGoal(idle);
		planner.addGoal(follow);
		
		GPlan plan = planner.plan();
		assertTrue(plan.hasGoal());
		assertEquals(follow, plan.getGoal());
		assertEquals(2, plan.actionSize());
		assertEquals(acquireTarget, plan.actionAt(0));
		assertEquals(followTarget, plan.actionAt(1));
	}
	

	public void testPizzaPlan() {
		GState currentStatus = new GState();
		currentStatus.set("hasIngridients", true);
		currentStatus.set("hasPhoneNumber", true);
		currentStatus.set("isHungry", true);
		
		GAction phone = new GAction("phone");
		phone.cost(1);
		phone.expect("hasPhoneNumber", true);
		phone.result("pizzaOrdered", true);
		
		GAction mix = new GAction("mix");
		mix.cost(10);
		mix.expect("hasIngridients", true);
		mix.result("foodMixed", true);
		
		GAction wait = new GAction("wait-pizza");
		wait.cost(1);
		wait.expect("pizzaOrdered", true);
		wait.result("hasPizza", true);
		
		GAction bake = new GAction("bake");
		wait.cost(5);
		bake.expect("foodMixed", true);
		bake.result("pizzaBaked", true);
		
		GAction serve = new GAction("serve");
		serve.cost(1);
		serve.expect("pizzaBaked", true);
		serve.result("hasPizza", true);
		
		GAction eat = new GAction("eat");
		eat.cost(1);
		eat.expect("hasPizza", true);
		eat.result("isHungry", false);
		
		GGoal notHungry = new GGoal("not-hungry");
		notHungry.expect("isHungry", false);
		
		GPlanner planner = new GPlanner();
		planner.setState(currentStatus);
		planner.addAction(phone);
		planner.addAction(mix);
		planner.addAction(wait);
		planner.addAction(bake);
		planner.addAction(serve);
		planner.addAction(eat);
		planner.addGoal(notHungry);
		
		GPlan plan = planner.plan();
		assertTrue(plan.hasGoal());
		assertEquals(notHungry, plan.getGoal());
		assertEquals(3, plan.actionSize());
		assertEquals(phone, plan.actionAt(0));
		assertEquals(wait, plan.actionAt(1));
		assertEquals(eat, plan.actionAt(2));
		
		
		wait.cost(1000000);
		plan = planner.plan();
		assertTrue(plan.hasGoal());
		assertEquals(notHungry, plan.getGoal());
		assertEquals(4, plan.actionSize());
		assertEquals(mix, plan.actionAt(0));
		assertEquals(bake, plan.actionAt(1));
		assertEquals(serve, plan.actionAt(2));
		assertEquals(eat, plan.actionAt(3));
	}
	
	
}
