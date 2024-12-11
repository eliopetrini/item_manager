#Was: FROM openjdk:17

#FROM arm32v7/eclipse-temurin
FROM openjdk:17
COPY ./target/ItemManager.jar /tmp
WORKDIR /tmp
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ItemManager.jar"]
#FROM maven:3.9.5-eclipse-temurin-11 AS build
#COPY src /tmp/src
#COPY pom.xml /tmp
#RUN mvn -f /tmp/pom.xml clean package


#FROM eclipse-temurin:17-jdk
#COPY --from=build /tmp/target/ItemManager.jar /usr/local/lib/ItemManager.jar
#ENTRYPOINT ["java","-jar","/usr/local/lib/ItemManager.jar"]
