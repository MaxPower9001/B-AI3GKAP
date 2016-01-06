/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adtgraph.algorithm.afgb3;

import adtgraph.algorithm.afgb3.Aufgabe_3;
import adtgraph.extern.Graph;
import static adtgraph.algorithm.afgb3.Aufgabe_3.*;
import adtgraph.extern.Vertex;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import adtgraph.utils.*;

public class Aufgabe_3Tests {
    Util utils;
    Graph testgraph;
    long runtime;
    ArrayList<Vertex> resultX = new ArrayList<Vertex>();
    ArrayList<String> resultTests = new ArrayList<String>();
    ArrayList<Vertex> vertexlist = new ArrayList<>();

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }
    
    @Test
    public void Tests() throws Exception {
        
        testgraph = Graph.importG("io/graph_08.graph");
        resultX = edmondsk(testgraph, Vertex.createV("Rostock"), Vertex.createV("Muenchen"));
        testgraph.exportG("io/graph_08_edmondsk.dot");
        System.out.println(resultX);
        
        testgraph = Graph.importG("io/graph_08.graph");
        resultX = fordf(testgraph, Vertex.createV("Rostock"), Vertex.createV("Muenchen"));
        testgraph.exportG("io/graph_08_fordf.dot");
        System.out.println(resultX);
        
        testgraph = Graph.importG("io/graph_09.graph");
        resultX = edmondsk(testgraph, Vertex.createV("Quelle"), Vertex.createV("Senke"));
        testgraph.exportG("io/graph_09_edmondsk.dot");
        System.out.println(resultX);
        
        testgraph = Graph.importG("io/graph_09.graph");
        resultX = fordf(testgraph, Vertex.createV("Quelle"), Vertex.createV("Senke"));
        testgraph.exportG("io/graph_09_fordf.dot");
        System.out.println(resultX);
        
        testgraph = Graph.importG("io/graph_14.graph");
        System.out.println(fordf(testgraph, Vertex.createV("sysinit.target"), Vertex.createV("shutdown.target")));
        testgraph.exportG("io/graph_14_fordf.dot");  
        System.out.println(resultX);
        
    }


    // Hier sollte das Ergebnis in jedem Durchlauf immer gleich sein, da per Queue ausgewählt wird    
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
    
    @Test
    public void IOTests() throws Exception {

        
    }
    
    @Test
    public void RuntimeTests() throws Exception {
        testgraph = Graph.importG("io/graph_08.graph");
        runtime = (long) edmondskRuntime(testgraph, Vertex.createV("Rostock"), Vertex.createV("Muenchen"))[1];
        resultTests.add(runtime+"");
        testgraph = Graph.importG("io/graph_08.graph");
        runtime = (long) fordfRuntime(testgraph, Vertex.createV("Rostock"), Vertex.createV("Muenchen"))[1];
        resultTests.add(runtime+"");
        utils.outputToCSV("io/graph_08.runtime",resultTests);
        resultTests = new ArrayList<String>();

        
        testgraph = Graph.importG("io/graph_09.graph");
        runtime = (long) edmondskRuntime(testgraph, Vertex.createV("Quelle"), Vertex.createV("Senke"))[1];
        resultTests.add(runtime+"");
        testgraph = Graph.importG("io/graph_09.graph");
        runtime = (long) fordfRuntime(testgraph, Vertex.createV("Quelle"), Vertex.createV("Senke"))[1];
        resultTests.add(runtime+"");
        utils.outputToCSV("io/graph_09.runtime",resultTests);
        resultTests = new ArrayList<String>();
        
        testgraph = Graph.importG("io/graph_14.graph");
        runtime = (long) edmondskRuntime(testgraph, Vertex.createV("sysinit.target"), Vertex.createV("shutdown.target"))[1];
        resultTests.add(runtime+"");
        testgraph = Graph.importG("io/graph_14.graph");
        runtime = (long) fordfRuntime(testgraph, Vertex.createV("sysinit.target"), Vertex.createV("shutdown.target"))[1];
        resultTests.add(runtime+"");
        utils.outputToCSV("io/graph_14.runtime",resultTests);
        resultTests = new ArrayList<String>();
        
    }
}
