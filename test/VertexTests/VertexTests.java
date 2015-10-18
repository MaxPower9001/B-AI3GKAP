/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VertexTests;

import adtgraph.extern.Graph;
import adtgraph.extern.Vertex;
import java.util.Random;
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
public class VertexTests {
    
    Random random = new Random();

    public VertexTests() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void CreateV() {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        
        for (int i = 0; i < 12000; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 20; j++) {
                char c = chars[random.nextInt(chars.length)];
                sb.append(c);
            }
            String output = sb.toString();
            assertEquals(output, Vertex.createV(output).name());
        }
    }

    @Test
    public void CreateG() {
        for(int i = 0; i < Vertex.vertexList.size()/2; i++){
            Graph.createG(Vertex.vertexList.get(random.nextInt(Vertex.vertexList.size())));
        }
    }

    @Test
    public void addEdge() {
        Graph testGraph = Graph.createG(Vertex.vertexList.get(random.nextInt(Vertex.vertexList.size())));
        
        for(int i = 0; i < Vertex.vertexList.size()/2; i++){
            testGraph.addEdge(Vertex.vertexList.get(random.nextInt(Vertex.vertexList.size())),Vertex.vertexList.get(random.nextInt(Vertex.vertexList.size())));
        }
        
        System.out.println(testGraph.getEdges());
    }
    
    @Test
    public void IO() throws Exception{
        Graph.importG("io/graph_01.graph").exportG("io/graph_01.dot");
        Graph.importG("io/graph_02.graph").exportG("io/graph_02.dot");
        Graph.importG("io/graph_03.graph").exportG("io/graph_03.dot");
        Graph.importG("io/graph_04.graph").exportG("io/graph_04.dot");
        Graph.importG("io/graph_05.graph").exportG("io/graph_05.dot");
        Graph.importG("io/graph_06.graph").exportG("io/graph_06.dot");
        Graph.importG("io/graph_07.graph").exportG("io/graph_07.dot");
        Graph.importG("io/graph_08.graph").exportG("io/graph_08.dot");
        Graph.importG("io/graph_09.graph").exportG("io/graph_09.dot");
        Graph.importG("io/graph_10.graph").exportG("io/graph_10.dot");
        Graph.importG("io/graph_11.graph").exportG("io/graph_11.dot");
        Graph.importG("io/graph_12.graph").exportG("io/graph_12.dot");
    }
    
}
