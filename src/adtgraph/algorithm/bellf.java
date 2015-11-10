package adtgraph.algorithm;

import adtgraph.extern.Graph;
import adtgraph.extern.Vertex;
import static adtgraph.utils.Util.outputToCSV;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class bellf {
    private bellf(){
        
    }   
    
    public static ArrayList<Vertex> bellf(Graph graph, Vertex start, Vertex goal) {
        if (start == goal) {
            return new ArrayList();
        }
        ArrayList<Vertex> shortestRoute = new ArrayList<>();

        // cache all vertices from current graph
        ArrayList<Vertex> vertices = graph.getVertices();

        // cache all edges from current graph
        ArrayList<Vertex> edges = graph.getEdges();

        // initialize predecessorMap and costMap
        Map<Vertex, Vertex> predecessorMap = new HashMap<>();
        Map<Vertex, Integer> costMap = new HashMap<>();

        // prefill predecessorMap and costMap with start vertex and null for all other vertices
        for (int i = 0; i < vertices.size(); i++) {
            Vertex currentVertex = vertices.get(i);
            if (currentVertex == start) {
                predecessorMap.put(currentVertex, currentVertex);
                costMap.put(currentVertex, 0);
            } else {
                predecessorMap.put(currentVertex, null);
                costMap.put(currentVertex, null);
            }
        }
        // repeat |vertices| - 1 times
        for (int i = 0; i < vertices.size() - 1; i++) {
            for (int j = 0; j < edges.size(); j = j + 2) {
                if (costMap.get(edges.get(j)) != null) {
                    if (costMap.get(edges.get(j + 1)) == null || costMap.get(edges.get(j)) + graph.getEdgeMatrix().getValE(edges.get(j), edges.get(j + 1), "cost") < costMap.get(edges.get(j + 1))) {
                        costMap.replace(edges.get(j + 1), costMap.get(edges.get(j)) + graph.getEdgeMatrix().getValE(edges.get(j), edges.get(j + 1), "cost"));
                        predecessorMap.replace(edges.get(j + 1), edges.get(j));
                    }
                }
            }
        }
        // check for negative cycle
        for (int j = 0; j < edges.size(); j = j + 2) {
            if (costMap.get(edges.get(j)) != null) {
                if (costMap.get(edges.get(j + 1)) != null && costMap.get(edges.get(j)) + graph.getEdgeMatrix().getValE(edges.get(j), edges.get(j + 1), "cost") < costMap.get(edges.get(j + 1))) {
                    return null;
                }
            }
        }

        shortestRoute.add(goal);
        Vertex predecessor = predecessorMap.get(goal);
        if (predecessor == null) {
            return null;
        }
        shortestRoute.add(predecessor);
        while (predecessor != start) {
            if (predecessor == null) {
                return null;
            }
            predecessor = predecessorMap.get(predecessor);
            shortestRoute.add(predecessor);
        }

        Collections.reverse(shortestRoute);

        return shortestRoute;
    }

    public static ArrayList<Vertex> bellfRuntime(String filename, String testname,Graph graph, Vertex start, Vertex goal) {
        ArrayList<String> args = new ArrayList<>();
        args.add(testname);
        args.add("Bellmann Ford");
        args.add("0");
        args.add("0");

        ArrayList<Vertex> shortestRoute = new ArrayList<>();

        long currentTime = Instant.now().toEpochMilli();

        shortestRoute = bellf(graph,start, goal);

        long measuredTime = Instant.now().toEpochMilli() - currentTime;
        args.add(String.valueOf(measuredTime));
        if (shortestRoute == null) {
            args.add("1");
        } else {
            args.add("0");
        }
        outputToCSV(filename, args);

        return shortestRoute;
    }
    
    public static ArrayList<Vertex> bellfIO(String filename, String testname,Graph graph, Vertex start, Vertex goal) {
        ArrayList<String> args = new ArrayList<>();
        args.add(testname);
        args.add("Bellmann Ford");
        int writes = 0;
        int reads  = 0;
                
        /**
         * ALGORITHM START
         */
        if (start == goal) {
            return new ArrayList();
        }
        ArrayList<Vertex> shortestRoute = new ArrayList<>();

        // cache all vertices from current graph
        ArrayList<Vertex> vertices = graph.getVertices();

        // cache all edges from current graph
        ArrayList<Vertex> edges = graph.getEdges();

        // initialize predecessorMap and costMap
        Map<Vertex, Vertex> predecessorMap = new HashMap<>();
        Map<Vertex, Integer> costMap = new HashMap<>();

        // prefill predecessorMap and costMap with start vertex and null for all other vertices
        for (int i = 0; i < vertices.size(); i++) {
            Vertex currentVertex = vertices.get(i);
            if (currentVertex == start) {
                predecessorMap.put(currentVertex, currentVertex);
                costMap.put(currentVertex, 0);
            } else {
                predecessorMap.put(currentVertex, null);
                costMap.put(currentVertex, null);
            }
        }
        // repeat |vertices| - 1 times
        for (int i = 0; i < vertices.size() - 1; i++) {
            for (int j = 0; j < edges.size(); j = j + 2) {
                if (costMap.get(edges.get(j)) != null) {
                    if (costMap.get(edges.get(j + 1)) == null || costMap.get(edges.get(j)) + graph.getEdgeMatrix().getValE(edges.get(j), edges.get(j + 1), "cost") < costMap.get(edges.get(j + 1))) {
                        costMap.replace(edges.get(j + 1), costMap.get(edges.get(j)) + graph.getEdgeMatrix().getValE(edges.get(j), edges.get(j + 1), "cost"));
                        predecessorMap.replace(edges.get(j + 1), edges.get(j));
                    }
                }
            }
        }
        // check for negative cycle
        for (int j = 0; j < edges.size(); j = j + 2) {
            if (costMap.get(edges.get(j)) != null) {
                if (costMap.get(edges.get(j + 1)) != null && costMap.get(edges.get(j)) + graph.getEdgeMatrix().getValE(edges.get(j), edges.get(j + 1), "cost") < costMap.get(edges.get(j + 1))) {
                    return null;
                }
            }
        }

        shortestRoute.add(goal);
        Vertex predecessor = predecessorMap.get(goal);
        if (predecessor == null) {
            return null;
        }
        shortestRoute.add(predecessor);
        while (predecessor != start) {
            if (predecessor == null) {
                return null;
            }
            predecessor = predecessorMap.get(predecessor);
            shortestRoute.add(predecessor);
        }

        Collections.reverse(shortestRoute);
        
        /**
         * ALGORITHM END
         * -------------
         * Post-proccessing
         */
        args.add(String.valueOf(reads));
        args.add(String.valueOf(writes));
        args.add("0");
        if (shortestRoute == null) {
            args.add("1");
        } else {
            args.add("0");
        }
        outputToCSV(filename, args);
        return shortestRoute;
    }

}
