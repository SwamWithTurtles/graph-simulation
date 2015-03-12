import dagger.ObjectGraph;

public class Main {

    public static void main(String[] args) {
        ObjectGraph objectGraph = ObjectGraph.create(new StrategyModule());
        GraphSimulator simulator = objectGraph.get(GraphSimulator.class);

        simulator.simulate();
    }
}
