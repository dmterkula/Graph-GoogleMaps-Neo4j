package dmterkula.selfstudy.graphs.graphImpl.dal.repositories;

import dmterkula.selfstudy.graphs.graphImpl.domain.Edge;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by David Terkula on 6/16/2017.
 */
@Component
public interface EdgeRepository extends GraphRepository<Edge> {

    @Query("MATCH ()-[r:Connects]-() RETURN r")
    Iterable<Edge> findAllEdges();

    @Query("MATCH (a:Node {id:{0}})-[r]-(b:Node) RETURN r")
   Iterable<Edge> findAllEdgesOfNode(Long id);

    @Query("MATCH (a:Node {field:{0}})-[r]-(b:Node) RETURN r")
    Iterable<Edge> findAllEdgesOfNodeNamed(String field);

    @Query("MATCH (a:Node)-[r:Connects]-(b:Node) WHERE r.graphId={0} RETURN a.weight")
    Double findEdgeWeight(Long id);

    @Query("MATCH (a:Node {field:{0}}),(b:Node {field:{1}}) MERGE(a)-[r:Connects{weight:{2}}]->(b) RETURN r")
    Edge createWeightedConnection(String startNodeName, String endNodeName, double weight);

    @Query("MATCH (a:Node {id:{0}})-[r]->(b:Node) RETURN r")
    Iterable<Edge> findOutgoingEdgesOfNode(Long id);

    @Query("MATCH (a:Node {id:{0}})<-[r]-(b:Node) RETURN r")
    Iterable<Edge> findIncomingEdgesOfNode(Long id);


    @Query("MATCH (a:Node {field:{0}})-[r]->(b:Node) RETURN r")
    Iterable<Edge> findOutgoingEdgesOfNodeNamed(String startName);

    @Query("MATCH (a:Node {field:{0}})<-[r]-(b:Node) RETURN r")
    Iterable<Edge> findIncomingEdgesOfNode(String startName);

    @Query("MATCH (a:Node {field:{0}})-[r:Connects]->(b:Node{field:{1}}) RETURN r")
    Edge findEdgeBetween(String startName, String endName);


}