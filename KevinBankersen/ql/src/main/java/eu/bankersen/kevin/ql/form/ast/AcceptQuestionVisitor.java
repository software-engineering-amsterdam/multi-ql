package eu.bankersen.kevin.ql.form.ast;

public interface AcceptQuestionVisitor {

    <T> void accept(QuestionVisitor<T> v, T context);

}
