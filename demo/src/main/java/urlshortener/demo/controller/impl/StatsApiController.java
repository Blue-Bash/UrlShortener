package urlshortener.demo.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import urlshortener.demo.controller.StatsApi;
import urlshortener.demo.domain.Stats;
import urlshortener.demo.domain.URIItem;
import urlshortener.demo.domain.URIStats;
import urlshortener.demo.repository.URIRepository;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Collections;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-21T05:15:43.072Z[GMT]")

@RestController
public class StatsApiController implements StatsApi {

    private static final Logger log = LoggerFactory.getLogger(StatsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final MeterRegistry metrics;

    private final MetricsEndpoint metricsEndpoint;

    private final URIRepository repository;

    @org.springframework.beans.factory.annotation.Autowired
    public StatsApiController(ObjectMapper objectMapper, HttpServletRequest request, MeterRegistry metrics, MetricsEndpoint metricsEndpoint, URIRepository repository) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.metrics = metrics;
        this.metricsEndpoint = metricsEndpoint;
        this.repository = repository;
    }

    public ResponseEntity<Stats> getStats() {
        String accept = request.getHeader("Accept");
        Stats stats = new Stats();
        stats.setCpuUsage(BigDecimal.valueOf(getMetric("process.cpu.usage")));
        stats.setMemoryUsage((int) getMetric("jvm.memory.used"));
        stats.setServerUsage(BigDecimal.valueOf(getMetric("system.cpu.usage")));

        stats.setUrisCreated(BigDecimal.valueOf(getCustomMetric("uri.created")));
        stats.setUrisRemoved(BigDecimal.valueOf(getCustomMetric("uri.removed")));
        stats.setUrisNow(BigDecimal.valueOf(getCustomMetric("uri.now")));
        stats.setUrisAccessed(BigDecimal.valueOf(getCustomMetric("uri.accessed")));
        stats.setQrCreated(BigDecimal.valueOf(getCustomMetric("qr.created")));
        stats.setQrRemoved(BigDecimal.valueOf(getCustomMetric("qr.removed")));
        stats.setQrNow(BigDecimal.valueOf(getCustomMetric("qr.now")));
        stats.setQrAccessed(BigDecimal.valueOf(getCustomMetric("qr.accessed")));
        return new ResponseEntity<Stats>(stats, HttpStatus.OK);
    }

    public ResponseEntity<URIStats> getUriStats(String keyID) {
        String accept = request.getHeader("Accept");
        URIItem uri = repository.get(keyID);
        URIStats stats = new URIStats();

        stats.setUriAccesses(BigDecimal.valueOf(getCustomMetric("uri."+keyID+".accessed")));
        return new ResponseEntity<URIStats>(stats, HttpStatus.OK);
    }

    public double getMetric(String s) {
        return metricsEndpoint.metric(s, Collections.emptyList()).getMeasurements().stream().mapToDouble(MetricsEndpoint.Sample::getValue).average().orElse(0.0);
    }

    private double getCustomMetric(String s){
        return metrics.counter(s).count();
    }

}
