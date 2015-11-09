package adtgraph.intern;

import adtgraph.extern.Vertex;
import java.util.ArrayList;
import java.util.List;

public class EdgeMatrix {

    private final ArrayList<Vertex> vertexlist;

    private final List<List<Edge>> matrix;

    public EdgeMatrix() {
        this.matrix = new ArrayList<>();
        this.vertexlist = new ArrayList<>();
    }

    public ArrayList<Vertex> getIncident(Vertex vertex) {
        ArrayList<Vertex> tempT = getTarget(vertex);
        ArrayList<Vertex> tempS = getSource(vertex);
        ArrayList<Vertex> tempR = new ArrayList<>();        
                
        for(Vertex v : tempT){
            tempR.add(vertex);
            tempR.add(v);
        }
        
        for(Vertex v : tempS){
            tempR.add(v);
            tempR.add(vertex);
        }
        return tempR;
    }

    public ArrayList<Vertex> getAdjacent(Vertex vertex) {
        ArrayList<Vertex> temp = new ArrayList<>();
        
        if(getTarget(vertex) != null && getSource(vertex) != null){
            temp.addAll(getTarget(vertex));
            temp.addAll(getSource(vertex));            
        } 
        return temp;
        
    }

    public Edge getEdge(Vertex source, Vertex target) {
        for (int i = 0; i < vertexlist.size(); i++) {
            for (int j = 0; j < vertexlist.size(); j++) {
                if (matrix.get(i).get(j) != null && matrix.get(i).get(j).target() == target && matrix.get(i).get(j).source() == source) {
                    return matrix.get(i).get(j);
                }
            }
        }
         return null;
    }

    public ArrayList<Vertex> getVertices() {
        return vertexlist;
    }

    public ArrayList<Vertex> getEdges() {
        ArrayList<Vertex> temp  = new ArrayList<>();
        
        for (int i = 0; i < vertexlist.size(); i++) {
            for (int j = 0; j < vertexlist.size(); j++) {
                if (matrix.get(i).get(j) != null) {
                    temp.add(matrix.get(i).get(j).source());
                    temp.add(matrix.get(i).get(j).target());                    
                }
            }
        }
        
        return temp;
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
        if(vertexlist.indexOf((Vertex) vertex) != -1){
            if(vertexlist.size() > 1){
                matrix.remove(vertexlist.indexOf((Vertex) vertex));
                for (int i = 0; i < matrix.size(); i++) {
                    matrix.get(i).remove(vertexlist.indexOf((Vertex) vertex));
                }
                vertexlist.remove((Vertex) vertex);
            }
        }
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
                if (matrix.get(i).get(j) != null && matrix.get(i).get(j).source() == vertex) {
                    temp.add(matrix.get(i).get(j).target());
                }
            }
        }
        if(temp.isEmpty()){
            return null;
        } else {
            return temp;
        }
    }

    public ArrayList<Vertex> getSource(Vertex vertex) {
        ArrayList<Vertex> temp = new ArrayList<>();

        for (int i = 0; i < vertexlist.size(); i++) {
            for (int j = 0; j < vertexlist.size(); j++) {
                if (matrix.get(i).get(j) != null && matrix.get(i).get(j).target() == vertex) {
                    temp.add(matrix.get(i).get(j).source());
                }
            }
        }
        if(temp.isEmpty()){
            return null;
        } else {
            return temp;
        }
    }

    public int getValE(Vertex source, Vertex target, String name) {
        // Jedes Element in der Matrix wird durchlaufen
        for (int i = 0; i < vertexlist.size(); i++) {
            for (int j = 0; j < vertexlist.size(); j++) {
                
                // Ecke vorhanden und target und source entsprechen den Eingabeparametern
                if (matrix.get(i).get(j) != null && 
                    matrix.get(i).get(j).target() == target &&
                    matrix.get(i).get(j).source() == source) {
                    
                    // Alle Attribute der Ecke werden durchlaufen und nach dem String durchsucht
                    for (Attribute a : matrix.get(i).get(j).attribute()) {
                        if (a.getName().equals(name)) {
                            // Verlässt getValE Aufruf und gibt den entsprechenden value zurück
                            return a.value();
                        }
                    }
                }
            }
        }
        // Wenn kein Attribut gefunden
        return (Integer) null;
    }

    private void expandEdgeMatrix() {
        // Der Matrix wird eine weitere Spalte hinzugefügt
        matrix.add(new ArrayList<Edge>());

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
