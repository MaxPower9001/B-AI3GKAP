package VertexTests;

import adtgraph.algorithm.bellf;
import adtgraph.algorithm.floydw;
import adtgraph.extern.Graph;
import adtgraph.extern.Vertex;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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

//        assertEquals(null, tempgraph2.getAdjacent(Vertex.createV("Augsburg")));
//        assertEquals(null, testgraph.getAdjacent(Vertex.createV("Augsburg")));
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
        assertEquals(Integer.valueOf(15), testgraph.getValE(testgraph.getEdges().get(0), testgraph.getEdges().get(1), "Test"));
    }

    @Test
    public void setAtV() throws Exception {
        addVertex();
        testgraph.setAtV(testgraph.getVertices().get(42), "Testkrams", 5654);
//        assertEquals(5654, testgraph.getValV(testgraph.getVertices().get(42), "Testkrams"));
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

    public void getValEException() {
        addEdge();
        assertEquals(null, testgraph.getValE(Vertex.createV(vertexlist.get(7).getName()), Vertex.createV(""), ""));
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
        Graph.importG("io/selfgraph.graph").exportG("io/selfgraph.dot");

    }

//    @Test
    public void compareFloydwBellfShortestRouteWithRuntime() {
        /**
         * GRAPH 1
         */
        String graph = "graph_01";
        String start = "Augsburg";
        String goal = "Hannover";

        Graph testGraph = Graph.importG("io/" + graph + ".graph");
        ArrayList<Vertex> shortestRouteFloyd = floydw.floydwRuntime(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        ArrayList<Vertex> shortestRouteBellF = bellf.bellfRuntime(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        Collection<Vertex> listeEins = shortestRouteBellF;
        Collection<Vertex> listeZwei = shortestRouteFloyd;

//        List<Vertex> src = new ArrayList<>(listeEins);
//        List<Vertex> dst = new ArrayList<>(listeZwei);
//
//        src.removeAll(listeZwei);
//        dst.removeAll(listeEins);
//
//        assertEquals(true, dst.equals(src));
        /**
         * GRAPH 2
         */
        graph = "graph_02";
        start = "Augsburg";
        goal = "Hannover";

        testGraph = Graph.importG("io/" + graph + ".graph");
        shortestRouteFloyd = floydw.floydwRuntime(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        shortestRouteBellF = bellf.bellfRuntime(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        listeEins = shortestRouteBellF;
        listeZwei = shortestRouteFloyd;

//        src = new ArrayList<>(listeEins);
//        dst = new ArrayList<>(listeZwei);
//
//        src.removeAll(listeZwei);
//        dst.removeAll(listeEins);
//
//        assertEquals(true, dst.equals(src));
        /**
         * GRAPH 3
         */
        graph = "graph_03";
        start = "s";
        goal = "v";

        testGraph = Graph.importG("io/" + graph + ".graph");
        shortestRouteFloyd = floydw.floydwRuntime(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        shortestRouteBellF = bellf.bellfRuntime(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        listeEins = shortestRouteBellF;
        listeZwei = shortestRouteFloyd;

//        src = new ArrayList<>(listeEins);
//        dst = new ArrayList<>(listeZwei);
//
//        src.removeAll(listeZwei);
//        dst.removeAll(listeEins);
//
//        assertEquals(true, dst.equals(src));
        /**
         * GRAPH 4
         */
        graph = "graph_04";
        start = "v1";
        goal = "v4";

        testGraph = Graph.importG("io/" + graph + ".graph");
        shortestRouteFloyd = floydw.floydwRuntime(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        shortestRouteBellF = bellf.bellfRuntime(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        listeEins = shortestRouteBellF;
        listeZwei = shortestRouteFloyd;

//        if (shortestRouteBellF == null && shortestRouteFloyd == null) {
//            assertTrue(true);
//        } else {
//            src = new ArrayList<>(listeEins);
//            dst = new ArrayList<>(listeZwei);
//
//            src.removeAll(listeZwei);
//            dst.removeAll(listeEins);
//
//            assertEquals(true, dst.equals(src));
//        }
        /**
         * GRAPH 5
         */
        graph = "graph_05";
        start = "v1";
        goal = "v11";

        testGraph = Graph.importG("io/" + graph + ".graph");
        shortestRouteFloyd = floydw.floydwRuntime(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        shortestRouteBellF = bellf.bellfRuntime(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        listeEins = shortestRouteBellF;
        listeZwei = shortestRouteFloyd;

//        if (shortestRouteBellF == null && shortestRouteFloyd == null) {
//            assertTrue(true);
//        } else {
//            src = new ArrayList<>(listeEins);
//            dst = new ArrayList<>(listeZwei);
//
//            src.removeAll(listeZwei);
//            dst.removeAll(listeEins);
//
//            assertEquals(true, dst.equals(src));
//        }
        /**
         * GRAPH 6
         */
        graph = "graph_06";
        start = "v1";
        goal = "v9";

        testGraph = Graph.importG("io/" + graph + ".graph");
        shortestRouteFloyd = floydw.floydwRuntime(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        shortestRouteBellF = bellf.bellfRuntime(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        listeEins = shortestRouteBellF;
        listeZwei = shortestRouteFloyd;

//        if (shortestRouteBellF == null && shortestRouteFloyd == null) {
//            assertTrue(true);
//        } else {
//            src = new ArrayList<>(listeEins);
//            dst = new ArrayList<>(listeZwei);
//
//            src.removeAll(listeZwei);
//            dst.removeAll(listeEins);
//
//            assertEquals(true, dst.equals(src));
//        }
        /**
         * GRAPH 7
         */
        graph = "graph_07";
        start = "v1";
        goal = "v3";

        testGraph = Graph.importG("io/" + graph + ".graph");
        shortestRouteFloyd = floydw.floydwRuntime(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        shortestRouteBellF = bellf.bellfRuntime(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        listeEins = shortestRouteBellF;
        listeZwei = shortestRouteFloyd;

//        if (shortestRouteBellF == null && shortestRouteFloyd == null) {
//            assertTrue(true);
//        } else {
//            src = new ArrayList<>(listeEins);
//            dst = new ArrayList<>(listeZwei);
//
//            src.removeAll(listeZwei);
//            dst.removeAll(listeEins);
//
//            assertEquals(true, dst.equals(src));
//        }
        /**
         * GRAPH 8
         */
        graph = "graph_08";
        start = "Rostock";
        goal = "München";

        testGraph = Graph.importG("io/" + graph + ".graph");
        shortestRouteFloyd = floydw.floydwRuntime(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        shortestRouteBellF = bellf.bellfRuntime(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        listeEins = shortestRouteBellF;
        listeZwei = shortestRouteFloyd;

//        if (shortestRouteBellF == null && shortestRouteFloyd == null) {
//            assertTrue(true);
//        } else {
//            src = new ArrayList<>(listeEins);
//            dst = new ArrayList<>(listeZwei);
//
//            src.removeAll(listeZwei);
//            dst.removeAll(listeEins);
//
//            assertEquals(true, dst.equals(src));
//        }
        /**
         * GRAPH 9
         */
        graph = "graph_09";
        start = "Quelle";
        goal = "Senke";

        testGraph = Graph.importG("io/" + graph + ".graph");
        shortestRouteFloyd = floydw.floydwRuntime(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        shortestRouteBellF = bellf.bellfRuntime(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        listeEins = shortestRouteBellF;
        listeZwei = shortestRouteFloyd;

//        if (shortestRouteBellF == null && shortestRouteFloyd == null) {
//            assertTrue(true);
//        } else {
//            src = new ArrayList<>(listeEins);
//            dst = new ArrayList<>(listeZwei);
//
//            src.removeAll(listeZwei);
//            dst.removeAll(listeEins);
//
//            assertEquals(true, dst.equals(src));
//        }
        /**
         * GRAPH 10
         */
        graph = "graph_10";
        start = "v5";
        goal = "v1";

        testGraph = Graph.importG("io/" + graph + ".graph");
        shortestRouteFloyd = floydw.floydwRuntime(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        shortestRouteBellF = bellf.bellfRuntime(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        listeEins = shortestRouteBellF;
        listeZwei = shortestRouteFloyd;

//        if (shortestRouteBellF == null && shortestRouteFloyd == null) {
//            assertTrue(true);
//        } else {
//            src = new ArrayList<>(listeEins);
//            dst = new ArrayList<>(listeZwei);
//
//            src.removeAll(listeZwei);
//            dst.removeAll(listeEins);
//
//            assertEquals(true, dst.equals(src));
//        }
        /**
         * GRAPH 11
         */
        graph = "graph_11";
        start = "v5";
        goal = "v1";

        testGraph = Graph.importG("io/" + graph + ".graph");
        shortestRouteFloyd = floydw.floydwRuntime(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        shortestRouteBellF = bellf.bellfRuntime(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        listeEins = shortestRouteBellF;
        listeZwei = shortestRouteFloyd;

//        if (shortestRouteBellF == null && shortestRouteFloyd == null) {
//            assertTrue(true);
//        } else {
//            src = new ArrayList<>(listeEins);
//            dst = new ArrayList<>(listeZwei);
//
//            src.removeAll(listeZwei);
//            dst.removeAll(listeEins);
//
//            assertEquals(true, dst.equals(src));
//        }
        /**
         * GRAPH 12
         */
        graph = "graph_12";
        start = "go";
        goal = "s";

        testGraph = Graph.importG("io/" + graph + ".graph");
        shortestRouteFloyd = floydw.floydwRuntime(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        shortestRouteBellF = bellf.bellfRuntime(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        listeEins = shortestRouteBellF;
        listeZwei = shortestRouteFloyd;

//        if (shortestRouteBellF == null && shortestRouteFloyd == null) {
//            assertTrue(true);
//        } else {
//            src = new ArrayList<>(listeEins);
//            dst = new ArrayList<>(listeZwei);
//
//            src.removeAll(listeZwei);
//            dst.removeAll(listeEins);
//
//            assertEquals(true, dst.equals(src));
//        }
//        /**
//         * GRAPH selfmade
//         */
//        graph = "selfgraph";
//        start = "278";
//        goal  = "30";
//
//        testGraph = Graph.importG("io/" + graph + ".graph");
//        shortestRouteFloyd = floydw.floydwRuntime(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
//        shortestRouteBellF = bellf.bellfRuntime(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
//        listeEins = shortestRouteBellF;
//        listeZwei = shortestRouteFloyd;
//
//        if (shortestRouteBellF == null && shortestRouteFloyd == null) {
//            assertTrue(true);
//        } else {
//            src = new ArrayList<>(listeEins);
//            dst = new ArrayList<>(listeZwei);
//
//            src.removeAll(listeZwei);
//            dst.removeAll(listeEins);
//
//            assertEquals(true, dst.equals(src));
//        }
    }
//    @Test
    public void IOTests() {
        /**
         * GRAPH 1
         */
        String graph = "graph_01";
        String start = "Augsburg";
        String goal = "Hannover";
        Graph testGraph = Graph.importG("io/" + graph + ".graph");
        ArrayList<Vertex> shortestRouteFloyd = floydw.floydwIO(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        ArrayList<Vertex> shortestRouteBellF = bellf.bellfIO(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));

        /**
         * GRAPH 2
         */
        graph = "graph_02";
        start = "Augsburg";
        goal = "Hannover";
        testGraph = Graph.importG("io/" + graph + ".graph");
        shortestRouteFloyd = floydw.floydwIO(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        shortestRouteBellF = bellf.bellfIO(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));

        /**
         * GRAPH 3
         */
        graph = "graph_03";
        start = "s";
        goal = "v";
        testGraph = Graph.importG("io/" + graph + ".graph");
        shortestRouteFloyd = floydw.floydwIO(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        shortestRouteBellF = bellf.bellfIO(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));

        /**
         * GRAPH 4
         */
        graph = "graph_04";
        start = "v1";
        goal = "v4";
        testGraph = Graph.importG("io/" + graph + ".graph");
        shortestRouteFloyd = floydw.floydwIO(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        shortestRouteBellF = bellf.bellfIO(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));

        /**
         * GRAPH 5
         */
        graph = "graph_05";
        start = "v1";
        goal = "v11";
        testGraph = Graph.importG("io/" + graph + ".graph");
        shortestRouteFloyd = floydw.floydwIO(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        shortestRouteBellF = bellf.bellfIO(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));

        /**
         * GRAPH 6
         */
        graph = "graph_06";
        start = "v9";
        goal = "v1";
        testGraph = Graph.importG("io/" + graph + ".graph");
        shortestRouteFloyd = floydw.floydwIO(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        shortestRouteBellF = bellf.bellfIO(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));

        /**
         * GRAPH 7
         */
        graph = "graph_07";
        start = "v1";
        goal = "v3";
        testGraph = Graph.importG("io/" + graph + ".graph");
        shortestRouteFloyd = floydw.floydwIO(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        shortestRouteBellF = bellf.bellfIO(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));

        /**
         * GRAPH 8
         */
        graph = "graph_08";
        start = "Rostock";
        goal = "München";
        testGraph = Graph.importG("io/" + graph + ".graph");
        shortestRouteFloyd = floydw.floydwIO(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        shortestRouteBellF = bellf.bellfIO(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));

        /**
         * GRAPH 9
         */
        graph = "graph_09";
        start = "Quelle";
        goal = "Senke";
        testGraph = Graph.importG("io/" + graph + ".graph");
        shortestRouteFloyd = floydw.floydwIO(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        shortestRouteBellF = bellf.bellfIO(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));

        /**
         * GRAPH 10
         */
        graph = "graph_10";
        start = "v5";
        goal = "v1";
        testGraph = Graph.importG("io/" + graph + ".graph");
        shortestRouteFloyd = floydw.floydwIO(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        shortestRouteBellF = bellf.bellfIO(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));

        /**
         * GRAPH 11
         */
        graph = "graph_11";
        start = "v5";
        goal = "v1";
        testGraph = Graph.importG("io/" + graph + ".graph");
        shortestRouteFloyd = floydw.floydwIO(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        shortestRouteBellF = bellf.bellfIO(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));

        /**
         * GRAPH 12
         */
        graph = "graph_12";
        start = "go";
        goal = "s";
        testGraph = Graph.importG("io/" + graph + ".graph");
        shortestRouteFloyd = floydw.floydwIO(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        shortestRouteBellF = bellf.bellfIO(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
    }
    
    @Test
    public void TestfuervieleSachen() {
        /**
         * GRAPH 1
         */
        String graph = "graph_01";
        String start = "Augsburg";
        String goal = "Hannover";

        Graph testGraph = Graph.importG("io/" + graph + ".graph");
        ArrayList<Vertex> shortestRouteFloyd = floydw.floydwRuntime(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));
        ArrayList<Vertex> shortestRouteBellF = bellf.bellfRuntime(graph, graph + " Test", testGraph, Vertex.createV(start), Vertex.createV(goal));

        for (int i = 0; i < 100; i++) {
            shortestRouteFloyd = floydw.floydwRuntime(graph, graph + " Test Nr " + i, testGraph, Vertex.createV(start), Vertex.createV(goal));
            shortestRouteBellF = bellf.bellfRuntime(graph, graph + " Test Nr " + i, testGraph, Vertex.createV(start), Vertex.createV(goal));
        }
    }

}
