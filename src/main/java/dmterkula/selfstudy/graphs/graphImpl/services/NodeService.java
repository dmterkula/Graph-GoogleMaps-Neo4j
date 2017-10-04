package dmterkula.selfstudy.graphs.graphImpl.services;

import dmterkula.selfstudy.graphs.graphImpl.domain.Node;

import java.util.List;

/**
 * Created by David Terkula on 6/5/2017.
 */
public interface NodeService {

    Node findByInternalId(int id);

    Node findById(Long id);

    List<Node> findAll();

    void delete(Node node);

    Node createNode(Node node);

    Node findNodeByName(String name);

    List<Node> findNeighborsOfName(String field);

    List<Node> findNeighborsOfId(long id);

    List<Node> findOrphanNodes();

}
