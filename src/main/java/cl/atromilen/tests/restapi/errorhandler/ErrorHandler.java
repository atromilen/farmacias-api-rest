package cl.atromilen.tests.restapi.errorhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Álvaro Tromilen (alvaro.tromilen@gmail.com)
 *
 * Handler para la manipulaciópn de errores manejados por la aplicación. Útil para aplicaciones que van escalando
 * con el tiempo (quizás en este pequeño ejemplo no era tan necesario).
 */
@ControllerAdvice
public class ErrorHandler {

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

        return new Error(404, msg);
    }

    @ExceptionHandler(ComunasNotFoundException.class)
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    public @ResponseBody Error comunasNotFound(){

        return new Error(504, "Comunas no disponibles en este momento. Por favor, intente más tarde.");
    }

}
