package adtgraph.intern;

/**
 *
 * @author Rene
 */
public class Attribute {
    
    private String name;
    private int value;
    
    private Attribute(){
        
    }
    
    public Attribute(String name, int value){
        this.name = name;
        this.value = value;
    }    

    public String name() {
        return name;
    }

    public void name(String name) {
        this.name = name;
    }

    public int value() {
        return value;
    }

    public void value(int value) {
        this.value = value;
    }
}