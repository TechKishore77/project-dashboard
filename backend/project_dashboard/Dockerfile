FROM openjdk:11

VOLUME /tmp
WORKDIR /app

ARG JAR_FILE=build/libs/*.jar

CMD ['./gradlew', 'build']

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar ${0} ${@}"]
