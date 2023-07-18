package main.config;

import main.util.Coord;

/**
 * Wind represents a vector along the a, b or c coordinate
 * where any state is incremented by 1 in the natural or reverse direction
 *
 * 1. A wind is said to move along the ith "a"-coordinate line if it influences all coordinates with a = i
 *    Similarly for "b", "c"-coordinate line
 *
 * 2. The direction of the wind is defined by either a natural direction (denoted by +) or a reverse direction (-).
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
public enum Wind {

    /**
     *        NW      NE
     *         v     v
     *          \   /
     *  West >>> state <<< East
     *          /  \
     *         ^    ^
     *       NW     SE
     */
    West("a", -2, 1), // wind from west to east on a-2
    East("a", -2, -1),// wind from east to west on a-2
    NorthWest("c", 0, 1),// wind from northwest on c0
    SouthEast("c", 0, -1),// wind from southeast on c0
    SouthWest("b", 2, 1),// wind from southwest on b2
    NorthEast("b", 2, -1);//wind from northeast on b2

    private final String axis;
    private final int index;
    private final int dir;

    Wind(String axis, int index, int dir) {
        this.axis = axis;
        this.index = index;
        this.dir = dir;
    }


    /**
     * Wind creates a new state if the given state matches at least coordinate with the wind
     */
    public Coord blow(Coord state) {
        int step = 1;
        int a = state.getACoord();
        int b = state.getBCoord();
        int c = state.getCCoord();

        if (axis == "a" && index == a || axis == "b" && index == b || axis == "c" && index == c) {
            return Action.resultOnDir(state, step, dir, axis);
        }
        return state;
    }

}
