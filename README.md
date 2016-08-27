
# nutaxi-messaging

Umbrella project for nutaxi messaging features

## Apache Kafka

Apache Kafka is an open-source message broker project developed by the Apache Software Foundation written in Scala. The project aims to provide a unified, high-throughput, low-latency platform for handling real-time data feeds. It is, in its essence, a "massively scalable pub/sub message queue architected as a distributed transaction log," making it highly valuable for enterprise infrastructures to process streaming data.

### Local Apache Kafka Setup

*Prerequisites*

* Install [Docker](https://docs.docker.com/engine/installation/)

*Steps*

* Start Apache Kafka(and Apache ZooKeeper) instance

```
docker run -d -p 2181:2181 -p 9092:9092 --env ADVERTISED_HOST=localhost --env ADVERTISED_PORT=9092 --name=nutaxi-kafka spotify/kafka
```

* Check that the instance has been started

```
docker ps
```

*  Start the application

```
mvn spring-boot:run -Dspring.cloud.vault.token={token}
```

* Test the application

```
docker exec -it nutaxi-kafka /bin/bash
```

* Then execute the following command

```
/opt/kafka_2.11-0.8.2.1/bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic output
```
