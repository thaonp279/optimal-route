package main.navigator;

import main.config.Conf;
import main.util.Node;

import java.util.ArrayDeque;

/**
 * Depth First Search implements a LIFO frontier by overriding the addToFrontier method
 */
public class DFSNavigator extends Navigator {

    public DFSNavigator(Conf conf) {
        super(conf);
    }

    @Override
    protected void addToFrontier(Node[] children) {
        for (int i = children.length - 1; i >= 0; i--) {
            ((ArrayDeque<Node>) frontier).addFirst(children[i]);
        }
    }
}
