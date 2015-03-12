package dataAccess;

import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Label;

public enum LabelType {
    ENTITY("entity"),
    RELATION("related_to");

    private final String name;

    public Label getLabel() {
        return DynamicLabel.label(name);
    }

    LabelType(String name) {
        this.name = name;
    }
}
