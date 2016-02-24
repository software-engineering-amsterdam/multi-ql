package org.uva.sea.ql.ast;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.uva.sea.ql.ast.expr.*;
import org.uva.sea.ql.ast.form.Form;

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
    	DependencyChecker dp = new DependencyChecker();
    	f.getMainBlock().accept(dp,  new HashMap<String, Boolean>());
    	for (ASTNode node : dp.getViolations()) {
    		System.out.println("If statement that caused violation: " + Integer.toString(node.getStartLine()));
    		assertEquals(6, node.getStartLine());
    		assertEquals(11, node.getStartLine());
    	}
    	
    }
   
}
