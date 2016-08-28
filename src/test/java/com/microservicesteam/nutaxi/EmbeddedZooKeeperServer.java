package com.microservicesteam.nutaxi;

import java.io.IOException;
import java.util.Properties;

import org.apache.zookeeper.server.ServerConfig;
import org.apache.zookeeper.server.ZooKeeperServerMain;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;

public class EmbeddedZooKeeperServer {

    private ZooKeeperServerMain zooKeeperServer;

    public EmbeddedZooKeeperServer(Properties zkProperties) {

        QuorumPeerConfig quorumConfiguration = new QuorumPeerConfig();
        try {
            quorumConfiguration.parseProperties(zkProperties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        zooKeeperServer = new ZooKeeperServerMain();
        final ServerConfig configuration = new ServerConfig();
        configuration.readFrom(quorumConfiguration);

        new Thread() {
            @Override
            public void run() {
                try {
                    zooKeeperServer.runFromConfig(configuration);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
    }
}
