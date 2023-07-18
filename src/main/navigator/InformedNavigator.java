package main.navigator;

import main.config.Conf;
import main.util.Node;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Informed Navigator specifies general features of an informed search algorithm including:
 * 1. Priority Queue implementation for the frontier which sorts nodes with the minimum heuris
 * 2. Printing heuristics with frontier
 */
public abstract class InformedNavigator extends Navigator {

    public InformedNavigator(Conf conf) {
        super(conf);
        int initialCap = 1;
        Comparator<Node> heuristicOrder = Comparator.comparingDouble(Node::getEval);
        frontier = new PriorityQueue<>(initialCap, heuristicOrder);
        explored = new HashSet<>();
        this.conf = conf;
        printHeuristics = true;
    }

}
