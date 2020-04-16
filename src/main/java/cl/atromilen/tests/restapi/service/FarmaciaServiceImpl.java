package cl.atromilen.tests.restapi.service;

import cl.atromilen.tests.restapi.model.LocalFarmacia;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Capa de Servicio que consumirá la API del MINSAL y realizará la lógica para retornar
 * los registros solicitados por la capa Controller.
 */
//TODO pendiente implementación con RestTemplate para consumir API de Minsal
@Service
public class FarmaciaServiceImpl implements FarmaciaService{

    @Override
    public List<LocalFarmacia> getFarmaciaByComuna(String comuna) {
        return null;
    }

    @Override
    public List<LocalFarmacia> getFarmaciaByComunaAndNombreLocal(String comuna, String nombbreLocal) {
        return null;
    }
}
