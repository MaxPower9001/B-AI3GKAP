package adtgraph.extern;

import adtgraph.intern.Attribute;
import java.util.ArrayList;

public class Vertex {
    
    private String name;
    private final ArrayList<Attribute> attributeList;
    
    private static final ArrayList<Vertex> vertexList = new ArrayList<>();
        
    public Vertex(String name){
        this.attributeList = new ArrayList<>();
        this.name = name;        
    }
    
    public static Vertex createV(String name) {
        for (Vertex v : vertexList) {
            if(v.getName().equals(name)) {
                return v;
            }
        }
        Vertex temp = new Vertex(name);
        vertexList.add(temp);
        return temp;
    }

    public String getName() {
        return name;
    }

    public void name(String name) {
        this.name = name;
    }

    public ArrayList<Attribute> attribute() {
        return attributeList;
    }

    public void attribute(Attribute attribute) {
        for (Attribute a : attributeList){
            if (a == attribute) {
                return;
            }
            if (a.getName().equals(attribute.getName())){
                a.value(attribute.value());
            }
        }
            this.attributeList.add(attribute);
    }
    
    @Override
    public String toString(){
        return this.name;
    }
    
    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        } else if(this.getClass() == o.getClass()) {
            Vertex that = (Vertex) o;
            
            if(that.getName().equals(this.getName()) && this.attributeList.equals(that.attributeList)){
                return true;
            }else{
                return false;
            }
        }
        
        
        return false;
    }
}