package cl.atromilen.tests.restapi.errorhandler;

/**
 * @author Álvaro Tromilen (alvaro.tromilen@gmail.com)
 *
 * Excepción que será lanzada cuando no se encuentren farmacias en una consulta.
 */
public class FarmaciaNotFoundException extends RuntimeException {
    private String nombreComuna;
    private String nombreLocal;

    public FarmaciaNotFoundException(String nombreComuna, String nombreLocal) {
        this.nombreComuna = nombreComuna;
        this.nombreLocal = nombreLocal;
    }

    public String getNombreLocal() {
        return nombreLocal;
    }

    public String getNombreComuna() {
        return nombreComuna;
    }
}
