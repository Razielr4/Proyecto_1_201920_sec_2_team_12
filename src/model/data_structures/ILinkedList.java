package model.data_structures;

public interface ILinkedList<T> {
	public int size();
	public void add(T newElement);
	public void remove(int pos);
	public T get(int pos);
	public T get();
	public boolean isEmpty();
	
}
