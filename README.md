# Marine Navigation Route Planner
The marine navigation route planner that finds the optimal route in a triangular space map. The optimal route minimizes the total action cost from a start coordinate to a goal coordinate.


The agent can run (1 step forward), boost (2 steps) or stop (0 step), each with a different action cost. The ferry can also be moved by wind and can’t move onto land.

<img width="646" alt="Screen Shot 2023-07-18 at 10 35 03" src="https://github.com/thaonp279/optimal-route/assets/77321721/c24e1edd-76da-418f-a82a-88ec9c7a5968">




## Compiling
```
cd src
find . -name '*.java' > sources.txt
javac @sources.txt
```

## Running
```
cd src
java A1main [search name] [config]
search name: BFS | DFS | BestF | AStar
```

## Running JUnit tests
```
cd src
mkdir target
find . -name '*.java' > sources.txt
javac -d target -cp target:junit.jar @sources.txt
java -jar junit.jar --class-path target --scan-class-path
```

### UML Design
<img width="671" alt="Screen Shot 2023-07-18 at 10 35 49" src="https://github.com/thaonp279/optimal-route/assets/77321721/92a6cb77-18df-486f-804c-840561a88a6b">

