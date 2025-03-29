# Imagen base con Java 17
FROM eclipse-temurin:17.0.14_7-jdk

#Informar el puerto donde se ejecuta el contenedor(INFORMATIVO)
EXPOSE 8080

# Definir directorio raiz de nuestro contenedor
WORKDIR /root

# Copiar y pegar archivos dentro del contenedor
COPY ./pom.xml /root/
COPY ./.mvn /root/.mvn
COPY ./mvnw /root/

#Descargar las dependencias(Descarga las dependencias dentro del contenedor,pero que no construya el proyecto)
RUN ./mvnw dependency:go-offline

#Copiar el codigo fuente dentro del contenedor
COPY ./src /root/src

#Construir nuestra aplicacion
RUN ./mvnw clean install -DskipTests

#Levantar nuestra aplicacion cuando el contenedor inicie
ENTRYPOINT ["java", "-jar", "/root/target/ApiBus-0.0.1-SNAPSHOT.jar"]