package adtgraph.extern;

import adtgraph.intern.Attribute;
import adtgraph.intern.EdgeMatrix;
import java.util.ArrayList;

public class Graph {
    
    private final EdgeMatrix edgeMatrix = new EdgeMatrix();
    
    private Graph() {
        
    }
    
    public Graph(Vertex vertex){
        Graph graph = new Graph();
        graph.addVertex(vertex);
    }
    
    public static Graph createG(Vertex vertex){
        return new Graph(vertex);
    }
    
    
    public void addVertex(Vertex vertex){
        this.edgeMatrix.addVertex(vertex);
    }
    
    public void setAtE(Vertex source, Vertex target, String name, int value){
        this.edgeMatrix.setAtE(source, target, name, value);
    }
    
    public void deleteVertex(Vertex vertex){
        this.edgeMatrix.deleteVertex(vertex);
    }
    
    public void addEdge(Vertex source, Vertex target){
        this.edgeMatrix.addEdge(source,target);
    }
    
    public void deleteEdge(Vertex source, Vertex target){
        this.edgeMatrix.deleteEdge(source,target);
    }   
    
    public void setAtV(Vertex vertex, String name, int value){
        vertex.attribute(new Attribute(name,value));
    }
    
    public ArrayList<Vertex> getIncident(Vertex vertex){
        return this.edgeMatrix.getIncident(vertex);
    }
    
    public ArrayList<Vertex> getAdjacent(Vertex vertex){
        return this.edgeMatrix.getAdjacent(vertex);
    }
    
    public ArrayList<Vertex> getTarget(Vertex vertex){
        return this.edgeMatrix.getTarget(vertex);
    }
    
    public ArrayList<Vertex> getSource(Vertex vertex){
        return this.edgeMatrix.getSource(vertex);
    }
    
    public ArrayList<Vertex> getEdges(){
        return this.edgeMatrix.getEdges();
    }
    
    public ArrayList<Vertex> getVertices() {
        return this.edgeMatrix.getVertices();
    }  
    
    public int getValE(Vertex vertex, Vertex target, String name) throws Exception {
        return this.edgeMatrix.getValE(vertex,target,name);
    }
    
    public int getValV(Vertex vertex, String name) throws Exception{
        for(Attribute a : vertex.attribute()){
            if(a.name().equals(name)){
                return a.value();
            }
        }
        throw new Exception("Name nicht vorhanden!");
    }
    
    public void exportG(String filename) {
        System.out.println("I/O stinkt zum HImmel");
    }
    
    public static Graph importG(String filename) {
        return null;
    }
}
