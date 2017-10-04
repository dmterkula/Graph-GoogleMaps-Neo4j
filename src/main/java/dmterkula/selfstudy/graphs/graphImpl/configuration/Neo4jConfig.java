package dmterkula.selfstudy.graphs.graphImpl.configuration;

import org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

/**
 * Created by David Terkula on 6/8/2017.
 */
@EnableNeo4jRepositories
@Configuration
@ComponentScan("dmterkula.selfstudy.graphs.graphImpl.domain")
@EntityScan("dmterkula.selfstudy.graphs.graphImpl.domain")
public class Neo4jConfig extends Neo4jDataAutoConfiguration {
}
