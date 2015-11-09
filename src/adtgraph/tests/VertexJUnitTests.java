package adtgraph.tests;

import adtgraph.extern.Graph;
import adtgraph.extern.Vertex;

//import adtgraph.Attribute;
//import adtgraph.Edge;
//import adtgraph.EdgeMatrix;
//import adtgraph.Graph;
//import adtgraph.Vertex;
import java.util.ArrayList;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VertexJUnitTests {

    ArrayList<Vertex> vertexlist = new ArrayList<>();

    Graph tempgraph2 = Graph.importG("io/graph_01.graph");
    Graph testgraph = Graph.importG("io/graph_01.graph");

    Random random = new Random();

    public VertexJUnitTests() {
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

        for (int i = 0; i < 1200; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 20; j++) {
                char c = chars[random.nextInt(chars.length)];
                sb.append(c);
            }
            String output = sb.toString();
            Vertex temp = Vertex.createV(output);
            vertexlist.add(temp);
            assertEquals(output, temp.getName());
        }
    }

    @Test
    public void CreateG() {
        CreateV();
        for (int i = 0; i < vertexlist.size() / 2; i++) {
            Graph.createG(vertexlist.get(random.nextInt(vertexlist.size())));
        }
        
        
        ArrayList<Vertex> testList = new ArrayList<>();
        testList.add(Vertex.createV("Hans"));
        
        Graph testGraph = Graph.createG(testList.get(0));
        
        assertEquals(testList,testGraph.getVertices());
    }

    @Test
    public void addEdge() {
        CreateV();
        testgraph = Graph.createG(vertexlist.get(random.nextInt(vertexlist.size())));

        for (int i = 0; i < vertexlist.size() / 2; i++) {
            testgraph.addEdge(vertexlist.get(random.nextInt(vertexlist.size())), vertexlist.get(random.nextInt(vertexlist.size())));
        }
        
        ArrayList<Vertex> testList = new ArrayList<>();
        testList.add(Vertex.createV("Hans"));
        testList.add(Vertex.createV("Peter"));
        
        Graph testGraph = Graph.createG(testList.get(0));
        testGraph.addEdge(testList.get(0), testList.get(1));
        
        
        assertEquals(testList,testGraph.getEdges());
    }

    @Test
    public void addVertex() {
        CreateV();
        Graph testgraph = Graph.createG(Vertex.createV("TestVertex"));
        ArrayList<Vertex> testList = new ArrayList<>();
        
        testList.add(Vertex.createV("TestVertex"));
        
        testgraph.addVertex(Vertex.createV("Hans"));
        testList.add(Vertex.createV("Hans"));
        
        assertEquals(testList, testgraph.getVertices());
    }

    @Test
    public void deleteVertex() {
        
        testgraph.addEdge(Vertex.createV("Augsburg"), Vertex.createV("Hameln"));
        testgraph.addEdge(Vertex.createV("Augsburg"), Vertex.createV("Barmbek"));
        testgraph.addEdge(Vertex.createV("Augsburg"), Vertex.createV("Salzwedel"));

        tempgraph2.deleteVertex(Vertex.createV("Augsburg"));
        testgraph.deleteVertex(Vertex.createV("Augsburg"));

        ArrayList<Vertex> nullList = new ArrayList<>();
        
        assertEquals(nullList, tempgraph2.getAdjacent(Vertex.createV("Augsburg")));
        assertEquals(nullList, testgraph.getAdjacent(Vertex.createV("Augsburg")));

    }

    @Test
    public void deleteEdge() {
        tempgraph2.deleteEdge(Vertex.createV("Augsburg"), Vertex.createV("München"));
        testgraph.deleteEdge(Vertex.createV("Augsburg"), Vertex.createV("München"));

        assertEquals(tempgraph2.getVertices(), testgraph.getVertices());
    }

    @Test
    public void setAtE() {
        addEdge();
        testgraph.setAtE(testgraph.getEdges().get(0), testgraph.getEdges().get(1), "Test", 15);
        assertEquals((int) 15, (int) testgraph.getValE(testgraph.getEdges().get(0), testgraph.getEdges().get(1), "Test"));
    }

    @Test
    public void setAtV() {
        addVertex();
        testgraph.setAtV(testgraph.getVertices().get(1), "Testkrams", 5654);
        assertEquals((int) 5654, (int) testgraph.getValV(testgraph.getVertices().get(1), "Testkrams"));
    }

    @Test
    public void getIncident() {        
       Graph incidentG = Graph.createG(Vertex.createV("Hans"));
        incidentG.addEdge(Vertex.createV("Hans"), Vertex.createV("Peter"));
        incidentG.addEdge(Vertex.createV("Hans"), Vertex.createV("Franz"));
        incidentG.addEdge(Vertex.createV("Peter"), Vertex.createV("Franz"));
        incidentG.addEdge(Vertex.createV("Anna"), Vertex.createV("Hans"));
        
        ArrayList<Vertex> testList = new ArrayList<>();
        testList.add(Vertex.createV("Hans"));
        testList.add(Vertex.createV("Peter"));
        testList.add(Vertex.createV("Hans"));
        testList.add(Vertex.createV("Franz"));
        testList.add(Vertex.createV("Anna"));
        testList.add(Vertex.createV("Hans"));
        
        assertEquals(testList, incidentG.getIncident(Vertex.createV("Hans")));        
    }

    @Test
    public void getAdjacent() {
        Graph adjacentG = Graph.createG(Vertex.createV("Hans"));
        adjacentG.addEdge(Vertex.createV("Hans"), Vertex.createV("Peter"));
        adjacentG.addEdge(Vertex.createV("Hans"), Vertex.createV("Franz"));
        adjacentG.addEdge(Vertex.createV("Peter"), Vertex.createV("Franz"));
        adjacentG.addEdge(Vertex.createV("Anna"), Vertex.createV("Hans"));
        
        ArrayList<Vertex> testList = new ArrayList<>();
        testList.add(Vertex.createV("Peter"));
        testList.add(Vertex.createV("Franz"));
        testList.add(Vertex.createV("Anna"));
        
        assertEquals(testList, adjacentG.getAdjacent(Vertex.createV("Hans")));

    }

    @Test
    public void getTarget() {
        
        Graph adjacentG = Graph.createG(Vertex.createV("Hans"));
        adjacentG.addEdge(Vertex.createV("Hans"), Vertex.createV("Peter"));
        adjacentG.addEdge(Vertex.createV("Hans"), Vertex.createV("Franz"));
        adjacentG.addEdge(Vertex.createV("Peter"), Vertex.createV("Franz"));
        adjacentG.addEdge(Vertex.createV("Anna"), Vertex.createV("Hans"));
        
        ArrayList<Vertex> testList = new ArrayList<>();
        testList.add(Vertex.createV("Peter"));
        testList.add(Vertex.createV("Franz"));
        
        assertEquals(testList, adjacentG.getTarget(Vertex.createV("Hans")));

    }

    @Test
    public void getSource() {
        Graph adjacentG = Graph.createG(Vertex.createV("Hans"));
        adjacentG.addEdge(Vertex.createV("Hans"), Vertex.createV("Peter"));
        adjacentG.addEdge(Vertex.createV("Hans"), Vertex.createV("Franz"));
        adjacentG.addEdge(Vertex.createV("Peter"), Vertex.createV("Franz"));
        adjacentG.addEdge(Vertex.createV("Anna"), Vertex.createV("Hans"));
        
        ArrayList<Vertex> testList = new ArrayList<>();
        testList.add(Vertex.createV("Anna"));
        
        assertEquals(testList, adjacentG.getSource(Vertex.createV("Hans")));
    }

    @Test
    public void getEdges() {
        
        Graph adjacentG = Graph.createG(Vertex.createV("Hans"));
        adjacentG.addEdge(Vertex.createV("Hans"), Vertex.createV("Peter"));
        adjacentG.addEdge(Vertex.createV("Hans"), Vertex.createV("Franz"));
        adjacentG.addEdge(Vertex.createV("Peter"), Vertex.createV("Franz"));
        adjacentG.addEdge(Vertex.createV("Anna"), Vertex.createV("Hans"));
        
        ArrayList<Vertex> testList = new ArrayList<>();
        testList.add(Vertex.createV("Hans"));
        testList.add(Vertex.createV("Peter"));
        testList.add(Vertex.createV("Hans"));
        testList.add(Vertex.createV("Franz"));
        testList.add(Vertex.createV("Peter"));
        testList.add(Vertex.createV("Franz"));
        testList.add(Vertex.createV("Anna"));
        testList.add(Vertex.createV("Hans"));
        
        assertEquals(testList, adjacentG.getEdges());

    }

    @Test
    public void getVertices() {
        CreateV();

        Graph verticeTest = Graph.createG(Vertex.createV(vertexlist.get(0).getName()));

        for (Vertex v : vertexlist) {
            verticeTest.addVertex(v);
        }

        assertEquals(verticeTest.getVertices(), vertexlist);
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
    
    @Test
    public void bellf() {
        Graph bellfTest = Graph.importG("io/graph_01.graph");
        bellfTest.bellf(Vertex.createV("Augsburg"), Vertex.createV("Wolfsburg"));
        System.out.println("Test");
    }
}