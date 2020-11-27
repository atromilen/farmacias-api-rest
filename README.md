# farmacias-api-rest
>farmacias-api-rest es una API RESTful que permite obtener farmacias de una comuna de la Región Metropolitana (Chile). Para
>esto, utiliza como fuente de datos la API Rest del Minsal mediante _**RestTemplate**_.
>
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
3. Dentro del cliente, importa el archivo *farmacias-api-rest.postman_collection.json* que se encuentra en la raíz
del proyecto clonado anteriormente.
4. Una vez importado el archivo, podrás reutilizar las consultas para consumir la API REST.

#### Ejemplos de consultas a la API (Incluídas en archivo farmacias-api-rest.postman_collection.json)

* Listar comunas
> Nota: la API sólo entrega comunas de la Región Metropolitana
```
REQUEST: 
GET /api-rest/comunas

RESPONSE:
HTTP/1.1 200 OK
Content-Type: text/html;charset=UTF-8
<option value='0' selected>Elija Comuna</option><option value='82'>ALHUE</option><option value='83'>BUIN</option><option value='84'>CALERA DE TANGO</option><option value='85'>CERRILLOS</option><option value='86'>CERRO NAVIA</option><option value='87'>COLINA</option><option value='88'>CONCHALI</option><option value='89'>CURACAVI</option><option value='90'>EL BOSQUE</option><option value='91'>EL MONTE</option><option value='92'>ESTACION CENTRAL</option><option value='93'>HUECHURABA</option><option value='94'>INDEPENDENCIA</option><option value='95'>ISLA DE MAIPO</option><option value='96'>LA CISTERNA</option><option value='97'>LA FLORIDA</option><option value='98'>LA GRANJA</option><option value='99'>LA PINTANA</option><option value='100'>LA REINA</option><option value='101'>LAMPA</option><option value='102'>LAS CONDES</option><option value='103'>LO BARNECHEA</option><option value='104'>LO ESPEJO</option><option value='105'>LO PRADO</option><option value='106'>MACUL</option><option value='107'>MAIPU</option><option value='108'>MARIA PINTO</option><option value='109'>MELIPILLA</option><option value='110'>ÑUÑOA</option><option value='111'>PADRE HURTADO</option><option value='112'>PAINE</option><option value='113'>PEDRO AGUIRRE CERDA</option><option value='114'>PEÑAFLOR</option><option value='115'>PEÑALOLEN</option><option value='116'>PIRQUE</option><option value='117'>PROVIDENCIA</option><option value='118'>PUDAHUEL</option><option value='119'>PUENTE ALTO</option><option value='120'>QUILICURA</option><option value='121'>QUINTA NORMAL</option><option value='122'>RECOLETA</option><option value='123'>RENCA</option><option value='124'>SAN BERNARDO</option><option value='125'>SAN JOAQUIN</option><option value='126'>SAN JOSE DE MAIPO</option><option value='127'>SAN MIGUEL</option><option value='128'>SAN PEDRO</option><option value='129'>SAN RAMON</option><option value='130'>SANTIAGO</option><option value='133'>TALAGANTE</option><option value='134'>TIL-TIL</option><option value='135'>VITACURA</option>
```

* Buscar farmacias de turno en comuna Paine
```
REQUEST: 
GET /api-rest/farmacias?comuna=paine

RESPONSE:
HTTP/1.1 200 OK
Content-Type: application/json
[{"local_nombre":"CRUZ VERDE","local_direccion":"GENERAL BAQUEDANO 890, PAINE","local_telefono":"+560228252380","local_lat":"-33.813498","local_lng":"-70.743019"},{"local_nombre":"DR. SIMI","local_direccion":"GENERAL BAQUEDANO 1248. LOCAL 982, PAINE","local_telefono":"+5602","local_lat":"-33.815755","local_lng":"-70.743052"},{"local_nombre":"PLAZA PAINE","local_direccion":"LA CONCEPCION 264, PAINE","local_telefono":"+560228242638","local_lat":"-33.809498","local_lng":"-70.738717"},{"local_nombre":"SALCOBRAND ","local_direccion":"BAQUEDANO 502. INTERIOR CENTRO COMERCIAL, PAINE","local_telefono":"+560227150399","local_lat":"-33.808089","local_lng":"-70.742343"},{"local_nombre":"HUELQUEN","local_direccion":"CAMINO PADRE HURTADO C-2. L344. HUELQUEN, PAINE","local_telefono":"+56","local_lat":"-33.82376","local_lng":"-70.644629"},{"local_nombre":"LA BOTICA PANDA","local_direccion":"AVENIDA GENERAL BAQUEDANO 954, PAINE","local_telefono":"+56","local_lat":"","local_lng":""}]
```

* Buscar farmacia "TORRES MPD" en Recoleta
```
REQUEST: 
GET /api-rest/farmacias?comuna=recoleta&nombre_local=torres%20mpd

RESPONSE:
HTTP/1.1 200 OK
Content-Type: application/json
[{"local_nombre":"TORRES MPD","local_direccion":"AVENIDA EL SALTO 2972, RECOLETA","local_telefono":"+560225053570","local_lat":"-33.3996351","local_lng":"-70.62894990000001"}]
```

* Farmacia no encontrada (404 Not Found)
```
REQUEST: 
GET /api-rest/farmacias?comuna=recoleta&nombre_local=inexistente

RESPONSE:
HTTP/1.1 404 Not Found
Content-Type: application/json
{"code":3101,"message":"Consulta por comuna 'recoleta' y nombre de local 'inexistente' sin resultados"}
```
