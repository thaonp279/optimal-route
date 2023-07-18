package main.config;

import main.util.Coord;

import java.util.Arrays;

/**
 * Configuration class contains all configurations of a search problem including:
 * 1. State space map
 * 2. Start coordinate
 * 3. Goal coordinate
 * 4. Possible actions
 * 5. Wind factor
 *
 * Adapted from the starter code provided by @author at258
 */

public enum Conf{

	ACONF0(Map.JMAP00,0,0,3,3, Action.values(), new Wind[]{Wind.West}),
	ACONF1(Map.JMAP00,0,0,3,3, Action.values(), new Wind[]{}),
	ACONF2(Map.MAP0,2,0,2,5, Action.values(), new Wind[]{Wind.West}),
	ACONF3(Map.MAP1, 0, 0, 1, 3, Action.values(), new Wind[] {Wind.NorthEast}),


	//************************TEST Configurations  

	JCONF00(Map.JMAP00,1,1,3,4), //JCONF00 is the configuration in the spec
	JCONF11(Map.JMAP00,1,1,2,3), //JCONF11 fails

	//configurations used in the tests 
	JCONF01(Map.JMAP01,1,1,1,2),
	JCONF02(Map.JMAP01,2,1,2,2),
	JCONF03(Map.JMAP01,0,0,2,2), 
	JCONF04(Map.JMAP02,0,2,0,0),   
	JCONF05(Map.JMAP02,0,0,2,2),   



	//************************Configurations for evaluation 

	CONF0(Map.MAP0,0,0,5,5),
	CONF1(Map.MAP0,5,5,0,0),
	CONF2(Map.MAP0,0,5,5,0),
	CONF3(Map.MAP0,5,2,1,4),
	CONF4(Map.MAP0,4,4,0,2),

	CONF5(Map.MAP1,0,0,4,4),
	CONF6(Map.MAP1,4,4,0,0),
	CONF7(Map.MAP1,2,0,2,4),
	CONF8(Map.MAP1,4,0,0,0),
	CONF9(Map.MAP1,4,3,1,1),

	CONF10(Map.MAP2,5,5,2,3),
	CONF11(Map.MAP2,2,3,1,4),
	CONF12(Map.MAP2,5,0,4,5),
	CONF13(Map.MAP2,4,1,0,5),
	CONF14(Map.MAP2,0,0,4,5),

	CONF15(Map.MAP3,0,0,7,7),
	CONF16(Map.MAP3,9,9,7,8),
	CONF17(Map.MAP3,4,0,4,9),
	CONF18(Map.MAP3,1,1,4,5),
	CONF19(Map.MAP3,3,7,4,2),

	CONF20(Map.MAP4,0,7,6,1),
	CONF21(Map.MAP4,6,5,0,0),
	CONF22(Map.MAP4,1,0,4,3),
	CONF23(Map.MAP4,6,0,2,5),
	CONF24(Map.MAP4,0,1,6,6)
	;

	private final Map map; 
	private final Coord s;
	private final Coord g;
	private Action[] actions = new Action[] {Action.Run};
	private Wind[] winds = new Wind[] {};

	Conf(Map map, int rs, int cs, int rg, int cg){
		this.map=map;
		s=new Coord(rs,cs);//start: departure port
		g=new Coord(rg,cg);//goal: destination port
	}

	Conf(Map map, int rs, int cs, int rg, int cg, Action[] actions, Wind[] winds){
		this.map=map;
		s=new Coord(rs,cs);//start: departure port
		g=new Coord(rg,cg);//goal: destination port
		this.actions = actions;
		this.winds = winds;
	}

	/**
	 * Return all legal successor states as the result of:
	 * 		1. applying action on given state and
	 * 		2. adding wind factor
	 */
	public Coord[] successor(Coord state, Action action) {
		Coord[] results = actionResultOnAllDirs(state, action);

		results = Arrays.stream(results)
				.map(this::getStateAfterWinds) // add wind
				.filter(this::isLegalCoord) // remove states made illegal by wind
				.distinct() // remove duplicate states
				.toArray(Coord[]::new);
		return results;
	}

	/**
	 * Return all resulting states after taking action on all possible directions, without influence by wind factor.
	 * Order resulting states clock-wise starting from to the right of state
	 */
	public Coord[] actionResultOnAllDirs(Coord state, Action action) {
		int forward = 1;
		int reverse = -1;

		Coord[] results = new Coord[] {
				actionResultOnDir(state, action, forward, "a"),
				actionResultOnDir(state, action, forward, "c"),
				actionResultOnDir(state, action, reverse, "b"),
				actionResultOnDir(state, action, reverse, "a"),
				actionResultOnDir(state, action, reverse, "c"),
				actionResultOnDir(state, action, forward, "b")
		};
		results = Arrays.stream(results).distinct().toArray(Coord[]::new); // remove duplicate states
		return results;
	}

	/**
	 * Return all resulting states after taking action to a specific direction.
	 * If action moves state beyond legal state, return the further possible state.
	 *
	 * ie. if action is 3 steps from state but after the second step,
	 * resulting state is a land or out of bound, return state after step 1
	 *
	 * @param stepDir the direction of the action. 1 for natural direction and -1 for reverse direction
	 * @param axis the name of the coordinate line ("a", "b" or "c") along which the state moves.
	 * Refer to Action class for more detailed definitions
	 */
	public Coord actionResultOnDir(Coord state, Action action, int stepDir, String axis) {
		Coord interState = state;

		//check if any state leading up to and including result state is land or out of bound
		for (int step = 1; step <= action.getStepSize(); step ++) {
			Coord temp = Action.resultOnDir(state, step, stepDir, axis);
			if (!isLegalCoord(temp) || map.isLand(temp.getR(), temp.getC())) {
				return interState; // state immediately before
			}
			interState = temp;
		}
		return interState;
	}


	/**
	 * Get resulting state after the influence of wind.
	 */
	public Coord getStateAfterWinds(Coord state) {
		for (Wind wind : winds) {
			Coord temp = wind.blow(state);
			if (!map.isLand(temp.getR(), temp.getC())) { //if wind blows ferry against land, stay still
				state = temp;
			}
		}
		return state;
	}

	/**
	 * Get the minimum cost among all actions
	 */
	public double getMinActionCost() {
		double minCost = Double.POSITIVE_INFINITY;
		for(Action action: actions) {
			if (action.getCost() < minCost) {
				minCost = action.getCost();
			}
		}
		return minCost;
	}

	public Map getMap() {
		return map;
	}

	public Coord getS() {
		return s;
	}

	public Coord getG() {
		return g;
	}

	public boolean isLegalCoord(Coord coord) {
		return map.isLegalRowCol(coord.getR(), coord.getC());
	}

	public boolean isGoal(Coord coord) {
		return coord.equals(g);
	}

	public Action[] getActions() {
		return actions;
	}

}
