FROM openjdk:17-oracle

COPY scrapper/target/scrapper-0.0.1-SNAPSHOT.jar ./scrapper.jar

ENTRYPOINT ["java", "-jar", "./scrapper.jar"]