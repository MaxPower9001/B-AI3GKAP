/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adtgraph.algorithm.afgb4;

import adtgraph.extern.Graph;
import adtgraph.extern.Vertex;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rene
 */
public class hierholzeTest {
    public Graph graph;
    
    public hierholzeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getEulerTour method, of class hierholze.
     */
//    @Test
    public void testGetEulerTour() {
        System.out.println("getEulerTour");
        hierholze instance = new hierholze();
        ArrayList<Vertex> expResult = null;
        ArrayList<Vertex> result = instance.getEulerTour(graph);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEdgeAsDone method, of class hierholze.
     */
    @Test
    public void testSetEdgeAsDone() throws Exception {
        graph = Graph.importG("io/graph_02.graph");
        graph.exportG("testkram.dot");
        System.out.println("setEdgeAsDone");
        Vertex source = null;
        Vertex target = null;
        hierholze instance = new hierholze();
        instance.setEdgeAsDone(graph, source, target);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNotVisitedNeighbour method, of class hierholze.
     */
//    @Test
    public void testGetNotVisitedNeighbour() {
        System.out.println("getNotVisitedNeighbour");
        Vertex vertex = null;
        ArrayList<Vertex> visitedVertices = null;
        hierholze instance = new hierholze();
        Vertex expResult = null;
        Vertex result = instance.getNotVisitedNeighbour(graph, vertex, visitedVertices);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStartVertex method, of class hierholze.
     */
//    @Test
    public void testGetStartVertex() {
        System.out.println("getStartVertex");
        ArrayList<Vertex> verticesToChooseFrom = null;
        hierholze instance = new hierholze();
        Vertex expResult = null;
        Vertex result = instance.getStartVertex(graph, verticesToChooseFrom);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEdgesNotDone method, of class hierholze.
     */
//    @Test
    public void testGetEdgesNotDone() {
        System.out.println("getEdgesNotDone");
        hierholze instance = new hierholze();
        ArrayList<Vertex> expResult = null;
        ArrayList<Vertex> result = instance.getEdgesNotDone(graph);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
