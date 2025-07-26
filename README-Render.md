# 🚀 Despliegue en Render.com

Esta guía te ayudará a desplegar tu aplicación Spring Boot en Render.com usando Docker.

## 📋 Requisitos Previos

1. **Cuenta en Render.com** - [Regístrate aquí](https://render.com)
2. **Repositorio Git** - Tu código debe estar en GitHub, GitLab, o Bitbucket
3. **Base de datos PostgreSQL** - Puedes usar la base de datos de Render o externa

## 🎯 Pasos para Desplegar

### 1. Preparar el Repositorio

Asegúrate de que tu repositorio contenga estos archivos:

- `Dockerfile`
- `render.yaml` (opcional, para configuración automática)
- `src/main/resources/application-render.properties`

### 2. Crear Servicio en Render

1. **Ve a tu dashboard de Render**
2. **Haz clic en "New +"**
3. **Selecciona "Web Service"**
4. **Conecta tu repositorio Git**

### 3. Configurar el Servicio

#### Configuración Básica:

- **Name**: `psicovida-aplicacion`
- **Environment**: `Docker`
- **Region**: `Oregon` (o la más cercana a ti)
- **Branch**: `main` (o tu rama principal)
- **Root Directory**: `/` (dejar vacío)

#### Configuración de Build:

- **Build Command**: `docker build -t psicovida-aplicacion .`
- **Start Command**: `java $JAVA_OPTS -jar app.jar`

### 4. Configurar Variables de Entorno

En la sección "Environment Variables" de tu servicio, agrega:

```
SPRING_PROFILES_ACTIVE=render
JAVA_OPTS=-Xmx512m -Xms256m
DATABASE_URL=jdbc:postgresql://tu-host:5432/tu-database
DATABASE_USERNAME=tu-usuario
DATABASE_PASSWORD=tu-password
```

### 5. Configurar Health Check

- **Health Check Path**: `/actuator/health`
- **Health Check Timeout**: `300` segundos

## 🔧 Configuración Avanzada

### Usando render.yaml (Recomendado)

Si usas el archivo `render.yaml`, Render configurará automáticamente:

```yaml
services:
  - type: web
    name: psicovida-aplicacion
    env: docker
    region: oregon
    plan: starter
    healthCheckPath: /actuator/health
    dockerfilePath: ./Dockerfile
    dockerContext: .
    envVars:
      - key: SPRING_PROFILES_ACTIVE
        value: render
      - key: JAVA_OPTS
        value: "-Xmx512m -Xms256m"
    autoDeploy: true
```

### Configuración de Base de Datos

#### Opción 1: Base de Datos de Render

1. Crea un servicio de PostgreSQL en Render
2. Render automáticamente conectará los servicios
3. Las variables de entorno se configurarán automáticamente

#### Opción 2: Base de Datos Externa

Configura manualmente las variables:

- `DATABASE_URL`
- `DATABASE_USERNAME`
- `DATABASE_PASSWORD`

## 🚀 Despliegue Automático

Una vez configurado:

1. **Render detectará cambios** en tu repositorio
2. **Construirá automáticamente** la imagen Docker
3. **Desplegará la nueva versión**
4. **Ejecutará health checks** para verificar que funcione

## 📊 Monitoreo

### Logs

- Ve a tu servicio en Render
- Haz clic en "Logs" para ver logs en tiempo real

### Health Checks

- Tu aplicación expone `/actuator/health`
- Render verificará este endpoint automáticamente

### Métricas

- Render proporciona métricas básicas
- CPU, memoria, y tiempo de respuesta

## 🔍 Troubleshooting

### Problemas Comunes

1. **Build Fails**

   - Verifica que el Dockerfile esté correcto
   - Revisa los logs de build

2. **Health Check Fails**

   - Asegúrate de que la aplicación esté escuchando en el puerto correcto
   - Verifica que `/actuator/health` responda

3. **Database Connection Issues**
   - Verifica las variables de entorno de la base de datos
   - Asegúrate de que la base de datos esté accesible

### Comandos Útiles

```bash
# Verificar logs localmente
docker logs psicovida-aplicacion

# Ejecutar localmente para probar
docker-compose up

# Verificar health check
curl https://tu-app.onrender.com/actuator/health
```

## 💰 Costos

- **Starter Plan**: $7/mes (512MB RAM, 0.1 CPU)
- **Standard Plan**: $25/mes (1GB RAM, 0.5 CPU)
- **Pro Plan**: $50/mes (2GB RAM, 1 CPU)

## 🎉 ¡Listo!

Tu aplicación estará disponible en:
`https://tu-app.onrender.com`

### Endpoints Disponibles:

- **API**: `https://tu-app.onrender.com/api/...`
- **Health Check**: `https://tu-app.onrender.com/actuator/health`
- **Info**: `https://tu-app.onrender.com/actuator/info`
