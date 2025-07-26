# Usa una imagen base con Java
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo
WORKDIR /root

# Exponer el puerto en el que se ejecutará la aplicación
EXPOSE 8080

# Copia los archivos de la aplicación en el contenedor

COPY ./pom.xml /root/pom.xml
COPY ./.mvn /root/.mvn
COPY ./mvnw /root


# Instala las dependencias de la aplicación
RUN ./mvnw dependency:go-offline

# Copia el código fuente de la aplicación en el contenedor
COPY ./src /root/src

#Construir la aplicación
RUN ./mvnw clean install -DskipTests 

#Levantar la aplicación cuando el contendor inicie
ENTRYPOINT [ "java", "-jar", "/root/target/aplicacion-0.0.1-SNAPSHOT.jar" ]

