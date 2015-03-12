import dagger.Module;
import dagger.Provides;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;

import javax.inject.Singleton;

@Module(injects = GraphSimulator.class)
public class GraphSimulationModule {

    @Provides SimulatorStrategy provideStrategy() {
       return new ErdosRenyiStrategy();
    }

    @Provides @Singleton GraphDatabaseService provideGraphDatabaseService() {
        GraphDatabaseService graphDb = new GraphDatabaseFactory()
                .newEmbeddedDatabaseBuilder( "C:\\Users\\das\\Desktop\\neo4j-data" )
                .setConfig( GraphDatabaseSettings.nodestore_mapped_memory_size, "10M" )
                .setConfig( GraphDatabaseSettings.string_block_size, "60" )
                .setConfig( GraphDatabaseSettings.array_block_size, "300" )
                .newGraphDatabase();

        registerShutdownHook(graphDb);

        return graphDb;
    }

    private static void registerShutdownHook( final GraphDatabaseService graphDb )
    {
        // Registers a shutdown hook for the Neo4j instance so that it
        // shuts down nicely when the VM exits (even if you "Ctrl-C" the
        // running application).
        Runtime.getRuntime().addShutdownHook( new Thread()
        {
            @Override
            public void run()
            {
                graphDb.shutdown();
            }
        } );
    }

}
