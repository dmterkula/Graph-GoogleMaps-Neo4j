package dmterkula.selfstudy.graphs.graphImpl.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by David Terkula on 5/10/2017.
 */

@RelationshipEntity(type = "Connects")
public class Edge {
    @GraphId private Long graphId;
    @StartNode private Node start;
    @EndNode private Node end;
    private Double weight;

    public Edge(Node start, Node end, double weight){
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public Edge(){}

    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public Node getEnd() {
        return end;
    }

    public void setEnd(Node end) {
        this.end = end;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String toString(){
        return "{" + start.getField() + " --> " + end.getField() + ", " + getWeight() + "}";
    }

    public long getGraphId(){
        return graphId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (!getStart().equals(edge.getStart())) return false;
        if (!getEnd().equals(edge.getEnd())) return false;
        return getWeight().equals(edge.getWeight());
    }

   /* @Override
    public int hashCode() {
        int result = getStart().hashCode();
        result = 31 * result + getEnd().hashCode();
        result = 31 * result + getWeight().hashCode();
        return result;
    }*/
}
