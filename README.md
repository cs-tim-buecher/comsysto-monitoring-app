# comsysto-monitoring-app

Monitors some of comsysto's infrastructure.

## Prerequisites

Oracle Java JDK 8 needs to be installed and on PATH.

## Run

```
./gradlew bootRun
```

open http://localhost:8080/

## Build JAR with embedded Jetty

```
./gradlew build
```

Artifact created is `build/libs/comsysto-monitoring-0.1.0.jar`

You can run jetty with

```
java -jar build/libs/comsysto-monitoring-0.1.0.jar
```

## Build WAR for Deployment into Tomcat

TODO

https://spring.io/guides/gs/convert-jar-to-war/