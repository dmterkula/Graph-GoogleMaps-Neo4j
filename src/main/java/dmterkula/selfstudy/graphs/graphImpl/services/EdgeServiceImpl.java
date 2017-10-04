package dmterkula.selfstudy.graphs.graphImpl.services;

import dmterkula.selfstudy.graphs.graphImpl.dal.repositories.EdgeRepository;
import dmterkula.selfstudy.graphs.graphImpl.dal.repositories.NodeRepository;
import dmterkula.selfstudy.graphs.graphImpl.domain.Edge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by David Terkula on 6/16/2017.
 */
@Service
public class EdgeServiceImpl implements EdgeService {

    @Autowired
    private EdgeRepository edgeRepository;

    public EdgeServiceImpl(EdgeRepository edgeRepository){
        this.edgeRepository = edgeRepository;
    }

    public Edge findById(Long id){
        return edgeRepository.findOne(id);
    }

    public void delete(Edge e){
        edgeRepository.delete(e);
    }

    public List<Edge> findAllEdges(){
        return (List<Edge>)edgeRepository.findAllEdges();
    }

    public List<Edge> findAllEdgesOfNode(Long id){
         return (List) edgeRepository.findAllEdgesOfNode(id);
    }

    public List<Edge> findAllEdgesOfNodeNamed(String name){
        return (List)edgeRepository.findAllEdgesOfNodeNamed(name);
    }

    public Double findEdgeWeight(Long id){
        return edgeRepository.findEdgeWeight(id);
    }

    public Edge createWeightedConnection(String startNodeName, String endNodeName, double weight){
        return edgeRepository.createWeightedConnection(startNodeName, endNodeName, weight);
    }

    public List<Edge> findOutgoingEdgesOfNode(Long id){
        return (List)edgeRepository.findOutgoingEdgesOfNode(id);
    }

    public List<Edge> findIncomingEdgesOfNode(Long id){
        return (List)edgeRepository.findIncomingEdgesOfNode(id);
    }


    public List<Edge> findOutgoingEdgesOfNodeNamed(String nodeName){
        return (List)edgeRepository.findOutgoingEdgesOfNodeNamed(nodeName);
    }

    public List<Edge> findIncomingEdgesOfNodeNamed(String startName){
        return (List)edgeRepository.findIncomingEdgesOfNode(startName);
    }

    public Edge findEdgeBetween(String startName, String endName){
        return edgeRepository.findEdgeBetween(startName, endName);
    }

}
