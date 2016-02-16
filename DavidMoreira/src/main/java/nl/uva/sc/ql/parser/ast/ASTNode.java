package nl.uva.sc.ql.parser.ast;

public class ASTNode {

    private Object value;
    private String identifier;
    
    public ASTNode() {}
    
    public ASTNode(Object value) {
        this.value = value;
    }
    
    public void setValue(Object value) {
    	this.value = value;
    }
    
    public Object getValue() {
    	return this.value;
    }
    
    public String getIdentifier() {
    	return this.identifier;
    }
    
    public void setIdentifier(String identifier) {
    	this.identifier = identifier;
    }

    public Boolean asBoolean() {
        return (Boolean) value;
    }
    
    public Integer asInteger() {
        return (Integer) value;
    }

    public Double asDouble() {
        return (Double) value;
    }

    public String asString() {
        return String.valueOf(value);
    }

    public boolean isDouble() {
        return value instanceof Double;
    }

    @Override
    public int hashCode() {
        if (value == null) {
            return 0;
        }

        return this.value.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (value == o) {
            return true;
        }

        if (value == null || o == null || o.getClass() != value.getClass()) {
            return false;
        }

        ASTNode that = (ASTNode) o;

        return this.value.equals(that.value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
    
    public String getType(){
    	return "VOID";
    }
    
}
