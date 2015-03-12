import dataAccess.GraphDatabaseWrapper;

import javax.inject.Inject;

public class ErdosRenyiStrategy implements SimulatorStrategy {

    private final GraphDatabaseWrapper graphDb;

    @Inject
    public ErdosRenyiStrategy(GraphDatabaseWrapper graphDatabaseWrapper) {
        graphDb = graphDatabaseWrapper;
    }

    @Override
    public String strategyName() {
        return "Erdos-Renyi";
    }

    @Override
    public void setInitialConfig() {
        graphDb.reset();
        graphDb.createNodeWithId(1);
    }
}
