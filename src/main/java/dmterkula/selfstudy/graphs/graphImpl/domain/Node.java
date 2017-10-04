package dmterkula.selfstudy.graphs.graphImpl.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.HashSet;


/**
 * Created by David Terkula on 5/10/2017.
 */
@NodeEntity
public class Node<T> implements Comparable<Node<T>>{
    @GraphId private Long graphId;
    private Double value;
    //private HashSet<Edge> edges;
    private int id;
    private static int idCounter = 0;
    private T field;
    private int index;


    public Node(T field){
        this.field = field;
        value = Double.MAX_VALUE;
        this.id = idCounter++;
    }

    public Node(int id, T field){
        this.id = id;
        this.field = field;
        this.value = Double.MAX_VALUE;
    }

    public Node(){
    }

    public T getField() {
        return field;
    }

    public void setField(T field) {
        this.field = field;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Double getValue(){
        return value;
    }

    public void setValue(Double d){
        value = d;
    }

    public int compareTo(Node<T> node) {
        return getValue().compareTo(node.getValue());
    }

    public String toString(){
        return "" + getId() + ", " + getField();
    }

    /*public HashSet<Edge> getEdges() {
        return edges;
    }*/

   /* public void setEdges(HashSet<Edge> edges) {
        this.edges = edges;
    }

    public void addEdge(Edge e){
        edges.add(e);
    }

    public void removeEdge(Edge e){
        edges.remove(e);
    }

    public Edge getEdge(Node end){
        Edge returnMe = null;
        for(Edge e: edges){
            if(e.getEnd().equals(end)){
                returnMe = e;
            }
        }
        return returnMe;
    }

    public Edge getMinimumEdge(){
        Edge returnMe = null;
        double min = Double.POSITIVE_INFINITY;
        for(Edge e: edges){
            if(e.getWeight() < min){
                returnMe = e;
                min = e.getWeight();
            }
        }
        return returnMe;
    }*/

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node<?> node = (Node<?>) o;

        if (getId() != node.getId()) return false;
        //if (!getValue().equals(node.getValue())) return false;
        // if (getEdges() != null ? !getEdges().equals(node.getEdges()) : node.getEdges() != null) return false;
        return getField().equals(node.getField());
    }

    /*@Override
    public int hashCode() {
        int result = getValue().hashCode();
        result = 31 * result + (getEdges() != null ? getEdges().hashCode() : 0);
        result = 31 * result + getId();
        result = 31 * result + getField().hashCode();
        return result;
    }*/
}
