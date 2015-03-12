package dataAccess;

import org.neo4j.graphdb.RelationshipType;

public enum RelationType {
    RELATED_TO("related_to");

    private final String relationshipTypeName;

    RelationType(String relationshipTypeName) {
        this.relationshipTypeName = relationshipTypeName;
    }

    public RelationshipType getRelation() {
        return new RelationshipType() {
            @Override
            public String name() {
                return relationshipTypeName;
            }
        };
    }
}
