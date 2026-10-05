# Semana 7: Entity y repository (JPA)

**Caso:** Inven-Track, API REST para controlar el inventario de una salsamentaria. Recurso: `Product`.

## Archivos

- `src/main/java/com/inventrack/entity/Product.java`: la entity.
- `src/main/java/com/inventrack/repository/ProductRepository.java`: el repository.

## Entity

`@Entity` marca la clase como entidad JPA, `@Table(name = "products")` la mapea a la tabla `products` y `@Id` con `@GeneratedValue` define `id` como clave primaria autogenerada. Cada atributo es una columna y cada objeto `Product` es una fila. Las anotaciones `@NotBlank`, `@NotNull`, `@Positive` y `@Min` validan los datos antes de guardarlos.

## Repository y consulta por método

`ProductRepository` extiende `JpaRepository<Product, Long>` (entidad y tipo de su id), así que Spring genera la implementación sin escribir SQL. La consulta `findByNameContainingIgnoreCase(String name)` busca productos cuyo nombre contenga el texto, sin distinguir mayúsculas: `findBy` busca, `Name` es el campo, `Containing` equivale a `LIKE %texto%` e `IgnoreCase` ignora mayúsculas.

## Operaciones CRUD

| Operación     | Método                                 | Para qué se usa                                                               |
| ------------- | -------------------------------------- | ----------------------------------------------------------------------------- |
| Create        | `save(product)`                        | Registrar un producto nuevo (INSERT).                                         |
| Read (todos)  | `findAll()`                            | Listar el inventario.                                                         |
| Read (uno)    | `findById(id)`                         | Consultar un producto; devuelve `Optional`, así se responde 404 si no existe. |
| Read (buscar) | `findByNameContainingIgnoreCase(name)` | Buscar por parte del nombre.                                                  |
| Update        | `save(product)`                        | Con un `id` existente actualiza precio o stock (UPDATE).                      |
| Delete        | `delete(product)` / `deleteById(id)`   | Quitar del catálogo un producto que ya no se vende.                           |
