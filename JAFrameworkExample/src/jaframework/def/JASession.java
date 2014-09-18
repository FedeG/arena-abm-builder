package jaframework.def;

public interface JASession
{
	public <T> JAFile<T> getFile(Class<T> recClazz);
	public <T> JAFile<T> getFileByAlias(String alias);
	public <T> JAIndex<T> getIndexByAlias(JAFile<T> jafile, String indexAlias);
}
