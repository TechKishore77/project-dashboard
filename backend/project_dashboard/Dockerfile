FROM openjdk:11

VOLUME /tmp
WORKDIR /app

COPY . .

RUN chmod +x gradlew
RUN ./gradlew build

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app/build/libs/*.jar ${0} ${@}"]
