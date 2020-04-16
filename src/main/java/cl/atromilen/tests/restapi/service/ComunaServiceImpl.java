package cl.atromilen.tests.restapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class ComunaServiceImpl implements ComunaService{
    private final static Logger LOGGER = LoggerFactory.getLogger(ComunaServiceImpl.class);
    private final static String URL = "https://midastest.minsal.cl/farmacias/maps/index.php/utilidades/maps_obtener_comunas_por_regiones";

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public String getRMComunasAsCombobox() {
        LOGGER.info("ComunaServiceImpl.ComunaServiceImpl: buscando comunas de la RM en API del MINSAL...");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        //IMPORTANTE: un POST de tipo multipart/form-data no funcionará sin pasar los parámetros de esta forma
        MultiValueMap<String, Integer> params = new LinkedMultiValueMap<>();
        params.add("reg_id", 7);
        HttpEntity<MultiValueMap<String, Integer>> request = new HttpEntity<>(params, headers);
        LOGGER.info("Request a enviar: {}", request.toString());

        ResponseEntity<String> response = restTemplate.postForEntity(URL, request, String.class);
        LOGGER.info("Response de la API Minsal: {}", response.getBody());
        return response.getStatusCode() == HttpStatus.OK ? response.getBody() : null;
    }
}
