# Challenge Foro Hub API

API RESTful desarrollada en Java con Spring Boot para la gestión de un foro, donde los usuarios pueden crear tópicos, responder, marcar soluciones y más.  
Incluye autenticación JWT y documentación interactiva con Swagger UI.

Este proyecto corresponde al challenge **Practicando Spring Framework: Challenge Foro Hub del programa Oracle Next Education**.

---

## Características principales

- **Gestión de usuarios:** registro, actualización, eliminación y autenticación.
- **Gestión de tópicos:** creación, listado, actualización, eliminación y marcado de soluciones.
- **Gestión de respuestas:** agregar, listar, actualizar y marcar como solución.
- **Autenticación y autorización:** mediante JWT.
- **Documentación interactiva:** con Swagger UI

---

## Tecnologías utilizadas

- Java 17
- Spring Boot 3.3.13
- Spring Security (JWT)
- Spring Data JPA
- PostgreSQL
- SpringDoc OpenAPI (Swagger UI)

---

## Instalación y ejecución

1. **Clona el repositorio:**
   ```sh
   git clone https://github.com/tu-usuario/challenge_foro_hub.git
   cd challenge_foro_hub
   ```

2. **Configura la base de datos**  
   Edita `src/main/resources/application.properties` según tu motor de base de datos.

3. **Compila y ejecuta la aplicación:**
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

4. **Accede a la documentación interactiva:**  
   [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## Autenticación

- Para acceder a los endpoints protegidos, primero debes autenticarte en `/login` y obtener un token JWT.
- Usa el token en el header `Authorization` para las siguientes peticiones:
  ```
  Authorization: Bearer <tu_token_jwt>
  ```

---

## Endpoints principales

- `POST /login` — Autenticación de usuario (devuelve JWT)
- `POST /usuarios` — Registro de usuario
- `GET /usuarios` — Listar usuarios
- `GET /usuarios/{id}` — Detalle de usuario
- `PUT /usuarios/{id}` — Actualizar usuario
- `DELETE /usuarios/{id}` — Eliminar usuario

- `POST /topicos` — Crear tópico
- `GET /topicos` — Listar tópicos
- `GET /topicos/{id}` — Detalle de tópico
- `PUT /topicos/{id}` — Actualizar tópico
- `DELETE /topicos/{id}` — Eliminar tópico

- `POST /respuestas` — Agregar respuesta
- `GET /respuestas` — Listar respuestas
- `GET /respuestas/topico/{topicoId}` — Listar respuestas de un tópico
- `GET /respuestas/usuario/{usuarioId}` — Listar respuestas de un usuario

Consulta la documentación Swagger para ver todos los endpoints y sus detalles.

---

## Seguridad

- Solo usuarios autenticados pueden acceder a la mayoría de los endpoints.
- Los endpoints `/login`, `/v3/api-docs/**` y `/swagger-ui/**` son públicos.

---

## Autor

**Desarrollado por Luis Correa**