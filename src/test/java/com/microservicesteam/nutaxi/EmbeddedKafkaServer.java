package com.microservicesteam.nutaxi;

import java.util.Properties;

import kafka.server.KafkaConfig;
import kafka.server.KafkaServerStartable;

public class EmbeddedKafkaServer {

    private KafkaServerStartable kafka;

    private EmbeddedZooKeeperServer zookeeper;

    public EmbeddedKafkaServer(Properties kafkaProperties, Properties zkProperties) {
        KafkaConfig kafkaConfig = new KafkaConfig(kafkaProperties);
        zookeeper = new EmbeddedZooKeeperServer(zkProperties);
        kafka = new KafkaServerStartable(kafkaConfig);
        kafka.startup();
    }

    public void stop() {
        kafka.shutdown();
    }

}
