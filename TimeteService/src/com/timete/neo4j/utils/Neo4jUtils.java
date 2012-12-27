package com.timete.neo4j.utils;

import org.apache.log4j.Logger;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSetting;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;
import org.neo4j.kernel.GraphDatabaseAPI;
import org.neo4j.server.WrappingNeoServerBootstrapper;
import org.neo4j.server.configuration.Configurator;
import org.neo4j.server.configuration.ServerConfigurator;
import org.neo4j.shell.ShellSettings;

import com.timete.utils.PropertyUtils;
import com.timete.utils.PropertyUtils.Property;

public class Neo4jUtils {

	private static Logger log = Logger.getLogger(Neo4jUtils.class.getName());

	public static GraphDatabaseService graphDb;
	private static WrappingNeoServerBootstrapper srv;
	public static boolean dbStatus;

	static {
		try {
			graphDb = Neo4jUtils.startDB(PropertyUtils
					.getProperty(Property.NE4J_DBPATH.value));
			Neo4jUtils.registerShutdownHook(graphDb);
			dbStatus=true;
		} catch (Exception e) {
			log.error("Error", e);
			dbStatus=false;
		}

	}

	public static GraphDatabaseService startDB(String path) throws Exception {
		// let the database accept remote neo4j-shell connections
		GraphDatabaseAPI graphdb = (GraphDatabaseAPI) new GraphDatabaseFactory()
				.newEmbeddedDatabaseBuilder(path)
				.setConfig(ShellSettings.remote_shell_enabled,
						GraphDatabaseSetting.TRUE)
				.setConfig(GraphDatabaseSettings.node_auto_indexing,
						GraphDatabaseSetting.TRUE)
				.setConfig(GraphDatabaseSettings.node_keys_indexable,
						GraphDatabaseSetting.TRUE)
				.setConfig(GraphDatabaseSettings.relationship_auto_indexing,
						GraphDatabaseSetting.TRUE).newGraphDatabase();
		ServerConfigurator config;
		config = new ServerConfigurator(graphdb);
		// let the server endpoint be on a custom port
		config.configuration().setProperty(
				Configurator.WEBSERVER_PORT_PROPERTY_KEY, 7878);
		config.configuration().setProperty(
				Configurator.WEBSERVER_ADDRESS_PROPERTY_KEY, "0.0.0.0");
		srv = new WrappingNeoServerBootstrapper(graphdb, config);
		srv.start();

		return graphdb;
	}

	public static void registerShutdownHook(final GraphDatabaseService graphDb)
			throws Exception {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				srv.stop();
				graphDb.shutdown();
				dbStatus=false;
			}
		});
	}
}
