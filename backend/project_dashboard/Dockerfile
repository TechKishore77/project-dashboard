FROM openjdk:11

VOLUME /tmp

COPY . .

RUN ./gradlew build

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /build/libs/*.jar ${0} ${@}"]
