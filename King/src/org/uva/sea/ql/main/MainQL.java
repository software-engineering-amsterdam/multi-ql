package org.uva.sea.ql.main;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uva.sea.ql.ast.Edge;
import org.uva.sea.ql.ast.Graph;
import org.uva.sea.ql.ast.Vertex;
import org.uva.sea.ql.parser.antlr.GraphBaseListener;
import org.uva.sea.ql.parser.antlr.GraphLexer;
import org.uva.sea.ql.parser.antlr.GraphParser;
import org.uva.sea.ql.parser.antlr.QLLexer;
import org.uva.sea.ql.parser.antlr.QLParser;


public class MainQL {

	public static void main(String[] args) throws IOException {
		//Loading the DSL script into the ANTLR stream.
	    ANTLRInputStream input = new ANTLRFileStream(new File("resources/questionaire.gr").getPath());

	    //Passing the input to the lexer to create tokens
	    QLLexer lexer = new QLLexer(input);

	    CommonTokenStream tokens = new CommonTokenStream(lexer);

	    //Passing the tokens to the parser to create the parse trea. 
	    QLParser parser = new QLParser(tokens);
	   // parser.getBuildParseTree();
	    //Semantic model to be populated
	    /**Graph g = new Graph();

	    //Adding the listener to facilitate walking through parse tree. 
	    parser.addParseListener(new MyGraphBaseListener(g));
	    
	    //invoking the parser. 
	    parser.graph();

	    Graph.printGraph(g);*/
	    
	    
	   
	  //show AST in GUI
        JFrame frame = new JFrame("Antlr AST");
        JPanel panel = new JPanel();
        TreeViewer viewr = new TreeViewer(Arrays.asList(parser.getRuleNames()), parser.form());
        viewr.setScale(1.5);//scale a little
        panel.add(viewr);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);

	}

}

/**
 * Listener used for walking through the parse tree.
 

class MyGraphBaseListener extends GraphBaseListener {

  Graph g;

  public MyGraphBaseListener(Graph g) {
    this.g = g;
  }

  @Override
  public void exitEdge(GraphParser.EdgeContext ctx) {
    //Once the edge rule is exited the data required for the edge i.e 
    //vertices and the weight would be available in the EdgeContext
    //and the same can be used to populate the semantic model
    Vertex fromVertex = new Vertex(ctx.vertex(0).ID().getText());
    Vertex toVertex = new Vertex(ctx.vertex(1).ID().getText());
    double weight = Double.parseDouble(ctx.NUM().getText());
    Edge e = new Edge(fromVertex, toVertex, weight);
    g.addEdge(e);
  }
}
*/
