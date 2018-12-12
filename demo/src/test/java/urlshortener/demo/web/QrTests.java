package urlshortener.demo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jayway.jsonpath.JsonPath;

import org.hamcrest.core.IsNot;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import urlshortener.demo.controller.impl.QrApiController;
import urlshortener.demo.controller.impl.UriApiController;
import urlshortener.demo.domain.QRItem;
import urlshortener.demo.domain.URICreate;
import urlshortener.demo.domain.URIItem;
import urlshortener.demo.web.MockUtils;
import urlshortener.demo.repository.QRRepository;
import urlshortener.demo.repository.URIRepository;
import urlshortener.demo.controller.advice.BaseControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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
