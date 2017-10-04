package dmterkula.selfstudy.graphs.graphImpl.services;

import dmterkula.selfstudy.graphs.graphImpl.dal.repositories.NodeRepository;
import dmterkula.selfstudy.graphs.graphImpl.domain.Edge;
import dmterkula.selfstudy.graphs.graphImpl.domain.Node;
import dmterkula.selfstudy.graphs.graphImpl.setup.Engine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by David Terkula on 6/24/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootApplication
@ComponentScan("dmterkula.selfstudy.graphs.graphImpl")

public class NodeServiceImplTest {


    @Autowired private NodeService nodeService;
    @Autowired private EdgeService edgeService;
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;

    @Before
    public void setUp() throws Exception {
        nodes = new ArrayList();
        edges = new ArrayList<>();
        Node cincinnati = new Node(0,"Cincinnati,OH");
        Node louisville = new Node (1,"Louisville,KY");
        Node portland = new Node(2,"Portland,OR");
        Node sanFrancisco = new Node(3,"San+Francisco,CA");
        Node atlanta = new Node(4,"Atlana,GA");
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

    }


    @Test
    public void createNode() throws Exception {
        Node orlando = new Node(6, "Orlando,FL");
        nodeService.createNode(orlando);
        Node test = nodeService.findByInternalId(6);
        assertNotNull(test);
        assertEquals(orlando, test);

        Node boston = new Node("Boston,MA");
        nodeService.createNode(boston);
        Node test1 = nodeService.findNodeByName("Boston,MA");
        assertNotNull(test);
        assertEquals(boston, test1);
    }

    @Test
     public void delete() throws Exception {
        nodeService.delete(nodes.get(0));
        Node test = nodeService.findByInternalId(0);
        assertNull(test);
    }



// hard to now what graph id is after many creations and deletions. no guarantee. look at what is in neo4j browser and search
    // off what you see there -- it works, hard to test formally though
    /*@Test
    public void findById() throws Exception {
        Node cin = new Node();
        for(Node n: nodes){
            if(n.getField().equals("Cincinnati,OH")){
                cin = n;
            }
        }
        Integer i = cin.getId();
        Long id = Long.valueOf(i.longValue());
        Node test = nodeService.findById(id);
        assertNotNull(test);
        assertEquals(test, cin);

    }*/

    @Test
    public void findByInternalId() throws Exception {
        Node cin = new Node();
        for(Node n: nodes){
            if(n.getField().equals("Cincinnati,OH")){
                cin = n;
            }
        }

        Node test = nodeService.findByInternalId(0);
        assertNotNull(test);
        assertEquals(test, cin);

    }

    @Test
    public void findAll() throws Exception {
        List<Node> t = nodeService.findAll();
        assertTrue(!t.isEmpty());
        assertEquals(t.size(), 6);
        assertEquals(nodes.size(), 6);

        Node [] test = new Node[nodes.size()];
        Node[] tEquiv = new Node[t.size()];

        for(Node n: nodes){
            test[n.getId()] = n;
        }

        for(Node n: t){
           tEquiv[n.getId()] = n;
        }

        for(int i = 0; i < tEquiv.length; i++){
            assertEquals(tEquiv[i], test[i]);
        }

    }

    @Test
    public void findNodeByName() throws Exception {
        Node test = nodeService.findNodeByName("Cincinnati,OH");
        assertNotNull(test);
        assertEquals(nodes.get(0), test);
    }

    @Test
    public void findNeighborsOfName() throws Exception {
        List<Node> test = nodeService.findNeighborsOfName("Portland,OR");
        assertNotNull(test);
        Node sanFrancisco = new Node(3,"San+Francisco,CA");
        Node cincinnati = new Node(0,"Cincinnati,OH");
        assertEquals(test.size(), 2);
        if(test.get(0).getId() == 3){
            assertEquals(test.get(0), sanFrancisco);
            assertEquals(test.get(1), cincinnati);
        }
        else{
            assertEquals(test.get(0), cincinnati);
            assertEquals(test.get(1), sanFrancisco);
        }
    }

    @Test
    public void findNeighborsOfId() throws Exception {
        List<Node> test = nodeService.findNeighborsOfId(2);
        assertNotNull(test);
        Node sanFrancisco = new Node(3,"San+Francisco,CA");
        Node cincinnati = new Node(0,"Cincinnati,OH");
        assertEquals(test.size(), 2);
        if(test.get(0).getId() == 3){
            assertEquals(test.get(0), sanFrancisco);
            assertEquals(test.get(1), cincinnati);
        }
        else{
            assertEquals(test.get(1), sanFrancisco);
            assertEquals(test.get(0), cincinnati);
        }
    }

    @Test
    public void findOrphanNodes() throws Exception {
        List<Node> orphans = nodeService.findOrphanNodes();
        assertTrue(orphans.isEmpty());
        Node orphan = new Node(10, "Orlando,FL");
        nodeService.createNode(orphan);
        List<Node> test = nodeService.findOrphanNodes();
        assertNotNull(orphans);
        assertEquals(test.size(), 1);
        assertEquals(test.get(0), orphan);
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