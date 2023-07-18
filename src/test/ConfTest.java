import main.config.Action;
import main.config.Conf;
import main.util.Coord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfTest {

    @BeforeEach
    void setUp() {

    }

    /**
     *    0 1 2 3 4 5
     *    - - - - - -
     * 0|/.\./.\./.\./
     * 1|\./.\./.\./.\
     * 2|/S\./1\1/.\./
     * 3|\./.\./.\./.\
     * 4|/.\./.\./.\./
     * 5|\./.\./.\./.\
     */
    @Test
    void getStateAfterWinds() {
        Coord state = new Coord(2, 0);

        Coord wind1 = Conf.ACONF0.getStateAfterWinds(state);
        Coord wind2 = Conf.ACONF0.getStateAfterWinds(wind1); //blow state against land -> stay still

        assertEquals(new Coord(2, 1), wind1);
        assertEquals(wind1, wind2);
    }

    /**
     *    0 1 2 3 4 5
     *    - - - - - -
     * 0|/.\./.\./.\./
     * 1|\./.\./.\./.\
     * 2|/S\./1\1/S\./
     * 3|\./.\./.\./.\
     * 4|/.\./.\./.\./
     * 5|\./.\./.\./.\
     */
    @Test
    void actionResultOnDir() {
        // boost (make 2 steps) when only 1 step is possible -> make 1 step instead
        Coord state = new Coord(2, 0);
        Coord res = Conf.ACONF0.actionResultOnDir(state, Action.Boost, 1, "a");
        assertEquals(new Coord(2, 1), res);

        // boost when next state is land -> stay still
        Coord state2 = new Coord(2, 4);
        Coord res2 = Conf.ACONF0.actionResultOnDir(state2, Action.Boost, -1, "c");
        assertEquals(state2, res2);
    }

    @Test
    void actionResultOnAllDir() {
        // boost action with no obstacles
        Coord state1 = new Coord(4, 2);

        Coord[] res1 = Conf.ACONF0.actionResultOnAllDirs(state1, Action.Boost);

        assertEquals(6, res1.length);
        assertEquals(new Coord(4, 4), res1[0]);
        assertEquals(new Coord(5, 3), res1[1]);
        assertEquals(new Coord(5, 1), res1[2]);
        assertEquals(new Coord(4, 0), res1[3]);
        assertEquals(new Coord(3, 1), res1[4]);
        assertEquals(new Coord(3, 3), res1[5]);

        // run action on up tri with no obstacles
        Coord state2 = new Coord(4, 2);

        Coord[] res2 = Conf.ACONF0.actionResultOnAllDirs(state2, Action.Run);
        assertEquals(3, res2.length);
        assertEquals(new Coord(4, 3), res2[0]);
        assertEquals(new Coord(5, 2), res2[1]);
        assertEquals(new Coord(4, 1), res2[2]);

        // run action on down tri with no obstacles
        Coord state3 = new Coord(4, 3);

        Coord[] res3 = Conf.ACONF0.actionResultOnAllDirs(state3, Action.Run);

        assertEquals(3, res3.length);
        assertEquals(new Coord(4, 4), res3[0]);
        assertEquals(new Coord(4, 2), res3[1]);
        assertEquals(new Coord(3, 3), res3[2]);

        // run action with obstacles
        Coord state4 = new Coord(3, 2);

        Coord[] res4 = Conf.ACONF0.actionResultOnAllDirs(state4, Action.Run);

        assertEquals(3, res4.length);
        assertEquals(new Coord(3, 3), res4[0]);
        assertEquals(new Coord(3, 1), res4[1]);
        assertEquals(new Coord(3, 2), res4[2]);
    }


    /**
     *    0 1 2 3 4 5
     *    - - - - - -
     * 0|/.\./.\./.\./
     * 1|\./.\./.\./.\
     * 2|/.\./1\1/S\./
     * 3|\./.\./.\./.\
     * 4|/.\./.\./.\./
     * 5|\./.\./.\./.\
     */
    @Test
    void successor() {
        Coord state = new Coord(2, 4);
        Coord[] successors = Conf.ACONF0.successor(state, Action.Boost);

        assertEquals(4, successors.length);
        assertEquals(new Coord(3, 5), successors[0]);
        assertEquals(new Coord(3, 3), successors[1]);
        assertEquals(new Coord(2, 5), successors[2]);
        assertEquals(new Coord(1, 5), successors[3]);
    }

}