package cl.atromilen.tests.restapi.controller;

import cl.atromilen.tests.restapi.model.LocalFarmacia;
import cl.atromilen.tests.restapi.service.FarmaciaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by alvarotromilen on 4/15/20.
 */
@RestController
public class FarmaciaController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FarmaciaController.class);

    private final FarmaciaService farmaciaService;

    public FarmaciaController(FarmaciaService farmaciaService) {
        this.farmaciaService = farmaciaService;
    }

    @GetMapping("/farmacias")
    public ResponseEntity<List<LocalFarmacia>> consultar(@RequestParam(value = "comuna") String comuna,
                                                         @RequestParam(value = "nombre_local", required = false) String nombreLocal){
        LOGGER.info("FarmaciaController.consultar - comuna: {}, nombreLocal: {}", comuna, nombreLocal);
        //TODO agregar valicación de campo comuna not null ni vacío

        if(nombreLocal != null && !"".equalsIgnoreCase(nombreLocal.trim())){
            LOGGER.info("1");
            List<LocalFarmacia> listaFarmacias = farmaciaService.getFarmaciaByComunaAndNombreLocal(comuna, nombreLocal);
            HttpStatus httpStatus = listaFarmacias.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND;

            return new ResponseEntity<>(listaFarmacias, httpStatus);
        } else {
            LOGGER.info("2");
            return ResponseEntity.ok(farmaciaService.getFarmaciaByComuna(comuna));
        }
    }

}
