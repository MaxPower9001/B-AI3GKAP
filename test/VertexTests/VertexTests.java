package VertexTests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import adtgraph.extern.Graph;
import adtgraph.extern.Vertex;
import java.util.ArrayList;
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

    ArrayList<Vertex> vertexlist = new ArrayList<>();

    Graph tempgraph2 = Graph.importG("io/graph_01.graph");
    Graph testgraph = Graph.importG("io/graph_01.graph");

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
            Vertex temp = Vertex.createV(output);
            vertexlist.add(temp);
            assertEquals(output, temp.name());
        }
    }

    @Test
    public void CreateG() {
        CreateV();
        for (int i = 0; i < vertexlist.size() / 2; i++) {
            Graph.createG(vertexlist.get(random.nextInt(vertexlist.size())));
        }
    }

    @Test
    public void addEdge() {
        CreateV();
        testgraph = Graph.createG(vertexlist.get(random.nextInt(vertexlist.size())));

        for (int i = 0; i < vertexlist.size() / 2; i++) {
            testgraph.addEdge(vertexlist.get(random.nextInt(vertexlist.size())), vertexlist.get(random.nextInt(vertexlist.size())));
        }
    }

    @Test
    public void addVertex() {
        CreateV();
        testgraph = Graph.createG(Vertex.createV("TestVertex"));
        for (int i = 0; i < vertexlist.size() / 2; i++) {
            testgraph.addVertex(vertexlist.get(random.nextInt(vertexlist.size())));
        }
    }

    @Test
    public void deleteVertex() {
        testgraph.addEdge(Vertex.createV("Augsburg"), Vertex.createV("Hameln"));
        testgraph.addEdge(Vertex.createV("Augsburg"), Vertex.createV("Barmbek"));
        testgraph.addEdge(Vertex.createV("Augsburg"), Vertex.createV("Salzwedel"));

        tempgraph2.deleteVertex(Vertex.createV("Augsburg"));
        testgraph.deleteVertex(Vertex.createV("Augsburg"));

        assertEquals(null, tempgraph2.getAdjacent(Vertex.createV("Augsburg")));
        assertEquals(null, testgraph.getAdjacent(Vertex.createV("Augsburg")));

    }

    @Test
    public void deleteEdge() {
        tempgraph2.deleteEdge(Vertex.createV("Augsburg"), Vertex.createV("München"));
        testgraph.deleteEdge(Vertex.createV("Augsburg"), Vertex.createV("München"));

        assertEquals(tempgraph2.getVertices(), testgraph.getVertices());
    }

    @Test
    public void setAtE() throws Exception {
        addEdge();
        testgraph.setAtE(testgraph.getEdges().get(0), testgraph.getEdges().get(1), "Test", 15);
        assertEquals(15, testgraph.getValE(testgraph.getEdges().get(0), testgraph.getEdges().get(1), "Test"));
    }

    @Test
    public void setAtV() throws Exception {
        addVertex();
        testgraph.setAtV(testgraph.getVertices().get(42), "Testkrams", 5654);
        assertEquals(5654, testgraph.getValV(testgraph.getVertices().get(42), "Testkrams"));
    }

    @Test
    public void getIncident() {
        addVertex();
        
        testgraph.addEdge(vertexlist.get(56), vertexlist.get(938));
        testgraph.addEdge(vertexlist.get(938), vertexlist.get(65));
        testgraph.addEdge(vertexlist.get(938), vertexlist.get(938));
        testgraph.addEdge(vertexlist.get(38), vertexlist.get(101));
        
        ArrayList<Vertex> test = new ArrayList<>();
        test.add(vertexlist.get(56));
        test.add(vertexlist.get(938));
        test.add(vertexlist.get(938));
        test.add(vertexlist.get(65));
        
        
        assertEquals(test, testgraph.getIncident(vertexlist.get(938)));
        
    }

    @Test
    public void getAdjacent() {

    }

    @Test
    public void getTarget() {

    }

    @Test
    public void getSource() {

    }

    @Test
    public void getEdges() {

    }

    @Test
    public void getVertices() {
        CreateV();

        Graph verticeTest = Graph.createG(Vertex.createV(vertexlist.get(0).name()));

        for (Vertex v : vertexlist) {
            verticeTest.addVertex(v);
        }

        assertEquals(verticeTest.getVertices(), vertexlist);
    }

    @Test(expected = Exception.class)
    public void getValEException() throws Exception {
        addEdge();
        testgraph.getValE(Vertex.createV(vertexlist.get(7).name()), Vertex.createV(""), "");
    }
    
    @Test
    public void IO() throws Exception {

        tempgraph2 = Graph.importG("io/graph_01.graph");
        tempgraph2.setAtE(Vertex.createV("Augsburg"), Vertex.createV("München"), "Hans", 2);
        tempgraph2.exportG("io/graph_01_improved.dot");

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
