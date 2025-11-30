## DOCUMENTACIÓN DE LA API STOCK - MÓDULOS DE USUARIOS Y PRODUCTOS

 Todos los endpoints deben ser prefijados por /api/.

---
SECCIÓN 1: AUTENTICACIÓN Y AUTORIZACIÓN (Base: /api/auth)
---

Estos endpoints son públicos y no requieren un Token JWT.

| Método | Path | Descripción | Seguridad |
|--------|------|-------------|-----------|
| POST   | /api/auth/registro | Crea un nuevo usuario en el sistema. Por defecto, asigna el rol USUARIO. | Pública |
| POST   | /api/auth/login    | Permite el inicio de sesión. Devuelve un Token JWT. | Pública |

## Detalle del POST /api/auth/login

Cuerpo de la Solicitud (JSON):
```
{
"email": "usuario@ejemplo.com",
"password": "UnaPasswordSegura!"
}
```
Respuesta Exitosa (200 OK):
```
{
"token": "eyJhbGciOiJIUzI1NiI..."
}
```

### Uso del Token JWT

El token debe incluirse en la cabecera (Header) de todas las peticiones protegidas:

* Nombre de la Cabecera: Authorization
* Valor: Bearer [TOKEN_JWT]

---
SECCIÓN 2: GESTIÓN DE USUARIOS (Base: /api/usuarios)
---

Estos endpoints requieren que el usuario esté autenticado. Restricciones por rol (ADMIN) o propiedad.

### Listado y Búsqueda

| Método | Path | Descripción | Rol Requerido |
|--------|------|-------------|---------------|
| GET    | /api/usuarios | Lista todos los usuarios (activos e inactivos). | ADMIN |
| GET    | /api/usuarios/activos | Lista solo los usuarios activos. | ADMIN |
| GET    | /api/usuarios/inactivos | Lista solo los usuarios inactivos. | ADMIN |
| GET    | /api/usuarios/rol/{rol} | Lista usuarios por su rol (ej: /rol/ADMIN). | ADMIN |
| GET    | /api/usuarios/{id} | Obtiene detalles de un usuario por su ID. | ADMIN o Propietario |

## Creación y Modificación

| Método | Path | Descripción | Rol Requerido |
|--------|------|-------------|---------------|
| PUT    | /api/usuarios/{id} | Actualiza completamente los datos de un usuario. | ADMIN o Propietario |
| PATCH  | /api/usuarios/desactivar/{id} | Marca un usuario como inactivo. | ADMIN |
| PATCH  | /api/usuarios/activar/{id} | Marca un usuario como activo. | ADMIN |

## Detalle del PUT /api/usuarios/{id}

Cuerpo de la Solicitud (JSON):
```
{
"nombre": "Nombre Actualizado",
"apellido": "Apellido Actualizado",
"email": "nuevo.email@ejemplo.com",
"password": "NuevaPassword123",  
"rol": "USUARIO",               
"direccion": "Nueva Dirección",
"dni": "98765432X",
"telefono": "555987654"
}
```
--- 
SECCIÓN 3: GESTIÓN DE PRODUCTOS (Base: /api/productos)
---

Acceso segmentado por rol: USUARIO (común) solo tiene acceso a GET. CRUD reservado para ADMIN y GESTOR.

### Listado y Búsqueda (Acceso Común)

| Método | Path | Descripción | Rol Requerido |
|--------|------|-------------|---------------|
| GET    | /api/productos | Lista todos los productos (activos e inactivos). | Autenticado |
| GET    | /api/productos/activos | Lista solo los productos activos. | Autenticado |
| GET    | /api/productos/inactivos | Lista solo los productos inactivos. | Autenticado |
| GET    | /api/productos/{id} | Obtiene detalles de un producto por su ID. | Autenticado |
| GET    | /api/productos/buscar/nombre?nombre={query} | Busca productos por nombre. | Autenticado |
| GET    | /api/productos/buscar/marca?marca={query} | Busca productos por marca. | Autenticado |

## Operaciones CRUD (Acceso de Gestión)

| Método | Path | Descripción | Rol Requerido |
|--------|------|-------------|---------------|
| POST   | /api/productos | Crea un nuevo producto. | ADMIN, GESTOR |
| PUT    | /api/productos/{id} | Actualiza completamente un producto. | ADMIN, GESTOR |
| DELETE | /api/productos/{id} | Desactiva (soft delete) un producto. | ADMIN, GESTOR |

### Detalle de Creación y Actualización (POST / PUT)
Cuerpo de la Solicitud (JSON) - ProductoRequestDTO:

```
{
"nombre": "Monitor LED 27 pulgadas",
"descripcion": "Monitor 144Hz para gaming.",
"precio": 350.50,
"stock": 50,
"marca": "AOC",
"codigo": "MON27AOC001"
}
```