package adtgraph.intern;

public class Attribute {
    
    private String name;
    private int value;
    
    private Attribute(){
        
    }
    
    public Attribute(String name, int value){
        this.name = name;
        this.value = value;
    }    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int value() {
        return value;
    }

    public void value(int value) {
        this.value = value;
    }
    
    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        } else if(this.getClass() == o.getClass()){
            Attribute that = (Attribute) o;
            
            if(this.name == that.name && this.value == that.value){
                return true;
            }
        }
        
        return false;
    }
}