import javax.inject.Inject;

public class GraphSimulator {

    private final SimulatorStrategy strategy;

    @Inject
    GraphSimulator(SimulatorStrategy strategy) {
        this.strategy = strategy;
    }

    public void simulate() {
        System.out.println(strategy.strategyName());
    }

}
