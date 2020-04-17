# farmacias-api-rest
>farmacias-api-rest es una API RESTful que permite obtener farmacias de una comuna de la Región Metropolitana (Chile). Para
>esto, utiliza como fuente de datos la API Rest del Minsal mediante _**RestTemplate**_.
>
#### Versiones rama master
| Version | Autor|
|---------|------|
|1.0.0    |Álvaro Tromilen |

#### Tecnologías
* Java 8
* Spring Boot 2 (Spring MVC, RestTemplate)
* JUnit 5
* Mockito
* Maven

#### Instalación
1. Clonar proyecto en tu PC.
2. Abrir una terminal o un CMD para ir al directorio raíz del proyecto clonado. A continuación, ejecutar el archivo 
mvnw mediante el comando de Spring boot. Ejemplo:
```bash
$ cd /ruta/del/proyecto/recien/clonado
$ ./mvnw spring-boot:run
```

Espera unos segundos y tu aplicación estará corriendo y lista para ser testeada.

#### Probar la API
1. Instalar una aplicación cliente API REST (de preferencia, [Postman](https://www.postman.com/downloads/) 
o [Insomnia](https://insomnia.rest/)).
2. Una vez instalado el cliente de API REST, ejecútalo.
3. Dentro del cliente, importa el archivo *Consorcio-Test.postman_collection.json* que se encuentra en la raíz
del proyecto clonado anteriormente.
4. Una vez importado el archivo, podrás reutilizar las consultas para consumir la API REST.

