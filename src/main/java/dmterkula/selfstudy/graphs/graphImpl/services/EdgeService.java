package dmterkula.selfstudy.graphs.graphImpl.services;

import dmterkula.selfstudy.graphs.graphImpl.dal.repositories.NodeRepository;
import dmterkula.selfstudy.graphs.graphImpl.domain.Edge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by David Terkula on 6/16/2017.
 */
public interface EdgeService {

    void delete(Edge e);
    List<Edge> findAllEdges();
    Edge findById(Long id);
    List<Edge> findAllEdgesOfNode(Long id);
    List<Edge> findAllEdgesOfNodeNamed(String name);
    Double findEdgeWeight(Long id);
    Edge createWeightedConnection(String startNodeName, String endNodeName, double weight);
    List<Edge> findIncomingEdgesOfNode(Long id);
    List<Edge> findOutgoingEdgesOfNode(Long id);
    Edge findEdgeBetween(String startName, String endName);
    List<Edge> findOutgoingEdgesOfNodeNamed(String nodeName);
    List<Edge> findIncomingEdgesOfNodeNamed(String startName);


}
