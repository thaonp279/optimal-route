package main.navigator;

import main.config.Conf;

/**
 * Best First Search Navigator calls the default Navigator which implements a FIFO frontier
 */
public class BFSNavigator extends Navigator {
    public BFSNavigator(Conf conf) {
        super(conf);
    }
}
