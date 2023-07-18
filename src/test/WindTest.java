import main.util.Coord;
import main.config.Wind;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WindTest {

    /**
     *    0 1 2 3 4 5
     *    - - - - - -
     * 0|/.\./.\./.\./
     * 1|\./.\./.\./.\
     * 2|/.\./x\./.\./
     * 3|\./.\./.\./.\
     * 4|/.\./.\./.\./
     * 5|\./.\./.\./.\
     */
    @Test
    void blow() {
        Coord state = new Coord(2, 2);

        Coord west = Wind.West.blow(state);
        Coord east = Wind.East.blow(state);
        Coord northWest = Wind.NorthWest.blow(state);
        Coord southEast = Wind.SouthEast.blow(state);
        Coord southWest = Wind.SouthWest.blow(state);
        Coord northEast = Wind.NorthEast.blow(state);

        assertEquals(new Coord(2, 3), west);
        assertEquals(new Coord(3, 2), northWest);
        assertEquals(new Coord(3, 2), northEast);
        assertEquals(new Coord(2, 1), east);
        assertEquals(new Coord(2, 1), southEast);
        assertEquals(new Coord(2, 3), southWest);
    }

    /**
     *    0 1 2 3 4 5
     *    - - - - - -
     * 0|/.\./x\./.\./
     * 1|\./.\./.\./.\
     * 2|/.\./.\./.\./
     * 3|\./.\./.\./.\
     * 4|/.\./.\./.\./
     * 5|\./.\./.\./.\
     */
    @Test
    void notBlow() {
        Coord state = new Coord(0, 2);

        Coord west = Wind.West.blow(state);
        Coord northWest = Wind.NorthWest.blow(state);
        Coord southWest = Wind.SouthWest.blow(state);

        assertEquals(state, west);
        assertEquals(state, northWest);
        assertEquals(state, southWest);
    }
}