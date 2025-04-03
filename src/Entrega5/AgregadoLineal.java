package Entrega5;
import java.util.ArrayList;
import java.util.List;

public abstract class AgregadoLineal<E> {
	ArrayList<E> AgregadoLineal = new ArrayList<>();
	
	public int size() {
		return this.AgregadoLineal.size();
	}
	
    public boolean isEmpty() {
    	return this.AgregadoLineal.size() == 0;
    }
    

    public List<E> elements(){
    	return new ArrayList<>(this.AgregadoLineal);
    }
    
    public abstract void add(E e);
    
    public void addAll(List<E> list) {
        for (E e : list) {
            this.AgregadoLineal.add(e);
        }
    }
    	
    public E remove() {
    	List<E> 
    	
    }
    public List<E> removeAll(){
    	
    }
	

}
