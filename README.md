# Video Upload API

Una API REST robusta construida con Java Spring Boot para subir y gestionar videos en AWS S3, ideal para plataformas de contenido.

## 🚀 Características

- **Upload de Videos**: Subida segura de videos a AWS S3
- **Preview/Thumbnails**: Soporte para previews de videos o imágenes
- **URLs Firmadas**: Generación automática de URLs seguras para streaming
- **Validación**: Validación completa de archivos y tamaños
- **Autenticación**: Sistema de autenticación básica
- **Documentación**: API documentada con Swagger/OpenAPI
- **CORS**: Configuración lista para aplicaciones web

## 📋 Requisitos Previos

1. **Java 17+**
2. **Maven 3.6+**
3. **Cuenta de AWS** con acceso a S3
4. **Bucket de S3** configurado

## 🛠️ Configuración Inicial

### 1. Configurar AWS S3

1. Crear un bucket en AWS S3
2. Configurar las credenciales de AWS (IAM user con permisos S3)
3. Asegurarse de que el bucket permita el contenido que planeas subir

### 2. Configurar Variables de Entorno

Editar `src/main/resources/application.properties`:

```properties
aws.access-key-id=TU_ACCESS_KEY_ID
aws.secret-access-key=TU_SECRET_ACCESS_KEY
aws.region=us-east-1
aws.s3.bucket-name=tu-bucket-name
```

**O usar variables de entorno:**
```bash
export AWS_ACCESS_KEY_ID=your-access-key
export AWS_SECRET_ACCESS_KEY=your-secret-key
export AWS_REGION=us-east-1
export AWS_S3_BUCKET=your-bucket-name
```

### 3. Permisos IAM Requeridos

Tu usuario de AWS necesita estos permisos mínimos:

```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": [
                "s3:GetObject",
                "s3:PutObject",
                "s3:DeleteObject"
            ],
            "Resource": "arn:aws:s3:::tu-bucket-name/*"
        }
    ]
}
```

## 🏃‍♂️ Ejecutar la Aplicación

```bash
mvn spring-boot:run
```

La API estará disponible en: `http://localhost:8080`

## 📚 Documentación de API

Visita `http://localhost:8080/swagger-ui.html` para ver la documentación interactiva.

### Autenticación

Por defecto:
- **Usuario**: `admin`
- **Contraseña**: `admin123`

## 🔗 Endpoints Principales

### Upload de Video
```bash
POST /api/videos/upload
Content-Type: multipart/form-data

# Parámetros:
# - video: archivo de video (requerido)
# - preview: archivo de preview (opcional)
# - title: título del video (requerido)
# - description: descripción (opcional)
```

### Obtener Video
```bash
GET /api/videos/{id}
```

### Listar Videos
```bash
GET /api/videos
```

### Stream de Video (público)
```bash
GET /api/videos/stream/{id}
```

### Stream de Preview (público)
```bash
GET /api/videos/preview/{id}
```

### Eliminar Video
```bash
DELETE /api/videos/{id}
```

## 📝 Ejemplo de Uso con cURL

```bash
# Upload de video con preview
curl -X POST "http://localhost:8080/api/videos/upload" \
  -u admin:admin123 \
  -F "video=@mi-video.mp4" \
  -F "preview=@preview.jpg" \
  -F "title=Mi Video" \
  -F "description=Descripción del video"

# Obtener información del video
curl -X GET "http://localhost:8080/api/videos/{video-id}" \
  -u admin:admin123

# Acceder al video (público)
curl -X GET "http://localhost:8080/api/videos/stream/{video-id}"
```

## 🔒 Consideraciones de Seguridad

1. **Cambiar credenciales por defecto** en producción
2. **Usar HTTPS** en producción
3. **Configurar CORS** apropiadamente
4. **Validar tipos de archivo** según tus necesidades
5. **Implementar rate limiting** si es necesario
6. **Considerar usar AWS IAM roles** en lugar de access keys

## 📊 Límites de Archivo

- **Videos**: Máximo 500MB
- **Previews**: Máximo 50MB
- **Tipos permitidos**: 
  - Videos: video/*
  - Previews: image/*, video/*

## 🚀 Deploy en Producción

1. **Usar profiles de Spring**: `-Dspring.profiles.active=prod`
2. **Configurar variables de entorno** apropiadas
3. **Usar un servidor de aplicaciones** como Tomcat o desplegar como JAR
4. **Configurar monitoreo y logging**
5. **Usar base de datos** en lugar del almacenamiento en memoria

## 🔧 Personalización

- **Cambiar límites de archivo** en `VideoServiceImpl`
- **Añadir más validaciones** según tus necesidades
- **Implementar base de datos** para persistencia
- **Añadir procesamiento de video** con FFmpeg
- **Integrar CDN** para mejor performance

## ⚠️ Notas Importantes

- Esta implementación usa **almacenamiento en memoria** para simplicidad
- En producción, usar una **base de datos** (MySQL, PostgreSQL, etc.)
- Las **URLs firmadas** expiran en 1 hora por defecto
- Considerar **costos de AWS S3** según tu volumen de datos