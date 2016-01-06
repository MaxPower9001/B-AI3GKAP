package adtgraph.algorithm.afgb4;

import static adtgraph.algorithm.afgb4.mddek.verticesToDo;
import adtgraph.extern.Graph;
import adtgraph.extern.Vertex;
import java.util.ArrayList;

import static adtgraph.utils.Util.*;

public class hierholze {

    private static String mode;

    /**
     * Main function with debugging option for finding an euler tour in
     * specified graph
     * <p>
     * @param graph graph to find an euler tour in
     * @param mode sets the debug level <code>[NODEBUG,DEBUG,VERBOSE]</code>
     * @param startposition sets the starting position for the algorithm, 
     * please be careful since it is an INTEGER! OOB guaranteed!
     * @return VertexList containing the euler tour
     */
    public static ArrayList<Vertex> getEulerTour(Graph graph, String mode, int startposition) {
        hierholze.mode = mode;
        ArrayList<Vertex> returnValue = new ArrayList<>();

        while (!getEdgesNotDone(graph).isEmpty()) {
            Vertex startVertex = getEdgesNotDone(graph).get(startposition);
            returnValue = mergeSubtours(returnValue, findSubTour(graph, startVertex));
        }
        return returnValue;
    }

    /**
     * Main function with debug set to <code>NODEBUG</code> for finding an euler
     * tour in specified graph
     * <p>
     * @param graph graph to find an euler tour in
     * @return VertexList containing the euler tour
     */
    public static ArrayList<Vertex> getEulerTour(Graph graph, int startposition) {
        return getEulerTour(graph, NODEBUG, startposition);
    }

    /**
     * marks an edge as done
     *
     * @param graph graph containing the edge
     * @param source source vertex of the edge
     * @param target target vertex of the edge
     */
    private static void setEdgeAsDone(Graph graph, Vertex source, Vertex target) {
        graph.setAtE(source, target, DONE, 1);
    }

    /**
     * pulls all edges from <code>graph</code> and removes every pair of
     * vertices representing the edge if the edge attribute done != 0
     *
     * @param graph graph which is used to get all edges
     * @return contains all edges with attribute done == 0
     */
    private static ArrayList<Vertex> getEdgesNotDone(Graph graph) {
        
        
        ArrayList<Vertex> returnValue = (ArrayList<Vertex>) graph.getEdges().clone();
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
     * @param graph graph which is used to get all edges
     * @param vertex vertex to start from
     * @return contains all edges with attribute done == 0
     */
    private static ArrayList<Vertex> getEdgesNotDone(Graph graph, Vertex vertex) {

        ArrayList<Vertex> returnValue = (ArrayList<Vertex>) graph.getIncident(vertex).clone();
        for (int i = 0; i < returnValue.size(); i = i + 2) {
            Vertex source = returnValue.get(i);
            Vertex target = returnValue.get(i + 1);
            if (mode.equals(VERBOSE)) {
                System.out.println("Looking at: i=" + i + " rVSize: " + returnValue.size() + " [" + source + " -> " + target + "]");
            }

            if (graph.getValE(source, target, DONE) != 0) {
                returnValue.remove(i + 1);
                returnValue.remove(i);
                i = i - 2;
            }
        }
        return returnValue;
    }

    /**
     * finds a subtour starting from <code>startVertex</code> in specified
     * <code>graph</code>
     *
     * @param graph graph, which holds the vertex
     * @param startVertex vertex to start from
     * @return the eulerian subtour found
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
                if (mode.equals(DEBUG) || mode.equals(VERBOSE)) {
                    System.out.println("Added: [" + tempSourceVertex + " -> " + tempTargetVertex + "]");
                }
                setEdgeAsDone(graph, tempSourceVertex, tempTargetVertex);
                setEdgeAsDone(graph, tempTargetVertex, tempSourceVertex);
                tempSourceVertex = tempTargetVertex;
            } else {
                return returnValue;
            }

            tempTargetVertex = null;
        }
        return returnValue;
    }

    /**
     * Merges given <code>subTour</code> into <code>returnValue</code>, by
     * replacing the first element of the <code>subTour</code> in
     * <code>returnValue</code> with the complete <code>subTour</code>
     *
     * @param returnValue the ArrayList to mergo into
     * @param subTour the subTour to merge into returnValue
     */
    private static ArrayList<Vertex> mergeSubtours(ArrayList<Vertex> returnValue, ArrayList<Vertex> subTour) {
        if (returnValue.isEmpty()) {
            returnValue = subTour;
        } else {
            Vertex firstVertexOfSubtour = subTour.get(0);
            for (int i = 0; i < returnValue.size(); i++) {
                if (returnValue.get(i) == firstVertexOfSubtour) {
                    for (int j = 1; j < subTour.size(); j++) {
                        returnValue.add(i + j, subTour.get(j));
                    }
                    break;
                }
            }
        }
        return returnValue;
    }
}
