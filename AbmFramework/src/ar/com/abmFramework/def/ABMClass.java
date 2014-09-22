package ar.com.abmFramework.def;

public interface ABMClass<T> {

	public void alta(T alta);
	public void baja(T baja);
	public T leer(int Id);
	public void modicar(T modificar);
	public void armarABM(T clase);
}
