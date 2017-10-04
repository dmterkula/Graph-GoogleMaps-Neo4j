package dmterkula.selfstudy.graphs.binaryheap.domain;

import dmterkula.selfstudy.graphs.graphImpl.domain.Node;

import java.util.HashMap;

/**
 * Created by David Terkula on 5/4/2017.
 */
public class BinaryHeap<T extends Comparable<T>> {

    private TypeSafeArrayList<T> heap;
    private boolean isMin;

    public BinaryHeap(TypeSafeArrayList<T> list) {
        heap = new TypeSafeArrayList<T>();
        isMin = true;
        for (int i = 1; i < list.size(); i++) {
            insert(list.get(i));
        }
    }

    public BinaryHeap(TypeSafeArrayList<T> list, boolean isMin) {
        heap = new TypeSafeArrayList<T>();
        for (int i = 1; i < list.size(); i++) {
            insert(list.get(i));
        }
        this.isMin = isMin;
    }

    public BinaryHeap() {
        heap = new TypeSafeArrayList<T>();
        isMin = true;
    }

    public BinaryHeap(boolean isMin){
        heap = new TypeSafeArrayList<T>();
        this.isMin = isMin;
    }

    // used internally for heapifying
    public int size() {
        return heap.size();
    }

    public boolean isEmpty(){
        boolean returnMe = false;
        if(heap.size() == 1){
            returnMe = true;
        }
        return returnMe;
    }

    public void swap(int index, int parentIndex) {
        T childTemp = heap.get(index);
        if(childTemp instanceof Node){
            ((Node) childTemp).setIndex(parentIndex);
        }
        T parentTemp = heap.get(parentIndex);
        if(parentTemp instanceof Node){
            ((Node) parentTemp).setIndex(index);
        }
        heap.set(index, parentTemp);
        heap.set(parentIndex, childTemp);
    }

    public void removeAll(){
        heap = new TypeSafeArrayList<T>();
    }

    public void setMin(TypeSafeArrayList<T> newHeap, boolean b){

        if(b != isMin && isMin == false){ // need convert to max
            isMin = b;
            removeAll();
            for(int i = 1; i < newHeap.size(); i ++){
                insert(newHeap.get(i));
            }
        }
        else if(b != isMin && isMin == true){ // need to convert to min;
            isMin = b;
            removeAll();
            for(int i = 1; i < newHeap.size(); i ++){
                insert(newHeap.get(i));
            }
        }
        else{
            System.out.println("Oops, you set the boolean to the same value!");
        }
    }

    public TypeSafeArrayList<T> getList() {
        return heap;
    }

    public void insert(T t) {
        heap.add(t);
        if(t instanceof Node){
            ((Node) t).setIndex(heap.size()-1);
        }
        if(isMin == true) {
            heapifyUp(heap.size() - 1);
        }
        else{
            maxHeapifyUp(heap.size() -1);
        }

    }



    public T deleteMin() {
        assert (heap.size() > 0);
        T returnMe = null;
        if(isMin) {
            returnMe = heap.get(1);
            heap.set(1, heap.get(heap.size() - 1));
            heap.remove(heap.size() - 1);
            heapifyDown(1);
        }
        if(returnMe == null){
            System.out.println("Cannot delete min from max heap; use setMin method to convert to minHeap");
        }
        return returnMe;
    }

    public void heapifyDown(int index) {
        int leftChildIndex = 2 * index;
        int rightChildIndex = 2 * index + 1;

        if (leftChildIndex >= heap.size()) {
            return;
        }

        int smallestIndex = leftChildIndex;
        if (rightChildIndex < heap.size()) {
            if (heap.get(rightChildIndex).compareTo(heap.get(leftChildIndex)) < 0) { // right is smallest
                smallestIndex = rightChildIndex;
            }
        }
        if (heap.get(smallestIndex).compareTo(heap.get(index)) < 0) // child is < parent
            swap(smallestIndex, index);
            heapifyDown(smallestIndex);
    }

    public void heapifyUp(int index) {
        int parentIndex = index / 2;
        if (parentIndex == 0 || heap.get(index).compareTo(heap.get(parentIndex)) >= 0) {
            return;
        } else {
            if (heap.get(index).compareTo(heap.get(parentIndex)) < 0) {
                swap(index, parentIndex);
                index = parentIndex;
                heapifyUp(index);
            }
        }
    }

    public void maxHeapifyUp(int index) {
        int parentIndex = index / 2;
        if (parentIndex == 0 || heap.get(index).compareTo(heap.get(parentIndex)) <= 0) {
            return;
        } else {
            if (heap.get(index).compareTo(heap.get(parentIndex)) > 0) {
                swap(index, parentIndex);
                index = parentIndex;
                maxHeapifyUp(index);
            }
        }
    }
    public void maxHeapifyDown(int index) {
        int leftChildIndex = 2 * index;
        int rightChildIndex = 2 * index + 1;

        if (leftChildIndex >= heap.size()) {
            return;
        }
        int largestIndex = leftChildIndex;
        if (rightChildIndex < heap.size()) {
            if (heap.get(rightChildIndex).compareTo(heap.get(leftChildIndex)) > 0) { // right is largest
                largestIndex = rightChildIndex;
            }
        }
        if (heap.get(largestIndex).compareTo(heap.get(index)) > 0) { // child is > parent
            swap(largestIndex, index);
            maxHeapifyDown(largestIndex);
        }

    }

    public T deleteMax() {
        assert (heap.size() > 0);
        T returnMe = null;
        if(!isMin) {
             returnMe = heap.get(1);
            heap.set(1, heap.get(heap.size() - 1));
            heap.remove(heap.size() - 1);
            maxHeapifyDown(1);
        }
        if(returnMe == null){
            System.out.println("Cannot delete max from max heap; use setMin method to convert to maxHeap");
        }
        return returnMe;
    }

    public boolean isMin(){
        return isMin;
    }

    public String toString() {
        String heapData = "";
        for (int i = 0; i < size(); i++) {
            System.out.print(heap.get(i) + ",");
        }
        return heapData;
    }

    public void update(Node node, HashMap<Node, Double> map){
        int index = 1;
        int i = 1;
        boolean found = false;
        while(i < heap.size() && !found){
            if(heap.get(i).equals(node)){
                index = i;
                found = true;
                Node n = node;
                n.setValue(map.get(node));
                T t = (T) n;
                heap.set(i, t);
            }
            i++;
        }
        heapifyUp(index);


//        Node n = node;
//        n.setValue(map.get(node));
//        int i = n.getIndex();
//        T t = (T) n;
//        heap.set(i, t);
//        heapifyUp(i);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BinaryHeap<?> that = (BinaryHeap<?>) o;

        if (isMin != that.isMin) return false;
        return heap.equals(that.heap);
    }

    @Override
    public int hashCode() {
        return heap.hashCode();
    }
}
