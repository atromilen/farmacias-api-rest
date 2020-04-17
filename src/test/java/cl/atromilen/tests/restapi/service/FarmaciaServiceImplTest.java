package cl.atromilen.tests.restapi.service;

import cl.atromilen.tests.restapi.model.LocalFarmacia;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author Álvaro Tromilen
 *
 * Estas son pruebas de integración para testear el consumo de la API del Minsal para obtener farmacias.
 * No considerar como pruebas unitarias (dependen de una conexión http con la URL).
 */
@ExtendWith(SpringExtension.class)
@RestClientTest(FarmaciaServiceImpl.class)
class FarmaciaServiceImplTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(FarmaciaServiceImplTest.class);
    private final static String URL = "https://farmanet.minsal.cl/maps/index.php/ws/getLocalesRegion?id_region=7";

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private FarmaciaService farmaciaService;

    @Test
    void integrationTestConsultaDeFarmaciaPorComunaExitoso() {
        String expected = getFarmaciasJSON();
        this.server.expect(requestTo(URL))
                .andRespond(withSuccess(expected, MediaType.TEXT_HTML));

        List<LocalFarmacia> farmacias = farmaciaService.getFarmaciaByComuna("RECOLETA");
        Assertions.assertNotNull(farmacias);
        LocalFarmacia farmacia = farmacias.get(0);
        LOGGER.debug("farmacia: {}", farmacia);
        Assertions.assertEquals("RECOLETA", farmacia.getNombreComuna().trim());
    }

    @Test
    void integrationTestConsultaDeFarmaciaPorComunaYNombreLocalExitoso() {
        List<LocalFarmacia> farmacias = farmaciaService.getFarmaciaByComunaAndNombreLocal("BUIN", "AHUMADA");
        LocalFarmacia farmacia = farmacias.get(1);
        LOGGER.debug("farmacia: {}", farmacia);
        Assertions.assertEquals("BUIN", farmacia.getNombreComuna().trim());
        Assertions.assertEquals("AHUMADA", farmacia.getNombreLocal().trim());
    }

    private String getFarmaciasJSON() {
        return
                "[{\"fecha\":\"15-04-2020\",\"local_id\":\"534\",\"local_nombre\":\"TORRES\n MPD\"," +
                        "\"comuna_nombre\":\"RECOLETA\",\"localidad_nombre\":\"RECOLETA\"," +
                        "\"local_direccion\":\"AVENIDA EL SALTO\n 2972\"," +
                        "\"funcionamiento_hora_apertura\":\"10:30 hrs.\"," +
                        "\"funcionamiento_hora_cierre\":\"19:30\n hrs.\",\"local_telefono\":\"+560225053570\"," +
                        "\"local_lat\":\"-33.3996351\",\"local_lng\":\"-70.62894990000001\"," +
                        "\"funcionamiento_dia\":\"miercoles\",\"fk_region\":\"7\",\"fk_comuna\":\"122\"}" +
                        ",{\"fecha\":\"15-04-2020\",\"local_id\":\"753\",\"local_nombre\":\"AHUMADA\",\"comuna_nombre\":\"BUIN\"," +
                        "\"localidad_nombre\":\"BUIN\",\"local_direccion\":\"SAN\n MARTIN 174\"," +
                        "\"funcionamiento_hora_apertura\":\"08:30 hrs.\",\"funcionamiento_hora_cierre\":\"22:00\n hrs.\"," +
                        "\"local_telefono\":\"+560226313086\",\"local_lat\":\"-33.732\",\"local_lng\":\"-70.735941\"," +
                        "\"funcionamiento_dia\":\"miercoles\",\"fk_region\":\"7\",\"fk_comuna\":\"83\"}]";
    }
}