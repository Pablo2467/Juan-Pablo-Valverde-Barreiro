# Problema 3: Documentar y probar

**Nombre: Juan Pablo Valverde Barreiro**

**Materia: Complementaria III Desarrollo Fullstack**

**Docente: Jesus Ariel Gonzales Bonilla**

## Documentación

Elegí tres endpoints del recurso `members`: `POST /api/members` (crear), `GET /api/members/{id}` (consultar) y `GET /api/members/{id}` con un id inexistente (caso de error).

### Swagger

1. Agrego la dependencia `springdoc-openapi-starter-webmvc-ui` al `pom.xml`.
2. Al ejecutar la API, Swagger UI queda en `http://localhost:8080/swagger-ui.html`. Se genera solo a partir del controller y muestra cada endpoint con su método, URL, parámetros y esquema del JSON.
3. Para que la documentación indique los códigos de respuesta de cada endpoint, agregaría anotaciones en el controller. Por ejemplo, en el `POST`:

```java
@PostMapping
@ResponseStatus(HttpStatus.CREATED)
@Operation(summary = "Register a new member")
@ApiResponse(responseCode = "201", description = "Member created")
@ApiResponse(responseCode = "400", description = "Invalid data")
public Member create(@Valid @RequestBody Member member) { ... }
```

| Endpoint                | Cómo se ve documentado en Swagger                                                                                                     |
| ----------------------- | ------------------------------------------------------------------------------------------------------------------------------------- |
| `POST /api/members`     | Pide un body JSON con `fullName`, `email`, `membershipType` y `startDate`. Respuestas: 201 y 400.                                     |
| `GET /api/members/{id}` | Pide el parámetro `id` en la ruta. Respuestas: 200 y 404.                                                                             |
| `GET /api/members/9999` | Es el mismo endpoint anterior. En Swagger se prueba con "Try it out" escribiendo un id que no existe y se comprueba que responde 404. |

### Postman

| #   | Petición                                                        | Qué compruebo                                 | Esperado          |
| --- | --------------------------------------------------------------- | --------------------------------------------- | ----------------- |
| 1   | `POST http://localhost:8080/api/members`, con Body → raw → JSON | Que crea el socio y devuelve su `id`          | **201 Created**   |
| 2   | `GET http://localhost:8080/api/members/1`                       | Que el socio creado se consulta correctamente | **200 OK**        |
| 3   | `GET http://localhost:8080/api/members/9999`                    | **Caso de error:** el socio no existe         | **404 Not Found** |

Como segundo caso de error, un `POST` con el body inválido de abajo responde **400 Bad Request**, porque `@Valid` rechaza los campos vacíos, el correo mal formado y la fecha nula. La colección exportada está en la carpeta postman ubicado en la razi del proyecto `postman/gym.postman_collection.json`.

### Ejemplo de cuerpo JSON de un POST

Body válido (responde 201):

```json
{
  "fullName": "Laura Gómez",
  "email": "laura.gomez@example.com",
  "membershipType": "MONTHLY",
  "startDate": "2026-10-01"
}
```

Body inválido (responde 400):

```json
{
  "fullName": "",
  "email": "mal",
  "membershipType": "",
  "startDate": null
}
```

### English requirement

The controller receives the HTTP request, validates the input and returns the response with the right status code, but it contains no business logic. The service holds the business logic, for example deciding that a member that does not exist produces a 404 error. The repository is only responsible for data access, so it saves and queries members in the database without making business decisions.
