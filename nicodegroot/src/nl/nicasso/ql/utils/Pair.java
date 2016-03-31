package nl.nicasso.ql.utils;

public class Pair<T> {

	T left;
	T right;

	public Pair(T left, T right) {
		super();
		this.left = left;
		this.right = right;
	}

	public T getLeft() {
		return left;
	}

	public T getRight() {
		return right;
	}

	@Override
	public boolean equals(Object ob) {
		if (ob instanceof Pair<?>) {
			Pair<?> pair = (Pair<?>) ob;
			return pair.getLeft().equals(left) && pair.getRight().equals(right);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return left.hashCode() + right.hashCode();
	}

}