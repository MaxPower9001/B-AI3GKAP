/**
 * ############################################################################################
 * ############################################################################################
 * ##THIS CLASS COPIES THE MAIN FUNCTIONALITY OF 'hierholze.java' MODIFIED TO MEASURE RUNTIME##
 * ############################################################################################
 * ############################################################################################
 */
package adtgraph.algorithm.afgb4;

import adtgraph.extern.Graph;
import adtgraph.extern.Vertex;
import static adtgraph.utils.Util.*;
import java.util.ArrayList;

/**
 *
 * @author Rene
 */
public class hierholzeRT {   
    /**
     * Runs the Euler-Tour algorithm while measuring the runtime
     * @param graph     graph to find an euler tour in
     * @return          Object Array with:
     *                  <ul>
     *                  <li>Index 0 = Runtime in ms as <code>long</code></li>
     *                  <li>Index 1 = Euler-Tour as <code>ArrayList[Vertex]</code></li>
     *                  </ul>
     */
    public static Object[] getEulerTour(Graph graph, int startposition) {
        Object[] returnValue = new Object[2];
        
        long currentTime = System.currentTimeMillis();
        ArrayList<Vertex> eulerTour = hierholze.getEulerTour(graph, NODEBUG, startposition);
        currentTime = System.currentTimeMillis()-currentTime;
        
        returnValue[0] = currentTime;
        returnValue[1] = eulerTour;
        
        return returnValue;
    }
}
