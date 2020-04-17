package cl.atromilen.tests.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Álvaro Tromilen (alvaro.tromilen@gmail.com)
 * <p>
 * Modelo representativo de un objeto de tipo Farmacia. Bidireccional (usado para almacenar respuesta de farmacias
 * de la API del MINSAL como para ser expuestas en nuestra propia API RESTful con el formateo necesario).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocalFarmacia {
    @JsonProperty("local_nombre")
    private String nombreLocal;
    @JsonProperty(value = "local_direccion", access = JsonProperty.Access.WRITE_ONLY)
    private String direccion; //Se unificará su visualización mediante @JsonProperty
    @JsonProperty(value = "comuna_nombre", access = JsonProperty.Access.WRITE_ONLY)
    private String nombreComuna; //Se unificará su visualización mediante @JsonProperty
    @JsonProperty("local_telefono")
    private String telefono;
    @JsonProperty("local_lat")
    private String latitud;
    @JsonProperty("local_lng")
    private String longitud;

    public String getNombreLocal() {
        return nombreLocal;
    }

    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }

    //Con esto, unifico la dirección y la comuna que estaban por separados, para entregar una dirección completa
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public String getDireccion() {
        return String.format("%s, %s", direccion, nombreComuna);
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreComuna() {
        return nombreComuna;
    }

    public void setNombreComuna(String nombreComuna) {
        this.nombreComuna = nombreComuna;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    @Override
    public String toString() {
        return String.format("MinsalFarmacia[localNombre=%s, localDireccion=%s, localTelefono=%s," +
                " localLat=%s, localLng=%s]", nombreLocal, getDireccion(), telefono, latitud, longitud);
    }
}

