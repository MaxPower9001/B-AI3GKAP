package adtgraph.extern;

import adtgraph.intern.Attribute;
import adtgraph.extern.Vertex;
import adtgraph.intern.EdgeMatrix;
import static adtgraph.utils.Util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.lang.String;
import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Graph {

    private final EdgeMatrix edgeMatrix;

    public Graph(Vertex vertex) {
        edgeMatrix = new EdgeMatrix();
        this.addVertex(vertex);
    }

    public static Graph createG(Vertex vertex) {
        return new Graph(vertex);
    }

    public Graph addVertex(Vertex vertex) {
        this.edgeMatrix.addVertex(vertex);
        return this;
    }

    public Graph setAtE(Vertex source, Vertex target, String name, int value) {
        this.edgeMatrix.setAtE(source, target, name, value);
        return this;
    }

    public Graph deleteVertex(Vertex vertex) {
        this.edgeMatrix.deleteVertex(vertex);
        return this;
    }

    public Graph addEdge(Vertex source, Vertex target) {
        this.edgeMatrix.addEdge(source, target);
        return this;
    }

    public Graph deleteEdge(Vertex source, Vertex target) {
        this.edgeMatrix.deleteEdge(source, target);
        return this;
    }

    public Graph setAtV(Vertex vertex, String name, int value) {
        vertex.attribute(new Attribute(name, value));
        return this;
    }

    public ArrayList<Vertex> getIncident(Vertex vertex) {
        return this.edgeMatrix.getIncident(vertex);
    }

    public ArrayList<Vertex> getAdjacent(Vertex vertex) {
        return this.edgeMatrix.getAdjacent(vertex);
    }

    public ArrayList<Vertex> getTarget(Vertex vertex) {
        return this.edgeMatrix.getTarget(vertex);
    }

    public ArrayList<Vertex> getSource(Vertex vertex) {
        return this.edgeMatrix.getSource(vertex);
    }

    public ArrayList<Vertex> getEdges() {
        return this.edgeMatrix.getEdges();
    }

    public ArrayList<Vertex> getVertices() {
        return this.edgeMatrix.getVertices();
    }

    public int getValE(Vertex vertex, Vertex target, String name) {
        return this.edgeMatrix.getValE(vertex, target, name);
    }

    public int getValV(Vertex vertex, String name) {
        for (Attribute a : vertex.attribute()) {
            if (a.getName().equals(name)) {
                return a.value();
            }
        }
        return (Integer) null;
    }

    public void exportG(String filename) throws Exception {

        String nl = System.lineSeparator();

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filename), "utf-8"))) {
            String graphname = new File(filename).getName();
            graphname = graphname.substring(0, graphname.lastIndexOf('.'));
            boolean isOddPositionInList = true;
            Vertex source = null;
            Vertex target = null;

            // Print .dot file header
            writer.write("digraph " + graphname + nl);
            writer.write("{" + nl);

            // Print Vertices
            for (Vertex vv : this.getVertices()) {
                writer.write(vv.getName() + nl);
            }

            // Print Edges
            for (Vertex ve : this.getEdges()) {

                if (isOddPositionInList) {
                    source = ve;
                    writer.write(source.getName() + " -> ");
                } else {
                    target = ve;

                    writer.write(target.getName() + " [label=\"");

                    for (Attribute a : edgeMatrix.getEdge(source, target).attribute()) {
                        if (!a.getName().equals("")) {
                            writer.write(a.getName() + "=");
                        }
                        writer.write(a.value() + "  ");
                    }

                    writer.write("\"]" + nl);

                }
                isOddPositionInList = !isOddPositionInList;
            }
            writer.write("}" + nl);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Graph importG(String filename) {
        String splitBy = ",";
        String line;
        Graph graph = null;
        boolean isGerichtet = true;
        int iteration = 0;

        try (
                BufferedReader br = new BufferedReader(new FileReader(filename))) {

            while ((line = br.readLine()) != null) {
                if (iteration == 0) {
                    if (line.contains("#gerichtet")) {
                        isGerichtet = true;
                    } else if (line.contains("#ungerichtet")) {
                        isGerichtet = false;
                    } else {
                        System.out.println("Kein korrekter Header");
                    }
                    iteration++;
                    continue;
                }

                String[] graphOpts = line.split(splitBy);

                if (graphOpts.length != 3) {
                    System.out.println("Nicht im Format: <ecke1>,<ecke2>,<value>");

                } else {
                    if (iteration == 1) {
                        graph = Graph.createG(Vertex.createV(graphOpts[0]));
                        iteration++;
                    }

                    graph.addVertex(Vertex.createV(graphOpts[0]));
                    graph.addVertex(Vertex.createV(graphOpts[1]));
                    graph.addEdge(Vertex.createV(graphOpts[0]), Vertex.createV(graphOpts[1]));

                    graph.setAtE(Vertex.createV(graphOpts[0]), Vertex.createV(graphOpts[1]), "cost", Integer.parseInt(graphOpts[2]));

                    if (isGerichtet == false) {
                        graph.addEdge(Vertex.createV(graphOpts[1]), Vertex.createV(graphOpts[0]));
                        graph.setAtE(Vertex.createV(graphOpts[1]), Vertex.createV(graphOpts[0]), "cost", Integer.parseInt(graphOpts[2]));
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return graph;
    }

    public ArrayList<Vertex> bellf(Vertex start, Vertex goal) {
        if (start == goal) {
            return new ArrayList();
        }
        ArrayList<Vertex> shortestRoute = new ArrayList<>();

        // cache all vertices from current graph
        ArrayList<Vertex> vertices = this.getVertices();

        // cache all edges from current graph
        ArrayList<Vertex> edges = this.getEdges();

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
                    if (costMap.get(edges.get(j + 1)) == null || costMap.get(edges.get(j)) + edgeMatrix.getValE(edges.get(j), edges.get(j + 1), "cost") < costMap.get(edges.get(j + 1))) {
                        costMap.replace(edges.get(j + 1), costMap.get(edges.get(j)) + edgeMatrix.getValE(edges.get(j), edges.get(j + 1), "cost"));
                        predecessorMap.replace(edges.get(j + 1), edges.get(j));
                    }
                }
            }
        }
        // check for negative cycle
        for (int j = 0; j < edges.size(); j = j + 2) {
            if (costMap.get(edges.get(j)) != null) {
                if (costMap.get(edges.get(j + 1)) != null && costMap.get(edges.get(j)) + edgeMatrix.getValE(edges.get(j), edges.get(j + 1), "cost") < costMap.get(edges.get(j + 1))) {
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

    public ArrayList<Vertex> bellfRuntime(String filename, String testname, Vertex start, Vertex goal) {
        ArrayList<String> args = new ArrayList<>();
        args.add(testname);
        args.add("Bellmann Ford");
        args.add("0");
        args.add("0");

        ArrayList<Vertex> shortestRoute = new ArrayList<>();

        long currentTime = Instant.now().toEpochMilli();

        shortestRoute = bellf(start, goal);

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

    public ArrayList<Vertex> floydw(Vertex start, Vertex goal) {
        if (start == goal) {
            return new ArrayList();
        }
        ArrayList<Vertex> shortestRoute = new ArrayList<>();

        // cache all vertices from current graph
        ArrayList<Vertex> vertices = this.getVertices();

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
                    tempCost.put(vertices.get(j), edgeMatrix.getValE(vertices.get(i), vertices.get(j), "cost"));
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
                Integer dii = costMap.get(vertices.get(i)).get(i);
                if (dii != null && dii < 0) {
                    return null;
                }
            }
        }
        shortestRoute.add(start);

        Vertex interim = predecessorMap.get(start).get(goal);

        if (interim != null) {

            pathfinder(predecessorMap, start, goal, shortestRoute);

        } else {
            shortestRoute.add(interim);
        }
        return shortestRoute;
    }
 
    public ArrayList<Vertex> pathfinder(Map<Vertex, Map<Vertex, Vertex>> predecessorMap, Vertex start, Vertex target, ArrayList<Vertex> list) {
        Vertex interim = predecessorMap.get(start).get(target);
        if (interim != null) {
            pathfinder(predecessorMap, start, interim, list);
            pathfinder(predecessorMap, interim, target, list);
        } else {
            list.add(target);
        }
        return list;
    }

    public Integer minValue(Integer valueA, Integer valueB) {
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

    public ArrayList<Vertex> floydwRuntime(String filename, String testname, Vertex start, Vertex goal) {
        ArrayList<String> args = new ArrayList<>();
        args.add(testname);
        args.add("Floyd Warshall");
        args.add("0");
        args.add("0");

        ArrayList<Vertex> shortestRoute = new ArrayList<>();

        long currentTime = Instant.now().toEpochMilli();

        shortestRoute = floydw(start, goal);

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

}
