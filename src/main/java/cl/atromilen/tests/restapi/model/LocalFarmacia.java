package cl.atromilen.tests.restapi.model;

/**
 * Created by alvarotromilen on 4/15/20.
 */
public class LocalFarmacia {
    private String localNombre;
    private String localDireccion;
    private String comunaNombre; //TODO Este campo se debe ocultar en el response JSON
    private Integer localTelefono;
    private Double localLatitud;
    private Double localLongitud;

    public String getLocalNombre() {
        return localNombre;
    }

    public void setLocalNombre(String localNombre) {
        this.localNombre = localNombre;
    }

    public String getLocalDireccion() {
        return localDireccion;
    }

    public void setLocalDireccion(String localDireccion) {
        this.localDireccion = localDireccion;
    }

    public String getComunaNombre() {
        return comunaNombre;
    }

    public void setComunaNombre(String comunaNombre) {
        this.comunaNombre = comunaNombre;
    }

    public Integer getLocalTelefono() {
        return localTelefono;
    }

    public void setLocalTelefono(Integer localTelefono) {
        this.localTelefono = localTelefono;
    }

    public Double getLocalLatitud() {
        return localLatitud;
    }

    public void setLocalLatitud(Double localLatitud) {
        this.localLatitud = localLatitud;
    }

    public Double getLocalLongitud() {
        return localLongitud;
    }

    public void setLocalLongitud(Double localLongitud) {
        this.localLongitud = localLongitud;
    }
}

