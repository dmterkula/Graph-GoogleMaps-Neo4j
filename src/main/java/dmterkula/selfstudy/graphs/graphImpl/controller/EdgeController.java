package dmterkula.selfstudy.graphs.graphImpl.controller;

import dmterkula.selfstudy.graphs.graphImpl.domain.Edge;
import dmterkula.selfstudy.graphs.graphImpl.domain.Node;
import dmterkula.selfstudy.graphs.graphImpl.services.EdgeService;
import dmterkula.selfstudy.graphs.graphImpl.services.NodeService;
import dmterkula.selfstudy.graphs.graphImpl.setup.Engine;
import dmterkula.selfstudy.graphs.graphImpl.setup.GoogleMapsHandler;
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
 * Created by David Terkula on 6/16/2017.
 */
@Component
@RestController
public class EdgeController {

    @Autowired private NodeService nodeService;
    @Autowired private EdgeService edgeService;

    public EdgeController(EdgeService edgeService){
        this.edgeService = edgeService;
        this.nodeService = nodeService;
    }

   /* @RequestMapping(value = "/populateEdges", method = RequestMethod.GET)
    public List<Edge> populateDB() throws IOException {
        List<Edge> returnMe = new ArrayList<>();
        Engine engine = new Engine();
        HashSet<Edge> edges = engine.getInputEdges();
        for(Edge e: edges){
            returnMe.add(e);
            edgeService.createEdge(e);
        }
        return returnMe;
    }*/

    // not functional
    @RequestMapping(value = "/findEdgeById/{id)", method = RequestMethod.GET)
        public Edge findEdgeById(@PathVariable Long id){
            return edgeService.findById(id);
    }

    @RequestMapping(value = "/findEdgesOfNode/{id}", method = RequestMethod.GET)
    public List<Edge> findAllEdgesOfNode(@PathVariable Long id){
        return edgeService.findAllEdgesOfNode(id);
    }

    @RequestMapping(value = "/findEdgesOfNodeNamed/{field}", method = RequestMethod.GET)
    public List<Edge> findAllEdgesOfNode(@PathVariable String field){
        return edgeService.findAllEdgesOfNodeNamed(field);
    }

    //not functional
    @RequestMapping(value = "/findAllEdges", method = RequestMethod.GET)
    public List<Edge> findAlLEdges(){
        return edgeService.findAllEdges();
    }

    /// not functional
    @RequestMapping(value = "/findEdgeWeight/{id}", method = RequestMethod.GET)
    public Double findEdgeWeight(@PathVariable Long id){
        return edgeService.findById(id).getWeight();
    }

    @RequestMapping(value = "/findIncomingEdgesOfNode/{id}", method = RequestMethod.GET)
    public List<Edge> findIncomingEdgesofNode(@PathVariable Long id){
        return edgeService.findIncomingEdgesOfNode(id);
    }

    @RequestMapping(value = "/findOutgoingEdgesOfNode/{id}", method = RequestMethod.GET)
    public List<Edge> findOutgoingEdgesOfNode(@PathVariable Long id){
        return edgeService.findOutgoingEdgesOfNode(id);
    }


    @RequestMapping(value = "/findIncomingEdgesOfNodeNamed/{field}", method = RequestMethod.GET)
    public List<Edge> findIncomingEdgesofNode(@PathVariable("field") String startName){
        return edgeService.findIncomingEdgesOfNodeNamed(startName);
    }

    @RequestMapping(value = "/findOutgoingEdgesOfNodeNamed/{field}", method = RequestMethod.GET)
    public List<Edge> findOutgoingEdgesOfNode(@PathVariable ("field") String nodeName){
        return edgeService.findOutgoingEdgesOfNodeNamed(nodeName);
    }

    @RequestMapping(value = "/createWeightedConnectionFromNames/{field1}/{field2}", method = RequestMethod.GET)
    public Edge createWeightedConnection(@PathVariable("field1") String startNodeName, @PathVariable("field2") String endNodeName)throws IOException{
        GoogleMapsHandler googleMapsHandler = new GoogleMapsHandler();
        double distance = googleMapsHandler.getDistance(startNodeName, endNodeName);
        return edgeService.createWeightedConnection(startNodeName, endNodeName, distance);
    }

    @RequestMapping(value = "/createWeightedConnectionFromIds/{id1}/{id2}", method = RequestMethod.GET)
    public Edge createWeightedConnection(@PathVariable("id1") Long startNodeId, @PathVariable("id2") Long endNodeId)throws IOException{
        GoogleMapsHandler googleMapsHandler = new GoogleMapsHandler();
        String startNodeName = (String) nodeService.findById(startNodeId).getField();
        String endNodeName = (String) nodeService.findById(endNodeId).getField();
        double distance = googleMapsHandler.getDistance(startNodeName, endNodeName);
        return edgeService.createWeightedConnection(startNodeName, endNodeName, distance);
    }

    @RequestMapping(value = "/getEdgeBetween/{field1}/{field2}", method = RequestMethod.GET)
    public Edge getEdgeBetween(@PathVariable("field1") String startName, @PathVariable("field2") String endName){
        return edgeService.findEdgeBetween(startName, endName);
    }

}
