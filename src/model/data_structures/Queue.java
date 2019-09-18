package model.data_structures;

import java.util.Comparator;
import java.util.Iterator;

public class Queue<T> implements Iterable<T>, IQueue<T>{
	
	private Node<T> first;
	private Node<T> last;
	private int tamano;
	
	public Queue(T elemento){
		first = new Node<T>(elemento);
		last = first;
		tamano = 1;
	}

	public Queue() {
		first = null;
        last  = null;
        tamano = 0;
	}

	public Iterator<T> iterator() {
		return new QueueIterator<T>(first);
	}
	
	private class QueueIterator<E> implements Iterator<E>{
		
		Node<E> actual;
		
		public QueueIterator(Node<E> primero) {
            actual = primero;
        }

		public boolean hasNext() {
			return actual.darSiguiente() != null;
		}

		public E next() {
			if(hasNext()){
				E dar = actual.darElemento();
				actual = actual.darSiguiente();
				return dar;
			}else{
				return null;
			}
		}
		
		
	}

	public void enqueue(T item) {
		if(isEmpty()){
			first = new Node<T>(item);
			last = first;
			tamano ++;
			
		}else{
			last.cambiarSiguiente(new Node<T>(item));
			last = last.darSiguiente();
			tamano++;
			
		}
		
	}

	public T dequeue() {
		T respuesta = first.darElemento();
		first = first.darSiguiente();
		tamano --;
		return respuesta; 
	}
	
	public T get() {
		return last.darElemento();
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return tamano;
	}
	
	public void addOrderDelete(T item, Comparator<T> comparator) {
		if(isEmpty()) {
			enqueue(item);
		}else {
			
		QueueIterator<T> iterator = (QueueIterator<T>) this.iterator();
		boolean fin = false;
		Node<T> anterior = null;
		Node<T> add = new Node<T>(item);
		
		while(iterator.hasNext()){
			if(!fin && comparator.compare(item,iterator.actual.darElemento()) > 0) {
				if(anterior == null) {
					add.cambiarSiguiente(iterator.actual);
					first = add;
					anterior = first;
					fin = true;
				}else {
					add.cambiarSiguiente(iterator.actual);
					anterior.cambiarSiguiente(add);
					anterior = anterior.darSiguiente();
					fin = true;
				}
			}
			if(anterior == null) {
				anterior=first;
			}else {
				anterior = anterior.darSiguiente();
			}
			iterator.next();
		}
		anterior.cambiarSiguiente(null);
		
		}
	}

}
