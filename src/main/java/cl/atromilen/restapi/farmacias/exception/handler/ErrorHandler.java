package cl.atromilen.restapi.farmacias.exception.handler;

import cl.atromilen.restapi.farmacias.exception.FarmaciaNotFoundException;
import cl.atromilen.restapi.farmacias.exception.FarmaciaServiceException;
import cl.atromilen.restapi.farmacias.exception.InternalAPIException;
import cl.atromilen.restapi.farmacias.exception.ComunaServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Álvaro Tromilen (alvaro.tromilen@gmail.com)
 *
 * Catálogo de errores y el HTTP Status a devolver, según la causa de estos, pensado como una forma
 * limpia de hacer esto sin recurrir a sobrecargar de código la capa Controller y sin recurrir a AOP.
 */
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(FarmaciaServiceException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public @ResponseBody Error farmaciaServiceException(){
        return new Error(3100, "Servicio de búsqueda de farmacias no disponibles en este momento. Por favor, intente más tarde.");
    }

    @ExceptionHandler(FarmaciaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody Error farmaciaNotFound(FarmaciaNotFoundException e){
        String msg;

        if(e.getNombreLocal() != null && !"".equalsIgnoreCase(e.getNombreLocal())){
            msg = String.format("Consulta por comuna '%s' y nombre de local '%s' sin resultados",
                    e.getNombreComuna(), e.getNombreLocal());
        } else {
            msg = String.format("Consulta por comuna '%s' sin resultados", e.getNombreComuna());
        }

        return new Error(3101, msg);
    }

    @ExceptionHandler(ComunaServiceException.class)
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    public @ResponseBody Error comunasNotFound(){
        return new Error(3102, "Comunas no disponibles en este momento. Por favor, intente más tarde.");
    }

    @ExceptionHandler(InternalAPIException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody Error internalApiException(){
        return new Error(3103, "Error inesperado en el servidor. Por favor, intente más tarde");
    }

}
