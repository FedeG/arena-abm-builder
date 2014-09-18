package jaframework.def;

public interface JAFile<T>
{
	public void reset();
	public boolean eof();
	public boolean read(T record);
	public int filePos();
	public void write(T record);
	public void seek(int n);
	public int fileSize();
	public void close();
	public String getAlias();
	public void rewrite();
}

