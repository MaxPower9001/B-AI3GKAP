package adtgraph.algorithm.afgb4;

import adtgraph.extern.Graph;
import adtgraph.extern.Vertex;
import java.util.ArrayList;

import static adtgraph.utils.Util.*;

public class hierholze {
    public static String mode;

    /**
     * Main function with debugging option for finding an euler tour in 
     * specified graph
     * <p>
     * @param graph     graph to find an euler tour in
     * @param mode      sets the debug level <code>[NODEBUG,DEBUG,VERBOSE]</code>
     * @return          VertexList containing the euler tour
     */
    public static ArrayList<Vertex> getEulerTour(Graph graph, String mode) {
        hierholze.mode = mode;
        ArrayList<Vertex> returnValue = new ArrayList<>();

        while (!getEdgesNotDone(graph).isEmpty()) {
            Vertex startVertex = getStartVertex(graph, returnValue);
            System.out.println(findSubTour(graph, startVertex));
        }

        return returnValue;
    }
    
    /**
     * main function with debug set to <code>NODEBUG</code> for finding an euler 
     * tour in specified graph
     * <p>
     * @param graph     graph to find an euler tour in
     * @return          VertexList containing the euler tour
     */
    public static ArrayList<Vertex> getEulerTour(Graph graph) {
        return getEulerTour(graph, NODEBUG);
    }

    /**
     * marks an edge as done
     *
     * @param graph     graph containing the edge
     * @param source    source vertex of the edge
     * @param target    target vertex of the edge
     */
    private static void setEdgeAsDone(Graph graph, Vertex source, Vertex target) {
        graph.setAtE(source, target, DONE, 1);
    }

    /**
     * gets a vertex to start, if no vertex available return <code>null</code>
     *
     * @param graph                     graph to get a vertex from
     * @param verticesToChooseFrom      specifies the vertices to choose from
     * @return - vertex to start, or null if no vertex available
     */
    private static Vertex getStartVertex(Graph graph, ArrayList<Vertex> verticesToChooseFrom) {
        if (verticesToChooseFrom.isEmpty()) {
            return getEdgesNotDone(graph).get(0);
        } else {
            return getEdgesNotDone(graph, verticesToChooseFrom.get(0)).get(0);
        }
    }

    /**
     * pulls all edges from <code>graph</code> and removes every pair of vertices
     * representing the edge if the edge attribute done != 0
     *
     * @param graph     graph which is used to get all edges
     * @return          contains all edges with attribute done == 0
     */
    private static ArrayList<Vertex> getEdgesNotDone(Graph graph) {

        ArrayList<Vertex> returnValue = graph.getEdges();
        for (int i = 0; i < returnValue.size(); i = i + 2) {
            Vertex source = returnValue.get(i);
            Vertex target = returnValue.get(i + 1);

            if (graph.getValE(source, target, DONE) != 0) {
                returnValue.remove(i + 1);
                returnValue.remove(i);
                i = i - 2;
            }
        }
        return returnValue;
    }

    /**
     * pulls all incident edges to <code>vertex</code> and removes every pair of
     * vertices representing the edge if the edge attribute done != 0
     *
     * @param graph     graph which is used to get all edges
     * @param vertex    vertex to start from
     * @return          contains all edges with attribute done == 0
     */
    private static ArrayList<Vertex> getEdgesNotDone(Graph graph, Vertex vertex) {

        ArrayList<Vertex> returnValue = graph.getIncident(vertex);
        for (int i = 0; i < returnValue.size(); i = i + 2) {
            Vertex source = returnValue.get(i);
            Vertex target = returnValue.get(i + 1);
            if(mode.equals(VERBOSE)){
                System.out.println("Looking at: i=" + i + " rVSize: " + returnValue.size() + " [" + source + " -> " + target + "]");
            }

            if (graph.getValE(source, target, DONE) != 0 || target == vertex) {
                returnValue.remove(i + 1);
                returnValue.remove(i);
                i = i - 2;
            }
        }
        return returnValue;
    }

    /**
     * finds a subtour starting from <code>startVertex</code> in specified <code>graph</code>
     * @param graph         graph, which holds the vertex
     * @param startVertex   vertex to start from
     * @return              the eulerian subtour found
     */
    private static ArrayList<Vertex> findSubTour(Graph graph, Vertex startVertex) {
        Vertex tempTargetVertex = null;
        Vertex tempSourceVertex = startVertex;
        ArrayList<Vertex> returnValue = new ArrayList<>();
        while (tempTargetVertex != startVertex) {
            returnValue.add(tempSourceVertex);
            if (!getEdgesNotDone(graph, tempSourceVertex).isEmpty()) {
                tempTargetVertex = getEdgesNotDone(graph, tempSourceVertex).get(1);
            }
            if (tempTargetVertex != null) {
                if(mode.equals(DEBUG) || mode.equals(VERBOSE)){
                    System.out.println("Added: [" + tempSourceVertex + " -> " + tempTargetVertex + "]");
                }
                setEdgeAsDone(graph, tempSourceVertex, tempTargetVertex);
                setEdgeAsDone(graph, tempTargetVertex, tempSourceVertex);
                tempSourceVertex = tempTargetVertex;
            }
            else {
                return returnValue;
            }

            tempTargetVertex = null;
        }
        return returnValue;
    }
}
