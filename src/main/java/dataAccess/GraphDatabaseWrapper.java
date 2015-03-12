package dataAccess;

import dagger.Lazy;
import org.neo4j.graphdb.*;
import org.neo4j.kernel.impl.util.FileUtils;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GraphDatabaseWrapper {
    private final Lazy<GraphDatabaseService> graphDb;

    @Inject
    public GraphDatabaseWrapper(Lazy<GraphDatabaseService> graphDb) {
        this.graphDb = graphDb;
    }

    public void reset() {
        try {
            FileUtils.deleteRecursively(new File("C:\\Users\\das\\Desktop\\neo4j-data"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createNodeWithId(Integer id) {
        Map<String, String> idProperty = new HashMap<>();
        idProperty.put("id", id.toString());

        createNode(LabelType.ENTITY, idProperty);
    }

    private void createNode(LabelType labelType, Map<String, String> nodeProperties) {
        try(Transaction tx = graphDb.get().beginTx()) {
            Label label = DynamicLabel.label(labelType.name);
            Node node = graphDb.get().createNode(label);
            nodeProperties.forEach(node::setProperty);

            tx.success();
        }
    }

}
