package cl.atromilen.tests.restapi.service;

import cl.atromilen.tests.restapi.model.LocalFarmacia;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alvarotromilen on 4/15/20.
 */
//TODO pendiente implementación con RestTemplate para consumir API de Minsal
@Service
public class MinsalFarmaciaService implements FarmaciaService{

    @Override
    public List<LocalFarmacia> getFarmaciaByComuna(String comuna) {
        return null;
    }

    @Override
    public List<LocalFarmacia> getFarmaciaByComunaAndNombreLocal(String comuna, String nombbreLocal) {
        return null;
    }
}
