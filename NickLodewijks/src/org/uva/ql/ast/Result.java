package org.uva.ql.ast;

public class Result {
	private static final Result TRUE_RESULT = new Result(true);

	private final boolean result;
	private final String msg;

	private Result(boolean result) {
		this(result, "");
	}

	private Result(boolean result, String msg) {
		this.result = result;
		this.msg = msg;
	}

	public static Result TRUE() {
		return TRUE_RESULT;
	}

	public static Result FALSE(String msg) {
		return new Result(false, msg);
	}

	public boolean isTrue() {
		return result;
	}

	public boolean isFalse() {
		return !result;
	}

	public String getMessage() {
		return msg;
	}
}
