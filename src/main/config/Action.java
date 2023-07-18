package main.config;


import main.util.Coord;

/**
 * The Action class describes possible action on a state.
 * An action can be applied on the state in a particular direction and orientation specified by:
 *      1. The axis that it's moving along
 *      2. The direction of the move
 *
 * 1. The coordinate (a,b,c) is said to move along the "a" coordinate line if:
 *      1. (a',b',c') is the coordinate of the resulting state
 *      2. a' = a
 *      and similarly for "b", "c" coordinate line
 *
 * 2. The direction of an action is defined by either a natural direction (denoted by +) or a reverse direction (-).
 *      1. Along "a": natural if move to the right, reverse if to the left
 *      2. Along "b": natural if move up, reverse if move down
 *      3. Along "c": natural if move down, reverse if move up
 *      Shown in graph below.
 *
 *        -c      +b
 *         ^     ^
 *          \   /
 *  -a <--- state ---> +a
 *          /  \
 *         v    v
 *       -b     +c
 */
public enum Action {

    Run(1, 1),
    Boost(2, 3),
    Stop(0, 0.25);

    private final double cost; //cost of performing action
    private final int stepSize; //distance from current state after taking action

    Action(int stepSize, double cost) {
        this.cost = cost;
        this.stepSize = stepSize;
    }

    /**
     * Result state after taking a specific action.
     * @param state current state to take action from
     * @param step step size that the action will take
     * @param stepDir the direction of the action. 1 for natural direction and -1 for reverse direction
     * @param axis the name of the coordinate line ("a", "b" or "c") along which the state moves.
     * @return result state
     *
     */
    public static Coord resultOnDir(Coord state, int step, int stepDir, String axis) {
        boolean validStepDir = stepDir == 1 || stepDir == -1;
        boolean validStep = step >= 0;
        boolean validAxis = axis == "a" || axis == "b" || axis == "c";

        if (validStepDir && validStep && validAxis) {
            int a = state.getACoord();
            int b = state.getBCoord();
            int c = state.getCCoord();
            int triDir = state.getDir(); // direction of triangle, 0 if upward, 1 if downward

            // step size calculation
            int s1 = stepDir * (step + triDir) / 2;
            int s2 = stepDir * (step + 1 - triDir) / 2;

            // if in reverse direction, exchange step size variables
            if (stepDir == -1) {
                int temp = s1;
                s1 = s2;
                s2 = temp;
            }
            switch (axis) {
                case "a":
                    b += s1;
                    c += s2;
                    break;
                case "b":
                    a += s1;
                    c += s2;
                    break;
                case "c":
                    b += s1;
                    a -= s2;
                    break;
            }
            return new Coord(a, b, c);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int getStepSize() {
        return stepSize;
    }

    public double getCost() {
        return cost;
    }

}
