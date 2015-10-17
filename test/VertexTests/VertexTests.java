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
}
