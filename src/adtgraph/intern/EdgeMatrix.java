package adtgraph.intern;

import adtgraph.extern.Vertex;
import java.util.ArrayList;

public class EdgeMatrix {

    private ArrayList<Vertex> vertexlist;
    
    private EdgeMatrix() {
         vertexlist = new ArrayList<>();
    }
    
    public ArrayList<Vertex> getIncident(Vertex vertex){
        return null;                        
    }
    
    public ArrayList<Vertex> getAdjacent(Vertex vertex){
        return null;                        
    }
    
    public Edge getEdge(Vertex source, Vertex target){
        return null;
    }
    
    public ArrayList<Vertex> getVertices(){
        return null;
    }
    
    public ArrayList<Vertex> getEdges(){
        return null;
    }
}