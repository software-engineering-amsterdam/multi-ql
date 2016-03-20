package ast.evaluator.test;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	BooleanEvaluationTest.class,
	NumericEvaluationTest.class,
	StringEvaluationTest.class
})
public class EvaluatorTest {
}
