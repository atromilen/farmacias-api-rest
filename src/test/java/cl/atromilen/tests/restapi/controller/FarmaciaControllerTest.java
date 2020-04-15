package cl.atromilen.tests.restapi.controller;

import cl.atromilen.tests.restapi.mocks.ListaFarmaciasMock;
import cl.atromilen.tests.restapi.service.FarmaciaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by alvarotromilen on 4/15/20.
 */
@WebMvcTest(FarmaciaController.class)
public class FarmaciaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FarmaciaService farmaciaService;

    @Test
    public void consultaFarmaciasSinEnviarComunaTest() throws Exception {
        //TODO Implementación pendiente
    }

    @Test
    public void testConsultaExitosaSoloPorComuna() throws Exception {
        Mockito.when(farmaciaService.getFarmaciaByComuna(anyString()))
                .thenReturn(ListaFarmaciasMock.getAll());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/farmacias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("comuna", "someMockValue")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    public void testConsultaExitosaPorComunaYNombreDeLocal() throws Exception {
        Mockito.when(farmaciaService.getFarmaciaByComunaAndNombreLocal(anyString(), anyString()))
                .thenReturn(ListaFarmaciasMock.getCruzVerde1());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/farmacias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("comuna", "mockComuna")
                        .param("nombre_local", "mockNombreLocal")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    public void testConsultaSinResultadosDebeRetornarStatus404() throws Exception {
        Mockito.when(farmaciaService.getFarmaciaByComunaAndNombreLocal(anyString(), anyString()))
                .thenReturn(new ArrayList<>());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/farmacias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("comuna", "mockComuna")
                        .param("nombre_local", "mockNombreLocal")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }
}