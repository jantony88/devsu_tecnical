FROM openjdk:17 as builder
LABEL authors="jcoloma"
# Establecer el directorio de trabajo
WORKDIR /workspace/app
# Copiar el archivo pom.xml y descargar las dependencias
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN chmod +x mvnw
RUN ./mvnw  dependency:go-offline
# Copiar el código fuente y compilar el war
COPY src src
RUN ./mvnw package -DskipTests

#crear imagen
FROM openjdk:17
VOLUME /tmp

COPY --from=builder /workspace/app/target/movimientos_service.jar app.jar
#puerto por el que corree el contenedor expuesto para solicitudes
EXPOSE 8084

# "-XX:+UseG0.5GC", "-Xms1G", "-Xmx1G",
ENTRYPOINT ["java", "-jar", "-Djava.security.egd=file:/dev/./urandom", "-XX:+HeapDumpOnOutOfMemoryError", "-XX:HeapDumpPath=/tmp/heapdump.hprof", "app.jar"]