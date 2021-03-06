package com.microservicesteam.nutaxi;

import static com.google.common.base.Throwables.propagate;
import static java.util.concurrent.Executors.newSingleThreadExecutor;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;

import org.apache.zookeeper.server.ServerConfig;
import org.apache.zookeeper.server.ZooKeeperServerMain;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;

public class EmbeddedZooKeeperServer {

    private ExecutorService executorService = newSingleThreadExecutor();

    public EmbeddedZooKeeperServer(Properties zkProperties) {
        run(zkProperties);
    }

    private void run(Properties zkProperties) {
        executorService.execute(() -> {
            try {
                new ZooKeeperServerMain().runFromConfig(getServerConfig(zkProperties));
            } catch (IOException exception) {
                throw propagate(exception);
            }
        });
    }

    private static ServerConfig getServerConfig(Properties zkProperties) {
        ServerConfig configuration = new ServerConfig();
        configuration.readFrom(getQuorumConfiguration(zkProperties));
        return configuration;
    }

    private static QuorumPeerConfig getQuorumConfiguration(Properties zkProperties) {
        QuorumPeerConfig quorumConfiguration = new QuorumPeerConfig();
        try {
            quorumConfiguration.parseProperties(zkProperties);
        } catch (Exception e) {
            throw propagate(e);
        }
        return quorumConfiguration;
    }

    public void stop() {
        executorService.shutdown();
    }
}
