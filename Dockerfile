# Etapa de construcción
FROM maven:3.9.6-openjdk-17-slim AS build

# Establecer directorio de trabajo
WORKDIR /app

# Copiar archivos de configuración de Maven
COPY pom.xml .
COPY mvnw .
COPY mvnw.cmd .
COPY .mvn .mvn

# Descargar dependencias (capa separada para mejor cache)
RUN mvn dependency:go-offline -B

# Copiar código fuente
COPY src src

# Construir la aplicación
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM openjdk:17-jre-slim

# Instalar dependencias del sistema
RUN apt-get update && apt-get install -y \
  curl \
  && rm -rf /var/lib/apt/lists/*

# Crear usuario no-root para seguridad
RUN groupadd -r spring && useradd -r -g spring spring

# Establecer directorio de trabajo
WORKDIR /app

# Copiar el JAR desde la etapa de construcción
COPY --from=build /app/target/*.jar app.jar

# Cambiar propiedad del archivo
RUN chown spring:spring app.jar

# Cambiar al usuario no-root
USER spring

# Exponer puerto
EXPOSE 8080

# Variables de entorno para configuración
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Comando de inicio
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"] 