# Sistema de Control de Stock (Backend)

Este proyecto es el servicio de backend para un sistema de control de stock, desarrollado utilizando Java 17 y Spring Boot. Adopta una arquitectura de capas tradicional (Controladores, Servicios, Repositorios) siguiendo el patron MVC (Modelo-Vista-Controlador en el contexto REST). Implementa autenticacion basada en JWT (JSON Web Tokens) y un sistema de autorizacion por roles, garantizando una alta seguridad y desacoplamiento.

## Tecnologia Utilizada

* Lenguaje: Java 17
* Framework: Spring Boot 3+
* Base de Datos: MySQL
* Persistencia: Spring Data JPA (Hibernate)
* Seguridad: Spring Security
* Autenticación con JWT (JSON Web Tokens)
* Cifrado de contrasenas (BCrypt)
* Construccion: Maven

### DTOs (Data Transfer Objects)
Se emplean DTOs para la transferencia de datos entre la capa de presentacion (Controladores) y la capa de servicio, asi como en las peticiones (request) y respuestas (response).

__Beneficio__: Garantiza el desacoplamiento de la logica de negocio de la estructura de la base de datos (entidades JPA). Esto permite exponer solo los datos necesarios al cliente (ej. omitir la contrasena hasheada) y validar la entrada de datos de forma mas segura.

### Enums (Enumeraciones)
Se utilizan enumeraciones para definir los __Roles de Usuario__ (ADMIN, GESTOR, USUARIO) y las __Categorias de productos__.

__Beneficio__: Aportan seguridad de tipo y legibilidad al codigo. Al limitar los valores posibles a un conjunto fijo, se previene la introduccion de datos inconsistentes o errores de escritura en campos criticos como roles o estados, asegurando la integridad del negocio.

## Roles y Permisos

El sistema utiliza un control de acceso basado en roles (ADMIN, GESTOR, USUARIO).

## Rol
* Productos (CRUD)
* Usuarios (CRUD)

## Rutas Especificas

### ADMIN
* Acceso a /api/productos/activos y /api/productos/inactivos

### GESTOR
* Completo (CRUD)
* Solo lectura (GET)
* Acceso a /api/productos/activos y /api/productos/inactivos

### USUARIO
* Solo lectura (GET)

### PUBLICO

* Acceso a /api/auth/registro y /api/auth/login
* Acceso a /api/productos

---

## Configuracion del Entorno

El proyecto requiere variables de entorno para la conexion a la base de datos y la configuracion de seguridad.

1. Variables de Entorno Requeridas
Crea un archivo de configuracion (tipicamente application.properties o application.yml) y define las siguientes variables:

2. Configuracion de la Base de Datos
Asegurate de que tu servidor MySQL este en ejecucion y que la base de datos definida en SPRING_DATASOURCE_URL (por ejemplo, stock_db) haya sido creada.

* Clonar y Ejecutar el Proyecto

Paso 1: Clonar el Repositorio
Abre tu terminal y clona el repositorio del proyecto:

git clone [URL_DE_TU_REPOSITORIO]
cd [nombre-del-proyecto]

Paso 2: Configurar las Variables de Entorno
Asegurate de que las variables listadas en la seccion anterior esten configuradas en tu entorno de desarrollo o en un archivo de configuracion local de Spring Boot.

Paso 3: Ejecutar la Aplicacion
Usando Maven

---

# Compilar y empaquetar
mvn clean install

# Ejecutar la aplicacion
mvn spring-boot:run

Una vez que la aplicacion inicie, estara disponible en http://localhost:8080 (o el puerto configurado).

## Ver documento API
/docs/api/docs.md