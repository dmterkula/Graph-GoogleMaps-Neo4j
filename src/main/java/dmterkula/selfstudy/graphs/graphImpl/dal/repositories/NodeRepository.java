package dmterkula.selfstudy.graphs.graphImpl.dal.repositories;

import dmterkula.selfstudy.graphs.graphImpl.domain.Node;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by David Terkula on 6/4/2017.
 */
@Component
public interface NodeRepository extends GraphRepository<Node> {

    @Query("MATCH (n:Node {id:{0}}) RETURN n")
    Node findNodeByInternalId(int id);

    // returns node with a given name.
    @Query("MATCH (n:Node {field:{0}}) RETURN n")
    Node findNodeByName(String name);

    @Query("MATCH (a:Node)-[:Connects]-(b:Node {field: {0}}) RETURN a")
    Iterable<Node> findNeighborsOfName(String field);

    @Query("MATCH (a:Node)-[:Connects]-(b:Node {id: {0}}) RETURN a")
    Iterable<Node> findNeighborsOfId(long id);

    //@Query("MATCH (a:Node) -[r]- (b:Node) WHERE r IS null RETURN a")
    @Query("MATCH (a:Node) WHERE not ((a)--()) RETURN a")
    Iterable<Node> findOrphanNodes();

}
