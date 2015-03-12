import dataAccess.GraphDatabaseWrapper;

import javax.inject.Inject;

public class GraphSimulator {

    private final SimulatorStrategy strategy;
    private final GraphDatabaseWrapper graphDb;

    @Inject
    GraphSimulator(SimulatorStrategy strategy, GraphDatabaseWrapper graphDb) {
        this.strategy = strategy;
        this.graphDb = graphDb;
    }

    public void simulate() {
        graphDb.createNode("Name1");
        System.out.println(strategy.strategyName());
    }

}
