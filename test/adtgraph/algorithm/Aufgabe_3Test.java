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
    public void testFordf() {
        System.out.println("fordf");
        Graph graph = Graph.importG("io/graph_vorl.graph");
        Vertex source = Vertex.createV("q");
        Vertex sink = Vertex.createV("s");;
        ArrayList<Vertex> expResult = new ArrayList<>();
        expResult.add(Vertex.createV("q"));
        expResult.add(Vertex.createV("v2"));
        ArrayList<Vertex> result = Aufgabe_3.fordf(graph, source, sink);
        assertEquals(expResult, result);
    }

    /**
     * Test of edmondsk method, of class Aufgabe_3.
     */
    @Test
    public void testEdmondsk() {
        System.out.println("edmondsk");
        Graph graph = null;
        Vertex source = null;
        Vertex sink = null;
        ArrayList<Vertex> expResult = null;
        ArrayList<Vertex> result = Aufgabe_3.edmondsk(graph, source, sink);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
