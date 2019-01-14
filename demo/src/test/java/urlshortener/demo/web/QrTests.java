package urlshortener.demo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import urlshortener.demo.controller.advice.BaseControllerAdvice;
import urlshortener.demo.controller.impl.QrApiController;
import urlshortener.demo.domain.QRItem;
import urlshortener.demo.domain.URIItem;
import urlshortener.demo.repository.QRRepository;
import urlshortener.demo.repository.URIRepository;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class QrTests {

    private MockMvc mockMvc;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private HttpServletRequest request;

    @Mock
    private URIRepository uriRepository;

    @Mock
    private QRRepository qrRepository;

    @InjectMocks
    private QrApiController qrApiController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(qrApiController)
                .setControllerAdvice(new BaseControllerAdvice()).build();
    }

    @Test
    public void uriGetWorks() throws Exception {
        URIItem uriItem = new URIItem().id("googleHash").redirection("www.google.es");
        QRItem qrItem = new QRItem().uri(uriItem.getRedirection());
        qrItem.convertBase64(500, 500);

        when(uriRepository.get("googleHash")).thenReturn(uriItem);
        when(uriRepository.contains("googleHash")).thenReturn(true);
        when(qrRepository.get(uriItem.getRedirection())).thenReturn(qrItem);

         mockMvc.perform(get("/qr/{id}/", "googleHash"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.qr").value(qrItem.getQr()))
                .andExpect(jsonPath("$.uri").value(qrItem.getUri()));
    }
    
    @Test
    public void uriGetError() throws Exception {
         mockMvc.perform(get("/qr/{id}/", "googleHash"))
                .andExpect(status().isNotFound());
    }

}
