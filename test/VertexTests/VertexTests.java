/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VertexTests;

import adtgraph.extern.Vertex;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rene
 */
public class VertexTests {

    public VertexTests() {
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
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 12000; i++) {
            for (int j = 0; j < 20; j++) {
                char c = chars[random.nextInt(chars.length)];
                sb.append(c);
            }
            String output = sb.toString();
            assertEquals(output, Vertex.createV(output).name());
        }
    }
    
    @Test
    public void CreateG() {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 2500; i++) {
            for (int j = 0; j < 20; j++) {
                char c = chars[random.nextInt(chars.length)];
                sb.append(c);
            }
            String output = sb.toString();
            Vertex.createV(output);
        }
        
    }
}
