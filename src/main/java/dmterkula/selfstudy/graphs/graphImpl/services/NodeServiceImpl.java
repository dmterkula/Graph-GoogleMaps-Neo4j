package dmterkula.selfstudy.graphs.graphImpl.services;

import dmterkula.selfstudy.graphs.graphImpl.domain.Node;
import dmterkula.selfstudy.graphs.graphImpl.dal.repositories.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by David Terkula on 6/5/2017.
 */


@Service
public class NodeServiceImpl implements NodeService {

    @Autowired
    private NodeRepository nodeRepository;

   @Autowired
    public NodeServiceImpl(NodeRepository nodeRepository){
        this.nodeRepository = nodeRepository;
    }

    public NodeServiceImpl(){}

    public Node createNode(Node node){
        return nodeRepository.save(node);
    }

    public void delete(Node node){
        nodeRepository.delete(node);
    }

    public Node findById(Long id){
        return nodeRepository.findOne(id);
    }

    public Node findByInternalId(int id){
        return nodeRepository.findNodeByInternalId(id);
    }

    public List<Node> findAll(){
        return (List)nodeRepository.findAll();
    }

    public Node findNodeByName(String name){
        return nodeRepository.findNodeByName(name);
    }

    public List<Node> findNeighborsOfName(String field){
        return (List)nodeRepository.findNeighborsOfName(field);
    }

    public List<Node> findNeighborsOfId(long id){
        return (List)nodeRepository.findNeighborsOfId(id);
    }

    public List<Node> findOrphanNodes(){
        return (List)nodeRepository.findOrphanNodes();
    }

}
