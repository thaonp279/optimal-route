package main.navigator;

import main.config.Action;
import main.config.Conf;
import main.util.Coord;
import main.util.Node;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;

/**
 * The Navigator class implements a general search algorithm with FIFO frontier by default.
 */
public class Navigator {

    protected Queue<Node> frontier;
    protected HashSet<Coord> explored;
    protected Conf conf;
    protected boolean printHeuristics = false;

    /**
     * Construct a Navigator object for a specific problem configuration.
     * @param conf problem configuration
     */
    public Navigator(Conf conf) {
        frontier = new ArrayDeque<>();
        explored = new HashSet<>();
        this.conf = conf;
    }

    /**
     * Search route by removing and adding nodes to the frontier
     * until a path is found or the frontier is empty
     * @return the final node of the path or null if no path is found
     */
    public Node findRoute() {
        // initialize frontier with start state
        Node startNode = makeNewNode(conf.getS());
        frontier.add(startNode);

        while (frontier.peek() != null) {
            printFrontier();
            Node node = frontier.remove();
            explored.add(node.getState());
            if (conf.isGoal(node.getState())) {
                printPath(node);
                printVisits();
                return node;
            }
            addToFrontier(extend(node));
        }
        Node fail = null;
        printPath(fail);
        printVisits();
        return fail; //fail to find route
    }

    /**
     * Extend parent node and return resulting nodes that are not explored or currently in the frontier
     * @param parent node to be extended
     * @return array of resulting nodes
     */
    public Node[] extend(Node parent) {
        Node[] children = getChildNodes(parent);
        return Arrays.stream(children)
                .filter(c -> !isExplored(c.getState()) && !isInFrontier(c.getState()))
                .toArray(Node[]::new);
    }

    /**
     * Apply all possible actions on parent node and return resulting nodes
     */
    public Node[] getChildNodes(Node parent) {
        ArrayDeque<Node> children = new ArrayDeque<>();

        for (Action action : conf.getActions()) {
            Coord[] successors = conf.successor(parent.getState(), action);

            for (Coord state : successors) {
                Node childNode = makeNewNode(parent, state, action);
                children.add(childNode);
            }
        }

        return children.toArray(Node[]::new);
    }

    /**
     * Make node with corresponding heuristic
     */
    protected Node makeNewNode(Node parent, Coord state, Action action) {
        Node node = new Node(parent, state, action);
        node.updateEval(evalFunction(node));
        return node;
    }

    /**
     * Make node with initial state without parent and action
     */
    protected Node makeNewNode(Coord state) {
        Node node = new Node(state);
        node.updateEval(evalFunction(node));
        return node;
    }

    protected double evalFunction(Node node) {
        return 0;
    }

    /**
     * Frontier addition strategy, to be overridden by different search strategies
     */
    protected void addToFrontier(Node[] children) {
        frontier.addAll(Arrays.asList(children));
    }

    protected boolean isExplored(Coord state) {
        for (Coord s : explored) {
            if (s.equals(state)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get a node in the frontier with given state
     */
    protected Node getNodeInFrontier(Coord state) {
        for (Node node : frontier) {
            if (node.getState().equals(state)) {
                return node;
            }
        }
        return null;
    }

    protected boolean isInFrontier(Coord state) {
        return getNodeInFrontier(state) != null;
    }

    private void printPath(Node node) {
        StringBuilder stringBuilder = new StringBuilder();
        if (node == null) { // fail to find path
            stringBuilder.append("fail\n");
        } else {
            double pathCost = node.getPathCost();
            int offset = 0;

            while (node != null) {
                String nodeStr = node.getState().toString();

                // print action with each node if problem config has more than 1 action
                if (conf.getActions().length > 1 && node.getAction() != null) {
                    nodeStr = " " + node.getAction().name() + nodeStr;
                }
                stringBuilder.insert(offset,  nodeStr);
                node = node.getParent();
            }
            stringBuilder.append("\n").append(pathCost).append("\n");
        }
        System.out.print(stringBuilder);
    }

    protected void printFrontier() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        String delimiter = "";
        for (Node node: frontier) {
            stringBuilder.append(delimiter).append(node.getState());
            if (printHeuristics) {
                stringBuilder.append(":").append(node.getEval());
            }
            delimiter = ",";
        }
        stringBuilder.append("]\n");
        System.out.print(stringBuilder);
    }

    public int printVisits() {
        System.out.println(explored.size());
        return explored.size();
    }

    public Queue<Node> getFrontier() {
        return frontier;
    }

}
