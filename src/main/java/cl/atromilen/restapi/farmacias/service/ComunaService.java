package cl.atromilen.restapi.farmacias.service;

/**
 * @author Álvaro Tromilen (alvaro.tromilen@gmail.com)
 *
 * Contrato para traer comunas como Combobox o HTML Select. El cómo obtener esto queda a criterio de la
 * implementación específica.
 */
public interface ComunaService {
    String getRMComunasAsCombobox();
}
