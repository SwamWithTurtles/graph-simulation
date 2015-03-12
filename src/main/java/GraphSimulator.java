import dataAccess.GraphDatabaseWrapper;

import javax.inject.Inject;

public class GraphSimulator {

    private final SimulatorStrategy strategy;

    @Inject
    GraphSimulator(SimulatorStrategy strategy, GraphDatabaseWrapper graphDb) {
        this.strategy = strategy;
    }

    public void simulate() {
        strategy.setInitialConfig();
    }

}
