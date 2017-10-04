package dmterkula.selfstudy.graphs.graphImpl.services;

import dmterkula.selfstudy.graphs.graphImpl.domain.Edge;
import dmterkula.selfstudy.graphs.graphImpl.domain.Node;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by David Terkula on 6/27/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootApplication
@ComponentScan("dmterkula.selfstudy.graphs.graphImpl")
public class EdgeServiceImplTest {

    @Autowired
    private NodeService nodeService;
    @Autowired private EdgeService edgeService;
    private List<Node> nodes;
    private List<Edge> edges;

    @Before
    public void setUp() throws Exception {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        Node cincinnati = new Node(0,"Cincinnati,OH");
        Node louisville = new Node (1,"Louisville,KY");
        Node portland = new Node(2,"Portland,OR");
        Node sanFrancisco = new Node(3,"San+Francisco,CA");
        Node atlanta = new Node(4,"Atlanta,GA");
        Node austin = new Node(5,"Austin,TX");
        nodes.add(cincinnati);
        nodes.add(louisville);
        nodes.add(portland);
        nodes.add(sanFrancisco);
        nodes.add(atlanta);
        nodes.add(austin);
        for(Node n: nodes){
            nodeService.createNode(n);
        }

        edges.add(new Edge(cincinnati, louisville, 0));
        edges.add(new Edge(louisville, cincinnati, 0));
        edges.add(new Edge(atlanta, cincinnati, 0));
        edges.add(new Edge(cincinnati, atlanta, 0));
        edges.add(new Edge(cincinnati, austin, 0));
        edges.add(new Edge(austin, cincinnati, 0));
        edges.add(new Edge(cincinnati, portland, 0));
        edges.add(new Edge(portland, cincinnati, 0));

        edges.add(new Edge(louisville, atlanta, 0));
        edges.add(new Edge(atlanta, louisville, 0));
        edges.add(new Edge(austin, louisville, 0));
        edges.add(new Edge(louisville, austin, 0));

        edges.add(new Edge(austin, sanFrancisco, 0));
        edges.add(new Edge(sanFrancisco, austin, 0));
        edges.add(new Edge(atlanta, austin, 0));
        edges.add(new Edge(austin, atlanta, 0));

        edges.add(new Edge(sanFrancisco, portland, 0));
        edges.add(new Edge(portland, sanFrancisco, 0));



        for(Edge e: edges){
            edgeService.createWeightedConnection((String)e.getStart().getField(), (String)e.getEnd().getField(), e.getWeight());
        }

        List<Edge> all = edgeService.findAllEdges();
        List<Node> allN = nodeService.findAll();
        System.out.println(all.size());

    }





    // hard to test internal id of edges after many iterations of creation and deletion. use method only from neo4j browser and controller
    /*@Test
    public void findById() throws Exception {

    }*/



    @Test
    public void delete() throws Exception {
        edgeService.delete(edgeService.findEdgeBetween("Cincinnati,OH", "Louisville,KY"));
        Edge test = edgeService.findEdgeBetween("Cincinnati,OH", "Louisville,KY");
        assertNull(test);
    }

    @Test
    public void findAllEdges() throws Exception {
        List<Edge> test = edgeService.findAllEdges();
        boolean areEqual = true;
        for(Edge e: test){
            boolean match = false;
            for(int i = 0; i < edges.size() && !match; i++){
                if(e.equals(edges.get(i))){
                    match = true;
                }
            }
            if(!match){
                areEqual = false;
            }
        }
        assertTrue(areEqual);
    }

    @Test
    public void findAllEdgesOfNode() throws Exception {
        List<Edge> test = edgeService.findAllEdgesOfNodeNamed("Portland,OR");
        assertEquals(test.size(), 4);
        boolean areEqual = true;
        for(Edge e: test){
            boolean match = false;
            for(int i = 0; i < edges.size() && !match; i++){
                if(e.equals(edges.get(i))){
                    match = true;
                }
            }
            if(!match){
                areEqual = false;
            }
        }
        assertTrue(areEqual);
    }

    @Test
    public void createWeightedConnection() throws Exception {
        edgeService.createWeightedConnection("Austin,TX", "Portland,OR", 0);
        Edge e = edgeService.findEdgeBetween("Austin,TX", "Portland,OR");
        assertNotNull(e);
        assertTrue(e.getWeight() == 0);
    }

    @Test
    public void findOutgoingEdgesOfNode() throws Exception {
        List<Edge> all = edgeService.findAllEdges();
        List<Edge> test = edgeService.findOutgoingEdgesOfNodeNamed("Portland,OR");
        assertNotNull(test);
        assertEquals(test.size(), 2);
        if(test.get(0).getEnd().getField().equals("San+Francisco,CA")){
            assertEquals(test.get(0), edges.get(17)); // portland to san+francisco
            assertEquals(test.get(1), edges.get(7)); // portland to cincinnati
        }
        else{
            assertEquals(test.get(0), edges.get(7)); // portland to cincinnati
            assertEquals(test.get(1), edges.get(17)); // portland to san+francisco
        }


    }

    @Test
    public void findIncomingEdgesOfNode() throws Exception {
        List<Edge> test = edgeService.findIncomingEdgesOfNodeNamed("Portland,OR");
        assertNotNull(test);
        assertEquals(test.size(), 2);
        if(test.get(0).getEnd().getField().equals("Cincinnati,OH")){
            assertEquals(test.get(0), edges.get(6)); // san+francisco to portland
            assertEquals(test.get(1), edges.get(16)); // cincinnati to portland
        }
        else{
            assertEquals(test.get(0), edges.get(16)); // cincinnati to portland
            assertEquals(test.get(1), edges.get(6)); // san+francisco to portland
        }
    }


    @Test
    public void findEdgeBetween() throws Exception {
        Edge e = edgeService.findEdgeBetween("Atlanta,GA", "Austin,TX");
        assertNotNull(e);
        assertEquals(e, edges.get(14));
    }

    @After
    public void tearDown() throws Exception {

        for(Edge e: edgeService.findAllEdges()){
            edgeService.delete(e);
        }
        for(Node n: nodeService.findAll()){
            nodeService.delete(n);
        }


    }

}