# Inven-Track API

REST API built with Spring Boot, Spring Data JPA and H2 to manage the products of a delicatessen (salsamentaria).

## How to run?

Requirements: Java 21.

```bash
.\mvnw.cmd spring-boot:run
```

- API: http://localhost:8080/api/products
- Swagger UI: http://localhost:8080/swagger-ui.html

## Architecture

- `entity`: the `Product` JPA entity.
- `repository`: data access with Spring Data JPA.
- `service`: business logic and 404 handling.
- `controller`: REST endpoints.

## API reference

The API exposes a single resource, `products`, under the base path `/api/products`. `GET /api/products` returns the list of all registered products with status code 200. `GET /api/products/{id}` returns one product, or a 404 error if the id does not exist. `POST /api/products` creates a new product from a JSON body and responds with 201 Created; if the data is invalid, such as a blank name or a negative price, it responds with 400 Bad Request. `PUT /api/products/{id}` replaces the data of an existing product and returns 200, or 400 for invalid data and 404 if the product does not exist. `DELETE /api/products/{id}` removes the product and returns 204 No Content, or 404 if it does not exist.

Example body for POST and PUT:

```json
{
  "name": "Queso campesino",
  "description": "Bloque 500g",
  "price": 12500,
  "stock": 40
}
```

## Testing

The Postman collection is in `postman/` and includes a 400 and a 404 case.
