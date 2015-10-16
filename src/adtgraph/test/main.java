package adtgraph.test;


import adtgraph.extern.Graph;
import adtgraph.extern.Vertex;

public class main {
    public static void main(String [] args) throws Exception            
	{
            Vertex.createV("Hans");
            Vertex.createV("Hans");
            Vertex.createV("Peter");
            Vertex.createV("Uschi");
            Vertex.createV("Hans");
            Vertex.createV("Hans");
            Vertex.createV("Peter");
            Vertex.createV("Uschi");
            
            
            Graph g1 = Graph.createG(Vertex.createV("Peter"));
            System.out.println(Vertex.vertexList.toString());
            g1.addVertex(Vertex.createV("Uschi"));
            g1.addVertex(Vertex.createV("Franz"));
            g1.addVertex(Vertex.createV("Franz"));
            g1.addVertex(Vertex.createV("Franz"));
            g1.addVertex(Vertex.createV("Franz"));
            g1.addVertex(Vertex.createV("Franz"));
            g1.addVertex(Vertex.createV("Franz"));
            g1.addEdge(Vertex.createV("Franz"), Vertex.createV("Uschi"));
//            g1.deleteEdge(Vertex.createV("Franz"),Vertex.createV("Uschi"));
            System.out.println(g1.getEdges());
            System.out.println("----------------");
            System.out.println(g1.getSource(Vertex.createV("Franz")));
            System.out.println("G1 sout");
            System.out.println(g1.getVertices().toString());
            System.out.println(g1.getEdges().toString());
            g1.setAtV(Vertex.createV("Franz"), "peter", 12);
            g1.setAtV(Vertex.createV("Franz"), "kneter", 13);
            System.out.println(g1.getValV(Vertex.createV("Franz"), "kneter"));
        }    
}