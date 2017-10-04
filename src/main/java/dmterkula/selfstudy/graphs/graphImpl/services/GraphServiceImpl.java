package dmterkula.selfstudy.graphs.graphImpl.services;

import dmterkula.selfstudy.graphs.graphImpl.domain.Edge;
import dmterkula.selfstudy.graphs.graphImpl.domain.Graph;
import dmterkula.selfstudy.graphs.graphImpl.domain.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by David Terkula on 6/19/2017.
 */
@Service
public class GraphServiceImpl implements GraphService {

    @Autowired private NodeService nodeService;
    @Autowired private EdgeService edgeService;

    public Graph buildGraph(NodeService nodeService, EdgeService edgeService){
        Graph G = new Graph();
        HashSet<Node> nodes = new HashSet<>();
        HashSet<Edge> edges = new HashSet<Edge>();
        for(Node n: nodeService.findAll()){
            n.setValue(Double.MAX_VALUE);
            nodes.add(n);
        }

        for(Edge e: edgeService.findAllEdges()){
            edges.add(e);
        }
        G.setNodes(nodes);
        G.setEdges(edges);
        G.populateAdjacencyList();
        return G;
    }

    public HashMap<Node, Node> getMST(Long rootId, NodeService nodeService, EdgeService edgeService){
        return buildGraph(nodeService, edgeService).getMSTMap(nodeService.findById(rootId));
    }

    public HashMap<Node, Node> getMST(String rootName, NodeService nodeService, EdgeService edgeService){
        return buildGraph(nodeService, edgeService).getMSTMap(nodeService.findNodeByName(rootName));
    }

    public HashMap<Node, Node> getShortestPath(Long rootId, NodeService nodeService, EdgeService edgeService){
        return buildGraph(nodeService, edgeService).getShortestPathMap(nodeService.findById(rootId));
    }

    public HashMap<Node, Node> getShortestPath(String rootName, NodeService nodeService, EdgeService edgeService){
        return buildGraph(nodeService, edgeService).getShortestPathMap(nodeService.findNodeByName(rootName));
    }

    public Graph<Node> getMSTGraph(Long rootId, NodeService nodeService, EdgeService edgeService){
        return buildGraph(nodeService, edgeService).getMSTGraph(nodeService.findById(rootId));
    }

    public Graph<Node> getMSTGraph(String rootName, NodeService nodeService, EdgeService edgeService){
        return buildGraph(nodeService, edgeService).getMSTGraph(nodeService.findNodeByName(rootName));
    }

    public Node[] DFS(String rootName){
        return buildGraph(nodeService, edgeService).DFS(nodeService.findNodeByName(rootName));
    }

    public Node[] DFS(Long id){
        return buildGraph(nodeService, edgeService).DFS(nodeService.findById(id));
    }

}
