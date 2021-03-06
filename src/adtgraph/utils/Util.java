package adtgraph.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class Util {
    public static final String DONE = "done";
    public static final String COST = "cost";
    public static final String DEBUG = "debugmesenpai";
    public static final String VERBOSE = "verbose";
    public static final String NODEBUG = "nodebugplz";

    public static void outputToCSV(String filename, ArrayList<String> args) {

        String nl = System.lineSeparator();        
        File f = new File(filename);
        
        

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filename + ".csv", true), "utf-8"))) {

            // print line
            for (int i = 0; i < args.size(); i++) {
                if (i != args.size() - 1) {
                    writer.write(args.get(i) + ",");
                } else {
                    writer.write(args.get(i));
                }

            }
            writer.write(nl);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
