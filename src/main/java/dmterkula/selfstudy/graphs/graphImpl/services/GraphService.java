package dmterkula.selfstudy.graphs.graphImpl.services;
import dmterkula.selfstudy.graphs.graphImpl.domain.Graph;
import dmterkula.selfstudy.graphs.graphImpl.domain.Node;

import java.util.HashMap;

/**
 * Created by David Terkula on 6/19/2017.
 */

public interface GraphService {

    Graph buildGraph(NodeService nodeService, EdgeService edgeService);
    HashMap<Node, Node> getMST(Long rootId, NodeService nodeService, EdgeService edgeService);
    HashMap<Node, Node> getMST(String rootName, NodeService nodeService, EdgeService edgeService);
    HashMap<Node, Node> getShortestPath(Long rootId, NodeService nodeService, EdgeService edgeService);
    HashMap<Node, Node> getShortestPath(String rootName, NodeService nodeService, EdgeService edgeService);
    Graph<Node> getMSTGraph(Long rootId, NodeService nodeService, EdgeService edgeService);
    Graph<Node> getMSTGraph(String rootName, NodeService nodeService, EdgeService edgeService);
    Node [] DFS(String rootName);
    Node [] DFS(Long id);
}
