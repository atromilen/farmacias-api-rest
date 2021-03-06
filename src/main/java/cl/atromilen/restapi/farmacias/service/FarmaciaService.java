package cl.atromilen.restapi.farmacias.service;

import cl.atromilen.restapi.farmacias.model.LocalFarmacia;

import java.util.List;

/**
 * @author Álvaro Tromilen (alvaro.tromilen@gmail.com)
 * <p>
 * Contrato para el Service que consumirá la API del MINSAL y realizará la lógica para retornar
 * los registros solicitados por la capa Controller.
 */
public interface FarmaciaService {
    List<LocalFarmacia> getFarmaciaByComuna(String comuna);

    List<LocalFarmacia> getFarmaciaByComunaAndNombreLocal(String comuna, String nombreLocal);
}
