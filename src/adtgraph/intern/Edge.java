package adtgraph.intern;

import adtgraph.extern.Vertex;
import java.util.ArrayList;

public class Edge {
    
    private Vertex source;
    private Vertex target;
    
    private final ArrayList<Attribute> attributeList;
    
    private Edge(){
        this.attributeList = new ArrayList<>();
    }
    
    public Edge(Vertex source, Vertex target){
        this.attributeList = new ArrayList<>();
        this.source = source;
        this.target = target;
    }

    public Vertex source() {
        return source;
    }

    public void source(Vertex source) {
        this.source = source;
    }

    public Vertex target() {
        return target;
    }

    public void target(Vertex target) {
        this.target = target;
    }
    
    public ArrayList<Attribute> attribute() {
        return attributeList;
    }

    public void attribute(Attribute attribute) {
        for (Attribute a : attributeList){
            if (a == attribute) {
                return;
            }
        }
            this.attributeList.add(attribute);
    }
    
}
