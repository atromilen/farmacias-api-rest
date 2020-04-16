package cl.atromilen.tests.restapi.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ComunaServiceImplTest {
    private final static String URL = "https://midastest.minsal.cl/farmacias/maps/index.php/utilidades/maps_obtener_comunas_por_regiones";

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ComunaService comunaService = new ComunaServiceImpl();

    @Test
    void consultaDeComunasAlMinsalExitoso() {
        String expected = "<option>VALOR DE PRUEBA</option>";
        when(restTemplate.postForEntity(URL, getRequest(), String.class))
                .thenReturn(new ResponseEntity<>(expected, HttpStatus.OK));

        String comunas = comunaService.getRMComunasAsCombobox();

        Assertions.assertEquals(expected, comunas);

    }

    @Test
    void consultaDeComunasAlMinsalRetornoConErrorEnHttpStatus() {
        when(restTemplate.postForEntity(URL, getRequest(), String.class))
                .thenReturn(new ResponseEntity<>("AnInvalidString", HttpStatus.INTERNAL_SERVER_ERROR));

        String comunas = comunaService.getRMComunasAsCombobox();

        Assertions.assertNull(comunas);

    }

    private HttpEntity<MultiValueMap<String, Integer>> getRequest(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Integer> params = new LinkedMultiValueMap<>();
        params.add("reg_id", 7);
        return new HttpEntity<>(params, headers);
    }

}