package cl.atromilen.restapi.farmacias.exception;

public class FarmaciaNotFoundException extends RuntimeException {
    private String nombreComuna;
    private String nombreLocal;

    public FarmaciaNotFoundException(String nombreComuna) {
        this.nombreComuna = nombreComuna;
    }

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
