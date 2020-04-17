package cl.atromilen.restapi.farmacias.service;

import cl.atromilen.restapi.farmacias.exception.FarmaciaNotFoundException;
import cl.atromilen.restapi.farmacias.exception.FarmaciaServiceException;
import cl.atromilen.restapi.farmacias.exception.InternalAPIException;
import cl.atromilen.restapi.farmacias.model.LocalFarmacia;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
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
        LOGGER.info("FarmaciaServiceImpl.getFarmaciaByComuna: {}", comuna);
        List<LocalFarmacia> responseList = getFarmaciasFromMinsal();

        List<LocalFarmacia> farmaciaList = responseList.stream()
                .filter(
                        farmacia -> comuna.trim().equalsIgnoreCase(farmacia.getNombreComuna().trim())
                ).collect(Collectors.toList());

        if(farmaciaList.isEmpty()){
            throw new FarmaciaNotFoundException(comuna);
        }

        LOGGER.info("Lista de farmacias resultante contiene {} ojetos", farmaciaList.size());

        return farmaciaList;
    }

    @Override
    public List<LocalFarmacia> getFarmaciaByComunaAndNombreLocal(String comuna, String nombreLocal) {
        LOGGER.info("FarmaciaServiceImpl.getFarmaciaByComunaAndNombreLocal: {} - {}", comuna, nombreLocal);
        List<LocalFarmacia> responseList = getFarmaciasFromMinsal();
        List<LocalFarmacia> farmaciaList = responseList.stream()
                .filter(
                        farmacia -> comuna.trim().equalsIgnoreCase(farmacia.getNombreComuna().trim()) &&
                                nombreLocal.trim().equalsIgnoreCase(farmacia.getNombreLocal().trim())
                ).collect(Collectors.toList());

        if(farmaciaList.isEmpty()){
            throw new FarmaciaNotFoundException(comuna, nombreLocal);
        }

        LOGGER.info("Lista de farmacias resultante contiene {} ojetos", farmaciaList.size());

        return farmaciaList;
    }

    private List<LocalFarmacia> getFarmaciasFromMinsal() {
        ResponseEntity<String> response = restTemplate.getForEntity(URL, String.class);
        List<LocalFarmacia> farmaciaList;
        String jsonCleaned = response.getBody().replace("\r", "").replace("\n", "").trim();
        ObjectMapper mapper = new ObjectMapper();
        try {
            farmaciaList = mapper.readValue(jsonCleaned, new TypeReference<List<LocalFarmacia>>() {
            });
        } catch (JsonProcessingException e) {
            LOGGER.error("Error en el proceso de conversion JSON a ojeto Java", e);
            throw new InternalAPIException();
        }

        if(farmaciaList == null || farmaciaList.isEmpty()){
            throw new FarmaciaServiceException();
        }

        return farmaciaList;
    }

}
