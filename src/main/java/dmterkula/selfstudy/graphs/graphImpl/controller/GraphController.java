package dmterkula.selfstudy.graphs.graphImpl.controller;

import dmterkula.selfstudy.graphs.graphImpl.domain.Edge;
import dmterkula.selfstudy.graphs.graphImpl.domain.Graph;
import dmterkula.selfstudy.graphs.graphImpl.domain.Node;
import dmterkula.selfstudy.graphs.graphImpl.services.EdgeService;
import dmterkula.selfstudy.graphs.graphImpl.services.GraphService;
import dmterkula.selfstudy.graphs.graphImpl.services.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by David Terkula on 6/19/2017.
 */
@Component
@RestController
public class GraphController {

    @Autowired private NodeService nodeService;
    @Autowired private EdgeService edgeService;
    @Autowired private GraphService graphService;
    public GraphController(NodeService nodeService, EdgeService edgeService, GraphService graphService){
        this.nodeService = nodeService;
        this.edgeService = edgeService;
        this.graphService = graphService;
    }

    @RequestMapping(value = "/buildGraph", method = RequestMethod.GET)
    public Graph buildGraph(){
        return graphService.buildGraph(nodeService, edgeService);
    }

    @RequestMapping(value = "/getMSTByRootId/{id}", method = RequestMethod.GET)
    public HashMap<Node, Node> getMST(@PathVariable("id") Long rootId){
        return graphService.getMST(rootId, nodeService, edgeService);
    }

    @RequestMapping(value = "/getMSTByRootName/{field}", method = RequestMethod.GET)
    public HashMap<Node, Node> getMST(@PathVariable("field") String rootName){
        return graphService.getMST(rootName, nodeService, edgeService);
    }

    @RequestMapping(value = "/getShortestPathByRootId/{id1}", method = RequestMethod.GET)
    public HashMap<Node, Node> getShortestPath(@PathVariable("id1") Long rootId){
        return graphService.getShortestPath(rootId, nodeService, edgeService);
    }

    @RequestMapping(value = "/getShortestPathByRootName/{field1}", method = RequestMethod.GET)
    public HashMap<Node, Node> getShortestPath(@PathVariable("field1") String rootName){
        return graphService.getShortestPath(rootName, nodeService, edgeService);
    }

    @RequestMapping(value = "/getMSTGraphByRootId/{id}", method = RequestMethod.GET)
    public Graph<Node> getMSTGraphByRootId(@PathVariable("id") Long rootId){
        return graphService.getMSTGraph(rootId, nodeService, edgeService);
    }

    @RequestMapping(value = "/getMSTGraphByRootName/{field}", method = RequestMethod.GET)
    public Graph<Node> getMSTGraphByRootName(@PathVariable("field") String rootName){
        return graphService.getMSTGraph(rootName, nodeService, edgeService);
    }

    @RequestMapping(value = "/DFSFromRootName/{rootName}", method = RequestMethod.GET)
        public Node [] DFS(@PathVariable String rootName){
        return graphService.DFS(rootName);

    }

    @RequestMapping(value = "/DFSFromRootId/{rootId}", method = RequestMethod.GET)
    public Node [] DFS(@PathVariable Long rootId){
        return graphService.DFS(rootId);

    }



}
