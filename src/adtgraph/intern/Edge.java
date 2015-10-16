package adtgraph.intern;

import adtgraph.extern.Vertex;

public class Edge {
    
    private Vertex source;
    private Vertex target;
    
    private Edge(){
        
    }
    
    public Edge(Vertex source, Vertex target){
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
    
}
