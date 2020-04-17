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

#### Algunos posibles escenarios de consultas (pueden verificarse con Postman o Insomnia)

* Consulta de farmacias por comuna con éxito: farmacias encontradas
```
REQUEST: 
GET /api-rest/farmacias?comuna=paine HTTP/1.1

RESPONSE:
HTTP/1.1 200 OK
Content-Type: application/json
[{"local_nombre":"CRUZ VERDE","local_direccion":"GENERAL BAQUEDANO 890, PAINE","local_telefono":"+560228252380","local_lat":"-33.813498","local_lng":"-70.743019"},{"local_nombre":"DR. SIMI","local_direccion":"GENERAL BAQUEDANO 1248. LOCAL 982, PAINE","local_telefono":"+5602","local_lat":"-33.815755","local_lng":"-70.743052"},{"local_nombre":"PLAZA PAINE","local_direccion":"LA CONCEPCION 264, PAINE","local_telefono":"+560228242638","local_lat":"-33.809498","local_lng":"-70.738717"},{"local_nombre":"SALCOBRAND ","local_direccion":"BAQUEDANO 502. INTERIOR CENTRO COMERCIAL, PAINE","local_telefono":"+560227150399","local_lat":"-33.808089","local_lng":"-70.742343"},{"local_nombre":"HUELQUEN","local_direccion":"CAMINO PADRE HURTADO C-2. L344. HUELQUEN, PAINE","local_telefono":"+56","local_lat":"-33.82376","local_lng":"-70.644629"},{"local_nombre":"LA BOTICA PANDA","local_direccion":"AVENIDA GENERAL BAQUEDANO 954, PAINE","local_telefono":"+56","local_lat":"","local_lng":""}]
```
* Consultar farmacias por comuna y nombre de local, obteniendo resultados

```
REQUEST: 
GET /api-rest/farmacias?comuna=recoleta&nombre_local=torres%20mpd HTTP/1.1

RESPONSE:
HTTP/1.1 200 OK
Content-Type: application/json
[{"local_nombre":"TORRES MPD","local_direccion":"AVENIDA EL SALTO 2972, RECOLETA","local_telefono":"+560225053570","local_lat":"-33.3996351","local_lng":"-70.62894990000001"}]
```

* Consultar farmacias por comuna y nombre de local, sin resultados (ej: nombre de local inexistente)
```
REQUEST: 
GET /api-rest/farmacias?comuna=recoleta&nombre_local=inexistente HTTP/1.1

RESPONSE:
HTTP/1.1 404 Not Found
Content-Type: application/json
{"code":3101,"message":"Consulta por comuna 'recoleta' y nombre de local 'inexistente' sin resultados"}
```
