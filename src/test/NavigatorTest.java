import main.config.Action;
import main.config.Conf;
import main.util.Coord;
import main.util.Node;
import main.navigator.Navigator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NavigatorTest {
    Navigator nav;

    @BeforeEach
    void setUp() {
        nav = new Navigator(Conf.ACONF1);
    }


    /**
     *    0 1 2 3 4 5
     *    - - - - - -
     * 0|/.\./.\./.\./
     * 1|\./.\./.\./.\
     * 2|/.\./.\./.\./
     * 3|\./b\./b\./.\
     * 4|/b\r/S\r/b\./
     * 5|\./b\r/b\./.\
     */
    @Test
    void getChildNodes() {
        // test multiple actions
        Node parent = new Node(new Coord(4, 2));
        Node[] children = nav.getChildNodes(parent);

        Node child0 = new Node(parent, new Coord(4, 3), Action.Run);
        Node child3 = new Node(parent, new Coord(4, 4), Action.Boost);
        Node child9 = new Node(parent, new Coord(4, 2), Action.Stop);
        assertEquals(10, children.length);
        assertEquals(child0, children[0]);
        assertEquals(child3, children[3]);
        assertEquals(child9, children[9]);

    }


}