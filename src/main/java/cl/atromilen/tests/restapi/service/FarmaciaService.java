package cl.atromilen.tests.restapi.service;

import cl.atromilen.tests.restapi.model.LocalFarmacia;

import java.util.List;

/**
 * Created by alvarotromilen on 4/15/20.
 */
public interface FarmaciaService {
    List<LocalFarmacia> getFarmaciaByComuna(String comuna);
    List<LocalFarmacia> getFarmaciaByComunaAndNombreLocal(String comuna, String nombbreLocal);
}
