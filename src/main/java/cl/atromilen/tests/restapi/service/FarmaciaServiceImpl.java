package cl.atromilen.tests.restapi.service;

import cl.atromilen.tests.restapi.errorhandler.FarmaciaNotFoundException;
import cl.atromilen.tests.restapi.model.LocalFarmacia;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FarmaciaServiceImpl implements FarmaciaService {
    private final static Logger LOGGER = LoggerFactory.getLogger(FarmaciaServiceImpl.class);
    private final static String URL = "https://farmanet.minsal.cl/maps/index.php/ws/getLocalesRegion?id_region=7";

    private final RestTemplate restTemplate;

    public FarmaciaServiceImpl(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    public List<LocalFarmacia> getFarmaciaByComuna(String comuna) {
        LOGGER.info("FarmaciaServiceImpl.getFarmaciaByComuna");
        List<LocalFarmacia> responseList = getFarmaciasFromMinsal(comuna, null);
        LOGGER.info("Lista de farmacias RM contiene {} ojetos. Filtrando por comuna {}...", responseList.size(), comuna);
        List<LocalFarmacia> farmaciaList = responseList.stream()
                .filter(
                        farmacia -> comuna.trim().equalsIgnoreCase(farmacia.getNombreComuna().trim())
                ).collect(Collectors.toList());
        LOGGER.info("Lista de farmacias resultante contiene {} ojetos", farmaciaList.size());

        return farmaciaList;
    }

    @Override
    public List<LocalFarmacia> getFarmaciaByComunaAndNombreLocal(String comuna, String nombreLocal) {
        LOGGER.info("FarmaciaServiceImpl.getFarmaciaByComunaAndNombreLocal");
        List<LocalFarmacia> responseList = getFarmaciasFromMinsal(comuna, nombreLocal);
        LOGGER.info("Lista de farmacias RM contiene {} ojetos. Filtrando por comuna {} y nombre de local {}...",
                responseList.size(), comuna, nombreLocal);
        List<LocalFarmacia> farmaciaList = responseList.stream()
                .filter(
                        farmacia -> comuna.trim().equalsIgnoreCase(farmacia.getNombreComuna().trim()) &&
                                nombreLocal.trim().equalsIgnoreCase(farmacia.getNombreLocal().trim())
                ).collect(Collectors.toList());
        LOGGER.info("Lista de farmacias resultante contiene {} ojetos", farmaciaList.size());

        return farmaciaList;
    }

    private List<LocalFarmacia> getFarmaciasFromMinsal(String comuna, String nombreLocal) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(URL, String.class);

        if(response.getBody() == null || "".equalsIgnoreCase(response.getBody())){
            throw new FarmaciaNotFoundException(comuna, nombreLocal);
        }

        String jsonCleaned = response.getBody().replaceAll("\r\n", "").trim();

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonCleaned, new TypeReference<List<LocalFarmacia>>() {
            });
        } catch (JsonProcessingException e) {
            LOGGER.error("Error en el proceso de conversion JSON a ojeto Java", e);
            return null;
        }
    }

}
