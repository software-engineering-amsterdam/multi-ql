package eu.bankersen.kevin.ql.ast.type;

public class UndifinedType extends Type {
    
    @Override
    public String toString() {
	return "Undefined";
    }

    @Override
    public boolean isSimilar(Type type) {
	return type instanceof UndifinedType;
    }

}
