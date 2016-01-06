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

import static adtgraph.utils.Util.*;
import java.util.Arrays;

/**
 *
 * @author Rene
 */
public class mddekTest {
    public Graph graph;
    
    public mddekTest() {
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
    @Test
    public void testMddek() throws Exception {
        graph = Graph.importG("io/graph_15.graph");
//        graph.exportG("testkram.dot");
        System.out.println("dichteste Ecke!!");
//        ArrayList<Vertex> expResult = null;
        ArrayList<Vertex> result = mddek.mddek(graph,0);
//        assertEquals(expResult, result);
    }
    
}
