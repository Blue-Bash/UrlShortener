package urlshortener.demo.repository.impl;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.stereotype.Repository;
import urlshortener.demo.domain.Stats;
import urlshortener.demo.domain.URIItem;
import urlshortener.demo.domain.URIStats;
import urlshortener.demo.repository.StatsRepository;
import urlshortener.demo.repository.URIRepository;

import java.math.BigDecimal;
import java.util.Collections;

@Repository
public class StatsRepositoryImpl implements StatsRepository {

    private final MeterRegistry metrics;

    private final MetricsEndpoint metricsEndpoint;

    private final URIRepository repository;

    @Autowired
    public StatsRepositoryImpl(MeterRegistry metrics, MetricsEndpoint metricsEndpoint, URIRepository repository) {
        this.metrics = metrics;
        this.metricsEndpoint = metricsEndpoint;
        this.repository = repository;
    }

    public Stats getStats() {
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
        return stats;
    }

    public URIStats getUriStats(String keyID) {
        URIItem uri = repository.get(keyID);
        URIStats stats = new URIStats();

        stats.setUriAccesses(BigDecimal.valueOf(getCustomMetric("uri."+keyID+".accessed")));
        return stats;
    }

    private double getMetric(String s) {
        return metricsEndpoint.metric(s, Collections.emptyList()).getMeasurements().stream().mapToDouble(MetricsEndpoint.Sample::getValue).average().orElse(0.0);
    }

    private double getCustomMetric(String s){
        return metrics.counter(s).count();
    }

}
