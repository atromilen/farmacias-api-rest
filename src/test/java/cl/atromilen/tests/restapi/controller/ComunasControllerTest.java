package cl.atromilen.tests.restapi.controller;

import cl.atromilen.tests.restapi.errorhandler.ComunasNotFoundException;
import cl.atromilen.tests.restapi.mocks.ComunasMock;
import cl.atromilen.tests.restapi.service.ComunaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ComunasController.class)
class ComunasControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ComunaService comunaService;

    @Test
    void testDevolverComunasConExito() throws Exception {
        Mockito.when(comunaService.getRMComunasAsCombobox())
                .thenReturn(ComunasMock.getAll());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api-rest/comunas").contentType(MediaType.TEXT_HTML)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testConsultaComunasAlMinsalTrajoUnOjetoNulo() throws Exception {
        Mockito.when(comunaService.getRMComunasAsCombobox())
                .thenThrow(new ComunasNotFoundException());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api-rest/comunas").contentType(MediaType.TEXT_HTML)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isGatewayTimeout());
    }


}