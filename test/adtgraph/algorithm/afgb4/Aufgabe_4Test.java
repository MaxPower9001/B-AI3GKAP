/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adtgraph.algorithm.afgb4;

import adtgraph.extern.Graph;
import adtgraph.extern.Vertex;
import adtgraph.utils.Util;
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
public class Aufgabe_4Test {
    Util utils;
    Graph testgraph;
    long runtime;
    int startposi;
    ArrayList<Vertex> resultX = new ArrayList<Vertex>();
    ArrayList<String> resultTests = new ArrayList<String>();
    ArrayList<Vertex> vertexlist = new ArrayList<>();
    
    public Aufgabe_4Test() {
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

    @Test
    public void RuntimeTests() throws Exception {
        testgraph = Graph.importG("io/graph_15.graph");
        startposi = 0;
        runtime = (long) mddekRT.mddek(testgraph,startposi)[0];
        resultTests.add(runtime+"");
        utils.outputToCSV("io/graph_15.runtime",resultTests);
        resultTests = new ArrayList<String>();
    }
    
    @Test
    public void RuntimeTestGraph10alleStarteckenMddekRT() throws Exception {
        testgraph = Graph.importG("io/graph_10.graph");
        for(startposi = 0; startposi < testgraph.getVertices().size() ; startposi++) {
            runtime = (long) mddekRT.mddek(testgraph,startposi)[0];
            resultTests.add(runtime+"");
            utils.outputToCSV("io/graph_10.runtime",resultTests);
            resultTests = new ArrayList<String>();
        }
    }
    
    // TODO startposi parameter im hierholze Algorithmus einbauen...
    @Test
    public void RuntimeTestGraph10alleStarteckenHierholzeRT() throws Exception {
        testgraph = Graph.importG("io/graph_2.graph");
        for(startposi = 0; startposi < testgraph.getVertices().size() ; startposi++) {
            runtime = (long) hierholzeRT.getEulerTour(testgraph,startposi)[0];
            resultTests.add(runtime+"");
            utils.outputToCSV("io/graph_10.runtime",resultTests);
            resultTests = new ArrayList<String>();
        }
    }
    
}
