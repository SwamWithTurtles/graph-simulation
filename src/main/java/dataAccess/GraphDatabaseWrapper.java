package dataAccess;

import dagger.Lazy;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.Transaction;
import org.neo4j.helpers.collection.IteratorUtil;
import org.neo4j.kernel.impl.util.FileUtils;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphDatabaseWrapper {
    private static final String ID_PROPERTY = "node_id";
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
        idProperty.put(ID_PROPERTY, id.toString());

        createNode(LabelType.ENTITY, idProperty);
    }

    public void connectNodes(Integer id1, Integer id2) {
        try(Transaction tx = graphDb.get().beginTx()) {
            Node node1 = getSingleNodeWithId(id1);
            Node node2 = getSingleNodeWithId(id2);

            node1.createRelationshipTo(node2, RelationType.RELATED_TO.getRelation());

            tx.success();
        }
    }

    private Node getSingleNodeWithId(Integer id) {
        ResourceIterable<Node> potentialNodes = graphDb.get().findNodesByLabelAndProperty(LabelType.ENTITY.getLabel(), ID_PROPERTY, id.toString());
        List<Node> nodes = IteratorUtil.asList(potentialNodes);
        
        if(nodes.isEmpty()) {
            throw new NonExistentNodeIdException();
        } else if(nodes.size() > 1) {
            throw new AmbiguousNodeIdException();
        } else {
            return nodes.get(0);
        }
    }


    private void createNode(LabelType labelType, Map<String, String> nodeProperties) {
        try(Transaction tx = graphDb.get().beginTx()) {
            Node node = graphDb.get().createNode(labelType.getLabel());
            nodeProperties.forEach(node::setProperty);

            tx.success();
        }
    }

}
