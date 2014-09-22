package ar.com.abmFramework.def;

public interface ABMSession {
	
	public <T> ABMClass<T> getABM(Class<T> recClazz);
	public <T> ABMClass<T> getABMByAlias(String alias);
}
