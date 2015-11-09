package adtgraph.intern;

import adtgraph.extern.Vertex;
import java.util.ArrayList;

public class Edge {

    private Vertex source;
    private Vertex target;

    private final ArrayList<Attribute> attributeList;

    private Edge() {
        this.attributeList = new ArrayList<>();
    }

    public Edge(Vertex source, Vertex target) {
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
        for (Attribute a : attributeList) {
            if (a == attribute) {
                return;
            }
            if (a.getName().equals(attribute.getName())) {
                a.value(attribute.value());
            }
        }
        this.attributeList.add(attribute);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (this.getClass() == o.getClass()) {
            Edge that = (Edge) o;

            if (this.source.equals(that.source) && this.target.equals(that.target) && this.attributeList.equals(that.attributeList)) {
                return true;
            }

        }

        return false;
    }

}
