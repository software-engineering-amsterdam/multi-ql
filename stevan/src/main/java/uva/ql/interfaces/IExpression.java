package uva.ql.interfaces;

public interface IExpression {

	public final static int VARIABLE 		= 101;
	public final static int NUMBER 			= 102;
	
	public final static int SML_THEN		= 201;
	public final static int GRT_THEN		= 202;
	public final static int SML_EQL			= 203;
	public final static int GRT_EQL			= 204;
	public final static int NOT_EQL			= 205;
	public final static int EQL				= 206;
	
	public final static int ASSIGN_EXP 		= 301;
	
	//public final static int NUM_VAR_EXP		= 401;
	//public final static int EXP		 		= 402;
	public final static int NOT_EXP 		= 403;
	public final static int MULTIPLY_EXP 	= 404;
	public final static int DIVIDE_EXP 		= 405;
	public final static int MINUS_EXP 		= 406;
	public final static int ADD_EXP 		= 407;
	//public final static int EQUALITY_EXP	= 408;
	public final static int AND_EXP 		= 409;
	public final static int OR_EXP 			= 410;
}
