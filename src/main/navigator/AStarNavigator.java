package main.navigator;

import main.config.Conf;
import main.util.Node;

import java.util.ArrayDeque;

/**
 * AStar orders the frontier by the evaluation function fn = current path cost + heuristic
 */
public class AStarNavigator extends InformedNavigator {

    public AStarNavigator(Conf conf) {
        super(conf);
    }

    /**
     * Cal eval function fn = current path cost + manhattan distance * min action cost
     * Weighing the manhattan distance with the min action cost guarantees the admissibility of the evaluation
     */
    @Override
    protected double evalFunction(Node node) {
        double minCost = conf.getMinActionCost();
        double manDist = node.getState().getManhattanDist(conf.getG());
        return node.getPathCost() + manDist * minCost;
    }

    /**
     * Return extended nodes if:
     * 1. Their states are not already in the frontier and explored
     * 2. Or if their state is already in the frontier but the node has a lower path cost.
     *    Remove higher cost node as a result.
     */
    @Override
    public Node[] extend(Node parent) {
        ArrayDeque<Node> children = new ArrayDeque<>();

        for (Node child: getChildNodes(parent)) {
            if (!isExplored(child.getState()) && !isInFrontier(child.getState())) {
                children.add(child);
            } else if (isInFrontier(child.getState())) {
                Node pastNode = getNodeInFrontier(child.getState());
                if (pastNode.getPathCost() > child.getPathCost()) {
                    frontier.remove(pastNode);
                    children.add(child);
                }
            }
        }
        return children.toArray(Node[]::new);
    }
}