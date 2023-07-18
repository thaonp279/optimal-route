import jdk.dynalink.NoSuchDynamicMethodException;
import main.config.Conf;
import main.navigator.AStarNavigator;
import main.navigator.BFSNavigator;
import main.navigator.BestFSNavigator;
import main.navigator.DFSNavigator;
import main.util.Node;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Eval {

    @Test
    void main() {
        StringBuilder stringBuilder = new StringBuilder();
        double bestSum = 0;
        double aStarSum = 0;

        for (Conf conf: Conf.values()) {
            BFSNavigator bfs = new BFSNavigator(conf);
            DFSNavigator dfs = new DFSNavigator(conf);
            BestFSNavigator bestf = new BestFSNavigator(conf);
            AStarNavigator astar = new AStarNavigator(conf);

            Node bfsNode = bfs.findRoute();
            String bfsCost = bfsNode == null? "null" : String.valueOf(bfsNode.getPathCost());
            Node dfsNode = dfs.findRoute();
            String dfsCost = dfsNode == null? "null" : String.valueOf(dfsNode.getPathCost());
            Node bestfNode = bestf.findRoute();
            String bestfCost = bestfNode == null? "null" : String.valueOf(bestfNode.getPathCost());
            Node astarNode = astar.findRoute();
            String astarCost = astarNode == null? "null" : String.valueOf(astarNode.getPathCost());

            bestSum += bestf.printVisits();
            aStarSum += astar.printVisits();
            stringBuilder.append(conf.name())
                    .append("&")
                    .append(bfsCost)
                    .append("&")
                    .append(dfsCost)
                    .append("&")
                    .append(bestfCost)
                    .append("&")
                    .append(astarCost)
                    .append("&")
                    .append(bfs.printVisits())
                    .append("&")
                    .append(dfs.printVisits())
                    .append("&")
                    .append(bestf.printVisits())
                    .append("&")
                    .append(astar.printVisits())
                    .append("\\\\")
                    .append("\n");

        }
        System.out.println(bestSum);
        System.out.println(aStarSum);

        /*
        File file = new File("out.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
         */
    }
}
