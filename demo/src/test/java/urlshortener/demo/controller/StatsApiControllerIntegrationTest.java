package urlshortener.demo.controller;

import io.micrometer.core.instrument.MeterRegistry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import urlshortener.demo.domain.Stats;
import urlshortener.demo.domain.URIStats;
import urlshortener.demo.repository.URIRepository;
import urlshortener.demo.web.fixture.UriItemFixture;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatsApiControllerIntegrationTest {

    @Autowired
    private StatsApi api;

    @Autowired
    private URIRepository repository;

    @Autowired
    private MeterRegistry metrics;

    @Autowired
    private MetricsEndpoint metricsEndpoint;

    @Test
    public void getStatsTest() throws Exception {
        ResponseEntity<Stats> responseEntity = api.getStats();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Stats stats = responseEntity.getBody();
        assertNotNull(stats);
        assertEquals(BigDecimal.valueOf(getCustomMetric("qr.accessed")), stats.getQrAccessed());
        assertEquals(BigDecimal.valueOf(getCustomMetric("qr.created")), stats.getQrAccessed());
        assertEquals(BigDecimal.valueOf(getCustomMetric("qr.now")), stats.getQrAccessed());
        assertEquals(BigDecimal.valueOf(getCustomMetric("qr.removed")), stats.getQrAccessed());
        assertEquals(BigDecimal.valueOf(getCustomMetric("uri.accessed")), stats.getQrAccessed());
        assertEquals(BigDecimal.valueOf(getCustomMetric("uri.created")), stats.getQrAccessed());
        assertEquals(BigDecimal.valueOf(getCustomMetric("uri.now")), stats.getQrAccessed());
        assertEquals(BigDecimal.valueOf(getCustomMetric("uri.removed")), stats.getQrAccessed());
    }

    @Test
    public void getURIStatsTest() throws Exception {
        repository.add(UriItemFixture.someURI());
        ResponseEntity<URIStats> responseEntity = api.getUriStats("abc");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        URIStats stats = responseEntity.getBody();
        assertNotNull(stats);
        assertEquals(BigDecimal.valueOf(getCustomMetric("uri.abc.accessed")), stats.getUriAccesses());

    }

    private double getCustomMetric(String s){
        return metrics.counter(s).count();
    }

}
