package adtgraph.intern;

import adtgraph.extern.Vertex;
import java.util.ArrayList;
import java.util.List;

public class EdgeMatrix {

    private ArrayList<Vertex> vertexlist = new ArrayList<>();

    private List<List<Edge>> matrix = new ArrayList<>();

    public EdgeMatrix() {

    }

    public ArrayList<Vertex> getIncident(Vertex vertex) {
        return null;
    }

    public ArrayList<Vertex> getAdjacent(Vertex vertex) {
        return null;
    }

    public Edge getEdge(Vertex source, Vertex target) {
        return null;
    }

    public ArrayList<Vertex> getVertices() {
        return vertexlist;
    }

    public ArrayList<Vertex> getEdges() {
        return null;
    }

    public void addVertex(Vertex vertex) {
        for (Vertex v : this.vertexlist) {
            if (v == vertex) {
                return;
            }
        }
        this.vertexlist.add(vertex);
        expandEdgeMatrix();
    }

    public void setAtE(Vertex source, Vertex target, String name, int value) {
        matrix.get(vertexlist.indexOf((Vertex) source)).get(vertexlist.indexOf((Vertex) target)).attribute(new Attribute(name, value));
    }

    public void deleteVertex(Vertex vertex) {
        matrix.remove(vertexlist.indexOf((Vertex) vertex));
        for (int i = 0; i < matrix.size(); i++) {
            matrix.get(i).remove(vertexlist.indexOf((Vertex) vertex));
        }
        vertexlist.remove((Vertex) vertex);
    }

    public void addEdge(Vertex source, Vertex target) {
        addVertex(source);
        addVertex(target);
        matrix.get(vertexlist.indexOf((Vertex) source)).set(vertexlist.indexOf((Vertex) target), new Edge(source, target));
    }

    public void deleteEdge(Vertex source, Vertex target) {
        matrix.get(vertexlist.indexOf((Vertex) source)).set(vertexlist.indexOf((Vertex) target), null);
    }

    public ArrayList<Vertex> getTarget(Vertex vertex) {
        ArrayList<Vertex> temp = new ArrayList<>();

        for (int i = 0; i < vertexlist.size(); i++) {
            for (int j = 0; j < vertexlist.size(); j++) {
                if (matrix.get(i).get(j).source() == vertex) {
                    temp.add(matrix.get(i).get(j).target());
                }
            }
        }
        return temp;
    }

    public ArrayList<Vertex> getSource(Vertex vertex) {
        ArrayList<Vertex> temp = new ArrayList<>();

        for (int i = 0; i < vertexlist.size(); i++) {
            for (int j = 0; j < vertexlist.size(); j++) {
                if (matrix.get(i).get(j).target() == vertex) {
                    temp.add(matrix.get(i).get(j).source());
                }
            }
        }
        return temp;
    }

    public int getValE(Vertex source, Vertex target, String name) throws Exception {
        for (int i = 0; i < vertexlist.size(); i++) {
            for (int j = 0; j < vertexlist.size(); j++) {
                if (matrix.get(i).get(j).target() == target && matrix.get(i).get(j).source() == source) {
                    for (Attribute a : matrix.get(i).get(j).attribute()) {
                        if (a.name().equals(name)) {
                            return a.value();
                        }
                    }
                }
            }
        }
         throw new Exception("Name nicht vorhanden!");
    }

    private void expandEdgeMatrix() {
        // Der Matrix wird eine weitere Spalte hinzugefügt
        matrix.add(new ArrayList<>());

        // Die neue Spalte wird anhand der Größe der vertexlist mit null initialisiert
        for (Vertex v : vertexlist) {
            matrix.get(matrix.size() - 1).add(null);
        }

        // An Alle Zeilen wird ein weiterer null-Eintrag angehängt um die neue Spalte darzustellen
        for (List<Edge> i : matrix) {
            i.add(null);
        }
    }
}
