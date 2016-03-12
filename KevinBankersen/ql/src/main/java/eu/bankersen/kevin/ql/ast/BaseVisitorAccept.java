package eu.bankersen.kevin.ql.ast;

public interface BaseVisitorAccept {

    <T> T accept(BaseVisitor<T> v, T context);

}
