package dataAccess;

import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Transaction;

import javax.inject.Inject;

public class GraphDatabaseWrapper {
    private final GraphDatabaseService graphDb;

    @Inject
    public GraphDatabaseWrapper(GraphDatabaseService graphDb) {
        this.graphDb = graphDb;
    }

    public void createNode(String labelName) {
        try(Transaction tx = graphDb.beginTx()) {
            Label label = DynamicLabel.label(labelName);
            graphDb.createNode(label);

            tx.success();
        }
    }

}
