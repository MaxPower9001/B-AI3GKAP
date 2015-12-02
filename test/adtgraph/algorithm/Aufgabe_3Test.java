/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adtgraph.algorithm;

import adtgraph.extern.Graph;
import adtgraph.extern.Vertex;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Anges
 */
public class Aufgabe_3Test {
    
    public Aufgabe_3Test() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of fordf method, of class Aufgabe_3.
     */
    @Test
    public void testFordf() throws Exception {
        System.out.println("-----------------------------");
        Graph graph = Graph.importG("io/graph_vorl.graph");
        Vertex source = Vertex.createV("q");
        Vertex sink = Vertex.createV("s");
        ArrayList<Vertex> expResult = new ArrayList<>();
        expResult.add(Vertex.createV("q"));
        expResult.add(Vertex.createV("v2"));
//        for(int i = 0; i < 100; i++){
//            graph = Graph.importG("io/graph_vorl.graph");
//            System.out.println("Durchlaufnr.: " + i);
//            ArrayList<Vertex> result = Aufgabe_3.fordf(graph, source, sink);
//            System.out.println(result);
//            result = null;
//        }
//        ArrayList<Vertex> result = Aufgabe_3.fordf(graph, source, sink);
//        System.out.println(result);
//        graph.exportG("bittebittebittebittebitte.dot");
//        
        System.out.println("------------Graph 08 -----------------");
        graph = Graph.importG("io/graph_08.graph");
        source = Vertex.createV("Rostock");
        sink = Vertex.createV("Muenchen");
        ArrayList<Vertex> result = Aufgabe_3.edmondsk(graph, source, sink);
        System.out.println(result);
        graph.exportG("graph08_e.dot");
        
//        System.out.println("------------Graph 09 -----------------");
//        graph = Graph.importG("io/graph_09.graph");
//        source = Vertex.createV("Quelle");
//        sink = Vertex.createV("Senke");
//        ArrayList<Vertex> result = Aufgabe_3.fordf(graph, source, sink);
//        System.out.println(result);
//        
    }

    /**
     * Test of edmondsk method, of class Aufgabe_3.
     */
    @Test
    public void testEdmondsk() throws Exception {
        System.out.println("edmondsk");
        Graph graph = Graph.importG("io/graph_vorl.graph");
        Vertex source = Vertex.createV("q");
        Vertex sink = Vertex.createV("s");
        ArrayList<Vertex> expResult = null;
        for(int i = 0; i < 100; i++){
            System.out.println("Durchlaufnr.: " + i);
            ArrayList<Vertex> result = Aufgabe_3.edmondsk(graph, source, sink);
            System.out.println(result);
        }
    }
    
}
