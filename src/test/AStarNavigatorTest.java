import main.config.Action;
import main.config.Conf;
import main.util.Coord;
import main.util.Node;
import main.navigator.AStarNavigator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AStarNavigatorTest {

    /**
     *    0 1 2
     *    - - -
     * 0|/.\./.\
     * 1|\./.\./
     * 2|/.\C/P\
     */
    @Test
    void extend() {
        AStarNavigator astar = new AStarNavigator(Conf.JCONF01);
        Node n1 = new Node(new Coord(0,0));// path cost = 0
        Node n2 = new Node(n1, new Coord(2,1), Action.Boost);// path cost = 2
        astar.getFrontier().add(n2);

        Node parent = new Node(new Coord(2, 2));// path cost = 0 -> child's path cost = 1
        Node[] children = astar.extend( parent);

        // remove past node with new
        assertEquals(0, astar.getFrontier().size());
        assertEquals(2, children.length);
        assertEquals(parent.getState(), children[0].getState());
        assertEquals(n2.getState(), children[1].getState());
    }

    /**
     *    0 1 2 3 4 5
     *    - - - - - -
     * 0|/.\./.\./.\./
     * 1|\./.\./.\./.\
     * 2|/S\-/-\-/-\G/ >>>> wind on a = -2
     * 3|\./.\./.\./.\
     * 4|/.\./.\./.\./
     * 5|\./.\./.\./.\
     */
    @Test
    void findRoute() {
        AStarNavigator astar = new AStarNavigator(Conf.ACONF2);
        astar.findRoute();
    }
}
