package cl.atromilen.restapi.farmacias.service;

import cl.atromilen.restapi.farmacias.exception.FarmaciaNotFoundException;
import cl.atromilen.restapi.farmacias.exception.FarmaciaServiceException;
import cl.atromilen.restapi.farmacias.exception.InternalAPIException;
import cl.atromilen.restapi.farmacias.model.LocalFarmacia;
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
 * Pruebas unitarias y aisladas para no depender de una conexión real con la API del minsal (pruebas
 * de integración), permitiendo probar los distintos flujos de Service para recuperar farmacias de la RM.
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
    void testConsultaDeFarmaciaPorComunaExitoso() {
        this.server.expect(requestTo(URL))
                .andRespond(withSuccess(getFarmaciasJSON(), MediaType.TEXT_HTML));

        List<LocalFarmacia> farmacias = farmaciaService.getFarmaciaByComuna("RECOLETA");
        Assertions.assertNotNull(farmacias);
        LocalFarmacia farmacia = farmacias.get(0);
        LOGGER.debug("farmacia: {}", farmacia);
        Assertions.assertEquals("RECOLETA", farmacia.getNombreComuna().trim());
    }

    @Test
    void testConsultaDeFarmaciaPorComunaYNombreLocalExitoso() {
        this.server.expect(requestTo(URL))
                .andRespond(withSuccess(getFarmaciasJSON(), MediaType.TEXT_HTML));

        List<LocalFarmacia> farmacias = farmaciaService.getFarmaciaByComunaAndNombreLocal("BUIN", "AHUMADA");
        LocalFarmacia farmacia = farmacias.get(0);
        LOGGER.debug("farmacia: {}", farmacia);
        Assertions.assertEquals("BUIN", farmacia.getNombreComuna().trim());
        Assertions.assertEquals("AHUMADA", farmacia.getNombreLocal().trim());
    }

    @Test
    void testConsultaDeFarmaciaPorComunaSinResultadosGeneraNotfoundException() {
        this.server.expect(requestTo(URL))
                .andRespond(withSuccess(getFarmaciasJSON(), MediaType.TEXT_HTML));

        Assertions.assertThrows(FarmaciaNotFoundException.class, () ->
                farmaciaService.getFarmaciaByComuna("COMUNA_NO_EXISTE"));
    }

    @Test
    void testConsultaDeFarmaciaPorComunaYLocalSinResultadosGeneraNotfoundException() {
        this.server.expect(requestTo(URL))
                .andRespond(withSuccess(getFarmaciasJSON(), MediaType.TEXT_HTML));

        Assertions.assertThrows(FarmaciaNotFoundException.class, () ->
                farmaciaService.getFarmaciaByComunaAndNombreLocal("COMUNA_NO_EXISTE", "LOCAL_NO_EXISTE"));
    }

    @Test
    void testConsultaSinResultadosLanzaUnFarmaciaServiceException() {
        this.server.expect(requestTo(URL))
                .andRespond(withSuccess("[]", MediaType.TEXT_HTML));

        Assertions.assertThrows(FarmaciaServiceException.class, () -> farmaciaService.getFarmaciaByComuna("ANYCOMUNA"));
    }

    @Test
    void testConsultaFallidaPorErrorEnMapeoJSON() {
        this.server.expect(requestTo(URL))
                .andRespond(withSuccess("{\"JSON\":\"INVALIDO\"}", MediaType.TEXT_HTML));

        Assertions.assertThrows(InternalAPIException.class, () -> farmaciaService.getFarmaciaByComuna("ANYCOMUNA"));
    }

    private String getFarmaciasJSON() {
        String json =
                "[{\"local_nombre\":\"TORRES\n MPD\",\"comuna_nombre\":\"RECOLETA\",\"local_direccion\":\"AVENIDA EL SALTO\n 2972\"," +
                        "\"local_telefono\":\"+560225053570\",\"local_lat\":\"-33.3996351\",\"local_lng\":\"-70.62894990000001\"}," +
                        "{\"local_nombre\":\"AHUMADA\",\"comuna_nombre\":\"BUIN\",\"local_direccion\":\"SAN\n MARTIN 174\"," +
                        "\"local_telefono\":\"+560226313086\",\"local_lat\":\"-33.732\",\"local_lng\":\"-70.735941\"}]";

        return json.replace("\n", "").replace("\r", "").trim();
    }
}