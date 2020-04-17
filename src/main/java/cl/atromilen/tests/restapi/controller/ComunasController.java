package cl.atromilen.tests.restapi.controller;

import cl.atromilen.tests.restapi.service.ComunaService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Álvaro Tromilen (alvaro.tromilen@gmail.com)
 *
 * Servicio REST que devolverá un listado de comunas ya formateado como un combobox o html select
 * para su uso directo en alguna aplicación cliente.
 */
@RestController
@RequestMapping("/api-rest")
public class ComunasController {

    private final ComunaService comunaService;

    public ComunasController(ComunaService comunaService) {
        this.comunaService = comunaService;
    }

    @GetMapping(value = "/comunas", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getComunas(){
        String comboboxComunas = comunaService.getRMComunasAsCombobox();

        return ResponseEntity.ok(comboboxComunas);
    }

}
