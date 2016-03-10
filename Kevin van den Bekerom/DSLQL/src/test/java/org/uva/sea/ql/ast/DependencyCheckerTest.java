package org.uva.sea.ql.ast;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.uva.sea.ql.ast.expr.*;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.errors.QLError;
import org.uva.sea.ql.graph.DependencyGraphBuilder;
import org.uva.sea.ql.graph.Graph;
import org.uva.sea.ql.typechecker.DependencyChecker;

import java.io.IOException;
import java.util.HashMap;

import org.uva.sea.ql.*;
/**
 * Unit test for simple App.
 */
public class DependencyCheckerTest 

//String FA = "D:\\Master\\Software Construction\\Github\\Kevin van den Bekerom\\DSLQL\\src\\main\\resources\\SampleForm.txt";
//String FB = "D:\\Master\\Software Construction\\Github\\Kevin van den Bekerom\\DSLQL\\src\\main\\resources\\DependancyCheckUnsafe.txt";
//String FC = "D:\\Master\\Software Construction\\Github\\Kevin van den Bekerom\\DSLQL\\src\\main\\resources\\DependancyCheckSafe.txt";
//String FD = "D:\\Master\\Software Construction\\Github\\Kevin van den Bekerom\\DSLQL\\src\\main\\resources\\TypeCheckTest.txt";
	

    extends TestCase
{
	
	ASTBuilder fb = new ASTBuilder();
	
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DependencyCheckerTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( DependencyCheckerTest.class );
    }

    public void testDependencyIfStatements() throws IOException {
    	Form f = fb.buildAST("D:\\Master\\Software Construction\\Github\\"
    			+ "Kevin van den Bekerom\\DSLQL\\src\\main\\resources\\DependancyCheckIfStatements.txt");
    	System.out.println(f.getName());
    	Graph g = new DependencyGraphBuilder(f.getMainBlock()).getDependencyGraph();
    	g.printEdges();
    	DependencyChecker dp = new DependencyChecker();
    	for (QLError error : dp.getErrors(f.getMainBlock(), g)) {
    		System.out.println(error.getErrorMessage());
    		assertTrue(true);
    	}
    	
    }
   
}
