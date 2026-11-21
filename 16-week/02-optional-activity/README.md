# Portafolio · Desarrollo Fullstack (2026-B)

**Estudiante:** Juan Pablo Valverde Barreiro, Jesus Esteban Lopez Charry, Aura cabrera · **GitHub:** [@Pablo2467](https://github.com/Pablo2467)
**Programa:** Ingeniería de Sistemas · **Asignatura:** Desarrollo Fullstack

## 1. Mis entregas

| Corte | Qué hice | Enlace | Evidencia |
|---|---|---|---|
| C1 | Definición del producto Inven-Track y su documentación | [it-docs](https://github.com/Pablo2467/App-Inven-track/tree/main/it-docs) | Ver capturas en la sección 2 |
| C2 | Parcial práctico: API en capas con Spring Boot (StockFlow) | [ENLACE_AL_PARCIAL](ENLACE_AL_PARCIAL) | ![StockFlow](img/c2-stockflow.png) |
| C3 | Proyecto de aula: aplicación fullstack Inven-Track | [App-Inven-track](https://github.com/Pablo2467/App-Inven-track) | Ver sección 2 |

## 2. Proyecto final: Inven-Track

Sistema full stack de inventario por lotes para una salsamentaria: administra productos y proveedores, registra entradas y salidas, y avisa de lo que está por vencer o con poco stock. Las salidas descuentan primero el lote vigente más antiguo (FIFO).

**Repositorio:** https://github.com/Pablo2467/App-Inven-track

**Tecnologías:** Angular 20 · TypeScript · Java 21 · Spring Boot 3.3.4 · Spring Security + JWT · PostgreSQL 16 · Docker Compose · JUnit/Mockito/MockMvc · JaCoCo

**Estructura**

| Carpeta | Contenido |
|---|---|
| `it-api` | API REST, seguridad, servicios y pruebas |
| `it-portal` | Cliente web en Angular |
| `it-db` | Esquema PostgreSQL, datos de demostración y diagrama ER |
| `it-infra` | Docker Compose y configuración |
| `it-docs` | Documentación del proyecto |

**Cómo ejecutarlo** (solo necesitas Docker Desktop y Git):

```powershell
git clone https://github.com/Pablo2467/App-Inven-track.git
cd App-Inven-track\it-infra
Copy-Item .env.example .env
docker compose up -d --build
```

Antes de levantarlo, cambia en `.env` la contraseña de PostgreSQL y el `JWT_SECRET` (mínimo 32 caracteres).

| Qué | Dirección |
|---|---|
| Portal | http://localhost:4200 |
| API | http://localhost:8080 |
| Swagger | http://localhost:8080/swagger-ui.html |

**Usuarios de demostración:** `dueno` (administra todo) y `empleada` (opera el inventario). Las contraseñas de demostración están en la documentación de instalación del repositorio.

**URL en producción:** no aplica. El proyecto se ejecuta en local con Docker.

### Capturas

| Login | Inicio |
|---|---|
| ![Login](https://github.com/Pablo2467/App-Inven-track/raw/main/it-docs/inven-track/assets/login.jpg) | ![Inicio](https://github.com/Pablo2467/App-Inven-track/raw/main/it-docs/inven-track/assets/dashboard.jpg) |

| Productos | Lotes |
|---|---|
| ![Productos](https://github.com/Pablo2467/App-Inven-track/raw/main/it-docs/inven-track/assets/productos.jpg) | ![Lotes](https://github.com/Pablo2467/App-Inven-track/raw/main/it-docs/inven-track/assets/lotes.jpg) |

| Salidas |
|---|
| ![Salidas](https://github.com/Pablo2467/App-Inven-track/raw/main/it-docs/inven-track/assets/salidas.jpg) |

## 3. Reflexión

Este curso me ayudó a entender cómo se construye una aplicación completa y no solo sus partes por separado. Al principio veía la API, el portal y la base de datos como temas aislados; con Inven-Track entendí cómo se conectan: el portal pide datos a la API con un token, la API aplica las reglas de negocio en la capa de servicios y PostgreSQL protege la integridad de los datos. Lo que más me quedó claro fue la seguridad con JWT: antes no sabía por qué una API no necesita guardar sesiones, y ahora puedo explicar qué lleva el token y por qué el rol se valida en el servidor y no solo en la pantalla.

Lo más difícil fue la parte de ejecución y configuración. Tuve problemas con Docker Desktop apagado, con puertos ocupados por otro PostgreSQL de mis otros proyectos y con herramientas que no tenía instaladas, como Maven. Lo resolví leyendo los mensajes de error con calma, revisando qué contenedor estaba corriendo y usando Docker para ejecutar las pruebas sin instalar nada más. De ahí aprendí que un proyecto no está terminado hasta que otra persona puede levantarlo desde cero siguiendo el manual.

Si lo hiciera de nuevo, descontaría el inventario por fecha de vencimiento y no por fecha de ingreso, porque para productos perecederos es más preciso, y agregaría más pruebas del portal, que hoy son pocas.

Mi próximo paso: agregar pruebas de componentes al portal en Angular y desplegar la aplicación en la nube, para practicar el ciclo completo hasta producción.