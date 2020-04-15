package cl.atromilen.tests.restapi.rest;

import cl.atromilen.tests.restapi.model.LocalFarmacia;
import cl.atromilen.tests.restapi.service.FarmaciaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by alvarotromilen on 4/15/20.
 */
@RestController
@RequestMapping("/farmacia")
public class FarmaciaController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FarmaciaController.class);

    @Qualifier("mockService")
    @Autowired
    private FarmaciaService farmaciaService;

    @GetMapping("/filtrar-por-comuna")
    public ResponseEntity<List<LocalFarmacia>> consultar(@RequestParam(value = "comuna") String comuna,
                                                         @RequestParam(value = "nombre_local") String nombreLocal){
        LOGGER.info("FarmaciaController.consultar - comuna: {}, nombreLocal: {}", comuna, nombreLocal);
        //TODO agregar valicación de campo comuna not null ni vacío

        if(nombreLocal != null && !"".equalsIgnoreCase(nombreLocal.trim())){
            LOGGER.info("1");
            return ResponseEntity.ok(farmaciaService.getFarmaciaByComunaAndNombreLocal(comuna, nombreLocal));
        } else {
            LOGGER.info("2");
            return ResponseEntity.ok(farmaciaService.getFarmaciaByComuna(comuna));
        }
    }

}
