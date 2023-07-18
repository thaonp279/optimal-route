package main.navigator;

import main.config.Conf;
import main.util.Node;

/**
 * Best First Search orders the frontier using purely heuristic function
 */
public class BestFSNavigator extends InformedNavigator {

    public BestFSNavigator(Conf conf) {
        super(conf);
    }

    @Override
    protected double evalFunction(Node node) {
        return node.getState().getManhattanDist(conf.getG());
    }
}
