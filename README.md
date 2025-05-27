# library-db-memory-api

API para la gestión de prestamos en una biblioteca con base de datos H2 (en memoria) para Ceiba.

> <p>#jpa #java #spring-boot #h2</p>

---

## Tabla de Contenido

- [Descripción](#descripción)
- [Construido con](#construido-con)
- [Desarrollo](#desarrollo)
- [Endpoints](#endpoints)
- [Requerimiento](#Requerimiento)
- [Autor y contacto](#autor-y-contacto)

---

## Descripción

API que permite la gestión de prestamos en una biblioteca con una base de datos H2 en memoria (temporal) para la empresa Ceiba.

- En este readme se encuentra el requerimiento completo que se desarrollo para está API.

---

## Construido con

El código se encuentra implementado con Java 1.8 y Gradle 6.8.3 usando las siguientes librerías:

- Lombok - Para simplificar la creación de los medios de acceso a datos de un objeto
- Spring Boot - Framework de Java
- H2 - sistema de gestión de bases de datos relacionales (SGBDR) en memoria
- Log4j - Para escribir mensajes de registro
- Swagger - Para la documentación técnica del código

---

## Desarrollo
1. Abrir el readme: [Instrucciones_Instalacion_Ejecucion_ShoppingCart_Indra.pdf](https://github.com/JonEchev/shopping-cart-api/blob/main/deliverables/Instrucciones_Instalacion_Ejecucion_ShoppingCart_Indra.pdf)
2. Instale Java 1.8 y un IDE de desarrollo como IntelliJ.
3. Clone este repositorio desde GitHub: https://github.com/JonEchev/shopping-cart-api rama: main.
4. Permita la construcción del proyecto con gradle.
5. Inicialice el proyecto, ejecutando la clase: BibliotecaApplication.java
6. La documentación Swagger del backend se encuentra en la siguiente ruta: http://localhost:80/shopping/swagger-ui/index.html

Para ejecutar las pruebas unitarias:
1. Ejecute el proyecto desde test en gradle.

---

## Endpoints

- **LOCAL**
    - API (GET): http://localhost:80/prestamo/1
    - API (POST): http://localhost:80/prestamo

---

## Requerimiento

### Problema de negocio
El sistema busca automatizar el comportamiento de un bibliotecario cuando un usuario
desea prestar un libro.

Un préstamo es representado en nuestro negocio por los siguientes atributos

**isbn**: identificador único de un libro (campo alfanumérico de máximo 10 dígitos)  
**identificacionUsuario**: número de la identificación del usuario (campo alfanumérico de maximo 10 digitos)  
**tipoUsuario**: determina la relación que tiene el usuario con la biblioteca, corresponde a un campo que puede tener solo alguno de los siguientes dígitos numérico  
1. usuario afilado
2. usuario empleado de la biblioteca
3. usuario invitado

### Objetivo
Crear una API tipo REST la cual permita llevar a cabo las siguientes funcionalidades
1. El Path debe ser `/prestamo`  y el método HTTP tipo **POST**: permite crear un prestamo con las siguientes validaciones
    1. Un usuario invitado solo puede tener un libro prestado en la biblioteca, si un usuario invitado intenta prestar más de un libro debería retornar un error HTTP 400 con el siguiente json.  
       **Para verificar si un usuario ya tiene un préstamo se debe usar el campo _identificacionUsuario_**
        ```json
            {
             "mensaje" : "El usuario con identificación xxxxxx ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo"
            }
        ```       
       Donde **xxxxxx** corresponde a la identificación del usuario que intenta hacer el prestamo
    2. Al momento de realizar el préstamo se debe hacer el cálculo de la fecha máxima de devolución del libro, con la siguiente reglas de negocio
        1. Si el préstamo lo hace un usuario tipo **afiliado** la fecha de devolución debería ser la fecha actual más 10 días sin contar sábados y domingos
        2. Si el préstamo lo hace un usuario tipo **empleado** la fecha de devolución debería ser la fecha actual más 8 días sin contar sábados y domingos
        3. Si el préstamo lo hace un usuario tipo **invitado** la fecha de devolución debería ser la fecha actual más 7 días sin contar sábados y domingos  
        **Esta fecha deberá ser almacenada en la base de datos junto con toda la información del préstamo**
   3. Si en el campo **tipoUsuario** llega un valor diferente a los permitidos, deberá retornar un un error HTTP 400 con el siguiente JSON
        ```json
            {
              "mensaje" : "Tipo de usuario no permitido en la biblioteca"
            }
        ```
   **Ejemplo de petición y respuesta exitosa**  
   Petición  path: `/prestamo` método: **POST**
   ```json
    {
        "isbn":"DASD154212",
        "identificaciónUsuario":"154515485",
        "tipoUsuario":1
    }
    ```
   **Respuesta exitosa**
    ```json
        {
            "id": 1,
            "fechaMaximaDevolucion" : "15/02/2021"
        }
    ```
2. El path debe ser `/prestamo/{id-prestamo}` y el método HTTP tipo **GET**, donde la variable  {id-prestamo} corresponde al identificador con el cual se almacenó el préstamo en la base de datos, explicado en el primer punto.
   El siguiente es un ejemplo de petición y un ejemplo de cómo debería ser la respuesta en un caso exitoso  
   Petición  path: `/prestamo/1` método: **GET**
   ```json
        {
            "id": 1,	
            "isbn":"DASD154212",
            "identificaciónUsuario":"154515485",
            "tipoUsuario":1,
             "fechaMaximaDevolucion" : "15/02/2021"
        }
    ```
### Restricciones técnicas
1. La base de datos debe ser en memoria, en el archivo application.properties ya se encuentra la configuración la cual está soportada por el motor H2, si necesita modificar estos archivos o algo de la conexión asegúrese de que sea una base de datos en memoria.
    1. Si necesita ejecutar sentencias DDL(crear tablas, modificar tablas...) antes de que la aplicación se ejecute, debe crear un archivo llamado _schema.sql_ en la carpeta _src/main/resources_ y spring automáticamente lo ejecutará

### Archivos de solo lectura:
Estos archivos no podrán ser modificados:
- _src/test/java/com/ceiba/biblioteca/PrestamoTests_: en esta clase se encuentran las pruebas automatizadas encargadas de calificar la prueba, usted podrá ir ejecutando estas pruebas para ir verificando el avance y completitud de la solución. Pero no podrá modificar nada de esta clase.

---

## Autor y contacto

- **Nombre**: Jonatan Echeverry
- **Correo electrónico**: jonechev1@gmail.com
- **GitHub**: [@JonEchev](https://github.com/JonEchev)
- **LinkedIn**: [jonatan-echeverry](https://www.linkedin.com/in/jonatan-echeverry-7130251a0/)
- **Blog:** [Crazy Genius!](https://crazycuestionct.blogspot.com/search/label/Programaci%C3%B3n)
