#!/bin/bash

# Script para construir y desplegar la aplicación Psicovida
# Uso: ./build-and-deploy.sh [ambiente]

set -e

# Colores para output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Función para imprimir mensajes
print_message() {
    echo -e "${GREEN}[INFO]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Verificar si Docker está instalado
check_docker() {
    if ! command -v docker &> /dev/null; then
        print_error "Docker no está instalado. Por favor instala Docker primero."
        exit 1
    fi
    
    if ! docker info &> /dev/null; then
        print_error "Docker no está ejecutándose. Por favor inicia Docker."
        exit 1
    fi
}

# Construir la imagen Docker
build_image() {
    print_message "Construyendo imagen Docker..."
    docker build -t psicovida-aplicacion:latest .
    
    if [ $? -eq 0 ]; then
        print_message "Imagen construida exitosamente"
    else
        print_error "Error al construir la imagen"
        exit 1
    fi
}

# Ejecutar tests
run_tests() {
    print_message "Ejecutando tests..."
    docker run --rm psicovida-aplicacion:latest mvn test
}

# Ejecutar localmente
run_local() {
    print_message "Ejecutando aplicación localmente..."
    docker-compose up -d
    
    print_message "Aplicación iniciada en http://localhost:8080"
    print_message "Para ver logs: docker-compose logs -f aplicacion"
    print_message "Para detener: docker-compose down"
}

# Preparar para AWS ECR
prepare_aws() {
    if [ -z "$AWS_ACCOUNT_ID" ] || [ -z "$AWS_REGION" ]; then
        print_error "Variables AWS_ACCOUNT_ID y AWS_REGION deben estar configuradas"
        print_message "Ejemplo: export AWS_ACCOUNT_ID=123456789012"
        print_message "Ejemplo: export AWS_REGION=us-east-1"
        exit 1
    fi
    
    print_message "Preparando imagen para AWS ECR..."
    
    # Etiquetar imagen para ECR
    docker tag psicovida-aplicacion:latest $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/psicovida-aplicacion:latest
    
    print_message "Imagen etiquetada para ECR"
    print_message "Para subir a ECR ejecuta:"
    echo "aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com"
    echo "docker push $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/psicovida-aplicacion:latest"
}

# Función principal
main() {
    local environment=${1:-local}
    
    print_message "Iniciando proceso de construcción y despliegue para ambiente: $environment"
    
    # Verificar Docker
    check_docker
    
    # Construir imagen
    build_image
    
    case $environment in
        "local")
            run_local
            ;;
        "aws")
            prepare_aws
            ;;
        "test")
            run_tests
            ;;
        *)
            print_error "Ambiente no válido. Usa: local, aws, o test"
            exit 1
            ;;
    esac
    
    print_message "Proceso completado exitosamente!"
}

# Ejecutar función principal
main "$@" 