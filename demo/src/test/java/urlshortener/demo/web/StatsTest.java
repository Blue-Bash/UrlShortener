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
import urlshortener.demo.controller.advice.BaseControllerAdvice;
import urlshortener.demo.controller.impl.StatsApiController;
import urlshortener.demo.domain.Stats;
import urlshortener.demo.domain.URIStats;
import urlshortener.demo.repository.StatsRepository;

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
    private StatsRepository statsRepository;

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
        when(statsRepository.getStats()).thenReturn(new Stats());

        mockMvc.perform(get("/stats")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.['uris.accessed']").value(0.0))
                .andExpect(jsonPath("$.['uris.now']").value(0.0))
        ;
    }

    @Test
    public void uriStatGetWorks() throws Exception {
        when(statsRepository.getUriStats(anyString())).thenReturn(new URIStats());

        mockMvc.perform(get("/stats/abc")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }
}
