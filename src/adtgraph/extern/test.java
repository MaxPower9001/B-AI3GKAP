
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adtgraph.extern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Rene
 */
public class test {
    public static void main(String[] args){
        
        ArrayList<Integer> inhalt = new ArrayList<>();        
        List<List<Integer>> matrix = new ArrayList<>();
        
        inhalt.add(11);
        inhalt.add(22);
        inhalt.add(33);
        inhalt.add(44);
        
        for (Integer i : inhalt) {
            matrix.add(new ArrayList());
        }
        
        for (int i = 0;i < inhalt.size();i++){
            for (int j = 0; j < inhalt.size();j++){
                matrix.get(i).add(j, 0);
            }
        }
        
        System.out.println(matrix.toString());
        
        System.out.println("---------------------------------------------------------------------");
        
        matrix.get(1).set(2, 1);
        matrix.get(2).set(1, 1);
        System.out.println(matrix.toString());
        
        System.out.println("---------------------------------------------------------------------");
        matrix.add(new ArrayList<>());
        for(Integer i : inhalt){
            matrix.get(matrix.size()-1).add(0);
        }
        for(List<Integer> i : matrix){
            i.add(0);
        }
         System.out.println(matrix.toString());
    }

public class TestMatrix {

    

}
    
}
