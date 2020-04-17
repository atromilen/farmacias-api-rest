package cl.atromilen.tests.restapi.exception.handler;

/**
 * @author Álvaro Tromilen (alvaro.tromilen@gmail.com)
 *
 * Un ojeto de tipo Error encapsulará todos los posibles errores existentes en las consultas a nuestra API Restful
 * (errores por peticiones mal hechas, registros no encontrados, etc), siendo devuelto en el ojeto ResponseEntity
 * manejado por el Controller.
 */
public class Error {
    private int code;
    private String message;

    public Error(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
