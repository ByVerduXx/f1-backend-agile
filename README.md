# F1 Backend Agile

## Requisitos previos
JDK 17
Maven
Docker

## Configuración inicial
Levantar la base de datos con Docker Compose
Para levantar la base de datos que se utiliza en este proyecto, simplemente navega hasta el directorio raíz del proyecto y ejecuta:
```bash
docker-compose up -d
```
Esto levantará una instancia de **MySQL** que será accesible en localhost:3307.

## Instalar dependencias
Después de clonar el proyecto, entra en el directorio del proyecto desde la terminal y ejecuta:
```bash
mvn clean install
```
Esto descargará todas las dependencias necesarias y empaquetará tu proyecto.

## Ejecución
Una vez que todas las dependencias estén instaladas y la base de datos esté en marcha, puedes iniciar la aplicación con:
```bash
mvn spring-boot:run
```
Esto levantará el servidor Spring Boot, y la aplicación estará accesible en http://localhost:8080/.

## Tests
Para ejecutar los tests, simplemente ejecuta:
```bash
mvn verify
```
Esto ejecutará todos los tests unitarios y de integración.

## Spotless
Para mantener siempre un mismo formato en el código, independientemente de quien lo realice, utilizamos el plugin Spotless.
Puedes comprobar si tu código cumple con los requisitos de spotless mediante:
```bash
mvn spotless:check
```
Si tu código no pasa las validaciones de spotless ejecuta el isguiente comando para formatear el código:
```bash
mvn spotless:apply
```
