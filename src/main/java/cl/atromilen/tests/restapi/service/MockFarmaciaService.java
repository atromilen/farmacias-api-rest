package cl.atromilen.tests.restapi.service;

import cl.atromilen.tests.restapi.model.LocalFarmacia;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alvarotromilen on 4/15/20.
 */
public class MockFarmaciaService implements FarmaciaService {

    @Override
    public List<LocalFarmacia> getFarmaciaByComuna(String comuna) {
        List<LocalFarmacia> localFarmacias = new ArrayList<>();
        LocalFarmacia localFarmacia = new LocalFarmacia();
        localFarmacia.setLocalNombre("CRUZ VERDE");
        localFarmacia.setLocalDireccion("AV SAN JOSE DE LA ESTRELLA 20");
        localFarmacia.setComunaNombre("LA FLORIDA");
        localFarmacia.setLocalTelefono(222123456);
        localFarmacia.setLocalLatitud(-1.2345);
        localFarmacia.setLocalLongitud(-3.4566);

        LocalFarmacia localFarmacia2 = new LocalFarmacia();
        localFarmacia2.setLocalNombre("AHUMADA");
        localFarmacia2.setLocalDireccion("LA CONCEPCION 220");
        localFarmacia2.setComunaNombre("PROVIDENCIA");
        localFarmacia2.setLocalTelefono(234123421);
        localFarmacia2.setLocalLatitud(-2.2345);
        localFarmacia2.setLocalLongitud(-6.4566);

        localFarmacias.add(localFarmacia);
        localFarmacias.add(localFarmacia2);

        return localFarmacias;
    }

    @Override
    public List<LocalFarmacia> getFarmaciaByComunaAndNombreLocal(String comuna, String nombbreLocal) {
        List<LocalFarmacia> localFarmacias = new ArrayList<>();
        LocalFarmacia localFarmacia = new LocalFarmacia();
        localFarmacia.setLocalNombre("CRUZ VERDE");
        localFarmacia.setLocalDireccion("AV SAN JOSE DE LA ESTRELLA 20");
        localFarmacia.setComunaNombre("LA FLORIDA");
        localFarmacia.setLocalTelefono(222123456);
        localFarmacia.setLocalLatitud(-1.2345);
        localFarmacia.setLocalLongitud(-3.4566);

        localFarmacias.add(localFarmacia);

        return localFarmacias;
    }
}
