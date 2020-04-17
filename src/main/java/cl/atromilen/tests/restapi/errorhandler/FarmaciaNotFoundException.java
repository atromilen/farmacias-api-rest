package cl.atromilen.tests.restapi.errorhandler;

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
