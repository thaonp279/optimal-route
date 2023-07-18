package main.util;

import main.config.Action;

public class Node {
    private Coord state;
    private Node parent;
    private double pathCost;
    private int depth;
    private double eval = 0; // evaluation value to be used by priority queue to rank nodes
    private Action action;

    /**
     *  Constructor for start node
     */
    public Node(Coord state) {
        this.parent = null;
        this.state = state;
        this.action = null;
        pathCost = 0;
        depth = 0;
    }

    public Node(Node parent, Coord state, Action action) {
        this.parent = parent;
        this.state = state;
        this.action = action;

        pathCost = parent.getPathCost() + action.getCost();
        depth = parent.getDepth() + 1;
    }

    public void updateEval(double h) {
        eval = h;
    }

    public Coord getState() {
        return state;
    }

    public Node getParent() {
        return parent;
    }

    public double getPathCost() {
        return pathCost;
    }

    public int getDepth() {
        return depth;
    }

    public double getEval() {
        return eval;
    }

    public Action getAction() {
        return action;
    }

    @Override
    public boolean equals(Object o) {
        Node node = (Node) o;
        return node.getState().equals(state)
                && node.getParent() == parent
                && node.getPathCost() == pathCost
                && node.getDepth() == depth
                && node.getEval() == eval
                && node.getAction() == action;
    }


}
