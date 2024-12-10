mvn clean install -DskipTests && java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5015 -jar target/multiUserApproval-0.0.1-SNAPSHOT.jar
