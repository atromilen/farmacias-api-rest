package cl.atromilen.tests.restapi.mocks;

import cl.atromilen.tests.restapi.model.LocalFarmacia;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alvarotromilen on 4/15/20.
 */
public class ListaFarmaciasMock {

    private static List<LocalFarmacia> getListaFarmacias() {
        List<LocalFarmacia> farmacias = new ArrayList<>();
        LocalFarmacia cruzVerde1 = new LocalFarmacia();
        cruzVerde1.setNombreLocal("CRUZ VERDE");
        cruzVerde1.setDireccion("VICUNA MACKENNA 9797");
        cruzVerde1.setNombreComuna("LA FLORIDA");
        cruzVerde1.setTelefono("222123456");
        cruzVerde1.setLatitud("-1.2345");
        cruzVerde1.setLongitud("-3.4566");

        LocalFarmacia cruzVerde2 = new LocalFarmacia();
        cruzVerde2.setNombreLocal("CRUZ VERDE");
        cruzVerde2.setDireccion("LA FLORIDA 9385");
        cruzVerde2.setNombreComuna("LA FLORIDA");
        cruzVerde2.setTelefono("234123421");
        cruzVerde2.setLatitud("-2.2345");
        cruzVerde2.setLongitud("-6.4566");

        LocalFarmacia ahumada = new LocalFarmacia();
        ahumada.setNombreLocal("FARMACIAS AHUMADA");
        ahumada.setDireccion("LA FLORIDA 9497");
        ahumada.setNombreComuna("LA FLORIDA");
        ahumada.setTelefono("226313292");
        ahumada.setLatitud("-2.2345");
        ahumada.setLongitud("-6.4566");

        farmacias.add(cruzVerde1);
        farmacias.add(cruzVerde2);
        farmacias.add(ahumada);

        return farmacias;
    }

    public static List<LocalFarmacia> getAll() {
        return getListaFarmacias();
    }

    public static List<LocalFarmacia> getCruzVerde1() {
        return getListaFarmacias().subList(0, 1);
    }
}
