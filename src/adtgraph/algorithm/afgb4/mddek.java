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
import java.util.Arrays;
import java.util.Collections;

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
        for (int i = 0; i < vertexSequence.size(); i++) {
            Vertex tempTarget = vertexSequence.get(i);
            Integer tempMinDistance = graph.getValE(vertex, tempTarget, COST);
            if(tempMinDistance == null){
                tempMinDistance = graph.getValE(tempTarget, vertex, COST);
            }
            if(tempMinDistance < minDistance){
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
            int tempCost = calcCostOfSequence(vertexSequence, graph, i, vertex);
            if(tempCost < minCost) {
                minCost = tempCost;
                minPosition = i;
            }
        }
        vertexSequence.add(minPosition,vertex);
        int costOfCurrentSequence = 0;
        for(int i = 0; i < vertexSequence.size()-1; i++){
            costOfCurrentSequence = costOfCurrentSequence + graph.getValE(vertexSequence.get(i), vertexSequence.get(i+1), COST);
        }
        System.out.println("aktueller Pfad: " + vertexSequence + " mit Kosten " + costOfCurrentSequence);
        return vertexSequence;
    }
    
    public static ArrayList<Vertex> mddek(Graph graph,int startposi) {
        verticesToDo = (ArrayList<Vertex>) graph.getVertices().clone();
        ArrayList<Vertex> vertexSequence = new ArrayList<>();
        vertexSequence.add(verticesToDo.get(startposi));
        vertexSequence.add(verticesToDo.get(startposi));
        verticesToDo.remove(startposi);
        
        while(!verticesToDo.isEmpty()) {
            Vertex tempNearestVertex = calcNearestVertex(graph,vertexSequence);
            vertexSequence = findVertexPositionInW(graph,tempNearestVertex,vertexSequence);
            verticesToDo.remove(tempNearestVertex);
        } 
        return vertexSequence;
    }
    
    private static int calcCostOfSequence(ArrayList<Vertex> vertexSequence,Graph graph, int index, Vertex vertex) {
        int costOfCurrentSequence = 0;
        ArrayList<Vertex> workingCopy = (ArrayList<Vertex>) vertexSequence.clone();
        workingCopy.add(index, vertex);
        for(int i = 0; i < workingCopy.size()-1; i++){
            costOfCurrentSequence = costOfCurrentSequence + graph.getValE(workingCopy.get(i), workingCopy.get(i+1), COST);
        }
        return costOfCurrentSequence;
    }
}
