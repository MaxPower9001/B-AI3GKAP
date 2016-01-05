/**
 * ############################################################################################
 * ############################################################################################
 * ##THIS CLASS COPIES THE MAIN FUNCTIONALITY OF 'mddek.java' MODIFIED TO MEASURE RUNTIME##
 * ############################################################################################
 * ############################################################################################
 */
package adtgraph.algorithm.afgb4;

import adtgraph.extern.Graph;
import adtgraph.extern.Vertex;
import static adtgraph.utils.Util.COST;
import static adtgraph.utils.Util.NODEBUG;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Rene
 */
public class mddekRT {
    /**
     * Runs the mddek algorithm while measuring the runtime
     * @param graph     graph to find an euler tour in
     * @return          Object Array with:
     *                  <ul>
     *                  <li>Index 0 = Runtime in ms as <code>long</code></li>
     *                  <li>Index 1 = Euler-Tour as <code>ArrayList[Vertex]</code></li>
     *                  </ul>
     */
    public static Object[] mddek(Graph graph,int startposi) {
        Object[] returnValue = new Object[2];
        
        long currentTime = System.currentTimeMillis();
        ArrayList<Vertex> vertexSequence = mddek.mddek(graph,startposi);
        currentTime = System.currentTimeMillis()-currentTime;
        
        returnValue[0] = currentTime;
        returnValue[1] = vertexSequence;
        
        return returnValue;
    }
}
