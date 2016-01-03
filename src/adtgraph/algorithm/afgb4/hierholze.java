
package adtgraph.algorithm.afgb4;

import adtgraph.extern.Graph;
import adtgraph.extern.Vertex;
import java.util.ArrayList;


public class hierholze {
    public static final String DONE    = "done";
    public static final String VISITED = "visited";
    
    /**
     * main function for finding an euler tour in specified graph
     * @param graph - graph to find an euler tour in
     * @return - VertexList containing the euler tour
     */
    public ArrayList<Vertex> getEulerTour(Graph graph){
        ArrayList<Vertex> returnValue = null;
        
        Vertex startVertex = getStartVertex(graph, graph.getVertices());
//        getNotVisitedNeighbour(graph, startVertex, returnValue)startVertex
        
        return returnValue;
    }
    
    /**
     * marks an edge as done
     * @param graph - graph containing the edge
     * @param source - source vertex of the edge
     * @param target - target vertex of the edge
     */
    public void setEdgeAsDone(Graph graph, Vertex source, Vertex target){
        graph.setAtE(source, target, DONE, 1);
    }
    
    /**
     * finds an unvisited neighbour to @param vertex
     * @param graph - graph containing the vertices
     * @param vertex - vertex to find a neighbour to
     * @param visitedVertices - list of all vertices which have been visited
     * @return returns a vertex neighbouring @param vertex which has not been
     * visited yet; returns null if all neighbours are visited
     */
    public Vertex getNotVisitedNeighbour(Graph graph, Vertex vertex, ArrayList<Vertex> visitedVertices){
        ArrayList<Vertex> neighbours = graph.getAdjacent(vertex);
        neighbours.removeAll(visitedVertices);
        return neighbours.get(0);
    }
        
    /**
     * gets an unfinished vertex from @param v by getting all vertices and removing
     * all vertices with vertex attribute done != 0, then returns the first one
     * returns null if no vertex is available
     * @param graph - the graph which holds the edges; important for getting the
     * attribute
     * @param verticesToChooseFrom - vertexlist with all vertices to pick from
     * @return - vertex with attribute done != 0; null if no vertex available
     */
    public Vertex getStartVertex(Graph graph, ArrayList<Vertex> verticesToChooseFrom){
        for (int i = 0; i < verticesToChooseFrom.size(); i++) {
            Vertex vertex = verticesToChooseFrom.get(i);
            
            if(graph.getValV(vertex, DONE) != 0){
                verticesToChooseFrom.remove(vertex);
                i--;
            }
        }
        return verticesToChooseFrom.get(0);
    }
    /**
     * pulls all edges from @param g and removes every pair of vertices
     * representing the edge if the edge attribute done != 0
     * @param graph - graph which is used to get all edges
     * @return - contains all edges with attribute done == 0
     */
    public ArrayList<Vertex> getEdgesNotDone(Graph graph){
        
        ArrayList<Vertex> returnValue = graph.getEdges();
        for (int i = 0; i < returnValue.size(); i=i+2) {
            Vertex source = returnValue.get(i);
            Vertex target = returnValue.get(i+1);
            
            if(graph.getValE(source, target, DONE) != 0){
                returnValue.remove(i);
                returnValue.remove(i+1);
                i=i-2;
            }
        }
        return returnValue;
    }
}
