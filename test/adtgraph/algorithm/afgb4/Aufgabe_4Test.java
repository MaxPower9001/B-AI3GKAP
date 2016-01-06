/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adtgraph.algorithm.afgb4;

import adtgraph.extern.Graph;
import adtgraph.extern.Vertex;
import adtgraph.utils.Util;
import static adtgraph.utils.Util.COST;
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
    int reads;
    int writes;
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
    public void RuntimeTestGraph10alleStarteckenMddekRT() throws Exception {
        testgraph = Graph.importG("io/graph_10.graph");
        for(startposi = 0; startposi < testgraph.getVertices().size() ; startposi++) {
            Object[] returnValue = new Object[3];
            resultTests.add("graph_10");
            resultTests.add("mddek");
            resultTests.add(testgraph.getVertices().get(startposi)+"");
            resultTests.add(testgraph.getVertices().size()+"");
            returnValue = mddekRT.mddek(testgraph, startposi);            
            runtime = (int) returnValue[0];
            resultTests.add("");
            resultTests.add("");
            resultTests.add(runtime+"");
            resultTests.add(returnValue[2]+"");
            resultTests.add(calcCostOfSequence(testgraph,(ArrayList<Vertex>)returnValue[2])+"");
            utils.outputToCSV("io/graph_10.runtime",resultTests);
            resultTests = new ArrayList<String>();
        }
    }
    
    @Test
    public void IOGraph10alleStarteckenMddekIO() throws Exception {
        testgraph = Graph.importG("io/graph_10.graph");
        for(startposi = 0; startposi < testgraph.getVertices().size() ; startposi++) {
            Object[] returnValue = new Object[3];
            resultTests.add("graph_10");
            resultTests.add("mddek");
            resultTests.add(testgraph.getVertices().get(startposi)+"");
            resultTests.add(testgraph.getVertices().size()+"");
            returnValue = mddekIO.mddek(testgraph, startposi);
            writes = (int) returnValue[0];
            reads = (int) returnValue[1];
            resultTests.add(writes+"");
            resultTests.add(reads+"");
            resultTests.add("");
            resultTests.add(returnValue[2]+"");
            resultTests.add(calcCostOfSequence(testgraph,(ArrayList<Vertex>)returnValue[2])+"");
            utils.outputToCSV("io/graph_10.io",resultTests);
            resultTests = new ArrayList<String>();
        }
    }      
    
    @Test
    public void RuntimeTestGraph12alleStarteckenMddekRT() throws Exception {
        testgraph = Graph.importG("io/graph_12.graph");
        for(startposi = 0; startposi < testgraph.getVertices().size() ; startposi++) {
            Object[] returnValue = new Object[3];
            resultTests.add("graph_12");
            resultTests.add("mddek");
            resultTests.add(testgraph.getVertices().get(startposi)+"");
            resultTests.add(testgraph.getVertices().size()+"");
            returnValue = mddekRT.mddek(testgraph, startposi);
            runtime = (int) returnValue[0];
            resultTests.add("");
            resultTests.add("");
            resultTests.add(runtime+"");
            resultTests.add(returnValue[2]+"");
            resultTests.add(calcCostOfSequence(testgraph,(ArrayList<Vertex>)returnValue[2])+"");
            utils.outputToCSV("io/graph_12.runtime",resultTests);
            resultTests = new ArrayList<String>();
        }
    }
    
    @Test
    public void IOGraph12alleStarteckenMddekIO() throws Exception {
        testgraph = Graph.importG("io/graph_12.graph");
        for(startposi = 0; startposi < testgraph.getVertices().size() ; startposi++) {
            Object[] returnValue = new Object[3];
            resultTests.add("graph_12");
            resultTests.add("mddek");
            resultTests.add(testgraph.getVertices().get(startposi)+"");
            resultTests.add(testgraph.getVertices().size()+"");
            returnValue = mddekIO.mddek(testgraph, startposi);
            writes = (int) returnValue[0];
            reads = (int) returnValue[1];
            resultTests.add(writes+"");
            resultTests.add(reads+"");
            resultTests.add("");
            resultTests.add(returnValue[2]+"");
            resultTests.add(calcCostOfSequence(testgraph,(ArrayList<Vertex>)returnValue[2])+"");
            utils.outputToCSV("io/graph_12.io",resultTests);
            resultTests = new ArrayList<String>();
        }
    }
    
    @Test
    public void IOGraph11alleStarteckenMddekIO() throws Exception {
        testgraph = Graph.importG("io/graph_11.graph");
        for(startposi = 0; startposi < testgraph.getVertices().size() ; startposi++) {
            Object[] returnValue = new Object[3];
            resultTests.add("graph_11");
            resultTests.add("mddek");
            resultTests.add(testgraph.getVertices().get(startposi)+"");
            resultTests.add(testgraph.getVertices().size()+"");
            returnValue = mddekIO.mddek(testgraph, startposi);
            writes = (int) returnValue[0];
            reads = (int) returnValue[1];
            resultTests.add(writes+"");
            resultTests.add(reads+"");
            resultTests.add("");
            resultTests.add(returnValue[2]+"");
            resultTests.add(calcCostOfSequence(testgraph,(ArrayList<Vertex>)returnValue[2])+"");
            utils.outputToCSV("io/graph_11.io",resultTests);
            resultTests = new ArrayList<String>();
        }
    }
    
    @Test
    public void RuntimeTestGraph15alleStarteckenMddekRT() throws Exception {
        testgraph = Graph.importG("io/graph_15.graph");
        for(startposi = 0; startposi < testgraph.getVertices().size() ; startposi++) {
            Object[] returnValue = new Object[3];
            resultTests.add("graph_15");
            resultTests.add("mddek");
            resultTests.add(testgraph.getVertices().get(startposi)+"");
            resultTests.add(testgraph.getVertices().size()+"");
            returnValue = mddekRT.mddek(testgraph, startposi);
            runtime = (int) returnValue[0];
            resultTests.add("");
            resultTests.add("");
            resultTests.add(runtime+"");
            resultTests.add(returnValue[2]+"");
            resultTests.add(calcCostOfSequence(testgraph,(ArrayList<Vertex>)returnValue[2])+"");
            utils.outputToCSV("io/graph_15.runtime",resultTests);
            resultTests = new ArrayList<String>();
        }
    }
    
    @Test
    public void IOGraph15alleStarteckenMddekIO() throws Exception {
        testgraph = Graph.importG("io/graph_15.graph");
        for(startposi = 0; startposi < testgraph.getVertices().size() ; startposi++) {
            Object[] returnValue = new Object[3];
            resultTests.add("graph_15");
            resultTests.add("mddek");
            resultTests.add(testgraph.getVertices().get(startposi)+"");
            resultTests.add(testgraph.getVertices().size()+"");
            returnValue = mddekIO.mddek(testgraph, startposi);
            writes = (int) returnValue[0];
            reads = (int) returnValue[1];
            resultTests.add(writes+"");
            resultTests.add(reads+"");
            resultTests.add("");
            resultTests.add(returnValue[2]+"");
            resultTests.add(calcCostOfSequence(testgraph,(ArrayList<Vertex>)returnValue[2])+"");
            utils.outputToCSV("io/graph_15.io",resultTests);
            resultTests = new ArrayList<String>();
        }
    }
    
    @Test
    public void RuntimeTestGraph11alleStarteckenMddekRT() throws Exception {
        testgraph = Graph.importG("io/graph_11.graph");
        for(startposi = 0; startposi < testgraph.getVertices().size() ; startposi++) {
            Object[] returnValue = new Object[3];
            resultTests.add("graph_11");
            resultTests.add("mddek");
            resultTests.add(testgraph.getVertices().get(startposi)+"");
            resultTests.add(testgraph.getVertices().size()+"");
            returnValue = mddekRT.mddek(testgraph, startposi);
            runtime = (int) returnValue[0];
            resultTests.add("");
            resultTests.add("");
            resultTests.add(runtime+"");
            resultTests.add(returnValue[2]+"");
            resultTests.add(calcCostOfSequence(testgraph,(ArrayList<Vertex>)returnValue[2])+"");
            utils.outputToCSV("io/graph_11.runtime",resultTests);
            resultTests = new ArrayList<String>();
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Test
    public void RuntimeTestGraph02alleStarteckenHierholzeRT() throws Exception {

        testgraph = Graph.importG("io/graph_02.graph");
        for(startposi = 0; startposi < testgraph.getVertices().size() ; startposi++) {
            Object[] returnValue = new Object[3];
            resultTests.add("graph_02");
            resultTests.add("hierholze");
            resultTests.add(testgraph.getVertices().get(startposi)+"");
            resultTests.add(testgraph.getVertices().size()+"");
            returnValue = hierholzeRT.getEulerTour(testgraph, startposi);
            runtime = (int) returnValue[0];
            resultTests.add("");
            resultTests.add("");
            resultTests.add(runtime+"");
            resultTests.add(returnValue[2]+"");
            resultTests.add(calcCostOfSequence(testgraph,(ArrayList<Vertex>)returnValue[2])+"");
            utils.outputToCSV("io/graph_02.runtime",resultTests);
            resultTests = new ArrayList<String>();
        }
    }
    
    @Test
    public void IOTestGraph02alleStarteckenHierholzeIO() throws Exception {
        testgraph = Graph.importG("io/graph_02.graph");
        for(startposi = 0; startposi < testgraph.getVertices().size() ; startposi++) {
            Object[] returnValue = new Object[3];
            resultTests.add("graph_02");
            resultTests.add("hierholze");
            resultTests.add(testgraph.getVertices().get(startposi)+"");
            resultTests.add(testgraph.getVertices().size()+"");
            resultTests.add("");
            returnValue = hierholzeIO.getEulerTour(testgraph,startposi);
            writes = (int) returnValue[0];
            reads = (int) returnValue[1];
            resultTests.add(reads+"");
            resultTests.add(writes+"");
            resultTests.add("");
            resultTests.add(returnValue[2]+"");
            resultTests.add(calcCostOfSequence(testgraph,(ArrayList<Vertex>)returnValue[2])+"");
            utils.outputToCSV("io/graph_02.io",resultTests);
            resultTests = new ArrayList<String>();
        }
    }
      
    @Test
    public void RuntimeTestGraph10alleStarteckenHierholzeRT() throws Exception {

        testgraph = Graph.importG("io/graph_10.graph");
        for(startposi = 0; startposi < testgraph.getVertices().size() ; startposi++) {
            Object[] returnValue = new Object[3];
            resultTests.add("graph_10");
            resultTests.add("hierholze");
            resultTests.add(testgraph.getVertices().get(startposi)+"");
            resultTests.add(testgraph.getVertices().size()+"");
            returnValue = hierholzeRT.getEulerTour(testgraph, startposi);
            runtime = (int) returnValue[0];
            resultTests.add("");
            resultTests.add("");
            resultTests.add(runtime+"");
            resultTests.add(returnValue[2]+"");
            resultTests.add(calcCostOfSequence(testgraph,(ArrayList<Vertex>)returnValue[2])+"");
            utils.outputToCSV("io/graph_10.runtime",resultTests);
            resultTests = new ArrayList<String>();
        }
    }
    
    @Test
    public void IOTestGraph10alleStarteckenHierholzeIO() throws Exception {
        testgraph = Graph.importG("io/graph_10.graph");
        for(startposi = 0; startposi < testgraph.getVertices().size() ; startposi++) {
            Object[] returnValue = new Object[3];
            resultTests.add("graph_10");
            resultTests.add("hierholze");
            resultTests.add(testgraph.getVertices().get(startposi)+"");
            resultTests.add(testgraph.getVertices().size()+"");
            resultTests.add("");
            returnValue = hierholzeIO.getEulerTour(testgraph,startposi);
            writes = (int) returnValue[0];
            reads = (int) returnValue[1];
            resultTests.add(reads+"");
            resultTests.add(writes+"");
            resultTests.add("");
            resultTests.add(returnValue[2]+"");
            resultTests.add(calcCostOfSequence(testgraph,(ArrayList<Vertex>)returnValue[2])+"");
            utils.outputToCSV("io/graph_10.io",resultTests);
            resultTests = new ArrayList<String>();
        }
    }
    
        @Test
    public void RuntimeTestGraph11alleStarteckenHierholzeRT() throws Exception {

        testgraph = Graph.importG("io/graph_11.graph");
        for(startposi = 0; startposi < testgraph.getVertices().size() ; startposi++) {
            Object[] returnValue = new Object[3];
            resultTests.add("graph_11");
            resultTests.add("hierholze");
            resultTests.add(testgraph.getVertices().get(startposi)+"");
            resultTests.add(testgraph.getVertices().size()+"");
            returnValue = hierholzeRT.getEulerTour(testgraph, startposi);
            runtime = (int) returnValue[0];
            resultTests.add("");
            resultTests.add("");
            resultTests.add(runtime+"");
            resultTests.add(returnValue[2]+"");
            resultTests.add(calcCostOfSequence(testgraph,(ArrayList<Vertex>)returnValue[2])+"");
            utils.outputToCSV("io/graph_11.runtime",resultTests);
            resultTests = new ArrayList<String>();
        }
    }
    
    @Test
    public void IOTestGraph11alleStarteckenHierholzeIO() throws Exception {
        testgraph = Graph.importG("io/graph_11.graph");
        for(startposi = 0; startposi < testgraph.getVertices().size() ; startposi++) {
            Object[] returnValue = new Object[3];
            resultTests.add("graph_11");
            resultTests.add("hierholze");
            resultTests.add(testgraph.getVertices().get(startposi)+"");
            resultTests.add(testgraph.getVertices().size()+"");
            resultTests.add("");
            returnValue = hierholzeIO.getEulerTour(testgraph,startposi);
            writes = (int) returnValue[0];
            reads = (int) returnValue[1];
            resultTests.add(reads+"");
            resultTests.add(writes+"");
            resultTests.add("");
            resultTests.add(returnValue[2]+"");
            resultTests.add(calcCostOfSequence(testgraph,(ArrayList<Vertex>)returnValue[2])+"");
            utils.outputToCSV("io/graph_11.io",resultTests);
            resultTests = new ArrayList<String>();
        }
    }
    public int calcCostOfSequence(Graph graph, ArrayList<Vertex> vertexSequence) {
        int costOfCurrentSequence = 0;
        for(int i = 0; i < vertexSequence.size()-1; i++){
            costOfCurrentSequence = costOfCurrentSequence + graph.getValE(vertexSequence.get(i), vertexSequence.get(i+1), COST);
        }
        return costOfCurrentSequence;
    }
    
}
