package adtgraph.extern;

import adtgraph.intern.Attribute;
import java.util.ArrayList;

public class Vertex {
    
    private String name;
    private ArrayList<Attribute> attributeList = new ArrayList<>();
    
    private static ArrayList<Vertex> vertexList = new ArrayList<>();
    
    private Vertex(){
    
    }
    
    public Vertex(String name){
        this.name = name;        
    }
    
    public static Vertex createV(String name) {
        Vertex temp = null;
        boolean found = false;
        for (Vertex v : vertexList) {
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

    public ArrayList<Attribute> attribute() {
        return attributeList;
    }

    public void attribute(Attribute attribute) {
        boolean found = false;
        for (Attribute a : attributeList){
            if (a == attribute) {
                found = true;
                return;
            }
        }
            this.attributeList.add(attribute);
    }
}