package adtgraph.extern;

import adtgraph.intern.Attribute;
import adtgraph.extern.Vertex;
import adtgraph.intern.EdgeMatrix;
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
        this.getEdgeMatrix().addVertex(vertex);
        return this;
    }

    public Graph setAtE(Vertex source, Vertex target, String name, int value) {
        this.getEdgeMatrix().setAtE(source, target, name, value);
        return this;
    }

    public Graph deleteVertex(Vertex vertex) {
        this.getEdgeMatrix().deleteVertex(vertex);
        return this;
    }

    public Graph addEdge(Vertex source, Vertex target) {
        this.getEdgeMatrix().addEdge(source, target);
        return this;
    }

    public Graph deleteEdge(Vertex source, Vertex target) {
        this.getEdgeMatrix().deleteEdge(source, target);
        return this;
    }

    public Graph setAtV(Vertex vertex, String name, int value) {
        vertex.attribute(new Attribute(name, value));
        return this;
    }

    public ArrayList<Vertex> getIncident(Vertex vertex) {
        return this.getEdgeMatrix().getIncident(vertex);
    }

    public ArrayList<Vertex> getAdjacent(Vertex vertex) {
        return this.getEdgeMatrix().getAdjacent(vertex);
    }

    public ArrayList<Vertex> getTarget(Vertex vertex) {
        return this.getEdgeMatrix().getTarget(vertex);
    }

    public ArrayList<Vertex> getSource(Vertex vertex) {
        return this.getEdgeMatrix().getSource(vertex);
    }

    public ArrayList<Vertex> getEdges() {
        return this.getEdgeMatrix().getEdges();
    }

    public ArrayList<Vertex> getVertices() {
        return this.getEdgeMatrix().getVertices();
    }

    public int getValE(Vertex vertex, Vertex target, String name) {
        return this.getEdgeMatrix().getValE(vertex, target, name);
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

                    for (Attribute a : getEdgeMatrix().getEdge(source, target).attribute()) {
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

    public EdgeMatrix getEdgeMatrix() {
        return edgeMatrix;
    }
}