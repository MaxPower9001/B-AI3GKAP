package adtgraph.algorithm;

import adtgraph.Graph;
import adtgraph.Vertex;
import static adtgraph.utils.Util.outputToCSV;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class floydw {

    static int algowrites = 0;
    static int algoreads = 0;
    static int graphwrites = 0;
    static int graphreads = 0;

    private floydw() {

    }

    public static ArrayList<Vertex> floydw(Graph graph, Vertex start, Vertex goal) {
        if (start.getName().equals(goal.getName())) {
            return new ArrayList();
        }
        ArrayList<Vertex> shortestRoute = new ArrayList<>();

        // cache all vertices from current graph
        ArrayList<Vertex> vertices = graph.getVertices();
        
        // cache all edges from current graph
        ArrayList<Vertex> edges = graph.getEdges();

        // initialize predecessorMap and costMap
        Map<Vertex, Map<Vertex, Vertex>> predecessorMap = new HashMap<>();
        Map<Vertex, Map<Vertex, Integer>> costMap = new HashMap<>();

        for (int i = 0; i < vertices.size(); i++) {
            Map<Vertex, Integer> tempCost = new HashMap<>();
            Map<Vertex, Vertex> tempPred = new HashMap<>();
            for (int j = 0; j < vertices.size(); j++) {
                if (i == j) {
                    tempCost.put(vertices.get(j), 0);
                    tempPred.put(vertices.get(j), null);
                } else {
                    tempCost.put(vertices.get(j), graph.getValE(vertices.get(i), vertices.get(j), "cost"));
                    tempPred.put(vertices.get(j), null);
                }

            }
            costMap.put(vertices.get(i), tempCost);
            predecessorMap.put(vertices.get(i), tempPred);
        }

        for (int j = 0; j < vertices.size(); j++) {
            for (int i = 0; i < vertices.size(); i++) {
                for (int k = 0; k < vertices.size(); k++) {
                    if (i != j && j != k) {
                        Integer dik = costMap.get(vertices.get(i)).get(vertices.get(k));
                        Integer dij = costMap.get(vertices.get(i)).get(vertices.get(j));
                        Integer djk = costMap.get(vertices.get(j)).get(vertices.get(k));
                        if (dij != null && djk != null && minValue(dik, dij + djk) != dik) {
                            costMap.get(vertices.get(i)).replace(vertices.get(k), dij + djk);
                            predecessorMap.get(vertices.get(i)).replace(vertices.get(k), vertices.get(j));
                        }
                    }
                }
                Integer dii = costMap.get(vertices.get(i)).get(vertices.get(i));
                if (dii != null && dii < 0) {
                    return null;
                }
            }
        }
        shortestRoute.add(start);

        pathfinder(predecessorMap, start, goal, shortestRoute);
        boolean startgoaledge = false;
        for(int i = 0; i < edges.size(); i=i+2){
            if(edges.get(i).getName().equals(start.getName()) && edges.get(i+1).getName().equals(goal.getName())){
                startgoaledge=true;
            }
        }
        
        if (shortestRoute.get(0).getName().equals(start.getName()) && shortestRoute.get(1).getName().equals(goal.getName()) && !startgoaledge) {
            return null;
        } else {
            return shortestRoute;
        }
    }

    public static ArrayList<Vertex> floydwIO(String filename, String testname, Graph graph, Vertex start, Vertex goal) {
        ArrayList<String> args = new ArrayList<>();
        args.add(testname);
        args.add("Floyd Warshall");

        algowrites = 0;
        algoreads = 0;
        graphwrites = 0;
        graphreads = 0;

        /**
         * ALGORITHM START
         */
        if (start.getName().equals(goal.getName())) {
            return new ArrayList();
        }
        ArrayList<Vertex> shortestRoute = new ArrayList<>();

        // cache all vertices from current graph
        ArrayList<Vertex> vertices = graph.getVertices();
        algowrites++;
        graphreads++;
        
        // cache all edges from current graph
        ArrayList<Vertex> edges = graph.getEdges();
        algowrites++;
        graphreads++;

        // initialize predecessorMap and costMap
        Map<Vertex, Map<Vertex, Vertex>> predecessorMap = new HashMap<>();
        Map<Vertex, Map<Vertex, Integer>> costMap = new HashMap<>();

        for (int i = 0;
                i < vertices.size();
                i++) {
            algoreads++;
            Map<Vertex, Integer> tempCost = new HashMap<>();
            Map<Vertex, Vertex> tempPred = new HashMap<>();
            for (int j = 0; j < vertices.size(); j++) {
                algoreads++;
                if (i == j) {
                    tempCost.put(vertices.get(j), 0);
                    tempPred.put(vertices.get(j), null);
                    algowrites++;
                    algowrites++;
                    algoreads++;
                    algoreads++;
                } else {
                    tempCost.put(vertices.get(j), graph.getValE(vertices.get(i), vertices.get(j), "cost"));
                    tempPred.put(vertices.get(j), null);
                    graphreads++;
                    algowrites++;
                    algowrites++;
                    algoreads++;
                    algoreads++;
                    algoreads++;
                    algoreads++;
                }

            }
            costMap.put(vertices.get(i), tempCost);
            predecessorMap.put(vertices.get(i), tempPred);
            algowrites++;
            algowrites++;
            algoreads++;
            algoreads++;
        }

        for (int j = 0;
                j < vertices.size();
                j++) {
            algoreads++;
            for (int i = 0; i < vertices.size(); i++) {
                algoreads++;
                for (int k = 0; k < vertices.size(); k++) {
                    algoreads++;
                    if (i != j && j != k) {
                        Integer dik = costMap.get(vertices.get(i)).get(vertices.get(k));
                        Integer dij = costMap.get(vertices.get(i)).get(vertices.get(j));
                        Integer djk = costMap.get(vertices.get(j)).get(vertices.get(k));
                        algoreads = algoreads + 12;
                        if (dij != null && djk != null && minValue(dik, dij + djk) != dik) {
                            costMap.get(vertices.get(i)).replace(vertices.get(k), dij + djk);
                            predecessorMap.get(vertices.get(i)).replace(vertices.get(k), vertices.get(j));
                            algoreads = algoreads + 7;
                            algowrites++;
                            algowrites++;
                        }
                    }
                }
                Integer dii = costMap.get(vertices.get(i)).get(vertices.get(i));
                algoreads = algoreads + 4;
                if (dii != null && dii < 0) {
                    return null;
                }
            }
        }

        shortestRoute.add(start);
        algowrites++;

        pathfinderIO(predecessorMap, start, goal, shortestRoute);
        
        boolean startgoaledge = false;
        for(int i = 0; i < edges.size(); i=i+2){
            if(edges.get(i).getName().equals(start.getName()) && edges.get(i+1).getName().equals(goal.getName())){
                startgoaledge=true;
            }
        }
        
        if (shortestRoute.get(0).getName().equals(start.getName()) && shortestRoute.get(1).getName().equals(goal.getName()) && !startgoaledge) {
            return null;
        } else {
            algoreads++;
            algoreads++;
            graphreads++;
            graphreads++;
            /**
             * ALGORITHM END
             * ------------- 
             * Post-proccessing
             */
            args.add(String.valueOf(algoreads));
            args.add(String.valueOf(algowrites));
            args.add(String.valueOf(graphreads));
            args.add(String.valueOf(graphwrites));
            args.add("0");
            if (shortestRoute == null) {
                args.add("1");
            } else {
                args.add("0");
            }
            String shortestRouteString = "";
            if (shortestRoute != null) {
                for (Vertex v : shortestRoute) {
                    shortestRouteString = shortestRouteString + "->" + v.getName();
                }
            }
            args.add(shortestRouteString);
            outputToCSV(filename, args);
            return shortestRoute;
        }
    }

    public static ArrayList<Vertex> pathfinder(Map<Vertex, Map<Vertex, Vertex>> predecessorMap, Vertex start, Vertex target, ArrayList<Vertex> list) {
        Vertex interim = predecessorMap.get(start).get(target);
        if (interim != null) {
            pathfinder(predecessorMap, start, interim, list);
            pathfinder(predecessorMap, interim, target, list);
        } else {
            list.add(target);
        }
        return list;
    }

    public static ArrayList<Vertex> pathfinderIO(Map<Vertex, Map<Vertex, Vertex>> predecessorMap, Vertex start, Vertex target, ArrayList<Vertex> list) {
        Vertex interim = predecessorMap.get(start).get(target);
        algoreads++;
        algoreads++;
        if (interim != null) {
            pathfinder(predecessorMap, start, interim, list);
            pathfinder(predecessorMap, interim, target, list);
        } else {
            list.add(target);
            algowrites++;
        }
        return list;
    }

    /**
     *
     * @param valueA as Integer
     * @param valueB as Integer
     * @return Returns lowest value, where null equals infinity
     */
    public static Integer minValue(Integer valueA, Integer valueB) {
        if (valueA == null) {
            return valueB;
        } else if (valueB == null) {
            return valueA;
        } else if (valueB > valueA) {
            return valueA;
        } else {
            return valueB;
        }
    }

    public static ArrayList<Vertex> floydwRuntime(String filename, String testname, Graph graph, Vertex start, Vertex goal) {
        ArrayList<String> args = new ArrayList<>();
        args.add(testname);
        args.add("Floyd Warshall");
        args.add("0");
        args.add("0");
        args.add("0");
        args.add("0");

        ArrayList<Vertex> shortestRoute = new ArrayList<>();

        long currentTime = Instant.now().toEpochMilli();

        shortestRoute = floydw(graph, start, goal);

        long measuredTime = Instant.now().toEpochMilli() - currentTime;
        args.add(String.valueOf(measuredTime));
        if (shortestRoute == null) {
            args.add("1");
        } else {
            args.add("0");
        }
        String shortestRouteString = "";

        if (shortestRoute != null) {
            for (Vertex v : shortestRoute) {
                shortestRouteString = shortestRouteString + "->" + v.getName();
            }
        }
        args.add(shortestRouteString);

        outputToCSV(filename, args);

        return shortestRoute;
    }
}
