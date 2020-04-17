package cl.atromilen.tests.restapi.service;

import cl.atromilen.tests.restapi.errorhandler.ComunasNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;

/**
 * @author Álvaro Tromilen
 *
 * Estas son pruebas de integración para testear el consumo de la API del Minsal para obtener comunas.
 * No considerar como pruebas unitarias (dependen de una conexión http con la URL).
 */
@ExtendWith(SpringExtension.class)
class ComunaServiceImplTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ComunaServiceImpl.class);
    private final static String URL = "https://midastest.minsal.cl/farmacias/maps/index.php/utilidades/maps_obtener_comunas_por_regiones";

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ComunaService comunaService = new ComunaServiceImpl();

    @Test
    void integtrationTestConsultaDeComunasAlMinsalExitoso() {
        String expected = "<option>VALOR DE PRUEBA</option>";
        when(restTemplate.postForEntity(URL, getRequest(), String.class))
                .thenReturn(new ResponseEntity<>(expected, HttpStatus.OK));

        String comunas = comunaService.getRMComunasAsCombobox();
        LOGGER.debug("comunas: {}", comunas);

        Assertions.assertEquals(expected, comunas);

    }

    @Test
    void integrationTestConsultaDeComunasAlMinsalRetornoConErrorEnHttpStatus() {
        when(restTemplate.postForEntity(URL, getRequest(), String.class))
                .thenReturn(new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));

        Assertions.assertThrows(ComunasNotFoundException.class, () -> comunaService.getRMComunasAsCombobox());

    }

    private HttpEntity<MultiValueMap<String, Integer>> getRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Integer> params = new LinkedMultiValueMap<>();
        params.add("reg_id", 7);
        return new HttpEntity<>(params, headers);
    }

}