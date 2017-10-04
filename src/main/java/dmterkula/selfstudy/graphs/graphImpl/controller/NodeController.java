package dmterkula.selfstudy.graphs.graphImpl.controller;

import dmterkula.selfstudy.graphs.graphImpl.domain.Node;
import dmterkula.selfstudy.graphs.graphImpl.services.NodeService;
import dmterkula.selfstudy.graphs.graphImpl.setup.Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by David Terkula on 6/5/2017.
 */
@Component
@RestController
public class NodeController {

    @Autowired
    private NodeService nodeService;

    public NodeController(NodeService nodeService){
        this.nodeService = nodeService;
    }

    @RequestMapping(value = "/findByInternalId/{id}", method = RequestMethod.GET)
    public Node findById(@PathVariable ("id") int id){
        return nodeService.findByInternalId(id);
    }

    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    public Node findById(@PathVariable ("id") Long id){
        return nodeService.findById(id);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
        public List<Node> findAll(){
            return nodeService.findAll();
        }

    @RequestMapping(value = "/deleteNodeOfInternalId/{id}", method = RequestMethod.GET)
        public void delete(@PathVariable int id){
            Node deleteMe = nodeService.findByInternalId(id);
            nodeService.delete(deleteMe);
    }

    @RequestMapping(value = "/createNodeWithName/{field}", method = RequestMethod.GET)
    public Node createNodeWithName(@PathVariable String field){
            return nodeService.createNode(new Node(field));
    }

    @RequestMapping(value = "/createNodeWithIdAndName/{id}/{field}", method = RequestMethod.GET)
    public Node createNode(@PathVariable String field, @PathVariable int id){
        return nodeService.createNode(new Node(id, field));
    }

    @RequestMapping(value = "/findNodeByName/{name}", method = RequestMethod.GET)
    public Node getNodeByName(@PathVariable String name){
        return nodeService.findNodeByName(name);
    }

    @RequestMapping(value = "/findNeighborsOfName/{field}", method = RequestMethod.GET)
    public List<Node> findNeighborsOfName(@PathVariable String field){
        return nodeService.findNeighborsOfName(field);
    }

    @RequestMapping(value = "/findNeighborsOfId/{id}", method = RequestMethod.GET)
    public List<Node> findNeighborsOfId(@PathVariable long id){
        return nodeService.findNeighborsOfId(id);
    }

    @RequestMapping(value = "/findOrphanNodes", method = RequestMethod.GET)
    public List<Node> findOrphanNodes(){
        return nodeService.findOrphanNodes();
    }


    // dont use
    /*@RequestMapping(value = "/populateNode", method = RequestMethod.GET)
    public List<Node> populateDB() throws IOException{
        List<Node> returnMe = new ArrayList<>();
        Engine engine = new Engine();
        HashSet<Node> nodes = engine.getInputNodes();
        for(Node n: nodes){
            returnMe.add(n);
            nodeService.createNode(n);
        }
        return returnMe;
    }*/
}
