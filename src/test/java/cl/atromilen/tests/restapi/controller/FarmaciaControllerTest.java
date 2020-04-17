package cl.atromilen.tests.restapi.controller;

import cl.atromilen.tests.restapi.exception.FarmaciaNotFoundException;
import cl.atromilen.tests.restapi.exception.FarmaciaServiceException;
import cl.atromilen.tests.restapi.exception.InternalAPIException;
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
    public void testConsultaFallidaPorNoRecibirComuna() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api-rest/farmacias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("nombre_local", "anyString")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testConsultaExitosaSoloPorComuna() throws Exception {
        Mockito.when(farmaciaService.getFarmaciaByComuna(anyString()))
                .thenReturn(ListaFarmaciasMock.getAll());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api-rest/farmacias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("comuna", "anyString")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    public void testConsultaExitosaPorComunaYNombreDeLocal() throws Exception {
        Mockito.when(farmaciaService.getFarmaciaByComunaAndNombreLocal(anyString(), anyString()))
                .thenReturn(ListaFarmaciasMock.getCruzVerde1());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api-rest/farmacias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("comuna", "anyString")
                        .param("nombre_local", "anyString")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    public void testConsultaPorComunaSinResultadoDebeRetornarStatus404() throws Exception {
        Mockito.when(farmaciaService.getFarmaciaByComuna(anyString()))
                .thenThrow(new FarmaciaNotFoundException("COMUNATEST"));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api-rest/farmacias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("comuna", "COMUNATEST")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testConsultaPorComunaYLocalSinResultadoDebeRetornarStatus404() throws Exception {
        Mockito.when(farmaciaService.getFarmaciaByComunaAndNombreLocal(anyString(), anyString()))
                .thenThrow(new FarmaciaNotFoundException("COMUNATEST", "LOCALTEST"));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api-rest/farmacias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("comuna", "COMUNATEST")
                        .param("nombre_local", "LOCALTEST")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }

    @Test
    void testServiceUnavailablePorErrorEnRespuestaDeServicioDeBusquedaDeFarmacias() throws Exception {
        Mockito.when(farmaciaService.getFarmaciaByComuna(anyString()))
                .thenThrow(new FarmaciaServiceException());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api-rest/farmacias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("comuna", "COMUNATEST")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isServiceUnavailable());
    }

    @Test
    void testInternalAPIExceptionProvocaInternalServerError() throws Exception {
        Mockito.when(farmaciaService.getFarmaciaByComuna(anyString()))
                .thenThrow(new InternalAPIException());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api-rest/farmacias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("comuna", "COMUNATEST")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isInternalServerError());
    }
}