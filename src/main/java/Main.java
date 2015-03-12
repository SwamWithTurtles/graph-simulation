import dagger.ObjectGraph;

public class Main {

    public static void main(String[] args) {
        ObjectGraph objectGraph = ObjectGraph.create(new GraphSimulationModule());
        GraphSimulator simulator = objectGraph.get(GraphSimulator.class);

        simulator.simulate();
    }

}
