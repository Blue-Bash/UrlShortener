package urlshortener.demo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.MeterRegistry;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import urlshortener.demo.controller.advice.BaseControllerAdvice;
import urlshortener.demo.controller.impl.StatsApiController;
import urlshortener.demo.repository.URIRepository;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class StatsTest {

    private MockMvc mockMvc;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private HttpServletRequest request;

    @Mock
    private MeterRegistry metrics;

    @Mock
    private MetricsEndpoint metricsEndpoint;

    @Mock
    private URIRepository repository;

    @InjectMocks
    private StatsApiController statsApiController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(statsApiController).setControllerAdvice(new BaseControllerAdvice()).build();
    }

    @Test
    public void statGetWorks() throws Exception {

        //when(metricsEndpoint.metric(anyString(), anyList())).thenReturn(new MetricsEndpoint.MetricResponse());
        when(metrics.counter(anyString()).count()).thenReturn(0.0);

        mockMvc.perform(get("/stats")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.\"uri.accesses\"").value(0.0))
                .andExpect(jsonPath("$.\"uri.now\"").value(0.0))
        ;
    }

    @Test
    public void uriStatGetWorks() throws Exception {
        mockMvc.perform(get("/stats")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }
}
