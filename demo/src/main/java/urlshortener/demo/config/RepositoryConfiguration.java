package urlshortener.demo.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import urlshortener.demo.domain.URIItem;
import urlshortener.demo.repository.QRRepository;
import urlshortener.demo.repository.URIRepository;
import urlshortener.demo.repository.impl.QRRepositoryImpl;
import urlshortener.demo.repository.impl.StatsRepositoryHook;
import urlshortener.demo.repository.impl.URIRepositoryImpl;

@Configuration
public class RepositoryConfiguration {

    private final MeterRegistry metrics;

    @Autowired
    public RepositoryConfiguration(MeterRegistry metrics) {
        this.metrics = metrics;
    }

    @Bean("uriRepository")
    public URIRepository getURIRepository(){
        StatsRepositoryHook<String, URIItem> statsHook = new StatsRepositoryHook<>(null, metrics, "uri");
        return new URIRepositoryImpl(statsHook);
    }

    @Bean("qrRepository")
    public QRRepository getQRRepository(){
        return new QRRepositoryImpl();
    }

}
