package adtgraph.utils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class Util {
    public static void outputToCSV(String filename,ArrayList<String> args){

        String nl = System.lineSeparator();

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filename,true), "utf-8"))) {
            
            // print line
            for (int i = 0; i < args.size(); i++) {
                writer.write(args.get(i) + ",");
            }
            writer.write(nl);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
