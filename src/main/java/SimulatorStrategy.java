public interface SimulatorStrategy {
    String strategyName();

    void setInitialConfig();

    void step();
}
