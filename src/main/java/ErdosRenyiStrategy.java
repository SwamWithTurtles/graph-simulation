import dataAccess.GraphDatabaseWrapper;

import javax.inject.Inject;
import java.util.Random;
import java.util.stream.IntStream;

public class ErdosRenyiStrategy implements SimulatorStrategy {

    private final GraphDatabaseWrapper graphDb;
    private final Random random;

    @Inject
    public ErdosRenyiStrategy(GraphDatabaseWrapper graphDatabaseWrapper, Random random) {
        graphDb = graphDatabaseWrapper;
        this.random = random;
    }

    @Override
    public String strategyName() {
        return "Erdos-Renyi";
    }

    @Override
    public void setInitialConfig() {
        graphDb.reset();
        graphDb.createNodeWithId(1);
        graphDb.createNodeWithId(2);
        graphDb.connectNodes(1, 2);
    }

    @Override
    public void step() {
        int numberOfNodes = graphDb.numberOfNodes();

        graphDb.createNodeWithId(numberOfNodes + 1);

        IntStream.range(1, numberOfNodes).forEach(
                n -> {
                    if (randomnessSucceeds()) {
                        graphDb.connectNodes(numberOfNodes + 1, n);
                    }
                }
        );
    }

    private boolean randomnessSucceeds() {
        return random.nextBoolean();
    }


}
