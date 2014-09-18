package jaframework.def;

public interface JAIndex<T> extends JAFile<T>
{ 
	public JAFile<T> getFile();
	public void add(T key);
	public String getKey();
	public int search(T toSearch);
}
