/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adtgraph.algorithm.afgb4;

import adtgraph.extern.Graph;
import adtgraph.extern.Vertex;
import static adtgraph.utils.Util.COST;
import java.util.ArrayList;

/**
 *
 * @author Rene
 */
public class mddek {
    
    static ArrayList<Vertex> verticesToDo;
    
    // Distance d is defined as d(vertex,vertexSequence) = min{d(vertex,u) | u is a Vertex of vertexSequence
    // Returns Vertex of d
    private static Integer calcMinDistance(Graph graph, Vertex vertex, ArrayList<Vertex> vertexSequence) {
        int minDistance = Integer.MAX_VALUE;
        Vertex minVertex = null;
        for (int i = 0; i < vertexSequence.size(); i++) {
            Vertex tempTarget = vertexSequence.get(i);
            int tempMinDistance = graph.getValE(vertex, tempTarget, COST);
            if(tempMinDistance < minDistance){
                minVertex = tempTarget;
                minDistance = tempMinDistance;
            }
        }
            return minDistance;
    }
    
    private static Vertex calcNearestVertex(Graph graph, ArrayList<Vertex> vertexSequence) {
        int currentMinDistance = Integer.MAX_VALUE;
        Vertex currentMinVertex = null;
        for(Vertex vertex : verticesToDo) {
            int tempDist = calcMinDistance(graph,vertex,vertexSequence);
            if(tempDist < currentMinDistance) {
                currentMinDistance = tempDist;
                currentMinVertex = vertex;
            }
        }
        return currentMinVertex;
    }
    
    private static ArrayList<Vertex> findVertexPositionInW(Graph graph, Vertex vertex, ArrayList<Vertex> vertexSequence) {
        int minPosition = 1;
        int minCost = Integer.MAX_VALUE;
        // Durchlaufe alle Positionen in der Eckenfolge an denen vertex eingfügt werden könnte
        for(int i=1; i<vertexSequence.size()-1; i++) {
            int tempCost;
            tempCost = graph.getValE(vertexSequence.get(i-1), vertexSequence.get(i), COST);
            tempCost = tempCost + graph.getValE(vertexSequence.get(i), vertexSequence.get(i+1), COST);
            if(tempCost < minCost) {
                minCost = tempCost;
                minPosition = i;
            }
        }
        vertexSequence.add(minPosition,vertex);
        System.out.println("aktueller Pfad: " + vertexSequence + " mit Kosten " + minCost);
        return vertexSequence;
    }
    
    public static ArrayList<Vertex> mddek(Graph graph) {
        verticesToDo = graph.getVertices();
        ArrayList<Vertex> vertexSequence = new ArrayList<Vertex>();
        vertexSequence.add(verticesToDo.get(0));
        vertexSequence.add(verticesToDo.get(0));
        verticesToDo.remove(0);
        
        while(!verticesToDo.isEmpty()) {
            Vertex tempNearestVertex = calcNearestVertex(graph,vertexSequence);
            vertexSequence = findVertexPositionInW(graph,tempNearestVertex,vertexSequence);
            verticesToDo.remove(tempNearestVertex);
        } 
        return vertexSequence;
    }
}
