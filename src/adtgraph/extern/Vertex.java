package adtgraph.extern;

import java.util.ArrayList;

public class Vertex {
    
    private String name;
    
    private static ArrayList<Vertex> vertexlist = new ArrayList<>();
    
    private Vertex(){
    
    }
    
    public Vertex(String name){
        this.name = name;        
    }
    
    public static Vertex createV(String name) {
        Vertex temp = null;
        boolean found = false;
        for (Vertex v : vertexlist) {
            if(v.name().equals(name)) {
                temp = v;
                found = true;
                break;
            }
        }
        
        if(found){
            return temp;            
        }
        else return new Vertex(name);
        
    }

    public String name() {
        return name;
    }

    public void name(String name) {
        this.name = name;
    }
}