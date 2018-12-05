package urlshortener.demo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import urlshortener.demo.controller.advice.BaseServiceAdvice;
import urlshortener.demo.controller.impl.UriApiController;
import urlshortener.demo.domain.URICreate;
import urlshortener.demo.domain.URIItem;
import urlshortener.demo.domain.URIUpdate;
import urlshortener.demo.repository.URIRepository;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static urlshortener.demo.web.fixture.UriItemFixture.someURI;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UriTests {
    private MockMvc mockMvc;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private HttpServletRequest request;

    @Mock
    private URIRepository service;

    @InjectMocks
    private UriApiController uriApiController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(uriApiController)
                .setControllerAdvice(new BaseServiceAdvice()).build();
    }

    @Test
    public void getUriWorks() throws Exception {
        URIItem item = someURI();
        when(service.get("abc")).thenReturn(someURI());

        mockMvc.perform(get("/uri/{id}", item.getId())).andDo(print())
                .andExpect(status().isTemporaryRedirect())
                .andExpect(redirectedUrl(item.getRedirection()));
    }

    @Test
    public void getUriError() throws Exception {
        when(service.get("1")).thenReturn(null);
        mockMvc.perform(get("/uri/{id}", "1")).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void createUriWorks() throws Exception {
        URICreate uri = new URICreate();
        uri.setUri("https://google.es");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(uri);

        mockMvc.perform(put("/uri").contentType(MediaType.APPLICATION_JSON_UTF8).content(json)).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is("1234")))
                .andExpect(jsonPath("$.redirection", is("https://google.es")))
                .andExpect(jsonPath("$.hashpass", is("1234")));
    }

    @Test
    public void changeUriWorks() throws Exception {
        URIUpdate uri = new URIUpdate();
        uri.setNewName("name_example");
        uri.setHashpass("1234");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(uri);

        mockMvc.perform(put("/uri/{name}", "patata").contentType(MediaType.APPLICATION_JSON_UTF8).content(json)).andDo(print())
                .andExpect(status().isBadRequest());
    }
}
