package cl.atromilen.tests.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Álvaro Tromilen (alvaro.tromilen@gmail.com)
 *
 * Modelo representativo de un objeto de tipo Farmacia, con los datos a exponer en la respuesta JSON
 * de la API RESTful.
 */
public class LocalFarmacia {
    private String nombreLocal;
    @JsonIgnore
    private String direccion; //Se unificará su visualización mediante @JsonProperty
    @JsonIgnore
    private String nombreComuna; //Se unificará su visualización mediante @JsonProperty
    private Integer telefono;
    private Double latitud;
    private Double longitud;

    public String getNombreLocal() {
        return nombreLocal;
    }

    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }

    //Con esto, unifico la dirección y la comuna que estaban por separados, para entregar una dirección completa
    @JsonProperty(value = "direccion", access = JsonProperty.Access.READ_ONLY)
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

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
}

