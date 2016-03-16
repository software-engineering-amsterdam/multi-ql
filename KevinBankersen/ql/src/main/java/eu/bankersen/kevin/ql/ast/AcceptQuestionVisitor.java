package eu.bankersen.kevin.ql.ast;

public interface AcceptQuestionVisitor {

    <T> void accept(QuestionVisitor<T> v, T context);

}
