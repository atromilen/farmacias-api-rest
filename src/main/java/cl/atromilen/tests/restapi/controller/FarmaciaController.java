package cl.atromilen.tests.restapi.controller;

import cl.atromilen.tests.restapi.model.LocalFarmacia;
import cl.atromilen.tests.restapi.service.FarmaciaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by alvarotromilen on 4/15/20.
 *
 * @author Álvaro Tromilen
 *
 * Controlador REST que manejará las peticiones realizadas a la API RESTful, junto con las respuestas
 * y el manejo de eventuales errores, utilizando (implícitamente) ControllerAdvice.
 */
@RestController
@RequestMapping("/api-rest")
public class FarmaciaController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FarmaciaController.class);

    private final FarmaciaService farmaciaService;

    public FarmaciaController(FarmaciaService farmaciaService) {
        this.farmaciaService = farmaciaService;
    }

    @GetMapping("/farmacias")
    public ResponseEntity<List<LocalFarmacia>> consultar(
            @RequestParam(value = "comuna") String comuna,
            @RequestParam(value = "nombre_local", required = false) String nombreLocal) {
        LOGGER.info("FarmaciaController.consultar - comuna: {}, nombreLocal: {}", comuna, nombreLocal);
        List<LocalFarmacia> listaFarmacias;
        if (nombreLocal != null && !"".equalsIgnoreCase(nombreLocal.trim())) {
            listaFarmacias = farmaciaService.getFarmaciaByComunaAndNombreLocal(comuna, nombreLocal);
        } else {
            listaFarmacias = farmaciaService.getFarmaciaByComuna(comuna);
        }

        return ResponseEntity.ok(listaFarmacias);
    }

}
