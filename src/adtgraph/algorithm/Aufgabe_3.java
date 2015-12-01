package adtgraph.algorithm;

import adtgraph.extern.Graph;
import adtgraph.extern.Vertex;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Aufgabe_3 {

    public static ArrayList<Vertex> fordf(Graph graph, Vertex source, Vertex sink) {
        return fordfedmonsk(graph, source, sink, 1);
    }

    public static ArrayList<Vertex> edmondsk(Graph graph, Vertex source, Vertex sink) {
        return fordfedmonsk(graph, source, sink, 2);
    }

    /**
     *
     * @param source
     * @param sink
     * @param selectionBehaviour 1 => Ford Fulkerson, 2 => Edmund Karp
     * @return
     */
    private static ArrayList<Vertex> fordfedmonsk(Graph graph, Vertex source, Vertex sink, int selectionBehaviour) {
        // Initialisiere Rückgabe-Liste
        List<Vertex> returnList = new ArrayList<>();
        
        // Sub-Liste zur Random oder Queue Auswahl der nächsten Vertices
        List<Vertex> subList = new ArrayList<>();
        
        // Aus dem Graphen alle Vertices abrufen und zwischenspeichern
        List<Vertex> verticesList = new ArrayList<>();
        verticesList = graph.getVertices();

        // Schritt 1 - Für jeden Vertex wird ein Tripel erzeugt [Vorgänger, Fluss, Markierung]
        // Markierung 0 => nicht markiert, 1 => markiert, 2 => inspiziert
        HashMap<Vertex, Object[]> workingList = new HashMap<>();
        for (Vertex v : verticesList) {
            workingList.put(v, createTriplet(null,0,0));
        }
        
        // Bei der Quelle wird der Vorgänger angepasst, der Fluss auf unendlich (entspricht Integer.MAX_VALUE)
        // und die Markierung auf markiert gesetzt
        workingList.put(source, createTriplet(null,Integer.MAX_VALUE,1));
        // Der Sub-Liste wird der erste markierte Vertex hinzugefügt
        subList.add(source);
        
        
        
        boolean moreVerticesToInspect = true;
        // Schritt 2 - Wähle den nächsten schon markierten und nicht inspizierten Vertex anhand
        // des Auswahl-Schemas zur Bearbeitung aus, beginne die Schleife
        while(moreVerticesToInspect)
        {
            int howManyVerticesAreInspected = 0;
            Vertex currentVertex = chooseNextVertex(subList, selectionBehaviour);
            if(currentVertex == null)
            {
                break;
            }

            // Schritt 3 - Markiere alle Nachbarn, die noch nicht markiert sind und für die gilt:
            // Vorwärtskanten: Fluss < Kapazität
            // Rückwärtskanten: Fluss > 0
            List<Vertex> currentVertexIncidents = new ArrayList<>();
            currentVertexIncidents = graph.getIncident(currentVertex);

            for(int i = 0; i < currentVertexIncidents.size(); i = i + 2)
            {                
                Vertex tempSource = currentVertexIncidents.get(i);
                Vertex tempTarget = currentVertexIncidents.get(i+1);
                
                // Wenn der Vertex auf den der aktuelle Vertex zeigt noch nicht markiert ist
                // if(Rückwärtskante || Vorwärtskante)
                if(currentVertex != tempSource && (Integer) workingList.get(tempSource)[2] == 0 ||
                   currentVertex != tempTarget && (Integer) workingList.get(tempTarget)[2] == 0)
                {
                    // Bei einer Vorwärtskante muss flow < max sein
                    if(tempSource == currentVertex &&
                       graph.getValE(tempSource, tempTarget, "max") >
                       graph.getValE(tempSource, tempTarget, "flow"))
                    {
                        subList.add(tempTarget);
                        workingList.get(tempTarget)[2] = 1;
                    }
                    // Bei einer Rückwärtskante muss flow > 0 sein
                    else if(tempTarget == currentVertex &&
                            graph.getValE(tempSource, tempTarget, "flow") > 0)
                    {
                        subList.add(tempSource);
                        workingList.get(tempSource)[2] = 1;
                    }
                    // Entferne alle nicht relevanten Kanten für die folgende Untersuchung
                    // der Kanten auf Anpassungen
                    else
                    {
                        currentVertexIncidents.remove(i+1);
                        currentVertexIncidents.remove(i);
                        i = i - 2;
                    }
                }
                // Wenn Kante schon inspiziert dann zähle den "inspizierte Kanten"-Zähler hoch
                else 
                if(currentVertex != tempSource && (Integer) workingList.get(tempSource)[2] == 2 ||
                   currentVertex != tempTarget && (Integer) workingList.get(tempTarget)[2] == 2)
                {
                    currentVertexIncidents.remove(i+1);
                    currentVertexIncidents.remove(i);
                    i = i - 2;
                }
                else
                {
                    currentVertexIncidents.remove(i+1);
                    currentVertexIncidents.remove(i);
                    i = i - 2;
                }
            }
            for(int i = 0; i < workingList.size(); i++)
            {
                if((Integer) workingList.get(verticesList.get(i))[2] == 2)
                {
                    howManyVerticesAreInspected++;
                }
            }
            moreVerticesToInspect = workingList.size() > howManyVerticesAreInspected;
            if(moreVerticesToInspect)
            {
                // Schritt 4 - Trage die Vorgänger und den zusätzlichen minimalen Fluss
                // in die workingList für alle relevanten Nachbarn ein
                for(int i = 0; i < currentVertexIncidents.size(); i = i + 2)
                {
                    Vertex tempSource = currentVertexIncidents.get(i);
                    Vertex tempTarget = currentVertexIncidents.get(i+1);
                    if(tempSource == currentVertex)
                    {
                        int currentAdditionalFlow       = graph.getValE(tempSource, tempTarget, "max") - 
                                                          graph.getValE(tempSource, tempTarget, "flow");
                        Vertex predecessor              = (Vertex) workingList.get(currentVertex)[0];
                        int predecessorAdditionalFlow   = Integer.MAX_VALUE;
                        if(predecessor != null)
                        {
                             predecessorAdditionalFlow = (Integer) workingList.get(predecessor)[1];
                        }
                        int realAdditionalFlow = Math.min(currentAdditionalFlow, predecessorAdditionalFlow);
                        
                        workingList.put(tempTarget, createTriplet(currentVertex, realAdditionalFlow, 1));
                    }
                    else
                    {
                        Vertex predecessor              = (Vertex) workingList.get(currentVertex)[0];
                        int predecessorAdditionalFlow   = Integer.MAX_VALUE;
                        if(predecessor != null)
                        {
                             predecessorAdditionalFlow = (Integer) workingList.get(predecessor)[1];
                        }
                        int realAdditionalFlow = Math.min(graph.getValE(tempSource, tempTarget , "flow"), predecessorAdditionalFlow);
                        
                        workingList.put(tempSource, createTriplet(currentVertex, -realAdditionalFlow, 1));
                    }                    
                }
                // Schritt 5 - Setze die Markierung des aktuellen Vertex auf inspiziert
                // Somit verschwindet dieser auch von der WorkingList
                int currentAdditionalFlow = (Integer) workingList.get(currentVertex)[1];
                Vertex currentVertexPredecessor = (Vertex) workingList.get(currentVertex)[0];
                workingList.put(currentVertex, createTriplet(currentVertexPredecessor, currentAdditionalFlow, 2));
                subList.remove(currentVertex);
               
                // Schritt 6 & 7
                if((Integer)workingList.get(sink)[2] == 2)
                {
                    Vertex currentlyModified = sink;
                    int additionalFlow = (Integer)workingList.get(sink)[1];
                    while((Vertex) workingList.get(currentlyModified)[0] != null)
                    {
                        int currentFlowAtEdge = 0;
                        // TODO: bitte besser machen Herr Kretschmer!
                        if(!currentlyModified.getName().equals("s"))
                        {
                            currentFlowAtEdge = graph.getValE((Vertex) workingList.get(currentlyModified)[0], currentlyModified, "flow");
                        }
                        graph.setAtE((Vertex) workingList.get(currentlyModified)[0], currentlyModified, "flow", currentFlowAtEdge + additionalFlow);

                        currentlyModified = (Vertex) workingList.get(currentlyModified)[0];
                    }
                // WorkingList wird reinitialisiert für den nächsten Durchgang
                for (Vertex v : verticesList) 
                {
                    workingList.put(v, createTriplet(null,0,0));
                }
                workingList.put(source, createTriplet(null,Integer.MAX_VALUE,1));
                subList.add(source);
                }
                
            }
        }
        // Schritt 8
        for(int i = 0; i < workingList.size(); i++)
        {
            if((int) workingList.get(verticesList.get(i))[2] == 2)
            {
                returnList.add(verticesList.get(i));
            }
        }
        return (ArrayList<Vertex>) returnList;
    }

    private static Object[] createTriplet(Vertex vertex, int flow, int marking) 
    {
        Object[] temp = new Object[3];
        temp[0] = vertex;
        temp[1] = flow;
        temp[2] = marking;
        return temp;
    }
    
    private static Vertex chooseNextVertex(List<Vertex> subList, int selectionBehaviour)
    {
        if(selectionBehaviour == 1 && subList.size() > 0)
        {
            Random rnd = new Random();
            Vertex temp = subList.get(rnd.nextInt(subList.size()));
            return temp;
        }
        else 
        {
            for(Vertex v : subList)
            {
                if(v != null)
                {
                    Vertex temp = v;
                    return temp;
                }
            }
            return null;
        }
    }
}
