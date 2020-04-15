package cl.atromilen.tests.restapi.mocks;

import cl.atromilen.tests.restapi.model.LocalFarmacia;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alvarotromilen on 4/15/20.
 */
public class ListaFarmaciasMock {

    private static List<LocalFarmacia> getListaFarmacias(){
        List<LocalFarmacia> farmacias = new ArrayList<>();
        LocalFarmacia cruzVerde1 = new LocalFarmacia();
        cruzVerde1.setLocalNombre("CRUZ VERDE");
        cruzVerde1.setLocalDireccion("VICUNA MACKENNA 9797");
        cruzVerde1.setComunaNombre("LA FLORIDA");
        cruzVerde1.setLocalTelefono(222123456);
        cruzVerde1.setLocalLatitud(-1.2345);
        cruzVerde1.setLocalLongitud(-3.4566);

        LocalFarmacia cruzVerde2 = new LocalFarmacia();
        cruzVerde2.setLocalNombre("CRUZ VERDE");
        cruzVerde2.setLocalDireccion("LA FLORIDA 9385");
        cruzVerde2.setComunaNombre("LA FLORIDA");
        cruzVerde2.setLocalTelefono(234123421);
        cruzVerde2.setLocalLatitud(-2.2345);
        cruzVerde2.setLocalLongitud(-6.4566);

        LocalFarmacia ahumada = new LocalFarmacia();
        ahumada.setLocalNombre("FARMACIAS AHUMADA");
        ahumada.setLocalDireccion("LA FLORIDA 9497");
        ahumada.setComunaNombre("LA FLORIDA");
        ahumada.setLocalTelefono(226313292);
        ahumada.setLocalLatitud(-2.2345);
        ahumada.setLocalLongitud(-6.4566);

        farmacias.add(cruzVerde1);
        farmacias.add(cruzVerde2);
        farmacias.add(ahumada);

        return farmacias;
    }

    public static List<LocalFarmacia> getAll(){
        return getListaFarmacias();
    }

    public static List<LocalFarmacia> getCruzVerde1(){
        return getListaFarmacias().subList(0,1);
    }

    public static List<LocalFarmacia> getCruzVerde2(){
        return getListaFarmacias().subList(1,2);
    }

    public static List<LocalFarmacia> getFarmaciasAhumada(){
        return getListaFarmacias().subList(2,3);
    }
}
