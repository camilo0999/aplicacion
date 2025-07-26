#!/bin/bash

# Script para desplegar en Render.com
# Uso: ./deploy-render.sh

set -e

# Colores para output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
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

print_step() {
    echo -e "${BLUE}[STEP]${NC} $1"
}

# Verificar si Git está configurado
check_git() {
    if ! command -v git &> /dev/null; then
        print_error "Git no está instalado. Por favor instala Git primero."
        exit 1
    fi
    
    if [ ! -d ".git" ]; then
        print_error "No estás en un repositorio Git. Inicializa Git primero."
        exit 1
    fi
}

# Verificar archivos necesarios
check_files() {
    print_step "Verificando archivos necesarios..."
    
    required_files=("Dockerfile" "render.yaml" "src/main/resources/application-render.properties")
    
    for file in "${required_files[@]}"; do
        if [ ! -f "$file" ]; then
            print_error "Archivo requerido no encontrado: $file"
            exit 1
        else
            print_message "✓ $file encontrado"
        fi
    done
}

# Construir y probar localmente
test_local() {
    print_step "Probando construcción local..."
    
    print_message "Construyendo imagen Docker..."
    docker build -t psicovida-aplicacion:test .
    
    if [ $? -eq 0 ]; then
        print_message "✓ Imagen construida exitosamente"
    else
        print_error "✗ Error al construir la imagen"
        exit 1
    fi
}

# Verificar configuración de Git
check_git_config() {
    print_step "Verificando configuración de Git..."
    
    if [ -z "$(git config user.name)" ] || [ -z "$(git config user.email)" ]; then
        print_warning "Git no está configurado completamente"
        print_message "Configura Git con:"
        echo "git config --global user.name 'Tu Nombre'"
        echo "git config --global user.email 'tu-email@ejemplo.com'"
    else
        print_message "✓ Git configurado correctamente"
    fi
}

# Verificar estado del repositorio
check_repo_status() {
    print_step "Verificando estado del repositorio..."
    
    if [ -n "$(git status --porcelain)" ]; then
        print_warning "Hay cambios sin commitear"
        print_message "Archivos modificados:"
        git status --short
        
        read -p "¿Quieres hacer commit de estos cambios? (y/n): " -n 1 -r
        echo
        if [[ $REPLY =~ ^[Yy]$ ]]; then
            git add .
            git commit -m "Preparando despliegue en Render"
            print_message "✓ Cambios commiteados"
        fi
    else
        print_message "✓ Repositorio limpio"
    fi
}

# Verificar remoto
check_remote() {
    print_step "Verificando remoto Git..."
    
    if [ -z "$(git remote -v)" ]; then
        print_error "No hay remoto configurado"
        print_message "Agrega un remoto con:"
        echo "git remote add origin https://github.com/tu-usuario/tu-repo.git"
        exit 1
    else
        print_message "✓ Remoto configurado"
        git remote -v
    fi
}

# Push al repositorio
push_to_repo() {
    print_step "Subiendo cambios al repositorio..."
    
    print_message "Haciendo push al repositorio..."
    git push origin main
    
    if [ $? -eq 0 ]; then
        print_message "✓ Cambios subidos exitosamente"
    else
        print_error "✗ Error al subir cambios"
        exit 1
    fi
}

# Mostrar instrucciones finales
show_final_instructions() {
    print_step "Instrucciones para completar el despliegue en Render:"
    echo
    echo "1. Ve a https://render.com"
    echo "2. Crea una cuenta o inicia sesión"
    echo "3. Haz clic en 'New +' → 'Web Service'"
    echo "4. Conecta tu repositorio Git"
    echo "5. Configura el servicio:"
    echo "   - Name: psicovida-aplicacion"
    echo "   - Environment: Docker"
    echo "   - Region: Oregon (o la más cercana)"
    echo "   - Build Command: docker build -t psicovida-aplicacion ."
    echo "   - Start Command: java \$JAVA_OPTS -jar app.jar"
    echo "   - Health Check Path: /actuator/health"
    echo
    echo "6. Configura las variables de entorno:"
    echo "   - SPRING_PROFILES_ACTIVE=render"
    echo "   - JAVA_OPTS=-Xmx512m -Xms256m"
    echo "   - DATABASE_URL=tu-url-de-base-de-datos"
    echo "   - DATABASE_USERNAME=tu-usuario"
    echo "   - DATABASE_PASSWORD=tu-password"
    echo
    echo "7. Haz clic en 'Create Web Service'"
    echo
    print_message "¡Tu aplicación estará disponible en https://tu-app.onrender.com!"
}

# Función principal
main() {
    print_message "🚀 Iniciando preparación para despliegue en Render..."
    echo
    
    # Verificaciones
    check_git
    check_files
    check_git_config
    check_repo_status
    check_remote
    
    # Pruebas
    test_local
    
    # Push
    push_to_repo
    
    # Instrucciones finales
    show_final_instructions
    
    print_message "✅ Preparación completada exitosamente!"
}

# Ejecutar función principal
main "$@" 