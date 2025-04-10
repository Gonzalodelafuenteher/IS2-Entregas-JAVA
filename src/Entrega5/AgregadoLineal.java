package Entrega5;
import java.util.ArrayList;
import java.util.List;

public abstract class AgregadoLineal<E> {
	 protected ArrayList<E> Elementos = new ArrayList<>();
	
	public int size() {
		return this.Elementos.size();
	}
	
    public boolean isEmpty() {
    	return this.Elementos.size() == 0;
    }
    

    public List<E> Elementos(){
    	return new ArrayList<>(this.Elementos);
    }
    
    public abstract void add(E e);
    
    public void addAll(List<E> list) {
        for (E e : list) {
            this.add(e); 
        }
    }
    public E remove() {
        if (this.Elementos.isEmpty()) {
            throw new IllegalStateException("La lista está vacía");
        }
        return this.Elementos.remove(0);
    }
    
    
    
    public List<E> removeAll(){
    	List<E> copia = new ArrayList<>(Elementos); 
    	this.Elementos.clear();
    	return copia;
    }
    
	
}
