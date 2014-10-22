package implementation;

public final class Exceptions {

	public static <A> A soften(Thunk<A> action) {
		try {
			return action.execute();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	public static interface Thunk<A> {
		A execute() throws Throwable;
	}
}