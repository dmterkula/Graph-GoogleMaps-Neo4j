package dmterkula.selfstudy.graphs.graphImpl;

import dmterkula.selfstudy.graphs.graphImpl.domain.Edge;
import dmterkula.selfstudy.graphs.graphImpl.domain.Node;
import dmterkula.selfstudy.graphs.graphImpl.setup.Engine;



import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;


import java.io.File;
import java.io.IOException;

/**
 * Created by David Terkula on 5/6/2017.
 */

@EnableNeo4jRepositories
@SpringBootApplication
@ComponentScan("dmterkula.selfstudy.graphs.graphImpl")



public class Tester {

    public static void main(String [] args)throws IOException{
        SpringApplication.run(Tester.class, args);
       populateDatabase();

    }

    public static void populateDatabase()throws IOException{
        Engine engine = new Engine();
        SessionFactory sessionFactory = new SessionFactory("dmterkula.selfstudy.graphs.graphImpl.domain");
        Session session = sessionFactory.openSession();
        //Neo4jTemplate neo4jTemplate = new Neo4jTemplate(sessionFactory);
        for(Node n: engine.getInputNodes()){
            session.save(n);
        }

        for(Edge e: engine.getInputEdges()){
            session.save(e);
        }
    }

    /*private static void addNode(Node n) {
        GraphDatabaseFactory graphDatabaseFactory = new GraphDatabaseFactory();
        GraphDatabaseService graphDb = graphDatabaseFactory.newEmbeddedDatabase(new File(dbPath));
        try (Transaction tx = graphDb.beginTx()) {
            org.neo4j.graphdb.Node city = graphDb.createNode(Label.label((String) n.getField()));
            city.setProperty("value", n.getValue());
            tx.success();
        }

    }*/

}




