import main.config.Action;
import main.util.Coord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActionTest {

    Coord down;
    Coord up;
    Coord edge;

    @BeforeEach
    void setUp() {
        up = new Coord(2, 2);
        down = new Coord(3, 2);
        edge = new Coord(5, 0);
    }

    /**
     *    0 1 2 3 4 5
     *    - - - - - -
     * 0|/.\./.\./.\./
     * 1|\./.\./.\./.\
     * 2|/.\r/x\r/.\./
     * 3|\./.\r/.\./.\
     * 4|/.\./.\./.\./
     * 5|\./.\./.\./.\
     */
    @Test
    void runFromUp1() {
        int step = 1;
        int forward = 1;
        int reverse = -1;
        Coord r1 = Action.resultOnDir(up, step, forward, "a");
        Coord r2 = Action.resultOnDir(up, step, forward, "c");
        Coord r3 = Action.resultOnDir(up, step, reverse, "b");
        Coord r4 = Action.resultOnDir(up, step, reverse, "a");
        Coord r5 = Action.resultOnDir(up, step, reverse, "c");
        Coord r6 = Action.resultOnDir(up, step, forward, "b");

        assertEquals(new Coord(2, 3), r1);
        assertEquals(new Coord(3, 2), r2);
        assertEquals(new Coord(3, 2), r3);
        assertEquals(new Coord(2, 1), r4);
        assertEquals(new Coord(2, 1), r5);
        assertEquals(new Coord(2, 3), r6);

    }

    /**
     *    0 1 2 3 4 5
     *    - - - - - -
     * 0|/.\./.\./.\./
     * 1|\./b\./b\./.\
     * 2|/b\./x\./b\./
     * 3|\./b\./b\./.\
     * 4|/.\./.\./.\./
     * 5|\./.\./.\./.\
     */
    @Test
    void boostFromUp1() {

        int step = 2;
        int forward = 1;
        int reverse = -1;
        Coord r1 = Action.resultOnDir(up, step, forward, "a");
        Coord r2 = Action.resultOnDir(up, step, forward, "c");
        Coord r3 = Action.resultOnDir(up, step, reverse, "b");
        Coord r4 = Action.resultOnDir(up, step, reverse, "a");
        Coord r5 = Action.resultOnDir(up, step, reverse, "c");
        Coord r6 = Action.resultOnDir(up, step, forward, "b");

        assertEquals(new Coord(2, 4), r1);
        assertEquals(new Coord(3, 3), r2);
        assertEquals(new Coord(3, 1), r3);
        assertEquals(new Coord(2, 0), r4);
        assertEquals(new Coord(1, 1), r5);
        assertEquals(new Coord(1, 3), r6);

    }

    /**
     *    0 1 2 3 4 5
     *    - - - - - -
     * 0|/.\./.\./.\./
     * 1|\./.\./.\./.\
     * 2|/.\./r\./.\./
     * 3|\./r\x/r\./.\
     * 4|/.\./.\./.\./
     * 5|\./.\./.\./.\
     */
    @Test
    void runFromDown1() {

        int step = 1;
        int forward = 1;
        int reverse = -1;
        Coord r1 = Action.resultOnDir(down, step, forward, "a");
        Coord r2 = Action.resultOnDir(down, step, forward, "c");
        Coord r3 = Action.resultOnDir(down, step, reverse, "b");
        Coord r4 = Action.resultOnDir(down, step, reverse, "a");
        Coord r5 = Action.resultOnDir(down, step, reverse, "c");
        Coord r6 = Action.resultOnDir(down, step, forward, "b");

        assertEquals(new Coord(3, 3), r1);
        assertEquals(new Coord(3, 3), r2);
        assertEquals(new Coord(3, 1), r3);
        assertEquals(new Coord(3, 1), r4);
        assertEquals(new Coord(2, 2), r5);
        assertEquals(new Coord(2, 2), r6);
    }

    /**
     *    0 1 2 3 4 5
     *    - - - - - -
     * 0|/.\./.\./.\./
     * 1|\./.\./.\./.\
     * 2|/.\b/.\b/.\./
     * 3|\b/.\x/.\b/.\
     * 4|/.\b/.\b/.\./
     * 5|\./.\./.\./.\
     */
    @Test
    void boostFromDown1() {

        int step = 2;
        int forward = 1;
        int reverse = -1;
        Coord r1 = Action.resultOnDir(down, step, forward, "a");
        Coord r2 = Action.resultOnDir(down, step, forward, "c");
        Coord r3 = Action.resultOnDir(down, step, reverse, "b");
        Coord r4 = Action.resultOnDir(down, step, reverse, "a");
        Coord r5 = Action.resultOnDir(down, step, reverse, "c");
        Coord r6 = Action.resultOnDir(down, step, forward, "b");

        assertEquals(new Coord(3, 4), r1);
        assertEquals(new Coord(4, 3), r2);
        assertEquals(new Coord(4, 1), r3);
        assertEquals(new Coord(3, 0), r4);
        assertEquals(new Coord(2, 1), r5);
        assertEquals(new Coord(2, 3), r6);
    }

    /**
     *    0 1 2 3 4 5
     *    - - - - - -
     * 0|/.\./.\./.\./
     * 1|\./.\./.\./.\
     * 2|/.\./.\./.\./
     * 3|\./.\./.\./.\
     * 4|/.\./.\./.\./
     * 5|\x/.\./.\./.\
     */
    @Test
    void actionOnEdge() {
        int runStep = 1;
        int boostStep = 2;
        int forward = 1;
        int reverse = -1;

        Coord r1 = Action.resultOnDir(edge, runStep, reverse, "a");
        Coord r2 = Action.resultOnDir(edge, boostStep, reverse, "b");

        assertEquals(new Coord(5, -1), r1);
        assertEquals(new Coord(6, -1), r2);

    }
}