FROM openjdk:11

VOLUME /tmp
WORKDIR /app

COPY . .

# Build the application
RUN ./gradlew build

# Specify the JAR file location
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080

# Use the "exec" form to properly pass signals to the Java process
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app/app.jar ${0} ${@}"]
