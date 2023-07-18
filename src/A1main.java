import main.config.Conf;
import main.util.Coord;
import main.config.Map;
import main.navigator.AStarNavigator;
import main.navigator.BFSNavigator;
import main.navigator.BestFSNavigator;
import main.navigator.DFSNavigator;
import main.navigator.Navigator;

/********************Starter Code
 * 
 * This class contains some examples on how to handle the required inputs and outputs 
 * and other debugging options
 * 
 * @author at258
 * 
 * run with 
 * java A1main <Algo> <ConfID>
 * 
 */


public class A1main {

	public static void main(String[] args) {
		Conf conf = Conf.valueOf(args[1]);
		/*
		//Uncomment here for debugging only
		System.out.println("Configuration:"+args[1]);
		System.out.println("main.config.Map:");
		printMap(conf.getMap(), conf.getS(), conf.getG());
		System.out.println("Departure port: Start (r_s,c_s): "+conf.getS());
		System.out.println("Destination port: Goal (r_g,c_g): "+conf.getG());
		System.out.println("Search algorithm: "+args[0]);
		System.out.println();
		//run your search algorithm
		 */
		runSearch(args[0],conf);
	}

	private static void runSearch(String algo, Conf conf) {
		Navigator navigator;
		switch(algo) {
		case "BFS": //run BFS
			navigator = new BFSNavigator(conf);
			navigator.findRoute();
			break;
		case "DFS": //run DFS
			navigator = new DFSNavigator(conf);
			navigator.findRoute();
			break;
		case "BestF": //run BestF
			navigator = new BestFSNavigator(conf);
			navigator.findRoute();
			break;
		case "AStar": //run AStar
			navigator = new AStarNavigator(conf);
			navigator.findRoute();
			break;
		}
	}


	private static void printMap(Map m, Coord init, Coord goal) {

		int[][] map=m.getMap();

		System.out.println();
		int rows=map.length;
		int columns=map[0].length;

		//top row
		System.out.print("  ");
		for(int c=0;c<columns;c++) {
			System.out.print(" "+c);
		}
		System.out.println();
		System.out.print("  ");
		for(int c=0;c<columns;c++) {
			System.out.print(" -");
		}
		System.out.println();

		//print rows 
		for(int r=0;r<rows;r++) {
			boolean right;
			System.out.print(r+"|");
			if(r%2==0) { //even row, starts right [=starts left & flip right]
				right=false;
			}else { //odd row, starts left [=starts right & flip left]
				right=true;
			}
			for(int c=0;c<columns;c++) {
				System.out.print(flip(right));
				if(isCoord(init,r,c)) {
					System.out.print("S");
				}else {
					if(isCoord(goal,r,c)) {
						System.out.print("G");
					}else {
						if(map[r][c]==0){
							System.out.print(".");
						}else{
							System.out.print(map[r][c]);
						}
					}
				}
				right=!right;
			}
			System.out.println(flip(right));
		}
		System.out.println();


	}

	private static boolean isCoord(Coord coord, int r, int c) {
		//check if coordinates are the same as current (r,c)
		if(coord.getR()==r && coord.getC()==c) {
			return true;
		}
		return false;
	}



	public static String flip(boolean right) {
        //prints triangle edges
		if(right) {
			return "\\"; //right return left
		}else {
			return "/"; //left return right
		}

	}

}
