package adtgraph.extern;

import adtgraph.intern.Attribute;
import java.util.ArrayList;

public class Vertex {
    
    private String name;
    private ArrayList<Attribute> attributeList = new ArrayList<>();
    
    public static final ArrayList<Vertex> vertexList = new ArrayList<>();
        
    public Vertex(String name){
        this.name = name;        
    }
    
    public static Vertex createV(String name) {
        for (Vertex v : vertexList) {
            if(v.name().equals(name)) {
                return v;
            }
        }
        Vertex temp = new Vertex(name);
        vertexList.add(temp);
        return temp;
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
    
    @Override
    public String toString(){
        return this.name;
    }
}