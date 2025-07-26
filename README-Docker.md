# Dockerización de la Aplicación Psicovida

Este documento explica cómo dockerizar y desplegar la aplicación Spring Boot.

## Requisitos Previos

- Docker instalado
- Docker Compose instalado
- Maven (para desarrollo local)

## Construcción de la Imagen

### Construir la imagen Docker

```bash
# Construir la imagen
docker build -t psicovida-aplicacion .

# Verificar que la imagen se creó
docker images | grep psicovida-aplicacion
```

### Ejecutar con Docker Compose

```bash
# Ejecutar solo la aplicación (usando base de datos externa)
docker-compose up aplicacion

# Ejecutar aplicación con PostgreSQL local
docker-compose up

# Ejecutar en segundo plano
docker-compose up -d
```

## Variables de Entorno

Puedes configurar las siguientes variables de entorno:

- `DATABASE_URL`: URL de conexión a la base de datos
- `DATABASE_USERNAME`: Usuario de la base de datos
- `DATABASE_PASSWORD`: Contraseña de la base de datos
- `JAVA_OPTS`: Opciones de JVM (memoria, etc.)

## Comandos Útiles

```bash
# Ver logs de la aplicación
docker-compose logs -f aplicacion

# Detener servicios
docker-compose down

# Reconstruir imagen
docker-compose build --no-cache

# Ejecutar comandos dentro del contenedor
docker-compose exec aplicacion sh

# Verificar estado de los servicios
docker-compose ps
```

## Despliegue en Producción

### Para AWS Lambda

1. **Crear la imagen:**

```bash
docker build -t psicovida-aplicacion .
```

2. **Etiquetar para ECR:**

```bash
docker tag psicovida-aplicacion:latest [AWS_ACCOUNT_ID].dkr.ecr.[REGION].amazonaws.com/psicovida-aplicacion:latest
```

3. **Subir a ECR:**

```bash
aws ecr get-login-password --region [REGION] | docker login --username AWS --password-stdin [AWS_ACCOUNT_ID].dkr.ecr.[REGION].amazonaws.com
docker push [AWS_ACCOUNT_ID].dkr.ecr.[REGION].amazonaws.com/psicovida-aplicacion:latest
```

### Para otros servicios de contenedores

La imagen está optimizada para:

- AWS ECS/Fargate
- Google Cloud Run
- Azure Container Instances
- Kubernetes

## Configuración de Base de Datos

La aplicación está configurada para usar PostgreSQL. En producción, asegúrate de:

1. Configurar las variables de entorno de la base de datos
2. Usar una base de datos gestionada (RDS, Cloud SQL, etc.)
3. Configurar las credenciales de forma segura

## Monitoreo y Health Checks

La aplicación incluye endpoints de health check:

- `GET /actuator/health` - Estado general de la aplicación
- `GET /actuator/info` - Información de la aplicación

## Optimizaciones

- **Multi-stage build** para reducir el tamaño de la imagen
- **Usuario no-root** para seguridad
- **Health checks** para monitoreo
- **Variables de entorno** para configuración flexible
- **Logging optimizado** para contenedores
