package ast.model.type;

public enum Type {
	INTEGER,
	STRING,
	BOOLEAN,
	MONEY,
	UNKNOWN;
	
	public static Type getType(String type) {
		try {
			return Type.valueOf(type.toUpperCase());
		} catch (IllegalArgumentException e) {
			return UNKNOWN;
		}
	}
}
