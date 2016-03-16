package eu.bankersen.kevin.ql.ast;

public interface AcceptQuestionVisitor {

    <T> T accept(QuestionVisitor<T> v, T context);

}
