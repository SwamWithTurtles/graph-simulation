import dagger.Module;
import dagger.Provides;

@Module(injects = GraphSimulator.class)
public class StrategyModule {

    @Provides SimulatorStrategy provideStrategy() {
       return new ErdosRenyiStrategy();
    }

}
