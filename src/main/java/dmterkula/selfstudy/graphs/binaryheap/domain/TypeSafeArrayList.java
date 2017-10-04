package dmterkula.selfstudy.graphs.binaryheap.domain;

import dmterkula.selfstudy.graphs.graphImpl.domain.Node;

import java.util.ArrayList;

/**
 * Created by David Terkula on 5/6/2017.
 */
public class TypeSafeArrayList<T extends Comparable<T>> {
    private ArrayList<Object> elements;

    public TypeSafeArrayList(int size){
        elements = new ArrayList<Object>(size);
        elements.set(0, null);
    }

    public TypeSafeArrayList(){
        elements = new ArrayList<Object>();
        elements.add(0, null);
    }

    public void add(T t){
        elements.add(t);
    }

    public T get(int i){
        return (T)elements.get(i);
    }

    public int size(){
        return elements.size();
    }

    public void remove(int i){
        elements.remove(i);
    }

    public boolean isEmpty(){
        return elements.isEmpty();
    }

    public void set(int index, T t){
        if(t instanceof Node){
            ((Node) t).setIndex(index);

        }
        elements.set(index, t);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypeSafeArrayList<?> that = (TypeSafeArrayList<?>) o;

        return elements.equals(that.elements);
    }

    @Override
    public int hashCode() {
        return elements.hashCode();
    }
}
