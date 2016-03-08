package eu.bankersen.kevin.ql.ast;

public interface AcceptVisitor {
    
    <T> void accept(BasicVisitor v, T context);

}
