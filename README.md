# Couchbase Blogs

Esta es una aplicación SpringBoot que usa Couchbase como su base de datos. Está implementada de acuerdo a las
especificaciones requeridas en la descripción del trabajo práctico final para la materia Base de Datos 2.

## Prerequisitos

- Java 17
- Maven
- Couchbase Server (local o remoto)

## Setup

1. **Clonar el repositorio:**
    ```bash
    git clone https://github.com/Vainillas/couchbaseblogs.git
    cd couchbaseblogs
    ```

2. **Desplegar el servidor Couchbase:**
    - Desplegar couchbase-compose.yaml (levanta por defecto en localhost:8091)
    ```bash
    docker-compose -f Docker/couchbase-compose.yaml up -d
    ```
    - Iniciar sesión con las credenciales por defecto (`Administrator` y `password`).
    - Crear un bucket llamado `couchbase-blogs`.
    - Crear las colecciones `pages` y `posts`.

3. **Configurar la conexión con Couchbase (En el caso de que no se despliegue con el compose)**
    - Abrir `src/main/java/ar/edu/unrn/couchbaseblogs/config/CouchBaseConfig.java`.
    - Verificar que los datos correspondan con la implementación local o remota de Couchbase

   ```java
   @Override
   public String getConnectionString() {
       return "couchbase://127.0.0.1";
   }

   @Override
   public String getUserName() {
       return "Administrator";
   }

   @Override
   public String getPassword() {
       return "password";
   }

   @Override
   public String getBucketName() {
       return "couchbase-blogs";
   }
   ```

4. **Construir la aplicación:**
   ```bash
   mvn clean install
   ```

## Ejecutar la aplicación


#### OPCIONAL: En el archivo CouchbaseblogsApplication.java, es posible descomentar los métodos insertPage e insertPost para generar datos de prueba. Es importante comentarlos nuevamente después de haberlos generado.
```java
@Bean
  public CommandLineRunner commandLineRunner() {
    return args -> {
       insertPage();
       insertPost();
    };
  }
```


1. **Iniciar la aplicación: (por defecto en localhost:8080)**
   ```bash
   mvn spring-boot:run
2. **Verificar el funcionamiento de la API con [swagger](http://localhost:8080/swagger-ui/index.html)**
    